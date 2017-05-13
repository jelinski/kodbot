package pl.yeloon.magisterium.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import pl.yeloon.magisterium.model.Badge;

@Repository
public class BadgeDAOImpl implements BadgeDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Badge getById(int id) throws PersistenceException {
		Query query = em.createQuery("from Badge b where b.id = :id");
		query.setParameter("id", id);
		return (Badge) query.getSingleResult(); 
	}

	@Override
	public long getBadgeCount(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq =  cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Badge.class)));
		return em.createQuery(cq).getSingleResult();
	}
	
}
