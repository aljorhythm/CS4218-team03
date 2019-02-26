package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.DateInterface;
import sg.edu.nus.comp.cs4218.exception.DateException;
import sg.edu.nus.comp.cs4218.exception.EchoException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class DateApplication implements DateInterface {
    @Override
    public String getDate(String format) throws DateException {
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        if(format == null){
            String str_time = time.toString();
            return str_time;
        }
        return null;
    }

    /**
     * Runs the cd application with the specified arguments.
     * Assumption: The application must take in one arg. (cd without args is not supported)
     *
     * @param args   Array of arguments for the application.It may contain the format.
     * @param stdin  An InputStream, not used.
     * @param stdout An OutputStream, not used.
     *
     * @throws DateException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws DateException {
        if(args.length < 1 || args.length > 2 || !args[0].equals("date")){
            throw new DateException("Invalid syntax.");
        }
        String format = (args.length == 1) ? null : args[1];
        try {
            stdout.write((getDate(format)+"\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
