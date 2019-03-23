package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.CdInterface;
import sg.edu.nus.comp.cs4218.exception.CdException;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

import static sg.edu.nus.comp.cs4218.exception.CdException.*;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.resolveFilePath;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_FILE_SEP;

public class CdApplication implements CdInterface {

    /**
     * Runs the cd application with the specified arguments.
     * Assumption: The application must take in one arg. (cd without args is not supported)
     *
     * @param args   Array of arguments for the application.
     * @param stdin  An InputStream, not used.
     * @param stdout An OutputStream, not used.
     * @throws CdException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout)
            throws CdException {
        if (args == null) {
            throw new CdException(ERR_NULL_ARGS);
        }
        if (args.length == 0) {
            throw new CdException(ERR_NO_ARGS);
        } else if (args.length == 1) {
            changeToDirectory(args[0]);
        } else {
            throw new CdException(ERR_ARGS_LENGTH);
        }
    }

    @Override
    public void changeToDirectory(String pathArg) throws CdException {
        String path = pathArg;
        if (!path.endsWith(STRING_FILE_SEP)) {
            path += STRING_FILE_SEP;
        }
        Path pathObj = resolveFilePath(path);
        File newWorkingDir = pathObj.toFile();
        if (!newWorkingDir.exists()) {
            throw new CdException(String.format(ERR_NO_SUCH_DIR, pathObj.toAbsolutePath()));
        }
        if (!newWorkingDir.isDirectory()) {
            throw new CdException(ERR_IS_NOT_DIR);
        }
        Environment.currentDirectory = pathObj
                .normalize()
                .toString();
    }

}
