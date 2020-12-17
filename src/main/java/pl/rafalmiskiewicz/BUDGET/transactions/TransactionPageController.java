package pl.rafalmiskiewicz.BUDGET.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rafalmiskiewicz.BUDGET.plan.Plan;
import pl.rafalmiskiewicz.BUDGET.plan.PlanService;
import pl.rafalmiskiewicz.BUDGET.user.UserService;
import pl.rafalmiskiewicz.BUDGET.utilities.UserUtilities;
import pl.rafalmiskiewicz.BUDGET.validators.TransactionAddValidator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Controller
public class TransactionPageController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    @POST
    @RequestMapping(value = "/transaction")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String openTransactionNewMainPage(Model model) {
        Date date = new Date();
        date.setDate(1);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(formatter.format(date));

        List<Transaction> transactionList = transactionService.findAllByUserId(userService.findUserByEmail(UserUtilities.getLoggedUser()).getId());
        Plan plan = planService.findPlanByDate(date);
        Double amount= transactionList.stream().mapToDouble(t ->t.getAmount()).sum();
        model.addAttribute("amount", amount);
        model.addAttribute("transactionList", transactionList);
        model.addAttribute("plan", plan);
        model.addAttribute("planAmount", plan.getAmount()-amount);

        model.addAttribute(new Transaction());
        return "transaction/transaction";
    }

    @GET
    @RequestMapping(value = "/transaction/edit")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String getTransactionIdToEditNew(Transaction transaction, Model model) {

        System.out.println(transaction);
        transaction =transactionService.findTransactionById(transaction.getId_transaction());
        try {
            transaction.dateToString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("transaction", transaction);

        return "transaction/edittransaction";
    }

    @GET
    @RequestMapping(value = "/transaction/addtransaction")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String addTransaction( Model model) {

        Transaction h = new Transaction();
        model.addAttribute("transaction", h);

        return "transaction/addtransaction";
    }

    @POST
    @RequestMapping(value = "/transaction/inserttransaction")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String registerTransaction(Transaction transaction, BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        try {
            transaction.stringToDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        transaction.setUser(userService.findUserByEmail(UserUtilities.getLoggedUser()));

        //new TransactionAddValidator().validate(transaction, result);

        if (!result.hasErrors()) {
            transactionService.saveTransaction(transaction);
            model.addAttribute("message", messageSource.getMessage("transaction.add.success", null, locale));
            model.addAttribute("transaction", new Transaction());
        }
        returnPage = "transaction/addtransaction";

        return returnPage;
    }


    @POST
    @RequestMapping(value = "/transaction/edit/updatetransaction")
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    public String editTransaction(Transaction transaction, BindingResult result, Model model, Locale locale) {
        String returnPage = null;
        try {
            transaction.stringToDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //new TransactionAddValidator().validate(transaction, result);

        if (!result.hasErrors()) {
            transactionService.updateTransaction(transaction);
            model.addAttribute("message", messageSource.getMessage("transaction.edit.success", null, locale));
            model.addAttribute("transaction", new Transaction());
        }
        returnPage = "transaction/edittransaction";

        return returnPage;


    }



}
