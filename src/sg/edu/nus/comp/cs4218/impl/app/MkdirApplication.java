package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.MkdirInterface;
import sg.edu.nus.comp.cs4218.exception.MkdirException;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class MkdirApplication implements MkdirInterface {

    /**
     * Create folder from the given folder names. Do nothing if folder already exists.
     * If the path before the folder names does not exist raise an exception.
     *
     * @param folderName Array of string of folder names to be created
     *                   
     * @throws MkdirException If input or folderName is null. Also, if path does not exist.
     */
    @Override
    public void createFolder(String... folderName) throws MkdirException {
        if (folderName == null) {
            throw new MkdirException("Input is null!");
        }
        File file;
        for(int i = 0; i < folderName.length; i++) {
            if (folderName[i] == null) {
                throw new MkdirException("Dir name is null!");
            }
            if (folderName[i].equals("")) {
                continue;
            }
            file = new File(folderName[i]);
            if (!file.exists() && !file.mkdir()) {
                throw new MkdirException("Path does not exist!");
            }
        }
    }

    /**
     * Runs the mkdir application with the specified arguments.
     *
     * @param args   Array of arguments for the application, the path to those new files.
     * @param stdin  An InputStream. Not used.
     * @param stdout An OutputStream. Not used.
     *
     * @throws MkdirException If the shell doesn't work.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws MkdirException {
        createFolder(args);
    }
}
