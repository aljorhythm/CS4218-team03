package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.CdInterface;
import sg.edu.nus.comp.cs4218.exception.CdException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressWarnings("PMD.ShortVariable")
public class CdApplication implements CdInterface {
    public static final String ERR_IS_NOT_DIR = "Not a directory: %s";
    public static final String ERR_NO_SUCH_DIR = "%s: No such file or directory";
    public static final String ERR_NO_ARGS = "No arguments";
    public static final String ERR_NULL_ARGS = "Null arguments";
    public static final String ERR_TOO_MANY_ARGS = "Too many arguments";
    public static final String ERR_NULL_STDOUT = "Output Stream is null";

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

        if (args == null) {
            throw new CdException(ERR_NULL_ARGS);
        }

        if (stdout == null) {
            throw new CdException(ERR_NULL_STDOUT);
        }

        if (args.length == 0) {
            throw new CdException(ERR_NO_ARGS);
        }

        if (args.length > 1) {
            throw new CdException(ERR_TOO_MANY_ARGS);
        }


        String path = args[0];

        try {
            changeToDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeToDirectory(String path) throws CdException, IOException {
        String currentDirectory = Environment.getCurrentDirectory();
        File f = new File(path);

        // Relative path
        if (!f.isAbsolute()) {
            f = new File(currentDirectory, path);
        }

        // Invalid path - does not exist
        if (!f.exists()) {
            throw new CdException(ERR_NO_SUCH_DIR);
        }

        // Valid path but not a directory
        if (f.exists() && !f.isDirectory()) {
            throw new CdException(ERR_IS_NOT_DIR);
        }

        Environment.setCurrentDirectory(f.getCanonicalPath());
    }
}
