package pl.jellysoft.kodbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jellysoft.kodbot.controller.bean.MapBean;
import pl.jellysoft.kodbot.model.GameMap;
import pl.jellysoft.kodbot.resolver.*;
import pl.jellysoft.kodbot.resolver.evaluator.ActionType;
import pl.jellysoft.kodbot.resolver.evaluator.Evaluator;
import pl.jellysoft.kodbot.resolver.evaluator.EvaluatorException;
import pl.jellysoft.kodbot.resolver.evaluator.EvaluatorResult;
import pl.jellysoft.kodbot.resolver.evaluator.command.Command;
import pl.jellysoft.kodbot.resolver.parser.CodeParser;
import pl.jellysoft.kodbot.resolver.parser.ParserException;
import pl.jellysoft.kodbot.resolver.parser.ParserResult;
import pl.jellysoft.kodbot.resolver.simulator.Simulator;
import pl.jellysoft.kodbot.resolver.simulator.SimulatorException;
import pl.jellysoft.kodbot.resolver.statistic.MapUserScoreDTO;
import pl.jellysoft.kodbot.resolver.statistic.StatisticCounter;
import pl.jellysoft.kodbot.resolver.statistic.StatisticDTO;

import java.util.List;

@Service
public class ResolverService {

    @Autowired
    private MapService mapService;

    private ParserResult parse(String input) throws ParserException {
        return new CodeParser().parse(input);
    }

    private EvaluatorResult evaluate(List<Command> commands) throws EvaluatorException {
        return new Evaluator().evaluate(commands);
    }

    private Simulator.SimulatorResult simulate(List<ActionType> actions, MapBean mapBean) throws SimulatorException {
        return new Simulator().simulate(actions, mapBean);
    }

    private StatisticDTO calculateStatistics(List<Command> commands) {
        return new StatisticCounter().countStatistics(commands);
    }

    public ResolverResponse resolve(String input, GameMap gameMap) {
        try {

            ParserResult parserResult = parse(input);
            List<Command> commands = parserResult.getCommands();
            if (commands == null || commands.isEmpty())
                throw new ResolverException("No commands generated");
            EvaluatorResult evaluatorResult = evaluate(commands);
            List<ActionType> actions = evaluatorResult.getActions();
            Simulator.SimulatorResult simulatorResult = simulate(actions, mapService.createMapBeanFromMap(gameMap));

            if (simulatorResult.isUserWon()) {
                StatisticDTO newStatistics = calculateStatistics(commands);
                String nextMapKey = mapService.getNextGameMapKey(gameMap);
                MapUserScoreDTO userScore = new MapUserScoreDTO(simulatorResult.getBatteryLevel(), parserResult.getCommandCounter());
                return new ResolverWinResponse(actions, newStatistics, userScore, nextMapKey);
            } else {
                return new ResolverOkResponse(actions);
            }
        } catch (Exception e) {
            String message;
            if (e instanceof ResolverException)
                message = e.getMessage();
            else
                message = "Nastapil nieznany blad";

            return new ResolverErrorResponse(message);
        }
    }

}
