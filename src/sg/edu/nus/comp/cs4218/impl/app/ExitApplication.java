package sg.edu.nus.comp.cs4218.impl.app;

import java.io.InputStream;
import java.io.OutputStream;

import sg.edu.nus.comp.cs4218.app.ExitInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ExitException;

public class ExitApplication implements ExitInterface {
    public static final String ERR_NUM_ARGS = "Incorrect number of arguments";
    public static final String ERR_NULL_ARGS = "Argument is null";

    @Override
    public void terminateExecution() {
        System.exit(0);
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws ExitException {
        if (args == null) {
            throw new ExitException(ERR_NULL_ARGS);
        }

        if (args.length != 0) {
            throw new ExitException(ERR_NUM_ARGS);
        }

        terminateExecution();
    }
}
