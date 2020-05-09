package pl.jellysoft.kodbot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Locale locale, Map<String, Object> model) {
        model.put("lang", locale.toString());
        return "home";
    }

}
