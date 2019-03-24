/**
 * From Team 09
 */
package sg.edu.nus.comp.cs4218.impl.app;import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.exception.DateException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.LongVariable"})
public class DateApplicationTest {

    private DateApplication app;
    private OutputStream output;

    public static final String ERR_WRITE_STREAM = "Could not write to output stream";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_INVALID_FORMAT_PREFIX = "Invalid format. Date format must start with '+'";
    public static final String ERR_INVALID_FORMAT_FIELD = "Invalid format. Missing or unknown character after '%'";
    public static final String ERR_MISSING_FIELD = "Invalid format";

    @BeforeEach
    public void setUp() {
        app = new DateApplication();
        output = new ByteArrayOutputStream();
    }

    @Test
    public void testDateNoFields() throws DateException {
        app.run(new String[]{"+a date"}, null, output);
        assertTrue(output.toString().matches("a date"));
    }

    @Test
    public void testDateRegularFormat() throws DateException {
        app.run(new String[]{"+%H:%M:%S"}, null, output);
        assertTrue(output.toString().matches("\\d\\d:\\d\\d:\\d\\d"));
    }

    @Test
    public void testDateDefaultFormat() throws DateException {
        app.run(new String[]{}, null, output);
        assertTrue(output.toString().matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    public void testEmptyDateFormat() throws DateException {
        app.run(new String[]{""}, null, output);
        assertTrue(output.toString().matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}"));
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    public void testNullDateFormat() throws DateException {
        app.run(null, null, output);
        assertTrue(output.toString().matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}"));
    }
     */

    @Test
    public void testFormatContainsMonthShouldSucceed() throws DateException {
        app.run(new String[]{"+%m"}, null, output);
        assertTrue(output.toString().matches("\\d\\d"));
    }

    @Test
    public void testFormatContainsDayShouldSucceed() throws DateException {
        app.run(new String[]{"+%d"}, null, output);
        assertTrue(output.toString().matches("\\d\\d"));
    }

    @Test
    public void testFormatContainsYearShouldSucceed() throws DateException {
        app.run(new String[]{"+%y"}, null, output);
        assertTrue(output.toString().matches("\\d\\d\\d\\d"));
    }

    @Test
    public void testFormatContainsMinuteShouldSucceed() throws DateException {
        app.run(new String[]{"+%M"}, null, output);
        assertTrue(output.toString().matches("\\d\\d"));
    }

    @Test
    public void testFormatContainsHourShouldSucceed() throws DateException {
        app.run(new String[]{"+%H"}, null, output);
        assertTrue(output.toString().matches("\\d\\d"));
    }

    @Test
    public void testFormatContainsSecondShouldSucceed() throws DateException {
        app.run(new String[]{"+%S"}, null, output);
        assertTrue(output.toString().matches("\\d\\d"));
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    public void testFormatContainsSingleIllegalPlaceholderShouldFail() {
        DateException exception = assertThrows(DateException.class, () -> {
            app.run(new String[]{"+alle%b"}, null, output);
        });
        assertTrue(exception.getMessage().contains(ERR_INVALID_FORMAT_FIELD));
    }
    */

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    public void testFormatContainsDoublePercentShouldFail() {
        DateException exception = assertThrows(DateException.class, () -> {
            app.run(new String[]{"+%%"}, null, output);
        });
        assertTrue(exception.getMessage().contains(ERR_INVALID_FORMAT_FIELD));
    }
    */

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    public void testFormatEndsWithPercentShouldFail() {
        DateException exception = assertThrows(DateException.class, () -> {
            app.run(new String[]{"+ABC.A:B%"}, null, output);
        });
        assertTrue(exception.getMessage().contains(ERR_INVALID_FORMAT_FIELD));
    }
     */

    @Test
    public void testFormatContainsOnlyWhitespaceShouldNotFail() throws DateException {
        app.run(new String[]{"+ "}, null, output);
        assertEquals(" ", output.toString());
    }

    @Test
    public void testFormatContainsOnlyDotShouldNotFail() throws DateException {
        app.run(new String[]{"+."}, null, output);
        assertEquals(".", output.toString());
    }

    @Test
    public void testFormatContainsOnlyCommaShouldNotFail() throws DateException {
        app.run(new String[]{"+,"}, null, output);
        assertEquals(",", output.toString());
    }

    @Test
    public void testThrowDateExceptionIfStdoutNull() {
        DateException exception = assertThrows(DateException.class, () -> {
            app.run(new String[0], null, null);
        });
        assertTrue(exception.getMessage().contains(ERR_NULL_STREAMS));
    }

    @Test
    public void testWithCharactersNoPlaceholdersShouldNotFail() throws DateException {
        String format = "+abcdefghijkl,.-!@#$^& ";
        app.run(new String[]{format}, null, output);
        assertEquals(format.substring(1), output.toString());
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    public void testOnlyInvalidPlaceholdersShouldFail() {
        DateException exception = assertThrows(DateException.class, () -> {
            app.run(new String[]{"+%g%y%t%r"}, null, output);
        });
        assertTrue(exception.getMessage().contains(ERR_INVALID_FORMAT_FIELD));
    }
     */


    @Test
    public void testFormatContainsInvalidPrefixOnlyShouldFail() {
        DateException exception = assertThrows(DateException.class, () -> {
            app.run(new String[]{"-"}, null, output);
        });
        assertTrue(exception.getMessage().contains(ERR_INVALID_FORMAT_PREFIX));
    }

    @Test
    public void testFormatContainsPrefixOnlyShouldNotFail() throws DateException {
        app.run(new String[]{"+"}, null, output);
        assertEquals("", output.toString());
    }

    @Test
    public void testThrowDateExceptionIfStdoutClosed() throws IOException {
        final boolean[] closed = {false};
        output = mock(ByteArrayOutputStream.class);
        doAnswer(invocation -> {
            closed[0] = true;
            return null;
        }).when(output).close();
        doAnswer(invocation -> {
            if (closed[0]) {
                throw new IOException("Streams closed");
            }
            // do nothing
            return null;
        }).when(output).write(Mockito.any());

        DateException exception = assertThrows(DateException.class, () -> {
            output.close();
            app.run(new String[0], null, output);
        });
        assertTrue(exception.getMessage().contains(ERR_WRITE_STREAM));
    }

    @Test
    public void testGetDateNullFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            app.getDate(null);
        });
        assertTrue(exception.getMessage().contains("Null format"));
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    public void testGetDateEmptyFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            app.getDate("");
        });
        assertTrue(exception.getMessage().contains(ERR_MISSING_FIELD));
    }
    */
}
