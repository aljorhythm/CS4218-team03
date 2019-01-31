package sg.edu.nus.comp.cs4218.app;

import sg.edu.nus.comp.cs4218.Application;

import java.io.InputStream;

public interface CatInterface extends Application {
    /**
     * Returns string containing the content of the specified file
     *
     * @param fileName Array of String of file names
     * @return
     * @throws Exception
     */
    String catFiles(String... fileName) throws Exception;

    /**
     * Returns string containing the content of the standard input
     *
     * @param stdin InputStream containing arguments from Stdin
     * @return
     * @throws Exception
     */
    String catStdin(InputStream stdin)
            throws Exception;
}
