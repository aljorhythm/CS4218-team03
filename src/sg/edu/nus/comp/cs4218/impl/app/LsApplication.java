package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.LsInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;

import java.io.InputStream;
import java.io.OutputStream;

public class LsApplication implements LsInterface {
    @Override
    public String listFolderContent(Boolean isFoldersOnly, Boolean isRecursive, String... folderName) throws Exception {
        return null;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {

    }
}
