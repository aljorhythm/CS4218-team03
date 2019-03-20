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
    Pattern monthDayYearWithDashPattern = Pattern.compile("^(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])\\-\\d{4}$");
    Pattern hourMinuteSecondWhiteSpacePattern = Pattern.compile("^\\d{2} \\d{2} \\d{2}$");
    Pattern customStringDateCombinationPattern = Pattern.compile("^year \\d{4}$");

    @BeforeEach
    void setUp() {
        dateApp = new DateApplication();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testRegexPatterns_Success() {
        assertTrue(Pattern.matches(fullDatePattern.toString(), "01-21-2019 13:23:23"));
        assertTrue(Pattern.matches(monthDayYearWithDashPattern.toString(), "01-21-2019"));
        assertTrue(Pattern.matches(hourMinuteSecondWhiteSpacePattern.toString(), "13 23 23"));
        assertTrue(Pattern.matches(customStringDateCombinationPattern.toString(), "year 2019"));
    }

    @Test
    void testGetDate_DefaultInput_Success() throws Exception {
        assertTrue(Pattern.matches(fullDatePattern.toString(), dateApp.getDate("")));
    }

    @Test
    void testGetDate_MonthDayYearInput_Success() throws Exception {
        assertTrue(Pattern.matches(monthDayYearWithDashPattern.toString(), dateApp.getDate("+%m-%d-%y")));
    }

    @Test
    void testGetDate_HourMinuteSecondInput_Success() throws Exception {
        assertTrue(Pattern.matches(hourMinuteSecondWhiteSpacePattern.toString(), dateApp.getDate("+%H %M %S")));
    }

    @Test
    void testGetDate_NullInput_Failure() throws Exception {
        assertThrows(DateException.class, () -> {dateApp.getDate(null);});
    }

    @Test
    void testGetDate_ExtraStringInput_Success() throws Exception {
        assertTrue(Pattern.matches(customStringDateCombinationPattern.toString(), dateApp.getDate("+year %y")));
    }
}