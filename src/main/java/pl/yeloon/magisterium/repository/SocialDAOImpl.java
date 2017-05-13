package pl.yeloon.magisterium.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import pl.yeloon.magisterium.model.PartnerCode;
import pl.yeloon.magisterium.model.User;

@Repository
public class SocialDAOImpl implements SocialDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public PartnerCode getPartnerCode(String registrationCode) {
		Query query = em.createQuery("from PartnerCode pc where pc.code = :code");
		query.setParameter("code", registrationCode);
		try {
			return (PartnerCode) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public PartnerCode getPartnerCodeByFriendEmail(String invitedFriendEmail) {
		Query query = em.createQuery("from PartnerCode pc where pc.friendEmail = :friendEmail");
		query.setParameter("friendEmail", invitedFriendEmail);
		try {
			return (PartnerCode) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public void savePartnerCode(PartnerCode partnerCode) {
		if (partnerCode.isNew()) {
			em.persist(partnerCode);
		} else {
			em.merge(partnerCode);
		}
	}

	@Override
	public long countInvitedFriendsByUserWithId(int userId) {
		// oblicza ilosc promotion code z zadana wartoscia refererid
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PartnerCode> p = cq.from(PartnerCode.class);
		cq.select(cb.count(p));
		cq.where(cb.equal(p.get("refererId"), userId));
		return em.createQuery(cq).getSingleResult();
	}

	@Override
	public long countRegisteredFriendsByUserWithId(int userId) {
		// Oblicza ilosc uzytkownikow z zadana wartoscia refererId
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<User> u = cq.from(User.class);
		cq.select(cb.count(u));
		cq.where(cb.equal(u.get("refererId"), userId));
		return em.createQuery(cq).getSingleResult();
	}

}
