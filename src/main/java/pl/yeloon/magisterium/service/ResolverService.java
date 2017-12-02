package pl.yeloon.magisterium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.yeloon.magisterium.controller.bean.MapBean;
import pl.yeloon.magisterium.model.GameMap;
import pl.yeloon.magisterium.resolver.*;
import pl.yeloon.magisterium.resolver.evaluator.ActionType;
import pl.yeloon.magisterium.resolver.evaluator.Evaluator;
import pl.yeloon.magisterium.resolver.evaluator.Evaluator.EvaluatorResult;
import pl.yeloon.magisterium.resolver.evaluator.EvaluatorException;
import pl.yeloon.magisterium.resolver.evaluator.command.Command;
import pl.yeloon.magisterium.resolver.parser.CodeParser;
import pl.yeloon.magisterium.resolver.parser.Parser.ParserResult;
import pl.yeloon.magisterium.resolver.parser.ParserException;
import pl.yeloon.magisterium.resolver.simulator.Simulator;
import pl.yeloon.magisterium.resolver.simulator.Simulator.SimulatorResult;
import pl.yeloon.magisterium.resolver.simulator.SimulatorException;
import pl.yeloon.magisterium.resolver.statistic.MapUserScoreDTO;
import pl.yeloon.magisterium.resolver.statistic.StatisticCounter;
import pl.yeloon.magisterium.resolver.statistic.StatisticDTO;

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

	private SimulatorResult simulate(List<ActionType> actions, MapBean mapBean) throws SimulatorException {
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
			SimulatorResult simulatorResult = simulate(actions, mapService.createMapBeanFromMap(gameMap));

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
