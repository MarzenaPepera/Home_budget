package pl.rafalmiskiewicz.BUDGET.hours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rafalmiskiewicz.BUDGET.user.UserService;
import pl.rafalmiskiewicz.BUDGET.utilities.UserUtilities;
import pl.rafalmiskiewicz.BUDGET.validators.HourAddValidator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;


@Controller
public class HoursPageController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    private HourService hourService;

    @Autowired
    private UserService userService;

    @Autowired
    private HourRepository hourRepository;

    @POST
    @RequestMapping(value = "/hour")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String openHourNewMainPage(Model model) {
        List<Hour> hourList = hourService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        model.addAttribute("hourList", hourList);
        model.addAttribute(new Hour());
        return "hour/hour";
    }

    @GET
    @RequestMapping(value = "/hour/edit")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String getHourIdToEditNew(Hour hour, Model model) {

        System.out.println(hour);
        hour=hourService.findHourById(hour.getId_hours());
        try {
            hour.dateToString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("hour", hour);

        return "hour/edithour";
    }

    @GET
    @RequestMapping(value = "/hour/addhour")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String addHour( Model model) {

        Hour h = new Hour();
        model.addAttribute("hour", h);

        return "hour/addhour";
    }

    @POST
    @RequestMapping(value = "/hour/inserthour")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String registerHour(Hour hour,  BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        try {
            hour.divdedDateToString();
            hour.stringToDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hour.setUser(userService.findUserByEmail(UserUtilities.getLoggedUser()));

        new HourAddValidator().validate(hour, result);

        if (result.hasErrors()) {
            returnPage = "hour/addhour";
        } else {
            //hourRepository.save(hour);
            //hourService.saveHour(hour);
            hourService.saveHourNew(hour);
            //hourService.insertHourString(hour);
            model.addAttribute("message", messageSource.getMessage("hour.add.success", null, locale));
            model.addAttribute("hour", new Hour());
            returnPage = "hour/addhour";
        }

        return returnPage;


    }


    @POST
    @RequestMapping(value = "/hour/edit/updatehour")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String editHour(Hour hour,  BindingResult result, Model model, Locale locale) {

        String returnPage = null;
        try {
            hour.stringToDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //hour.setId_user(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        new HourAddValidator().validate(hour, result);

        if (result.hasErrors()) {
            returnPage = "hour/edithour";
        } else {
            hourService.updateHour(hour);

            model.addAttribute("message", messageSource.getMessage("hour.edit.success", null, locale));
            model.addAttribute("hour", new Hour());
            returnPage = "hour/edithour";
        }

        return returnPage;


    }



}
