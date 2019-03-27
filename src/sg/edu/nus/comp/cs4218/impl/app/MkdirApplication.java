package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.MkdirInterface;
import sg.edu.nus.comp.cs4218.exception.MkdirException;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.StringJoiner;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_SPACE;

public class MkdirApplication implements MkdirInterface {
    public static final String ERR_NO_ARGS = "No arguments";
    public static final String ERR_NULL_ARGS = "Null arguments";
    public static final String ERR_CREATE_DIR = "Cannot create directory(s)";

    @Override
    public void createFolder(String... folderName) throws MkdirException {
        ArrayList<String> errFolderNames = new ArrayList<String>();

        if (folderName == null)
        {
            throw new MkdirException(ERR_NULL_ARGS);
        } else if (folderName.length == 0) {
            throw new MkdirException(ERR_NO_ARGS);
        }

        for (String f : folderName) {
            if (f == null)
            {
                throw new MkdirException(ERR_NULL_ARGS);
            }
            File file = new File(f);
            boolean isCreated = file.mkdir();
            if (!isCreated) {
                errFolderNames.add(f);
            }
        }

        if (!errFolderNames.isEmpty()) {
            String errorMessage = formatErrorMessage(errFolderNames);
            throw new MkdirException(errorMessage);
        }
    }

    /**
     * Runs the mkdir application with the specified arguments. Creates all folders that have valid paths.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               folder.
     * @param stdin  An InputStream.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     *
     * @throws MkdirException If (1) no folders are specified or
     *                           (2) there are folders with invalid paths or
     *                           (3) there is already an existing folder in that path.
     *                        For (2), if multiple folder names are supplied, folders with valid paths are still created.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws MkdirException {
        createFolder(args);
    }

    /**
     * Formats a list of invalid folder names with error messages.
     * @param errFolderNames list of invalid folder names
     * @return an error message containing all folders that are unable to be created
     */
    private String formatErrorMessage(ArrayList<String> errFolderNames) {
        StringJoiner errMsgJoiner = new StringJoiner(Character.toString(CHAR_SPACE));
        for (String f : errFolderNames) {
            errMsgJoiner.add(f);
        }
        return ERR_CREATE_DIR + ": " + errMsgJoiner.toString();
    }
}
