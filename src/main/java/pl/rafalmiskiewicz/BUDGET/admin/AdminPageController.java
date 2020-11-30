package pl.rafalmiskiewicz.BUDGET.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rafalmiskiewicz.BUDGET.transactions.Transaction;
import pl.rafalmiskiewicz.BUDGET.transactions.TransactionService;
import pl.rafalmiskiewicz.BUDGET.user.User;
import pl.rafalmiskiewicz.BUDGET.user.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class AdminPageController {

    private static int ELEMENTS = 10;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;


    @GET
    @RequestMapping(value = "/admin/users/{page}")
    @Secured(value = {"ROLE_ADMIN"})
    public String openAdminAllUsersPage(@PathVariable("page") int page, Model model) {
        Page<User> pages = getAllUsersPageable(page - 1);
        int totalPages = pages.getTotalPages();
        int currentPage = pages.getNumber();
        List<User> userList = pages.getContent();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("userList", userList);
        model.addAttribute("recordStartCounter", currentPage * ELEMENTS);
        return "admin/users";
    }

    @GET
    @RequestMapping(value = "/admin/users/edit/{id}")
    @Secured(value = {"ROLE_ADMIN"})
    public String getUserToEdit(@PathVariable("id") int id, Model model) {

        User user = new User();
        user = adminService.findUserById(id);

        Map<Integer, String> roleMap = new HashMap<Integer, String>();
        roleMap = prepareRoleMap();

        Map<Integer, String> activityMap = new HashMap<Integer, String>();
        activityMap = prepareActivityMap();

        Map<Integer, String> isFiredMap = new HashMap<Integer, String>();
        isFiredMap = prepareFiredMap();



        int rola = user.getRoles().iterator().next().getId();
        user.setNrRoli(rola);

        model.addAttribute("roleMap", roleMap);
        model.addAttribute("activityMap", activityMap);
        model.addAttribute("isFiredMap", isFiredMap);
        model.addAttribute("user", user);

        return "admin/useredit";
    }

    @POST
    @RequestMapping(value = "/admin/updateuser/{id}")
    @Secured(value = "ROLE_ADMIN")
    public String updateUser(@PathVariable("id") int id, User user) {
        int nrRoli = user.getNrRoli();
        int czyActive = user.getActive();
        boolean is_fired = user.getIs_fired();
        adminService.updateUser(id, nrRoli, czyActive,is_fired);
        return "redirect:/admin/users/1";
    }


    @POST
    @RequestMapping(value = "/admin/hour")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String openHourNewMainPage(Model model) {
        List<Transaction> transactionList = transactionService.findAll();
        for (Transaction h: transactionList) {
            matchUsers(h);
        }
        model.addAttribute("hourList", transactionList);
        model.addAttribute(new Transaction());
        return "admin/hour";
    }


    // Pobranie listy userów
    private Page<User> getAllUsersPageable(int page) {
        Page<User> pages = adminService.findAll(PageRequest.of(page, ELEMENTS));
        for (User users : pages) {
            int numerRoli = users.getRoles().iterator().next().getId();
            users.setNrRoli(numerRoli);
        }
        return pages;
    }

    // przygotowanie mapy ról
    public Map<Integer, String> prepareRoleMap() {
        Locale locale = Locale.getDefault();
        Map<Integer, String> roleMap = new HashMap<Integer, String>();
        roleMap.put(1, messageSource.getMessage("word.admin", null, locale));
        roleMap.put(2, messageSource.getMessage("word.user", null, locale));
        roleMap.put(3, messageSource.getMessage("word.controller", null, locale));
        return roleMap;
    }

    // przygotowanie may aktywny/nieaktywny
    public Map<Integer, String> prepareActivityMap() {
        Locale locale = Locale.getDefault();
        Map<Integer, String> activityMap = new HashMap<Integer, String>();
        activityMap.put(0, messageSource.getMessage("word.nie", null, locale));
        activityMap.put(1, messageSource.getMessage("word.tak", null, locale));
        return activityMap;
    }

    private Map<Integer, String> prepareFiredMap() {
        Locale locale = Locale.getDefault();
        Map<Integer, String> isFiredMap = new HashMap<Integer, String>();
        isFiredMap.put(0, messageSource.getMessage("word.zatrudniony", null, locale));
        isFiredMap.put(1, messageSource.getMessage("word.zwolniony", null, locale));
        return isFiredMap;
    }

    void matchUsers(Transaction transaction){
        transaction.setUser(userService.findUserById(transaction.getUser().getId()));
    }

}
