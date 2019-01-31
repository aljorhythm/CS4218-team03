package sg.edu.nus.comp.cs4218.app;

import sg.edu.nus.comp.cs4218.Application;

public interface PwdInterface extends Application {
    /**
     * Display the absolute pathname of the current working directory
     *
     * @throws Exception
     */
    String getAbsolutePath() throws Exception;
}
