package sg.edu.nus.comp.cs4218.app;

import sg.edu.nus.comp.cs4218.Application;

public interface EchoInterface extends Application {
    /**
     * Return the string to be written by echo.
     *
     * @param args Array of String of args to be written
     */
    String constructResult(String... args) throws Exception;
}