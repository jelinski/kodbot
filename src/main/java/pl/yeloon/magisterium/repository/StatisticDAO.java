package pl.yeloon.magisterium.repository;

import pl.yeloon.magisterium.model.UserStatistic;

public interface StatisticDAO {

	UserStatistic getUserStatistic(int userId);

	void saveUserStatistic(UserStatistic userStatistic);

}
