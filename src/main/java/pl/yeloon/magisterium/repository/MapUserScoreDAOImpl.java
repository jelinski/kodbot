package pl.yeloon.magisterium.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import pl.yeloon.magisterium.model.MapUserScore;

@Repository
public class MapUserScoreDAOImpl implements MapUserScoreDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<MapUserScore> getUserMapUserScore(int userId) {
		Query query = em.createQuery("FROM MapUserScore mus WHERE mus.userId=:userId");
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public List<MapUserScore> getMapScores(int mapId) {
		Query query = em.createQuery("FROM MapUserScore mus WHERE mus.mapId=:mapId ORDER BY mus.commandCounter asc, mus.batteryLevel desc");
		query.setParameter("mapId", mapId);
		return query.getResultList();
	}

	@Override
	public MapUserScore getMapScores(int mapId, int userId) {
		Query query = em.createQuery("FROM MapUserScore mus WHERE mus.mapId=:mapId AND mus.userId=:userId");
		query.setParameter("mapId", mapId);
		query.setParameter("userId", userId);
		try {
			return (MapUserScore) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public void saveMapUserScore(MapUserScore mapUserScore) {
		if (mapUserScore.isNew()) {
			em.persist(mapUserScore);
		} else {
			em.merge(mapUserScore);
		}
	}

	@Override
	public List<MapUserScore> getMapRank(int mapId) {
		TypedQuery<MapUserScore> query = em.createQuery("from MapUserScore mus inner join fetch mus.user usr inner join fetch usr.statistic left join fetch usr.userConnection where mus.mapId=:mapId order by mus.commandCounter asc, mus.batteryLevel desc ", MapUserScore.class);
		query.setParameter("mapId", mapId);
		return query.getResultList();
	}

}
