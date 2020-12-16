package pl.rafalmiskiewicz.BUDGET.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.rafalmiskiewicz.BUDGET.plan.Plan;
import pl.rafalmiskiewicz.BUDGET.user.User;

public class PlanAddValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return User.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Plan plan = (Plan) obj;


        ValidationUtils.rejectIfEmpty(errors, "amount", "error.amount.empty");
        ValidationUtils.rejectIfEmpty(errors, "date", "error.month.empty");
        ValidationUtils.rejectIfEmpty(errors, "description", "error.description.empty");


//		if (!plan.getDate().equals(null)) {
//
//			boolean isMatch = AppdemoUtils.checkDate(BUDGETConstants.HOUR_PATTERN, h.getHour_from());
//			if(!isMatch) {
//				errors.rejectValue("hour_from", "hour.error.dateNotMatch");
//			}
//		}

    }

    public void validateMonthExist(Plan plan, Errors errors) {
        if (plan != null) {
            errors.rejectValue("date", "error.monthExist");
        }
    }

}
