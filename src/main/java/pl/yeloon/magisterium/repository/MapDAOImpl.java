package pl.yeloon.magisterium.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pl.yeloon.magisterium.model.Map;
import pl.yeloon.magisterium.model.MapAccessToken;

@Repository
public class MapDAOImpl implements MapDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Map getMapByKey(String key) {
		List<Map> mapList = new ArrayList<Map>();
		Query query = em.createQuery("from Map m where m.key = :key");
		query.setParameter("key", key);
		mapList = query.getResultList();
		if (mapList.size() > 0)
			return mapList.get(0);
		else
			return null;
	}

	@Override
	public void saveAccessToken(MapAccessToken accessToken) {
		if (accessToken.isNew()) {
			em.persist(accessToken);
		} else {
			em.merge(accessToken);
		}

	}

	@Override
	public MapAccessToken getAccessToken(int userId, int mapId) {
		Query query = em.createQuery("FROM MapAccessToken mat WHERE mat.userId=:userId AND mat.mapId=:mapId");
		query.setParameter("userId", userId);
		query.setParameter("mapId", mapId);
		try{
			return (MapAccessToken) query.getSingleResult();
		}
		catch(NoResultException nre){
			return null;
		}
	}

	@Override
	public List<Map> getAllMaps() {
        Query allQuery = em.createQuery("FROM Map m ORDER BY m.id");
        return allQuery.getResultList();
	}

	@Override
	public Map getMap(int mapId) {
		Query query = em.createQuery("FROM Map m where m.id=:mapId");
		query.setParameter("mapId", mapId);
		try{
			return (Map) query.getSingleResult();
		}
		catch(NoResultException nre){
			return null;
		}
	}

	@Override
	public MapAccessToken getAccessToken(String accessToken) {
		Query query = em.createQuery("FROM MapAccessToken mat WHERE mat.accessToken=:accessToken");
		query.setParameter("accessToken", accessToken);
		try{
			return (MapAccessToken) query.getSingleResult();
		}
		catch(NoResultException nre){
			return null;
		}
	}

	@Override
	public void saveMap(Map map) {
		if (map.isNew()) {
			em.persist(map);
		} else {
			em.merge(map);
		}
	}

}
