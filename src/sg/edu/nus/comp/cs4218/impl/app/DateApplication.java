package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.DateInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Joakim Boman on 2019-02-17.
 */
public class DateApplication implements DateInterface  {


    @Override
    public String getDate(String format) throws Exception {
        return "";
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {

    }
}
