package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.CdInterface;
import sg.edu.nus.comp.cs4218.exception.CdException;

import java.io.InputStream;
import java.io.OutputStream;

public class CdApplication implements CdInterface {
    public static final String ERR_IS_NOT_DIR = "Not a directory: %s";
    public static final String ERR_NO_SUCH_DIR = "%s: No such file or directory";
    public static final String ERR_NO_ARGS = "No arguments";
    public static final String ERR_NULL_ARGS = "Null arguments";
    public static final String ERR_TOO_MANY_ARGS = "Too many arguments";

    /**
     * Runs the cd application with the specified arguments.
     * Assumption: The application must take in one arg. (cd without args is not supported)
     *
     * @param args   Array of arguments for the application.
     * @param stdin  An InputStream, not used.
     * @param stdout An OutputStream, not used.
     *
     * @throws CdException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout)
            throws CdException {

    }

    @Override
    public void changeToDirectory(String path) throws CdException {

    }

}
