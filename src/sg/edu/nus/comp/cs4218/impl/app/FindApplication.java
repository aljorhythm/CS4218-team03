package sg.edu.nus.comp.cs4218.impl.app;


import sg.edu.nus.comp.cs4218.app.FindInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.FindException;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class is part of EF2, so only tests are provided and no implementation.
 */
public class FindApplication implements FindInterface {


    @Override
    public String findFolderContent(String fileName, String... folderName) throws FindException {
        return null;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws FindException {

    }
}
