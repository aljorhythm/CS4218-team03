package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.DateInterface;
import sg.edu.nus.comp.cs4218.exception.DateException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateApplication implements DateInterface {

    public void initMap(HashMap<String,String> dateStr){
        Date date = new Date();
        // Should specify Locale.US (or whatever)
        DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.ENGLISH);
        String formatDate = sdf.format(date);
        dateStr.put("%y",formatDate.substring(6,10));
        dateStr.put("%m",formatDate.substring(0,2));
        dateStr.put("%d",formatDate.substring(3,5));
        dateStr.put("%H",formatDate.substring(11,13));
        dateStr.put("%M",formatDate.substring(14,16));
        dateStr.put("%S",formatDate.substring(17));
    }

    /**
     *
     * @param format String of user-defined format
     * @return
     * @throws DateException
     */
    @Override
    public String getDate(String format) throws DateException {
        String formatStr = format;
        if(formatStr == null){
            throw new DateException("Null format");
        }
        HashMap<String,String> dateStr = new HashMap<>();
        initMap(dateStr);
        String formatDate = null;
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Environment.LOCALE);
        //This is the default version.
        if(formatStr.length() == 0){
            formatDate = sdf.format(date);
        }
        else{
            if(formatStr.charAt(0)=='+'){
                formatStr = formatStr.substring(1);
                check(formatStr,dateStr);
            } else {
                throw new DateException("Invalid format. Date format must start with '+'");
            }
            for(String entry : dateStr.keySet()){
                formatStr = formatStr.replaceAll(entry, dateStr.get(entry));
            }
            formatDate = formatStr;
        }
        return formatDate;
    }

    public static void check(String argument, Map<String,String> dataStr) throws DateException {
        // Empty argument: default date format
        if (argument.length() == 0) {
            return;
        }

        // Format arguments provided in SimpleDateFormat
        for(int iter = 0; iter < argument.length(); iter++) {
            char character = argument.charAt(iter);
            // If argument starts with '%', treat it as a tag.
            if (character == '%') {
                String arg = "" + character + argument.charAt(iter + 1);
                if (!dataStr.containsKey(arg)) {
                    throw new DateException("Invalid format.");
                }
                iter++;
            }
        }
    }

    /**
     * Runs the date application with the specified arguments.
     *
     * @param args   Array of arguments for the application.It may contain the format.
     * @param stdin  An InputStream, not used.
     * @param stdout An OutputStream, not used.
     *
     * @throws DateException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws DateException {
        if (args == null || stdout == null) {
            throw new DateException("Null Pointer Exception");
        }
        if(args.length > 1){
            throw new DateException("Invalid syntax.");
        }
        String format = (args.length == 0) ? "" : args[0];
        try {
            stdout.write((getDate(format)).getBytes());
        } catch (IOException e) {
            throw (DateException) new DateException("Could not write to output stream").initCause(e);
        }
    }
}
