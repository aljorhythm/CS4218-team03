package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.DateInterface;
import sg.edu.nus.comp.cs4218.exception.DateException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DateApplication implements DateInterface {

    public void initMap(HashMap<String,String> date_str){
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        String formatDate = sdf.format(date);
        date_str.put("%y",formatDate.substring(6,10));
        date_str.put("%m",formatDate.substring(0,2));
        date_str.put("%d",formatDate.substring(3,5));
        date_str.put("%H",formatDate.substring(11,13));
        date_str.put("%M",formatDate.substring(14,16));
        date_str.put("%S",formatDate.substring(17));
    }

    @Override
    public String getDate(String format) throws DateException {
        if(format == null){
            throw new DateException("Null format");
        }
        HashMap<String,String> date_str = new HashMap<>();
        initMap(date_str);
        String formatDate = null;
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        //This is the default version.
        if(format.length() == 0){
            formatDate = sdf.format(date);
        }
        else{
            if(format.charAt(0)=='+'){
                format = format.substring(1);
            }
            for(String entry : date_str.keySet()){
                format = format.replaceAll(entry, date_str.get(entry));
            }
            formatDate = format;
        }
        return formatDate;
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
        if(args.length > 1){
            throw new DateException("Invalid syntax.");
        }
        String format = (args.length == 0) ? "" : args[0];
        try {
            stdout.write((getDate(format)+"\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
