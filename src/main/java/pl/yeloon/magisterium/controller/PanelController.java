package pl.yeloon.magisterium.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.yeloon.magisterium.controller.bean.ChangePasswordBean;
import pl.yeloon.magisterium.controller.validator.ChangePasswordBeanValidator;
import pl.yeloon.magisterium.model.Badge;
import pl.yeloon.magisterium.model.User;
import pl.yeloon.magisterium.model.UserStatistic;
import pl.yeloon.magisterium.service.BadgeService;
import pl.yeloon.magisterium.service.SocialService;
import pl.yeloon.magisterium.service.StatisticService;
import pl.yeloon.magisterium.service.UserService;
import pl.yeloon.magisterium.util.SecurityUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequestMapping(value = "/panel")
@Controller
public class PanelController {

	private final ConnectionRepository connectionRepository;

	private final SocialService socialService;

	private final UserService userService;

	private final BadgeService badgeService;

	private final StatisticService statisticService;

	private final ChangePasswordBeanValidator changePasswordBeanValidator;

	private static final Logger logger = LoggerFactory.getLogger(PanelController.class);
	private MessageSourceAccessor messageSourceAccessor;

	@Autowired
	public PanelController(ConnectionRepository connectionRepository, SocialService socialService, UserService userService,
						   BadgeService badgeService, StatisticService statisticService, MessageSource messageSource, ChangePasswordBeanValidator changePasswordBeanValidator) {
		this.connectionRepository = connectionRepository;
		this.socialService = socialService;
		this.userService = userService;
		this.badgeService = badgeService;
		this.statisticService = statisticService;
		this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
		this.changePasswordBeanValidator = changePasswordBeanValidator;
	}

	@InitBinder("changePasswordBean")
	private void changePasswordBeanValidator(WebDataBinder binder) {
		binder.setValidator(changePasswordBeanValidator);
	}


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
	public ResponseStatus changePassword(@RequestBody @Valid ChangePasswordBean changePasswordBean, Errors errors) {
		ResponseStatus response = new ResponseStatus();
		if (errors.hasErrors()) {
			String errorsMessage = errors.getAllErrors().stream()
					.map(e -> messageSourceAccessor.getMessage(e.getCode()))
					.collect(Collectors.joining("<br>"));
			response.setStatus(errorsMessage);
		} else {
			Integer userId = SecurityUtils.getLoggedInUserId();
			if (userId != null) {
				User user = userService.getUserById(userId);
				if (user.getPassword().equals(changePasswordBean.getOldPassword())) {
					user.setPassword(changePasswordBean.getNewPassword());
					userService.saveUser(user);
					response.setStatus("Hasło zostało pomyślnie zmienione");
				} else {
					response.setStatus("Podano niepoprawne hasło");
				}
			} else {
				response.setStatus("Użytkownik zostal wylogowany");
			}
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
