package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.DateInterface;
import sg.edu.nus.comp.cs4218.exception.DateException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

/**
 * Date Application
 */
public class DateApplication implements DateInterface {

    public static final String FORMAT_DEFAULT = "MM-dd-Y HH:mm:ss";
    private static final Pattern FORMAT_REGEX = Pattern
            .compile("^\\+(%(d|y|m|H|M|S)[^\\w\\d^%]*)+$");

    /**
     * Runs the date application with arguments.
     * Assumption: arguments can be empty or not
     *
     * @param args   format supplied by user, can be empty
     * @param stdin  An InputStream
     * @param stdout An OutputStream
     * @throws DateException
     */
    public void run(final String[] args, final InputStream stdin, final OutputStream stdout) throws DateException {
        String date;
        if (args == null || args.length == 0) {
            date = getDate(FORMAT_DEFAULT);
        } else {
            date = getDate(args[0]);
        }
        try {
            stdout.write(date.getBytes());
        } catch (IOException e) {
            throw new DateException(e, DateException.ERR_WRITE_STREAM);
        }
    }

    /**
     * Validate and reformat the format supplied by user, return it in the UNIX syntax
     * Assumption: the format is supplied as a string
     *
     * @param formatString format supplied by user
     * @throws DateException
     */
    private String validateFormatString(final String formatString) throws DateException {
        if (!isValidFormat(formatString)) {
            throw new DateException(DateException.INVALID_FORMAT);
        }
        return convertFormatToUNIXNotation(formatString.substring(1));
    }

    /**
     * convert the format supplied to UNIX notation
     *
     * @param formatString format supplied by user
     * @throws DateException
     */
    private String convertFormatToUNIXNotation(final String formatString) {
        return formatString.replace("%d", "dd")
                .replace("%m", "MM")
                .replace("%y", "Y")
                .replace("%H", "HH")
                .replace("%M", "mm")
                .replace("%S", "ss");
    }

    /**
     * check if the format supplied by user is valid
     *
     * @param formatString format supplied by user
     * @throws DateException
     */
    private boolean isValidFormat(final String formatString) {
        final Matcher matcher = FORMAT_REGEX.matcher(formatString);
        return matcher.find();
    }

    @Override
    public String getDate(final String formatString) throws DateException {
        if (formatString == null || formatString.isEmpty()) {
            throw new DateException(DateException.INVALID_FORMAT);
        }
        String customFS = formatString;
        if (!formatString.equals(FORMAT_DEFAULT)) {
            customFS = validateFormatString(formatString);
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat(customFS, Locale.ENGLISH);
        final Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime()) + STRING_NEWLINE;
    }
}