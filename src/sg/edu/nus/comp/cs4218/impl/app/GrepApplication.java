package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.GrepInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.GrepException;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

public class GrepApplication implements GrepInterface {
    /**
     * Returns string containing lines which match the specified pattern in the given files
     *
     * @param pattern            String specifying a regular expression in JAVA format
     * @param isCaseInsensitive  Boolean option to perform case insensitive matching
     * @param isCountOfLinesOnly Boolean option to only write out a count of matched lines
     * @param fileNames          Array of file names
     * @throws Exception
     */
    @java.lang.Override
    public String grepFromFiles(String pattern, Boolean isCaseInsensitive, Boolean isCountOfLinesOnly, String... fileNames) throws Exception {
        return null;
    }

    /**
     * Returns string containing lines which match the specified pattern in Stdin
     *
     * @param pattern            String specifying a regular expression in JAVA format
     * @param isCaseInsensitive  Boolean option to perform case insensitive matching
     * @param isCountOfLinesOnly Boolean option to only write out a count of matched lines
     * @param stdin              InputStream containing arguments from Stdin
     * @throws Exception
     */
    @java.lang.Override
    public String grepFromStdin(String pattern, Boolean isCaseInsensitive, Boolean isCountOfLinesOnly, InputStream stdin) throws GrepException {
        if (pattern == null) {
            throw new GrepException("Pattern is null!");
        }
        Pattern p = Pattern.compile(pattern);
        
        return null;
    }

    @java.lang.Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws GrepException {

    }
}
