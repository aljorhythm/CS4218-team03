package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.DateInterface;
import sg.edu.nus.comp.cs4218.exception.DateException;
import sg.edu.nus.comp.cs4218.exception.EchoException;

import java.io.InputStream;
import java.io.OutputStream;

public class DateApplication implements DateInterface {
    @Override
    public String getDate(String format) throws DateException {
        return null;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws DateException {

    }
}
