package pl.rafalmiskiewicz.BUDGET.hours;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class HourTest {


    @Test
    void divdedDateToString() throws ParseException {
        Hour hour = new Hour();
        hour.setOnlyDate_from_string("2020-01-21");
        hour.setOnlyHour_from_string("13:00");
        hour.setOnlyHour_to_string("13:30");
        Date date_from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-01-21 13:00:00");
        Date date_to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-01-21 13:30:00");

        hour.divdedDateToString();
        hour.stringToDate();
        assertEquals(date_from, hour.getHour_from());
        assertEquals(date_to, hour.getHour_to());
    }

}
