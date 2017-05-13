package pl.yeloon.magisterium.repository;

import java.util.List;

import pl.yeloon.magisterium.model.User;

public interface UserDAO {

	User getUser(String email);
	User getUser(int id);
	void saveUser(User user);
	List<User> getUsers();
}
