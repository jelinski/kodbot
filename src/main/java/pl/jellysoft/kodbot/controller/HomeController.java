package pl.jellysoft.kodbot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;
import java.util.Map;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Map<String, Object> model) {
		model.put("lang", locale.toString());
		return "home";
	}

}
