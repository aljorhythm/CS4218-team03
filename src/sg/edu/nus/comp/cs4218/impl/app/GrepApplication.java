package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.GrepInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.GrepException;

import java.io.*;
import java.util.regex.Matcher;
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
    public String grepFromStdin(String pattern, Boolean isCaseInsensitive, Boolean isCountOfLinesOnly, InputStream stdin) throws GrepException, IOException {
        if (pattern == null) {
            throw new GrepException("Pattern is null!");
        }
        Pattern p;
        if (isCaseInsensitive != null && isCaseInsensitive) {
            p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        } else {
            p = Pattern.compile(pattern);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdin));
        StringBuilder result = new StringBuilder();
        int numberOfMatches = 0;
        for (String line; (line = reader.readLine()) != null; ) {
            Matcher m = p.matcher(line);
            if (m.find()){
                result.append(line).append("\n");
                numberOfMatches++;
            }
        }
        if (isCountOfLinesOnly) {
            return Integer.toString(numberOfMatches);
        }
        else {
            return result.toString().trim();
        }
    }

    /**
     * Run the grep application.
     *
     * @param args Array of arguments for the application.
     * @param stdin An inputstream.
     * @param stdout An outputstream.
     * @throws GrepException
     */
    @java.lang.Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws GrepException {

    }
}
