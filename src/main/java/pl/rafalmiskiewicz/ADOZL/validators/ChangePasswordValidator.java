package pl.rafalmiskiewicz.ADOZL.validators;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.rafalmiskiewicz.ADOZL.constants.AdozlConstants;
import pl.rafalmiskiewicz.ADOZL.user.User;
import pl.rafalmiskiewicz.ADOZL.utilities.AppdemoUtils;

public class ChangePasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return User.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        @SuppressWarnings("unused")
        User u = (User) obj;

        ValidationUtils.rejectIfEmpty(errors, "newPassword", "error.userPassword.empty");

    }

    public void checkPasswords(String newPass, Errors errors) {

        if (!newPass.equals(null)) {
            boolean isMatch = true;//AppdemoUtils.checkEmailOrPassword(AdozlConstants.passwordPattern, newPass);
            if (!isMatch) {
                errors.rejectValue("newPassword", "error.userPasswordIsNotMatch");
            }
        }
    }
}
