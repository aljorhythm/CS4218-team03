package sg.edu.nus.comp.cs4218.app;

import sg.edu.nus.comp.cs4218.Application;

public interface DateInterface extends Application{
    /**
     * Return the string of date.
     *
     * @param format String of user-defined format
     */
    String getDate(String format) throws Exception;
}
