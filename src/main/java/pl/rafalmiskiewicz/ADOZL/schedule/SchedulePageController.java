package pl.rafalmiskiewicz.ADOZL.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rafalmiskiewicz.ADOZL.hours.Hour;
import pl.rafalmiskiewicz.ADOZL.hours.HourService;
import pl.rafalmiskiewicz.ADOZL.places.PlacesService;
import pl.rafalmiskiewicz.ADOZL.user.Role;
import pl.rafalmiskiewicz.ADOZL.user.User;
import pl.rafalmiskiewicz.ADOZL.user.UserService;
import pl.rafalmiskiewicz.ADOZL.utilities.UserUtilities;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.text.ParseException;
import java.util.*;


@Controller
public class SchedulePageController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private HourService hourService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlacesService placesService;

    @POST
    @RequestMapping(value = "/schedule")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER", "ROLE_CONTRROLER"})
    public String openScheduleNewMainPage(Model model) {
        List<Schedule> scheduleList = scheduleService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        for (Schedule s : scheduleList) {
            matchPlaces(s);
        }
        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute(new Schedule());
        return "schedule/schedule";
    }

    @RequestMapping(value = "/schedule/addschedule")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String addSchedule(Schedule schedule, Model model, String action, BindingResult result, Locale locale) {
        String message = "";
        if (action != null && action.equals("save")) {
            try {
                schedule.stringToDate();
            } catch (ParseException e) {
                message = "Musisz uzupełnić wszystkie dane!";
                model.addAttribute("message", message);
                e.printStackTrace();
            }
            if (schedule.getId_user() != null && schedule.getId_role() != null && schedule.getId_places() != null && schedule.getHour_from() != null && schedule.getHour_to() != null) {
                if (!result.hasErrors()) {
                    scheduleService.saveSchedule(schedule);
                    model.addAttribute("message", messageSource.getMessage("schedule.add.success", null, locale));
                    model.addAttribute("schedule", new Schedule());
                }
            } else {
                message = "Musisz uzupełnić wszystkie dane!";
                model.addAttribute("message", message);
            }
        } else {
            Hour hour = new Hour();
            if (schedule.getOnlyDate_from_string() != null) {
                if (schedule.getHour_from_string().isEmpty())
                    schedule.setHour_from_string("00:00");
                if (schedule.getHour_to_string().isEmpty())
                    schedule.setHour_to_string("23:59");
                try {
                    schedule.stringToDate();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            Map<Integer, String> roleMap = new HashMap<Integer, String>();
            roleMap = prepareRoleMap();
            roleMap.remove(1);
            roleMap.put(null, "");

            hour.setUser(new User());
            hour.getUser().setRoles(new HashSet<>());

            Role r = new Role();
            r.setId(schedule.getId_role());
            r.setRole("ROLE_CONTROLLER");

            hour.getUser().getRoles().add(r);
            hour.getUser().setId(schedule.getId_user());
            hour.getUser().getRoles().contains(r);

            List<Hour> hourList = hourService.findAll(hour);

            if (schedule.getId_role() != null) {
                CollectionUtils.filter(hourList, h -> equalsRole(((Hour) h).getUser().getRoles(), schedule.getId_role()));
            }
            if (schedule.getHour_from() != null) {
                if (schedule.getHour_from_string().equals("00:00")) {
                    CollectionUtils.filter(hourList, h -> ((Hour) h).getHour_from().after(schedule.getHour_from()));
                } else {
                    CollectionUtils.filter(hourList, h -> schedule.getHour_from().after(((Hour) h).getHour_from()));
                }
            }
            if (schedule.getHour_to() != null) {
                if (schedule.getHour_to_string().equals("23:59")) {
                    CollectionUtils.filter(hourList, h -> ((Hour) h).getHour_to().before(schedule.getHour_to()));
                } else {
                    CollectionUtils.filter(hourList, h -> schedule.getHour_to().before(((Hour) h).getHour_to()));
                }
            }

            Map<Integer, String> placeMap = new HashMap<Integer, String>();
            placeMap = preparePlaceMap();
            placeMap.put(null, "");


            Map<Integer, String> userMap = new HashMap<Integer, String>();
            for (Hour h : hourList) {
                userMap.put(h.getUser().getId(), h.getUser().getName() + " " + h.getUser().getLastName());
            }
            userMap.put(null, "");

            model.addAttribute("hourList", hourList);
            model.addAttribute("roleMap", roleMap);
            model.addAttribute("placeMap", placeMap);
            model.addAttribute("userMap", userMap);

        }
        model.addAttribute("schedule", schedule);
        return "schedule/addschedule";
    }

    private boolean equalsRole(Set<Role> roles, Integer idRole) {
        for (Role role : roles) {
            if (role.getId() == idRole)
                return true;
        }
        return false;
    }

    @POST
    @RequestMapping(value = "/schedule/insertschedule")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public String registerSchedule(Schedule schedule, BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        try {
            schedule.stringToDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        schedule.setId_user(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());

        //new ScheduleAddValidator().validate(schedule, result);

        if (result.hasErrors()) {
            returnPage = "schedule/addschedule";
        } else {

            //scheduleRepository.save(schedule);
            //scheduleService.saveSchedule(schedule);
            scheduleService.saveScheduleNew(schedule);
            //scheduleService.insertScheduleString(schedule);
            model.addAttribute("message", messageSource.getMessage("schedule.add.success", null, locale));
            model.addAttribute("schedule", new Schedule());
            returnPage = "schedule/addschedule";
        }

        return returnPage;


    }

    void matchPlaces(Schedule schedule) {
        schedule.setPlaces(placesService.findPlacesById(schedule.getId_places()));
    }

    void matchUsers(Schedule schedule) {
        schedule.setUser(userService.findUserById(schedule.getId_user()));
    }

    void matchUsers(Hour hour) {
        hour.setUser(userService.findUserById(hour.getId_user()));
    }

    public Map<Integer, String> prepareRoleMap() {
        Locale locale = Locale.getDefault();
        Map<Integer, String> roleMap = new HashMap<Integer, String>();
        roleMap.put(1, messageSource.getMessage("word.admin", null, locale));
        roleMap.put(2, messageSource.getMessage("word.user", null, locale));
        roleMap.put(3, messageSource.getMessage("word.controller", null, locale));
        return roleMap;
    }

    public Map<Integer, String> preparePlaceMap() {
        Locale locale = Locale.getDefault();
        Map<Integer, String> roleMap = new HashMap<Integer, String>();
        roleMap.put(1, "Skrzyżowanie Szlak z Warszawską");
        roleMap.put(2, "AGH Czarnowiejska przystanek");
        roleMap.put(3, "AGH UR");
        return roleMap;
    }

}
