package pl.yeloon.magisterium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import pl.yeloon.magisterium.model.User;

//TODO szablony + internacjonalizacjia + ograniczenie ilosci wysylanych maili
@Service
public class MailService {

	private static final String FROM_ADDRESS = "kodbot.app@gmail.com";

	@Autowired
	private MailSender mailSender;

	@Autowired
	private UserService userService;

	public void sendMail(String to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(FROM_ADDRESS);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}

    public void sendInvitation(String to, String refererEmail, String registrationCode) {
        String url = UriComponentsBuilder.newInstance().scheme("https").host("www.kodbot.pl").path("register")
                .queryParam("registrationCode", registrationCode).toUriString();

         sendMail(to, "Twój znajomy zaprasza Cię do zagrania w KodBot", "Twój znajomy o emailu: \"" + refererEmail + "\" zaprasza Cię do zagrania w grę KodBot. Twój specjalny link aktywacyjny to: " + url);
	}

	public void remindPassword(String email) {
		User user = userService.getUser(email);
		if (user != null) {
			sendMail(email, "Zapomiane hasło", "Twoje hasło w serwisie KodBot.pl to: " + user.getPassword());
		}
	}

}
