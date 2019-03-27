package sg.edu.nus.comp.cs4218.impl.app;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.PwdInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.PwdException;

public class PwdApplication implements PwdInterface {
    public static final String ERR_NUM_ARGS = "Incorrect number of arguments";
    public static final String ERR_NULL_ARGS = "Argument is null";
    public static final String ERR_NULL_STDOUT = "OutputStream is null";

    @Override
    public String getAbsolutePath() {
        return Environment.currentDirectory;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws PwdException {
        if (args == null) {
            throw new PwdException(ERR_NULL_ARGS);
        }

        if (stdout == null) {
            throw new PwdException(ERR_NULL_STDOUT);
        }

        if (args.length != 0) {
            throw new PwdException(ERR_NUM_ARGS);
        }

        PrintWriter out = new PrintWriter(stdout);
        out.println(getAbsolutePath());
        out.flush();
    }
}
