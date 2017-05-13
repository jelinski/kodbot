package pl.yeloon.magisterium.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pl.yeloon.magisterium.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public User getUser(String email) {
		List<User> userList = new ArrayList<User>();
		Query query = em.createQuery("from User u where u.email = :email");
		query.setParameter("email", email);
		userList = query.getResultList();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	@Override
	public User getUser(int id) {
		Query query = em.createQuery("from User u where u.id = :id");
		query.setParameter("id", id);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public void saveUser(User user) {
		if (user.isNew()) {
			em.persist(user);
		} else {
			em.merge(user);
		}
	}

	@Override
	public List<User> getUsers() {
		return em.createQuery("from User", User.class).getResultList();
	}

}
