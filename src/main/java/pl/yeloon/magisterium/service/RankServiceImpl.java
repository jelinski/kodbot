package pl.yeloon.magisterium.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.yeloon.magisterium.controller.bean.MapRankDTO;
import pl.yeloon.magisterium.controller.bean.OverallRankDTO;
import pl.yeloon.magisterium.model.MapUserScore;
import pl.yeloon.magisterium.model.User;
import pl.yeloon.magisterium.model.UserStatistic;
import pl.yeloon.magisterium.repository.MapUserScoreDAO;
import pl.yeloon.magisterium.resolver.statistic.MapUserScoreDTO;

@Service
public class RankServiceImpl implements RankService {

	@Autowired
	MapUserScoreDAO mapUserScoreDAO;

	@Autowired
	UserService userService;

	@Override
	public List<MapUserScore> getUserMapUserScore(int userId) {
		return mapUserScoreDAO.getUserMapUserScore(userId);
	}

	@Override
	public List<MapUserScore> getMapScores(int mapId) {
		return mapUserScoreDAO.getMapScores(mapId);
	}

	@Override
	public MapUserScore getMapScores(int mapId, int userId) {
		return mapUserScoreDAO.getMapScores(mapId, userId);
	}

	@Override
	public boolean isBetter(MapUserScore score1, MapUserScore score2) {
		return isBetter(new MapUserScoreDTO(score1), new MapUserScoreDTO(score2));
	}

	@Override
	public boolean isBetter(MapUserScore score1, MapUserScoreDTO score2) {
		return isBetter(new MapUserScoreDTO(score1), score2);
	}

	@Override
	public boolean isBetter(MapUserScoreDTO score1, MapUserScoreDTO score2) {
		Objects.requireNonNull(score1);
		Objects.requireNonNull(score1.getBatteryLevel());
		Objects.requireNonNull(score1.getCommandCounter());
		if (score2 == null || score2.getBatteryLevel() == null || score2.getCommandCounter() == null)
			return true;
		return score1.compareTo(score2) == 1;
	}

	@Transactional
	@Override
	public void saveMapUserScore(MapUserScore mapUserScore) {
		mapUserScoreDAO.saveMapUserScore(mapUserScore);
	}

	@Transactional
	@Override
	public List<OverallRankDTO> getOverallRank() {
		List<OverallRankDTO> result = new ArrayList<OverallRankDTO>();
		List<User> users = userService.getUsers();
		for (User user : users) {
			UserStatistic userStatistic = user.getStatistic();
			if (userStatistic == null) {
				continue;
			}
			List<MapUserScore> userScores = getUserMapUserScore(user.getId());
			int commandSum = 0;
			int batterySum = 0;
			for (MapUserScore userScore : userScores) {
				commandSum += userScore.getCommandCounter();
				batterySum += userScore.getBatteryLevel();
			}
			int userOverall = userStatistic.getOverallScore();
			int overallScore = (int) ((100 / Math.pow(commandSum, 2)) * Math.pow(userOverall, 4) + batterySum);
			String username = user.getNickname();
			String imageUrl;
			try {
				imageUrl = user.getUserConnection().getImageUrl();
			} catch (EntityNotFoundException enfe) {
				imageUrl = null;
			}
			result.add(new OverallRankDTO(username, imageUrl, userOverall, overallScore));
		}

		Collections.sort(result, new Comparator<OverallRankDTO>() {
			@Override
			public int compare(OverallRankDTO o1, OverallRankDTO o2) {
				// Zamieniona kolejnosc poniewaz, wyniki maja byc sortowane od najwiekszego do najmniejszego
				return Integer.compare(o2.getOverallScore(), o1.getOverallScore());
			}
		});

		return result;
	}

	@Transactional
	@Override
	public List<MapRankDTO> getMapRank(int mapId) {
		List<MapUserScore> scores = mapUserScoreDAO.getMapRank(mapId);
		List<MapRankDTO> result = new ArrayList<MapRankDTO>(scores.size());
		for (MapUserScore score : scores) {
			User user = score.getUser();
			String imageUrl;
			try {
				imageUrl = user.getUserConnection().getImageUrl();
			} catch (Exception e) {
				imageUrl = null;
			} // jak nie ma to trudno.. nie bede plakac

			UserStatistic us = user.getStatistic();
			result.add(new MapRankDTO(user.getNickname(), imageUrl, us.getOverallScore(), score.getBatteryLevel(), score.getCommandCounter()));
		}
		return result;
	}

}
