package pl.yeloon.magisterium.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.yeloon.magisterium.controller.bean.ChangePasswordBean;
import pl.yeloon.magisterium.model.Badge;
import pl.yeloon.magisterium.model.User;
import pl.yeloon.magisterium.model.UserStatistic;
import pl.yeloon.magisterium.service.BadgeService;
import pl.yeloon.magisterium.service.SocialService;
import pl.yeloon.magisterium.service.StatisticService;
import pl.yeloon.magisterium.service.UserService;
import pl.yeloon.magisterium.util.SecurityUtils;

@RequestMapping(value = "/panel")
@Controller
public class PanelController {

    @Autowired
    private ConnectionRepository connectionRepository;

	@Autowired
	private SocialService socialService;

	@Autowired
	private UserService userService;

	@Autowired
	private BadgeService badgeService;

	@Autowired
	private StatisticService statisticService;

	private static final Logger logger = LoggerFactory.getLogger(PanelController.class);

	@RequestMapping(method = RequestMethod.GET)
    public String panel(Locale locale, Model model) {
        boolean isFacebookAuthorized = connectionRepository.findPrimaryConnection(Facebook.class) != null;
        model.addAttribute("facebookConnected", isFacebookAuthorized);
		Integer userId = SecurityUtils.getLoggedInUserId();
		if (userId != null) {
			User user = userService.getUserById(userId);
			UserStatistic userStatistic = statisticService.getUserStatistic(userId);
			model.addAttribute("statistic", userStatistic);
			List<Badge> badges = userService.getUserBadges(userId);
			model.addAttribute("badges", badges);
			model.addAttribute("nickname", user.getNickname());
			model.addAttribute("unblockedBadgeCount", badgeService.getBadgeCount() - badges.size());
		}

		model.addAttribute("locale", locale);
		return "panel";
	}

	@ResponseBody
	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public ResponseStatus changePassword(@RequestBody ChangePasswordBean changePasswordBean) {
		ResponseStatus response = new ResponseStatus();
		Integer userId = SecurityUtils.getLoggedInUserId();
		if (userId != null) {
			User user = userService.getUserById(userId);
			if (user.getPassword().equals(changePasswordBean.getOldPassword())) {
				if (changePasswordBean.getNewPassword().equals(changePasswordBean.getRepeatedNewPassword())) {
					if (changePasswordBean.getNewPassword().length() >= 6) {
						user.setPassword(changePasswordBean.getNewPassword());
						userService.saveUser(user);
						response.setStatus("Hasło zostało pomyślnie zmienione");
					} else {
						response.setStatus("Hasło musi mieć przynajmniej 6 znaków");
					}
				} else {
					response.setStatus("Podane hasła różnią się od siebie");
				}
			} else {
				response.setStatus("Podano niepoprawne hasło");
			}
		} else {
			response.setStatus("Użytkownik zostal wylogowany");
		}

		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/change-nickname", method = RequestMethod.POST)
	public ResponseStatus changeNickname(@RequestParam String nickname) {
		ResponseStatus response = new ResponseStatus();
		if (nickname.length() >= 4) {
			Integer userId = SecurityUtils.getLoggedInUserId();
			if (userId != null) {
				User user = userService.getUserById(userId);
				// TODO - regex na nickname = [a-zA-Z0-9 \- _]{4,}
				user.setNickname(nickname);
				userService.saveUser(user);
				response.setStatus("Nazwa użytkownika została zmieniona pomyślnie");
			} else {
				response.setStatus("Użytkownik nie jest zalogowany");
			}
		} else {
			response.setStatus("Nickname musi mieć przynajmniej 4 znaki");
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/invite-friend", method = RequestMethod.POST)
	public ResponseStatus inviteFriend(@RequestParam String friendEmail) {
		ResponseStatus response = new ResponseStatus();
		try {
			socialService.inviteFriend(friendEmail);
			response.setStatus("Pomyślnie zaproszono znajomego");
		} catch (Exception e) {
            // TODO replace by custom exception
			if (e instanceof IllegalArgumentException) {
				response.setStatus("Ten użytkownik został juz przez kogoś zaproszony");
			} else {
                logger.error("Problem with sending an invitation to a friend", e);
				response.setStatus("Wystąpił błąd podczas wysyłania zaproszenia do znajomego. Jeśli błąd ciągle się powtarza, proszę nas o tym powiadomić");
			}
		}
		return response;
	}

	private class ResponseStatus {
		private String status;

		@SuppressWarnings("unused")
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}
}
