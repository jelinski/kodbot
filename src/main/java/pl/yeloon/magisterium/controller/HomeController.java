package pl.yeloon.magisterium.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.yeloon.magisterium.service.ResolverService;

@Controller
public class HomeController {

//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	ResolverService resolverService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Map<String, Object> model) {
		model.put("lang", locale.toString());
		return "home";
	}

}
