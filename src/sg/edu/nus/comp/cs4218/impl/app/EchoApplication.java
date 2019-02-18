package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.EchoInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.EchoException;

import java.io.InputStream;
import java.io.OutputStream;

public class EchoApplication implements EchoInterface {
    @Override
    public String constructResult(String... args) throws Exception {
        return null;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws EchoException {

    }
}
