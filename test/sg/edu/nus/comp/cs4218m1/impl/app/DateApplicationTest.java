package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.impl.app.DateApplication;
import sg.edu.nus.comp.cs4218.exception.DateException;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateApplicationTest {
    DateApplication dateApp;
    Pattern fullDatePattern = Pattern.compile("^(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])\\-\\d{4} \\d{2}:\\d{2}:\\d{2}$");
    Pattern monthDayYear = Pattern.compile("^(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])\\-\\d{4}$");
    Pattern hourMinuteSecond = Pattern.compile("^\\d{2} \\d{2} \\d{2}$");
    Pattern customDate = Pattern.compile("^year \\d{4}$");

    @BeforeEach
    void setUp() {
        dateApp = new DateApplication();
    }

    @Test
    void testRegexPatternsSuccess() {
        assertTrue(Pattern.matches(fullDatePattern.toString(), "01-21-2019 13:23:23"));
        assertTrue(Pattern.matches(monthDayYear.toString(), "01-21-2019"));
        assertTrue(Pattern.matches(hourMinuteSecond.toString(), "13 23 23"));
        assertTrue(Pattern.matches(customDate.toString(), "year 2019"));
    }

    @Test
    void testGetDateDefaultInputSuccess() throws Exception {
        assertTrue(Pattern.matches(fullDatePattern.toString(), dateApp.getDate("")));
    }

    @Test
    void testGetDateMonthDayYearInputSuccess() throws Exception {
        assertTrue(Pattern.matches(monthDayYear.toString(), dateApp.getDate("+%m-%d-%y")));
    }

    @Test
    void testGetDateHourMinuteSecondInputSuccess() throws Exception {
        assertTrue(Pattern.matches(hourMinuteSecond.toString(), dateApp.getDate("+%H %M %S")));
    }

    @Test
    void testGetDateNullInputFailure() throws Exception {
        assertThrows(DateException.class, () -> {dateApp.getDate(null);});
    }

    @Test
    void testGetDateExtraStringInputSuccess() throws Exception {
        assertTrue(Pattern.matches(customDate.toString(), dateApp.getDate("+year %y")));
    }
}