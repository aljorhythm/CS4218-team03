package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.MkdirInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.MkdirException;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class MkdirApplication implements MkdirInterface {
    @Override
    public void createFolder(String... folderName) throws MkdirException {
        File file;
        for(int i = 1;i < folderName.length;i++){
            file = new File(folderName[i]);
            if (!file.exists()) {
                if(!file.mkdirs())
                    System.out.println("mkdir: " + folderName[i] + " invalid filename.");
                else {
                    System.out.println("mkdir: " + folderName[i] + " created successfully.");
                }
            } else {
                System.out.println("mkdir: " + folderName[i] + " existed.");
            }
        }
    }

    /**
     * Runs the mkdir application with the specified arguments.
     *
     * @param args   Array of arguments for the application,the path to those new files.
     * @param stdin  An InputStream. Not used.
     * @param stdout An OutputStream. Not used.
     *
     * @throws MkdirException If the shell doesn't work.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws MkdirException {
        if(!args[0].equals("mkdir") || args.length <= 1){
            throw new MkdirException("Invalid syntax.");
        }
        createFolder(args);
    }
}
