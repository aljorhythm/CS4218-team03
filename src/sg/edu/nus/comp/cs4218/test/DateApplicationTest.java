package sg.edu.nus.comp.cs4218.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.DateException;
import sg.edu.nus.comp.cs4218.impl.app.DateApplication;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Joakim Boman on 2019-02-17.
 */
class DateApplicationTest {
    DateApplication dateApp;

    @BeforeEach
    void setUp() {
        dateApp = new DateApplication();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testGetDate_DefaultInput_Success() throws Exception {
        assertTrue(dateApp.getDate("").matches("^(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])\\-\\d{4} \\d{2}:\\d{2}:\\d{2}$"));
    }

    @Test
    void testGetDate_MonthDayYearInput_Success() throws Exception {
        assertTrue(dateApp.getDate("%m-%d-%y").matches("^(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])\\-\\d{4}$"));
    }

    @Test
    void testGetDate_HourMinuteSecondInput_Success() throws Exception {
        assertTrue(dateApp.getDate("%H %M %S").matches("^\\d{2} \\d{2} \\d{2}$"));
    }

    @Test
    void testGetDate_NoPercentageInFormatInput_Failure() throws Exception {
        assertThrows(DateException.class, () -> {dateApp.getDate("H M S");});
    }

    @Test
    void testGetDate_UnknownLetterInput_Failure() throws Exception {
        assertThrows(DateException.class, () -> {dateApp.getDate("%A");});
    }
}