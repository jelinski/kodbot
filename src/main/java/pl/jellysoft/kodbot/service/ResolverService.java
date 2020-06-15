package pl.jellysoft.kodbot.service;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jellysoft.kodbot.controller.bean.MapBean;
import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.resolver.ResolverErrorResponse;
import pl.jellysoft.kodbot.resolver.ResolverOkResponse;
import pl.jellysoft.kodbot.resolver.ResolverResponse;
import pl.jellysoft.kodbot.resolver.ResolverWinResponse;
import pl.jellysoft.kodbot.resolver.evaluator.ActionType;
import pl.jellysoft.kodbot.resolver.evaluator.Evaluator;
import pl.jellysoft.kodbot.resolver.evaluator.EvaluatorException;
import pl.jellysoft.kodbot.resolver.evaluator.EvaluatorResult;
import pl.jellysoft.kodbot.resolver.evaluator.command.Command;
import pl.jellysoft.kodbot.resolver.parser.CodeParser;
import pl.jellysoft.kodbot.resolver.simulator.Simulator;
import pl.jellysoft.kodbot.resolver.simulator.SimulatorResult;
import pl.jellysoft.kodbot.resolver.statistic.MapUserScoreDTO;
import pl.jellysoft.kodbot.resolver.statistic.StatisticCounter;
import pl.jellysoft.kodbot.resolver.statistic.StatisticDTO;

import java.util.List;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;
import static pl.jellysoft.kodbot.resolver.simulator.SimulationContext.setupSimulationContext;

@Service
@RequiredArgsConstructor
public class ResolverService {

    private final MapService mapService;

    public ResolverResponse resolve(String input, GameMap gameMap) {
        return CodeParser.parse(input)
                .flatMap(parserResult -> {
                    List<Command> commands = parserResult.getCommands();
                    int commandCounter = parserResult.getCommandCounter();
                    return evaluate(commands).flatMap(evaluatorResult -> {
                        List<ActionType> actions = evaluatorResult.getActions();
                        return simulate(actions, mapService.createMapBeanFromMap(gameMap)).<ResolverResponse>map(simulatorResult -> {
                            if (simulatorResult.isUserWon()) {
                                StatisticDTO newStatistics = calculateStatistics(commands);
                                String nextMapKey = mapService.getNextGameMapKey(gameMap);
                                MapUserScoreDTO userScore = new MapUserScoreDTO(simulatorResult.getBatteryLevel(), commandCounter);
                                return new ResolverWinResponse(actions, newStatistics, userScore, nextMapKey);
                            } else {
                                return new ResolverOkResponse(actions);
                            }
                        });
                    });
                })
                .getOrElseGet(ResolverErrorResponse::new);
    }

    private Either<String, EvaluatorResult> evaluate(List<Command> commands) {
        try {
            // TODO pass commands to constructor or static method
            return right(new Evaluator().evaluate(commands));
        } catch (EvaluatorException ee) {
            return left(ee.getMessage());
        }
    }

    private Either<String, SimulatorResult> simulate(List<ActionType> actions, MapBean mapBean) {
        return setupSimulationContext(mapBean).map(simulationContext -> Simulator.simulate(actions, simulationContext));
    }

    private StatisticDTO calculateStatistics(List<Command> commands) {
        return new StatisticCounter().countStatistics(commands);
    }

}
