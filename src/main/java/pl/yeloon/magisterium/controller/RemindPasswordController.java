package pl.yeloon.magisterium.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.yeloon.magisterium.controller.bean.RemindPasswordBean;
import pl.yeloon.magisterium.controller.validator.RemindPasswordBeanValidator;
import pl.yeloon.magisterium.service.MailService;

@Controller
final class RemindPasswordController {

    @Autowired
    private RemindPasswordBeanValidator remindPasswordBeanValidator;

    @Autowired
    private MailService mailService;

    @InitBinder("remindPasswordBean")
    private void initRemindPasswordValidator(WebDataBinder binder) {
        binder.setValidator(remindPasswordBeanValidator);
    }

    @RequestMapping(value = "/remind-password", method = RequestMethod.GET)
    public RemindPasswordBean initRemindPassword() {
        return new RemindPasswordBean();
    }

    @RequestMapping(value = "/remind-password", method = RequestMethod.POST)
    public String processRemindPassword(@ModelAttribute @Valid RemindPasswordBean remindPasswordBean, BindingResult result) {
        if (result.hasErrors()) {
            return "remind-password";
        }
        mailService.remindPassword(remindPasswordBean.getEmail());
        return "redirect:/remind-password-success";
    }

    @RequestMapping(value = "/remind-password-success", method = RequestMethod.GET)
    public String processRemindPassword() {
        return "forgotten-password-send";
    }

}
