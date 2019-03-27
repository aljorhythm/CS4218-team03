package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.MkdirInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.MkdirException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;

public class MkdirApplication implements MkdirInterface {

    @Override
    public void createFolder(String... folderName) throws Exception {
        if (folderName == null || folderName.length == 0) {
            throw new MkdirException("Cannot create folder: Folder names not specified");
        }

        for (String folder: folderName) {
            try {
                Path currentDirectory = Paths.get(Environment.currentDirectory);
                Path folderPath = currentDirectory.resolve(folder);
                Files.createDirectory(folderPath);
            } catch (InvalidPathException e) {
                System.out.println("mkdir: Cannot create folder: " + Environment.currentDirectory + "/" + folder + " is an invalid path");
            } catch (FileAlreadyExistsException e) {
                System.out.println("mkdir: Cannot create folder: " + Environment.currentDirectory + "/" + folder + " already exists");
            } catch (IOException e) {
                System.out.println("mkdir: Cannot create " +folder + ": I/O error occurs or the parent directory does not exist");
            } catch (Exception e) {
                throw new Exception("Cannot create folder: An error occurs");
            }
        }
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if (args == null || args.length == 0) {
            throw new MkdirException("Cannot create folder: Folder names not specified");
        }

        try {
            createFolder(args);
        } catch (Exception e) {
            throw new MkdirException(e.getMessage());
        }
    }
}
