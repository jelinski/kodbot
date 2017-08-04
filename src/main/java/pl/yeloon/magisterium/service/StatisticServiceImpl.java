package pl.yeloon.magisterium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.yeloon.magisterium.model.UserStatistic;
import pl.yeloon.magisterium.repository.StatisticDAO;
import pl.yeloon.magisterium.resolver.statistic.StatisticDTO;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
    private StatisticDAO statisticDAO;

	@Transactional
	@Override
	public StatisticDTO updateUserStatistic(Integer userId, StatisticDTO statistic, boolean userAlreadyFinishedMap) {
		UserStatistic userStatistic = statisticDAO.getUserStatistic(userId);
		if (userStatistic == null) {
			userStatistic = new UserStatistic();
			zeroCurrentStats(userStatistic);
			userStatistic.setUserId(userId);
		}
		StatisticDTO oldStatistic = createStatisticDTO(userStatistic);
		addStatisticsToCurrentStats(userStatistic, statistic);
		int overall = userStatistic.getOverallScore();
		if (!userAlreadyFinishedMap)
			overall++;
		userStatistic.setOverallScore(overall);
		statistic.setOverall(overall);
		statisticDAO.saveUserStatistic(userStatistic);
		return oldStatistic;
	}

	@Override
	public UserStatistic getUserStatistic(Integer userId) {
		return statisticDAO.getUserStatistic(userId);
	}

	private void zeroCurrentStats(UserStatistic currentStats) {
		currentStats.setAssignCounter(0);
		currentStats.setAssignWithAdditionCounter(0);
		currentStats.setAssignWithSubtractionCounter(0);
		currentStats.setCalledFunctionCounter(0);
		currentStats.setDecrementCounter(0);
		currentStats.setDefinedFunctionCounter(0);
		currentStats.setIncrementCounter(0);
		currentStats.setJumpCounter(0);
		currentStats.setMoveCounter(0);
		currentStats.setOverallScore(0);
		currentStats.setRepeatCounter(0);
		currentStats.setTurnLeftCounter(0);
		currentStats.setTurnRightCounter(0);
	}

	private void addStatisticsToCurrentStats(UserStatistic currentStats, StatisticDTO statistic) {
		currentStats.setAssignCounter(currentStats.getAssignCounter() + statistic.getAssignCount());
		currentStats.setAssignWithAdditionCounter(currentStats.getAssignWithAdditionCounter() + statistic.getAssignWithAdditionCount());
		currentStats.setAssignWithSubtractionCounter(currentStats.getAssignWithSubtractionCounter() + statistic.getAssignWithSubtractionCount());
		currentStats.setCalledFunctionCounter(currentStats.getCalledFunctionCounter() + statistic.getCalledFunctionCount());
		currentStats.setDecrementCounter(currentStats.getDecrementCounter() + statistic.getDecrementCount());
		currentStats.setDefinedFunctionCounter(currentStats.getDefinedFunctionCounter() + statistic.getDefinedFunctionCount());
		currentStats.setIncrementCounter(currentStats.getIncrementCounter() + statistic.getIncrementCount());
		currentStats.setJumpCounter(currentStats.getJumpCounter() + statistic.getJumpCount());
		currentStats.setMoveCounter(currentStats.getMoveCounter() + statistic.getMoveCount());
		currentStats.setRepeatCounter(currentStats.getRepeatCounter() + statistic.getRepeatCount());
		currentStats.setTurnLeftCounter(currentStats.getTurnLeftCounter() + statistic.getTurnLeftCount());
		currentStats.setTurnRightCounter(currentStats.getTurnRightCounter() + statistic.getTurnRightCount());
	}

	@Override
	public StatisticDTO addStatistics(StatisticDTO s1, StatisticDTO s2) {
		StatisticDTO r = new StatisticDTO();
		r.setAssignCount(s1.getAssignCount() + s2.getAssignCount());
		r.setAssignWithAdditionCount(s1.getAssignWithAdditionCount() + s2.getAssignWithAdditionCount());
		r.setAssignWithSubtractionCount(s1.getAssignWithSubtractionCount() + s2.getAssignWithSubtractionCount());
		r.setCalledFunctionCount(s1.getCalledFunctionCount() + s2.getCalledFunctionCount());
		r.setDecrementCount(s1.getDecrementCount() + s2.getDecrementCount());
		r.setDefinedFunctionCount(s1.getDefinedFunctionCount() + s2.getDefinedFunctionCount());
		r.setIncrementCount(s1.getIncrementCount() + s2.getIncrementCount());
		r.setJumpCount(s1.getJumpCount() + s2.getJumpCount());
		r.setMoveCount(s1.getMoveCount() + s2.getMoveCount());
		r.setOverall(Math.max(s1.getOverall(), s2.getOverall()));
		r.setRepeatCount(s1.getRepeatCount() + s2.getRepeatCount());
		r.setTurnLeftCount(s1.getTurnLeftCount()+s2.getTurnLeftCount());
		r.setTurnRightCount(s1.getTurnRightCount()+s2.getTurnRightCount());
		return r;
	}

	private StatisticDTO createStatisticDTO(UserStatistic us) {
		StatisticDTO s = new StatisticDTO();
		s.setAssignCount(us.getAssignCounter());
		s.setAssignWithAdditionCount(us.getAssignWithAdditionCounter());
		s.setAssignWithSubtractionCount(us.getAssignWithSubtractionCounter());
		s.setCalledFunctionCount(us.getCalledFunctionCounter());
		s.setDecrementCount(us.getDecrementCounter());
		s.setDefinedFunctionCount(us.getDefinedFunctionCounter());
		s.setIncrementCount(us.getIncrementCounter());
		s.setJumpCount(us.getJumpCounter());
		s.setMoveCount(us.getMoveCounter());
		s.setOverall(us.getOverallScore());
		s.setRepeatCount(us.getRepeatCounter());
		s.setTurnLeftCount(us.getTurnLeftCounter());
		s.setTurnRightCount(us.getTurnRightCounter());
		return s;
	}

}
