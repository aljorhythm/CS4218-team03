package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.DateInterface;
import sg.edu.nus.comp.cs4218.exception.DateException;
import sg.edu.nus.comp.cs4218.exception.EchoException;

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
        System.out.println(Arrays.toString(args));
        System.out.println(getDate(null));
    }
}
