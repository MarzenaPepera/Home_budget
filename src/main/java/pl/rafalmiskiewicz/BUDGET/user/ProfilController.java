package pl.rafalmiskiewicz.BUDGET.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rafalmiskiewicz.BUDGET.utilities.UserUtilities;
import pl.rafalmiskiewicz.BUDGET.validators.ChangePasswordValidator;
import pl.rafalmiskiewicz.BUDGET.validators.EditUserProfileValidator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Locale;

@Controller
public class ProfilController {

    @Autowired
    MessageSource messageSource;
    @Autowired
    private UserService userService;

    @GET
    @RequestMapping(value = "/profil")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String showUserProfilePage(Model model) {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        int nrRoli = user.getRoles().iterator().next().getId();
        user.setNrRoli(nrRoli);
        model.addAttribute("user", user);
        return "profil";
    }

    @GET
    @RequestMapping(value = "/editpassword")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String editUserPassword(Model model) {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        model.addAttribute("user", user);
        return "editpassword";
    }

    @POST
    @RequestMapping(value = "/updatepass")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String changeUSerPassword(User user, BindingResult result, Model model, Locale locale) {
        String returnPage = null;

        new ChangePasswordValidator().validate(user, result);

        new ChangePasswordValidator().checkPasswords(user.getNewPassword(), result);

        if (result.hasErrors()) {
            returnPage = "editpassword";
        } else {
            userService.updateUserPassword(user.getNewPassword(), user.getEmail());
            returnPage = "editpassword";
            model.addAttribute("message", messageSource.getMessage("passwordChange.success", null, locale));
        }

        return returnPage;
    }

    @GET
    @RequestMapping(value = "/editprofil")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String changeUserData(Model model) {
        String username = UserUtilities.getLoggedUser();
        User user = userService.findUserByEmail(username);
        model.addAttribute("user", user);
        return "editprofil";
    }

    @POST
    @RequestMapping(value = "/updateprofil")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String changeUserDataAction(User user, BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        new EditUserProfileValidator().validate(user, result);
        if (result.hasErrors()) {
            returnPage = "editprofil";
        } else {
            userService.updateUserProfile(user.getName(), user.getLastName(), user.getEmail(), user.getId());
            model.addAttribute("message", messageSource.getMessage("profilEdit.success", null, locale));
            returnPage = "afteredit";
        }
        return returnPage;
    }


}
