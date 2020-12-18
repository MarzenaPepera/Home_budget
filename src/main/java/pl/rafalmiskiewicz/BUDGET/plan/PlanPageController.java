package pl.rafalmiskiewicz.BUDGET.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rafalmiskiewicz.BUDGET.user.UserService;
import pl.rafalmiskiewicz.BUDGET.utilities.UserUtilities;
import pl.rafalmiskiewicz.BUDGET.validators.PlanAddValidator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;


@Controller
public class PlanPageController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;


    @POST
    @RequestMapping(value = "/plan")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String openPlanNewMainPage(Model model) {
        List<Plan> planList = planService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        //Double amount= planList.stream().mapToDouble(t ->t.getAmount()).sum();
        //model.addAttribute("amount", amount);
        //planList.get(0).getDate().getMonth();
        model.addAttribute("planList", planList);
        model.addAttribute(new Plan());
        return "plan/plan";
    }

    @GET
    @RequestMapping(value = "/plan/edit")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String getPlanIdToEditNew(Plan plan, Model model) {


        plan = planService.findPlanById(plan.getId_plan());
        try {
            plan.dateToString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("plan", plan);
        model.addAttribute("month", (plan.getDate().getYear()+1900)+"-"+(plan.getDate().getMonth()+1));

        return "plan/editplan";
    }

    @GET
    @RequestMapping(value = "/plan/addplan")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String addPlan(Model model) {

        Plan h = new Plan();
        model.addAttribute("plan", h);

        return "plan/addplan";
    }

    @POST
    @RequestMapping(value = "/plan/insertplan")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String registerPlan(Plan plan, BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        if (plan.getDate_string() != null)
            try {
                plan.stringToDate();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        plan.setId_user(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());

        Plan planExist = planService.findPlanByDate(plan.getDate());

        new PlanAddValidator().validate(plan, result);
        new PlanAddValidator().validateMonthExist(planExist, result);
        if (result.hasErrors()) {
            model.addAttribute("plan", plan);
        } else {
            planService.savePlan(plan);
            model.addAttribute("message", messageSource.getMessage("plan.add.success", null, locale));
            model.addAttribute("plan", new Plan());
        }
        returnPage = "plan/addplan";

        return returnPage;
    }


    @POST
    @RequestMapping(value = "/plan/edit/updateplan")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String editPlan(Plan plan, BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        try {
            plan.stringToDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //new TransactionAddValidator().validate(plan, result);

        if (!result.hasErrors()) {
            planService.updatePlan(plan);
            model.addAttribute("message", messageSource.getMessage("plan.edit.success", null, locale));
            model.addAttribute("plan", new Plan());
        }
        returnPage = "plan/editplan";

        return returnPage;


    }


}
