package pl.jellysoft.kodbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

@Controller
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    private final MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public ErrorController(MessageSource messageSource) {
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError(HttpServletRequest request, Model model) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String uri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        logger.error(MessageFormat.format("{0} ERROR. Occurred while processing request for \"{1}\".", code, uri), exception);

        String errorHeader;
        String errorMessage;
        if (404 == code) {
            errorHeader = "web.errors.error404.header";
            errorMessage = "web.errors.error404.message";
        } else {
            errorHeader = "web.errors.generic_error.header";
            errorMessage = "web.errors.generic_error.message";
        }
        model.addAttribute("errorHeader", messageSourceAccessor.getMessage(errorHeader));
        model.addAttribute("errorMessage", messageSourceAccessor.getMessage(errorMessage));

        return "generic_error";
    }
}
