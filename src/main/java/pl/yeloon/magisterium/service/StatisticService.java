package pl.yeloon.magisterium.service;

import pl.yeloon.magisterium.model.UserStatistic;
import pl.yeloon.magisterium.resolver.statistic.StatisticDTO;

public interface StatisticService {

	StatisticDTO updateUserStatistic(Integer userId, StatisticDTO statistic, boolean userAlreadyFinishedMap);

	UserStatistic getUserStatistic(Integer userId);

	StatisticDTO addStatistics(StatisticDTO s1, StatisticDTO s2);

}
