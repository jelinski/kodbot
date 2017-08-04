package pl.yeloon.magisterium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.yeloon.magisterium.controller.bean.MapBean;
import pl.yeloon.magisterium.model.Map;
import pl.yeloon.magisterium.model.MapUserScore;
import pl.yeloon.magisterium.resolver.ResolverErrorResponse;
import pl.yeloon.magisterium.resolver.ResolverException;
import pl.yeloon.magisterium.resolver.ResolverOkResponse;
import pl.yeloon.magisterium.resolver.ResolverResponse;
import pl.yeloon.magisterium.resolver.ResolverWinResponse;
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
import pl.yeloon.magisterium.resolver.statistic.MapRankingInfoDTO;
import pl.yeloon.magisterium.resolver.statistic.MapUserScoreDTO;
import pl.yeloon.magisterium.resolver.statistic.StatisticCounter;
import pl.yeloon.magisterium.resolver.statistic.StatisticDTO;

@Service
public class ResolverService {

	@Autowired
	StatisticService statisticService;

	@Autowired
	BadgeService badgeService;

	@Autowired
	RankService rankService;

	@Autowired
	MapService mapService;

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

	public ResolverResponse resolve(String input, Integer userId, Map map) {
		try {

			ParserResult parserResult = parse(input);
			List<Command> commands = parserResult.getCommands();
			if (commands == null || commands.isEmpty())
				throw new ResolverException("No commands generated");
			EvaluatorResult evaluatorResult = evaluate(commands);
			List<ActionType> actions = evaluatorResult.getActions();
			SimulatorResult simulatorResult = simulate(actions, mapService.createMapBeanFromMap(map));

			if (simulatorResult.isUserWon()) {
				StatisticDTO newStatistics = calculateStatistics(commands);
				StatisticDTO oldStatistics = new StatisticDTO();
				MapUserScore newMapUserScore = new MapUserScore();
				newMapUserScore.setCommandCounter(parserResult.getCommandCounter());
				newMapUserScore.setBatteryLevel(simulatorResult.getBatteryLevel());
				MapUserScore oldMapUserScore = null;
				MapRankingInfoDTO mapRankingInfo = null;

				if (userId != null) {
					newMapUserScore.setMapId(map.getId());
					newMapUserScore.setUserId(userId);

					oldMapUserScore = rankService.getMapScores(map.getId(), userId);
					boolean userAlreadyFinishedMap = false;
					if (oldMapUserScore != null) { // Gracz gral juz w plansze
						userAlreadyFinishedMap = true;
						if (rankService.isBetter(newMapUserScore, oldMapUserScore)) { // poprawiono wynik - zaktualizuj najlepszy
							newMapUserScore.setId(oldMapUserScore.getId());
							rankService.saveMapUserScore(newMapUserScore);
						}
					} else { // Pierwszy wynik dla tej planszy - po prostu zapisz wynik
						rankService.saveMapUserScore(newMapUserScore);
					}

					if (rankService.isBetter(newMapUserScore, new MapUserScoreDTO(map.getBestBatteryLevel(), map.getBestCommandCounter()))) {
						map.setBestBatteryLevel(newMapUserScore.getBatteryLevel());
						map.setBestCommandCounter(newMapUserScore.getCommandCounter());
						mapService.saveMap(map); // aktualizuj najlepszy wynik dla planszy
					}

					mapRankingInfo = new MapRankingInfoDTO();
					List<MapUserScore> mapScores = rankService.getMapScores(map.getId());
					mapRankingInfo.setTotal(mapScores.size());
					for (int l = 0; l < mapScores.size(); l++) {
						if (mapScores.get(l).getUserId() == userId) {
							mapRankingInfo.setUserPosition(l + 1);
							break;
						}
					}

					// newStatistics - inkrementowanie overall wewnatrz wywolania
					oldStatistics = statisticService.updateUserStatistic(userId, newStatistics, userAlreadyFinishedMap);
					badgeService.assignBadgesForStatistics(userId, oldStatistics, newStatistics);
				}

				// Pobranie mapkeya dla nastepnej planszy
				String nextMapKey = null;
				if (userId != null || map.getId() < MapService.FORWARD_MAP_PEEK) {
					Map nextMap = mapService.getMap(map.getId() + 1);
					if (nextMap != null) {
						nextMapKey = nextMap.getKey();
					}
				}

				// Dodanie najlepszego wyniku do responsa
				MapUserScoreDTO mapBestScore = new MapUserScoreDTO(map.getBestBatteryLevel(), map.getBestCommandCounter());
				return new ResolverWinResponse(actions, oldStatistics, newStatistics, new MapUserScoreDTO(oldMapUserScore), new MapUserScoreDTO(newMapUserScore), mapBestScore, mapRankingInfo, nextMapKey);
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
