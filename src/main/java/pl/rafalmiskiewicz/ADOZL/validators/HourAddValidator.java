package pl.rafalmiskiewicz.ADOZL.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.rafalmiskiewicz.ADOZL.constants.AdozlConstants;
import pl.rafalmiskiewicz.ADOZL.hours.Hour;
import pl.rafalmiskiewicz.ADOZL.user.User;
import pl.rafalmiskiewicz.ADOZL.utilities.AppdemoUtils;

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
			boolean isMatch = AppdemoUtils.checkDate(AdozlConstants.HOUR_PATTERN, h.getHour_from());
			if(!isMatch) {
				errors.rejectValue("hour_from", "hour.error.dateNotMatch");
			}
		}

    }

}
