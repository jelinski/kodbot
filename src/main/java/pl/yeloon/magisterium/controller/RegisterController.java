package pl.yeloon.magisterium.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.social.connect.*;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.yeloon.magisterium.controller.bean.RegisterUserBean;
import pl.yeloon.magisterium.controller.validator.RegisterUserBeanValidator;
import pl.yeloon.magisterium.model.User;
import pl.yeloon.magisterium.service.BadgeService;
import pl.yeloon.magisterium.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.security.SecureRandom;

@Controller
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	private SecureRandom random = new SecureRandom();

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private ConnectionRepository connectionRepository;

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RegisterUserBeanValidator registerUserBeanValidator;

	@InitBinder("registerUserBean")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(registerUserBeanValidator);
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String initForm(@ModelAttribute RegisterUserBean registerUserBean,
            @RequestParam(required = false) String registrationCode) {
        if (StringUtils.hasText(registrationCode)) {
            registerUserBean.setRegistrationCode(registrationCode);
        }
		return "register";
	}

    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegister(@ModelAttribute @Valid RegisterUserBean registerUserBean, Errors errors, HttpServletRequest request) {
		if (errors.hasErrors()) {
			return "register";
		}
		logger.debug("New user registered");
		userService.createNewUser(registerUserBean);
		// mailService.sendMail(registerUserBean.getEmail()
		authenticateUserAndSetSession(registerUserBean, request);
		return "redirect:/panel";
	}

	/**
	 * Metoda obslugujaca rejestracje przy uzyciu facebooka - po nie odnalezieniu istniejacego uzytkownika o podanym email UWAGA! Stosuje sie rowniez do uzytkownikow, ktorzy nie udostepnia praw do emaila
	 * */
	@RequestMapping(value = "/register/facebook_user")
	public String handleConnected(WebRequest request) {

		@SuppressWarnings("unchecked")
		Connection<Facebook> connection = (Connection<Facebook>) providerSignInUtils.getConnectionFromSession(request);
		UserProfile userProfile = connection.fetchUserProfile();

		User user = null;
		String email = userProfile.getEmail();
		if (email != null)
			user = userService.getUser(email);

		if (user == null) {
			if (email == null) {
				return "rerequest";
			}

			User newUser = new User();
			newUser.setEmail(email);
			newUser.setNickname(email.substring(0, email.indexOf('@')));
			newUser.setFirstName(userProfile.getFirstName());
			newUser.setLastName(userProfile.getLastName());
			newUser.setPassword(new BigInteger(130, random).toString(32));
			newUser.setEnabled(true);
			userService.saveUser(newUser, BadgeService.FACEBOOK);
			providerSignInUtils.doPostSignUp(newUser.getEmail(), request);
		} else {
			providerSignInUtils.doPostSignUp(user.getEmail(), request);
		}

		return "redirect:/auth/facebook";
	}

	@RequestMapping(value = "/login_facebook")
	@ResponseBody
    public String login_facebook(@RequestParam String token) {
		ConnectionFactory<Facebook> facebookConnectionFactory = connectionFactoryLocator.getConnectionFactory(Facebook.class);
		FacebookConnectionFactory facebookConnectionFactory2 = (FacebookConnectionFactory) facebookConnectionFactory;
		Connection<Facebook> ff = facebookConnectionFactory2.createConnection(new AccessGrant(token));
		Facebook api = ff.getApi();
		ff.refresh();

		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			// Connection<Facebook> ff= facebookConnectionFactory2.createConnection(new AccessGrant(token));
			// Facebook api = ff.getApi();

			// FacebookTemplate
			String email = api.userOperations().getUserProfile().getEmail();
			User user = userService.getUser(email);
			if (user != null) {
				// login
				Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			connectionRepository.addConnection(ff);
		}

		// TODO - uniemozliwic polaczenie sie z kontem, jesli juz inne konto jest polaczone z tym kontem na fejsie
		// wtedy nie bedzie dzialc przycisk zalogowania, poniewaz istnieje niejednoznacznosc
		return "redirect:/";
	}

	/** Metoda obsługująca ANULOWANIE przez uzytkownika uwierzytelnienia aplikacji w facebook */
	@RequestMapping(value = "/signin")
	public String handleUserDiscard(WebRequest request) {
		return "redirect:/";
	}

	private void authenticateUserAndSetSession(RegisterUserBean user, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

		// generate session if one doesn't exist
		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}
}
