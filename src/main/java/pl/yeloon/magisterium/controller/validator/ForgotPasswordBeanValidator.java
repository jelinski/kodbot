package pl.yeloon.magisterium.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.yeloon.magisterium.controller.bean.ForgotPasswordBean;
import pl.yeloon.magisterium.service.UserService;

@Component
public class ForgotPasswordBeanValidator implements Validator {

	private static final String EMPTY_OR_WHITESPACE_EMAIL = "web.register.validation.email.required";
	private static final String EMAIL_NOT_IN_USE = "web.forgot_password.validation.email.not_in_use";
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ForgotPasswordBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", EMPTY_OR_WHITESPACE_EMAIL);
		
		ForgotPasswordBean fpb = (ForgotPasswordBean) target;
		
		if(!userService.checkIfUserExists(fpb.getEmail())){
			errors.rejectValue("email", EMAIL_NOT_IN_USE);
		}
	}

}
