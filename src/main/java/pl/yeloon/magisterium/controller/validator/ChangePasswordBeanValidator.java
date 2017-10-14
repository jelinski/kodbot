package pl.yeloon.magisterium.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.yeloon.magisterium.controller.bean.ChangePasswordBean;

@Component
final public class ChangePasswordBeanValidator implements Validator {

    private static final String PASSWORDS_DO_NOT_MATCH = "web.changePassword.validation.repeated_password.not_match";

    private static final String PASSWORD_TOO_SHORT = "web.changePassword.validation.password.short";

    private static final String EMPTY_OR_WHITESPACE_PASSWORD = "web.changePassword.validation.password.required";

    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePasswordBean.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ChangePasswordBean changePasswordBean = (ChangePasswordBean) o;
        if (changePasswordBean.getNewPassword().equals(changePasswordBean.getRepeatedNewPassword())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", EMPTY_OR_WHITESPACE_PASSWORD);

            if (changePasswordBean.getNewPassword().length() < 6) {
                errors.rejectValue("newPassword", PASSWORD_TOO_SHORT);
            }

            if (!changePasswordBean.getNewPassword().equals(changePasswordBean.getRepeatedNewPassword())) {
                errors.rejectValue("repeatedNewPassword", PASSWORDS_DO_NOT_MATCH);
            }
        }
    }
}
