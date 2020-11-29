package pl.rafalmiskiewicz.BUDGET.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.rafalmiskiewicz.BUDGET.constants.BUDGETConstants;
import pl.rafalmiskiewicz.BUDGET.hours.Hour;
import pl.rafalmiskiewicz.BUDGET.user.User;
import pl.rafalmiskiewicz.BUDGET.utilities.AppdemoUtils;

public class HourAddValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return User.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Hour h = (Hour) obj;

        ValidationUtils.rejectIfEmpty(errors, "hour_from", "error.userName.empty");
        ValidationUtils.rejectIfEmpty(errors, "hour_to", "error.userLastName.empty");


		if (!h.getHour_from().equals(null)) {
			boolean isMatch = AppdemoUtils.checkDate(BUDGETConstants.HOUR_PATTERN, h.getHour_from());
			if(!isMatch) {
				errors.rejectValue("hour_from", "hour.error.dateNotMatch");
			}
		}

    }

}
