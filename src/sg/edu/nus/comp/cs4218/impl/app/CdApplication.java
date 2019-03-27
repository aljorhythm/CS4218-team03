package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.CdInterface;
import sg.edu.nus.comp.cs4218.exception.CdException;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.StringJoiner;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_CURR_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_PARENT_DIR;

public class CdApplication implements CdInterface {
    public static final String ERR_IS_NOT_DIR = "Not a directory: %s";
    public static final String ERR_NO_SUCH_DIR = "%s: No such file or directory";
    public static final String ERR_NO_ARGS = "Insufficient arguments";
    public static final String ERR_NULL_ARGS = "Null arguments";
    public static final String ERR_TOO_MANY_ARGS = "Too many arguments";
    public static final String ESCAPED_PATH = "\\" + CHAR_FILE_SEP;


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
        changeToDirectory(getPathFromCdArguments(args));
    }

    @Override
    public void changeToDirectory(String path) throws CdException {
        String home = System.getProperty("user.home").trim();
        String newPath = Environment.currentDirectory.trim();
        String convertedPath = replacePathIdentifier(path, ESCAPED_PATH);
        newPath = replacePathIdentifier(newPath, ESCAPED_PATH);

        // Check for home
        if (convertedPath.charAt(0) == '~'){
            newPath = home + convertedPath.substring(1);
        } else if (convertedPath.length()>=home.length() && convertedPath.substring(0, home.length()).trim().equals(home)) {
            newPath = convertedPath;
        } else if (convertedPath.length()>=newPath.length() && convertedPath.substring(0, newPath.length()).trim().equals(newPath)) {
            newPath = convertedPath;
        } else
        {
            newPath = newPath + CHAR_FILE_SEP + convertedPath;
        }

        // Remove '..' or '.' in path
        ArrayList<String> normalizedPath = new ArrayList<String>();
        String[] myPath = newPath.split(ESCAPED_PATH);
        for (int i = 0; i < myPath.length; i++)
        {
            if (myPath[i].equals(STRING_PARENT_DIR))
            {
                normalizedPath.remove(normalizedPath.size() - 1);
            } else if (!myPath[i].equals(STRING_CURR_DIR))
            {
                normalizedPath.add(myPath[i]);
            }
        }
        StringJoiner stringJoiner = new StringJoiner(Character.toString(CHAR_FILE_SEP));
        for (String str : normalizedPath)
        {
            stringJoiner.add(str);
        }
        newPath = stringJoiner.toString();

        File folder = new File(newPath);
        if (!folder.exists()) {
            throw new CdException(String.format(ERR_NO_SUCH_DIR, convertedPath));
        }
        if (!folder.isDirectory()) {
            throw new CdException(String.format(ERR_IS_NOT_DIR, convertedPath));
        }

        Environment.currentDirectory = newPath;
    }

    /**
     * Checks if the number of arguments provided are correct
     * @param args arguments supplied by user
     * @return the folder name supplied by user if any
     * @throws CdException when no arguments or too many arguments
     */
    private String getPathFromCdArguments(String... args) throws CdException {
        switch (args.length) {
            case 0:
                throw new CdException(ERR_NO_ARGS);
            case 1:
                return args[0];
            default:
                throw new CdException(ERR_TOO_MANY_ARGS);
        }
    }

    /**
     * Replaces all '/' and '\' in given String with given iden
     * @param toReplace String to replace all '/' and '\'
     * @param iden String used to replace
     * @return String with replaced '/' and '\'
     */
    private String replacePathIdentifier(String toReplace, String iden) {
        String replacedPath = toReplace;
        replacedPath = replacedPath.replaceAll("\\\\", iden);
        replacedPath = replacedPath.replaceAll("/", iden);
        return replacedPath;
    }
}
