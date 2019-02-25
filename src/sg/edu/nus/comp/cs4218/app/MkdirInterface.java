package sg.edu.nus.comp.cs4218.app;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.exception.MkdirException;

public interface MkdirInterface extends Application {
    /**
     * Create folder from the given folder names. Do nothing if folder already exists. If folder
     * name is a path format, create the folder that satisfies the path specification.
     * <p>
     * Note: path would be a more accurate than folderName since params like ~/foo/bar should work
     *
     * @param folderName Array of string of folder names to be created
     * @throws Exception
     */
    void createFolder(String... folderName) throws MkdirException;
}
