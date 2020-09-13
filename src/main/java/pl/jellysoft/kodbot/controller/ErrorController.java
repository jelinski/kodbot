package pl.jellysoft.kodbot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class ErrorController {

    private static final String ERROR_HEADER = "errorHeader";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String WEB_ERRORS_ERROR_404_HEADER = "web.errors.error404.header";
    private static final String WEB_ERRORS_ERROR_404_MESSAGE = "web.errors.error404.message";
    private static final String WEB_ERRORS_GENERIC_ERROR_HEADER = "web.errors.generic_error.header";
    private static final String WEB_ERRORS_GENERIC_ERROR_MESSAGE = "web.errors.generic_error.message";
    private final MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public ErrorController(MessageSource messageSource) {
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        String uri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        log.error("{} ERROR. Occurred while processing request for \"{}\"", code, uri);
        log.error("Exception:", exception);

        if (Integer.valueOf(404).equals(code)) {
            addErrorMessageToModel(model, WEB_ERRORS_ERROR_404_HEADER, WEB_ERRORS_ERROR_404_MESSAGE);
        } else {
            addErrorMessageToModel(model, WEB_ERRORS_GENERIC_ERROR_HEADER, WEB_ERRORS_GENERIC_ERROR_MESSAGE);
        }

        return "generic_error";
    }

    private void addErrorMessageToModel(Model model, String headerKey, String messageKey) {
        model.addAttribute(ERROR_HEADER, messageSourceAccessor.getMessage(headerKey));
        model.addAttribute(ERROR_MESSAGE, messageSourceAccessor.getMessage(messageKey));
    }

}
