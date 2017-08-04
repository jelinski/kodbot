package pl.yeloon.magisterium.service;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.yeloon.magisterium.model.PartnerCode;
import pl.yeloon.magisterium.model.User;
import pl.yeloon.magisterium.repository.SocialDAO;
import pl.yeloon.magisterium.util.SecurityUtils;

@Service
public class SocialServiceImpl implements SocialService {

	private SecureRandom random = new SecureRandom();

	@Autowired
    private SocialDAO socialDAO;

	@Autowired
    private MailService mailService;

	@Autowired
    private UserService userService;

	@Autowired
    private BadgeService badgeService;

	@Transactional
	@Override
	public void inviteFriend(String friendEmail) {
		Integer refererId = SecurityUtils.getLoggedInUserId();
		if (refererId != null) {
			PartnerCode pc = socialDAO.getPartnerCodeByFriendEmail(friendEmail);
			if (pc != null) {
				// Ten uzytkownik zostal juz przez kogos zaproszony
				throw new IllegalArgumentException();
			} else {
				User referer = userService.getUserById(refererId);
				if (referer != null) {
					// TODO dodac ograniczenie na wysylanie nie wiecej niz X maili dziennie
					PartnerCode partnerCode = new PartnerCode();
					partnerCode.setCode(new BigInteger(130, random).toString(32));
					partnerCode.setRefererId(refererId);
					partnerCode.setFriendEmail(friendEmail);
					socialDAO.savePartnerCode(partnerCode);
					mailService.sendInvitation(friendEmail, referer.getEmail(), partnerCode.getCode());
					badgeService.assignBadgeToUserForFriendInvitating(refererId);
				}
			}
		}
	}

	@Override
	public PartnerCode getPartnerCode(String registrationCode) {
		return socialDAO.getPartnerCode(registrationCode);
	}

}
