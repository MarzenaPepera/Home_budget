package pl.rafalmiskiewicz.BUDGET.utilities;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppdemoUtils {

    public static boolean checkEmailOrPassword(String pattern, String pStr) {

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(pStr);
        return m.matches();
    }

    public static boolean checkDate(String pattern, Date date) {

       // Pattern p = Pattern.compile(pattern);
       // Matcher m = p.matcher(date);
        //return m.matches();
        //ToDo zrobić sprawdzenie daty
        return true;
    }

}
