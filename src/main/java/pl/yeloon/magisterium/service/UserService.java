package pl.yeloon.magisterium.service;

import java.util.List;

import pl.yeloon.magisterium.controller.bean.RegisterUserBean;
import pl.yeloon.magisterium.model.Badge;
import pl.yeloon.magisterium.model.User;

public interface UserService {

	User getUser(String email);

	void saveUser(User user);

	boolean checkIfUserExists(String email);

	void createNewUser(RegisterUserBean registerUserBean);

	void addBadgeToProfile(int userId, int badgeId);

	List<Badge> getUserBadges(int userId);

	User getUserById(int userId);

	void saveUser(User user, int badgeId);

	List<User> getUsers();

}
