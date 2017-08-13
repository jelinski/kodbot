package pl.yeloon.magisterium.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import pl.yeloon.magisterium.controller.bean.RegisterUserBean;
import pl.yeloon.magisterium.model.Badge;
import pl.yeloon.magisterium.model.PartnerCode;
import pl.yeloon.magisterium.model.User;
import pl.yeloon.magisterium.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDAO userDAO;

	@Autowired
    private BadgeService badgeService;

	@Autowired
    private SocialService socialService;

	@Override
	public List<User> getUsers(){
		return userDAO.getUsers();
	}
	
	@Override
	public User getUser(String email) {
		return userDAO.getUser(email);
	}

	@Override
	public User getUserById(int userId) {
		return userDAO.getUser(userId);
	}

	@Transactional
	@Override
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}
	
	@Transactional
	@Override
	public void saveUser(User user, int badgeId){
		Badge badge = badgeService.getById(badgeId);
		user.getBadges().add(badge);
		saveUser(user);
	}

	@Override
	public boolean checkIfUserExists(String email) {
		return userDAO.getUser(email) != null;
	}

	@Transactional
	@Override
	public void createNewUser(RegisterUserBean registerUserBean) {
		User newUser = new User();
		newUser.setEmail(registerUserBean.getEmail());
		newUser.setNickname(registerUserBean.getNickname());
		newUser.setPassword(registerUserBean.getPassword());
		newUser.setEnabled(true);
		String registrationCode = registerUserBean.getRegistrationCode();
		if (StringUtils.hasText(registrationCode)) {
			PartnerCode partnerCode = socialService.getPartnerCode(registrationCode);
            Integer referrerId = partnerCode.getRefererId();
			newUser.setRefererId(referrerId);
            badgeService.assignBadgeToUserForFriendRegistering(referrerId);
		}
		saveUser(newUser);
	}

	@Transactional
	@Override
	public void addBadgeToProfile(int userId, int badgeId) {
		User user = userDAO.getUser(userId);
		Badge badge = badgeService.getById(badgeId);
		List<Badge> usrBadges = user.getBadges();
		if (!usrBadges.contains(badge)) {
			usrBadges.add(badge);
			saveUser(user);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Badge> getUserBadges(int userId) {
		List<Badge> badgeList = userDAO.getUser(userId).getBadges();
		return new ArrayList<Badge>(badgeList);
	}

}
