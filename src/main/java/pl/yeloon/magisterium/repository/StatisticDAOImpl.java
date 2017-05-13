package pl.yeloon.magisterium.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pl.yeloon.magisterium.model.UserStatistic;

@Repository
public class StatisticDAOImpl implements StatisticDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public UserStatistic getUserStatistic(int userId){
		Query query = em.createQuery("FROM UserStatistic us WHERE us.userId=:userId");
		query.setParameter("userId", userId);
		try{
		return (UserStatistic) query.getSingleResult();
		}
		catch(NoResultException nre){
			return null;
		}
	}
	
	@Override
	public void saveUserStatistic(UserStatistic userStatistic){
		if (userStatistic.isNew()) {
			em.persist(userStatistic);
		} else {
			em.merge(userStatistic);
		}
	}
	
}
