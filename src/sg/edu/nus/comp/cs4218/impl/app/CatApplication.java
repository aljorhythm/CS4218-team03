package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.CatInterface;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.StringJoiner;

import static sg.edu.nus.comp.cs4218.impl.ShellImpl.MISSING_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class CatApplication implements CatInterface {
    public static final String ERR_IS_DIR = "This is a directory";
    public static final String ERR_READING_FILE = "Could not read file";
    public static final String ERR_READ_STREAM = "Could not read stream";
    public static final String ERR_WRITE_STREAM = "Could not write to output stream";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_INVALID_FILE = "No such file.";
    public static final String ERR_GENERAL = "Exception Caught";

    /**
     * Runs the cat application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     *
     * @throws CatException If the file(s) specified do not exist or are unreadable.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws CatException {
        boolean isEmptyArgs = checkCanRun(args, stdin, stdout);

        try {
            if (isEmptyArgs) {
                stdout.write(catStdin(stdin).getBytes());
            } else {
                stdout.write(catFiles(args).getBytes());
            }
        } catch (IOException ioe) {
            throw new CatException(ioe, ERR_WRITE_STREAM);
        } catch (CatException ce) {
            throw ce;
        } catch (Exception e) {
            throw new CatException(e, ERR_GENERAL);
        }
    }

    /**
     * Checks if able to run CatApplication successfully
     * @param args supplied by user
     * @param stdin input stream to read from
     * @param stdout output stream to write to
     * @return true if all arguments are empty, false otherwise
     * @throws CatException if stdout is null or stdin is null and all arguments are empty
     */
    private boolean checkCanRun(String[] args, InputStream stdin, OutputStream stdout) throws CatException {
        if (stdout == null) {
            throw new CatException(ERR_NULL_STREAMS);
        }

        boolean isEmptyArgs = true;

        if (args != null) {
            for (String arg : args) {
                if (arg == null) {
                    break;
                }
                if (!StringUtils.isBlank(arg)) {
                    isEmptyArgs = false;
                    break;
                }
            }
        }

        if (stdin == null && isEmptyArgs)
        {
            throw new CatException(MISSING_STREAM);
        }
        return isEmptyArgs;
    }

    @Override
    public String catFiles(String... fileName) throws CatException {
        StringJoiner result = new StringJoiner(STRING_NEWLINE);
        for (String fn : fileName) {
            if (fn == null) {
                throw new CatException(ERR_GENERAL);
            }
            File file = new File(fn);
            if (!file.exists()) {
                result.add(String.format("cat: %s: " + ERR_INVALID_FILE, fn));
                continue;
            }
            if (file.isDirectory()) {
                result.add(String.format("cat: %s: " + ERR_IS_DIR, fn));
                continue;
            }

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fn));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.add(line);
                }
                bufferedReader.close();
            } catch (IOException ioe) {
                throw new CatException(ioe, ERR_READING_FILE);
            }
        }
        result.add("");
        return result.toString();
    }

    @Override
    public String catStdin(InputStream stdin) throws Exception {
        if (stdin == null)
        {
            throw new Exception(ERR_NULL_STREAMS);
        }

        StringJoiner stringJoiner = new StringJoiner(STRING_NEWLINE);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdin));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringJoiner.add(line);
            }
            bufferedReader.close();
        } catch (IOException ioe) {
            throw new CatException(ioe, ERR_READ_STREAM);
        }
        stringJoiner.add("");
        return stringJoiner.toString();
    }
}
