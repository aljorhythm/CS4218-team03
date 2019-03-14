package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.GrepInterface;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

public class GrepApplication implements GrepInterface {
    /**
     * Returns string containing lines which match the specified pattern in the given files
     *
     * @param pattern            String specifying a regular expression in JAVA format
     * @param isCaseInsensitive  Boolean option to perform case insensitive matching
     * @param isCountOfLinesOnly Boolean option to only write out a count of matched lines
     * @param fileNames          Array of file names
     * @return All lines in the files that match the pattern
     * @throws GrepException
     * @throws ShellException
     * @throws IOException
     */
    @java.lang.Override
    public String grepFromFiles(String pattern, Boolean isCaseInsensitive, Boolean isCountOfLinesOnly, String... fileNames) throws GrepException {
        if (fileNames == null) {
            throw new GrepException("filesNames is null!");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fileNames.length; i++) {
            try {
                InputStream inputStream = IOUtils.openInputStream(fileNames[i]);
                result.append(grepFromStdin(pattern, isCaseInsensitive, isCountOfLinesOnly, inputStream)).append('\n');
                IOUtils.closeInputStream(inputStream);
            } catch (IOException e) {
                throw (GrepException) new GrepException("IO not working").initCause(e);
            } catch (ShellException e) {
                throw (GrepException) new GrepException("Shell exception").initCause(e);
            }
        }
        return result.toString().trim();
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
        Pattern regexPattern;
        if (isCaseInsensitive != null && isCaseInsensitive) {
            regexPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        } else {
            regexPattern = Pattern.compile(pattern);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdin));
        StringBuilder result = new StringBuilder();
        int numberOfMatches = 0;
        for (String line; (line = reader.readLine()) != null; ) {
            Matcher matcher = regexPattern.matcher(line);
            if (matcher.find()){
                result.append(line).append('\n');
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
        String result;
        Boolean isCaseInsensitive = false;
        Boolean isCountOfLinesOnly = false;
        Boolean patternProvided = false;
        String pattern = null;
        List<String> fileNames = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {

                if (args[i].charAt(1) == 'i') {
                    isCaseInsensitive = true;
                } else if (args[i].charAt(1) == 'c') {
                    isCountOfLinesOnly = true;
                } else {
                    throw new GrepException("Unknown option for grep!");
                }
            } else{
                if (patternProvided) {
                    fileNames.add(args[i]);
                } else {
                    pattern = args[i];
                    patternProvided = true;
                }
            }
        }
        if (!patternProvided || fileNames.isEmpty()) {
            throw new GrepException("Not correct number of arguments for grep!");
        }
        String[] fileNamesArray = fileNames.toArray(new String[0]);
        result = grepFromFiles(pattern, isCaseInsensitive, isCountOfLinesOnly, fileNamesArray);
        try {
            stdout.write(result.getBytes(CHARSET_UTF8));
        } catch (IOException e) {
            throw (GrepException) new GrepException("grep failed to write!").initCause(e);
        }
    }
}