package sg.edu.nus.comp.cs4218.impl.app;


import sg.edu.nus.comp.cs4218.app.FindInterface;
import sg.edu.nus.comp.cs4218.exception.FindException;

import java.io.InputStream;
import java.io.OutputStream;

public class FindApplication implements FindInterface {

    /**
     * Return the string listing the names of the matched file/folder in the specified folder.
     *
     * @param fileName   String of a regular expression of the file name
     * @param folderName Array of String of given folder/folders' name
     * @throws FindException
     */
    @Override
    public String findFolderContent(String fileName, String... folderName) throws FindException {
        return null;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws FindException {

    }
}
