package sg.edu.nus.comp.cs4218.impl.app;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sg.edu.nus.comp.cs4218.app.DateInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.DateException;

@SuppressWarnings({"PMD.ShortVariable", "PMD.ExcessiveMethodLength"})
public class DateApplication implements DateInterface {
    public final static String DAY = "%d";
    public final static String MONTH = "%m";
    public final static String YEAR = "%y";
    public final static String HOUR = "%H";
    public final static String MINUTE = "%M";
    public final static String SECOND = "%S";

    public final static String DAY_FORMATTED = "dd";
    public final static String MONTH_FORMATTED = "MM";
    public final static String YEAR_FORMATTED = "yyyy";
    public final static String HOUR_FORMATTED = "HH";
    public final static String MINUTE_FORMATTED = "mm";
    public final static String SECOND_FORMATTED = "ss";

    public static final String ERR_NUM_ARGS = "Incorrect number of arguments";
    public static final String ERR_MISSING_PLUS = "+ is missing";
    public static final String ERR_INVALID_ARG = "Invalid argument";
    public static final String ERR_NULL_ARGS = "Argument is null";
    public static final String ERR_NULL_STDOUT = "Output Stream is null";

    public static final String ONLY_PREFIX_ARG = "Only + in argument";

    @Override
    public String getDate(String format) {

        // If + is the only argument, only newline is printed.
        if (format.equals(ONLY_PREFIX_ARG)) {
            return "";
        }

        Date date = new Date();
        DateFormat formatter;
        if (format.isEmpty()) {
            formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        } else {
            formatter = new SimpleDateFormat(format);
        }
        return formatter.format(date);
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws DateException {
        if (args == null) {
            throw new DateException(ERR_NULL_ARGS);
        }

        if (stdout == null) {
            throw new DateException(ERR_NULL_STDOUT);
        }

        String res = "";

        if (args.length == 0) {
            //No additional arguments provided: use default date format
            res = getDate("");
        } else if (args.length == 1) {
            // Correct number of arguments provided: 1
            res = getDate(getFormattedArgument(args[0]));
        } else {
            // Too many arguments provided: throw exception
            throw new DateException(ERR_NUM_ARGS);
        }

        PrintWriter out = new PrintWriter(stdout);
        out.println(res);
        out.flush();
}

    public static String getFormattedArgument(String argument) throws DateException {
        // Empty argument: default date format
        if (argument.length() == 0) {
            return "";
        }

        //Missing or invalid prefix: throw exception
        if (argument.charAt(0) != '+') {
            throw new DateException(ERR_MISSING_PLUS);
        }

        // Argument contains only prefix
        if (argument.length() == 1) {
            return ONLY_PREFIX_ARG;
        }

        StringBuilder res = new StringBuilder();

        // Format arguments provided in SimpleDateFormat
        for(int i = 1; i < argument.length(); i++) {
            char c = argument.charAt(i);
            // If argument starts with '%', treat it as a tag.
            if (c == '%') {
                String arg = "" + c + argument.charAt(i + 1);
                switch (arg) {
                    case DAY:
                        res.append(DAY_FORMATTED);
                        break;
                    case MONTH:
                        res.append(MONTH_FORMATTED);
                        break;
                    case YEAR:
                        res.append(YEAR_FORMATTED);
                        break;
                    case HOUR:
                        res.append(HOUR_FORMATTED);
                        break;
                    case MINUTE:
                        res.append(MINUTE_FORMATTED);
                        break;
                    case SECOND:
                        res.append(SECOND_FORMATTED);
                        break;
                    default:
                        throw new DateException(arg + ": " + ERR_INVALID_ARG);
                }
                i++;
            } else {
                // If argument does not start with '%', do not treat it as a tag. Treat it as a string literal.
                res.append('\'');
                while (argument.charAt(i) != '%' && i < argument.length() - 1) {
                    res.append(argument.charAt(i));
                    i++;
                }
                if (i == argument.length() - 1) {
                    res.append(argument.charAt(i));
                    res.append('\'');
                } else {
                    res.append('\'');
                    i--;
                }
            }
        }


        return res.toString();
    }
}
