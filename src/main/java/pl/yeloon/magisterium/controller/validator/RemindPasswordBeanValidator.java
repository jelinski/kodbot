package pl.yeloon.magisterium.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.yeloon.magisterium.controller.bean.RemindPasswordBean;
import pl.yeloon.magisterium.service.UserService;

@Component
public class RemindPasswordBeanValidator implements Validator {

	private static final String EMPTY_OR_WHITESPACE_EMAIL = "web.register.validation.email.required";
	private static final String EMAIL_NOT_IN_USE = "web.remind_password.validation.email.not_in_use";
	
	@Autowired
    private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RemindPasswordBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", EMPTY_OR_WHITESPACE_EMAIL);
		
		RemindPasswordBean remindPasswordBean = (RemindPasswordBean) target;
		
		if(!userService.checkIfUserExists(remindPasswordBean.getEmail())){
			errors.rejectValue("email", EMAIL_NOT_IN_USE);
		}
	}

}
