package pl.yeloon.magisterium.controller;

import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handle404(HttpServletRequest request, Model model, Locale locale) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String uri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.error(MessageFormat.format("{0} ERROR. Occurred while processing request for \"{1}\". Authentication: {2}", code,
                uri, authentication), exception);
        String errorHeader;
        String errorMessage;
        if (404 == code) {
            errorHeader = "web.errors.error404.header";
            errorMessage = "web.errors.error404.message";
        } else {
            errorHeader = "web.errors.generic_error.header";
            errorMessage = "web.errors.generic_error.message";
        }
        model.addAttribute("errorHeader", messageSource.getMessage(errorHeader, null, locale));
        model.addAttribute("errorMessage", messageSource.getMessage(errorMessage, null, locale));
        return "generic_error";
	}
}
