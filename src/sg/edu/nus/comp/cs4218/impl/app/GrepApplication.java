package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.GrepInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@SuppressWarnings({"PMD.ShortVariable", "PMD.LongVariable"})
public class GrepApplication implements GrepInterface {

    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_READING_FILE = "Could not read file";
    public static final String ERR_INVALID_PATTERN = "Pattern's syntax is invalid";
    public static final String ERR_NO_PATTERN = "No pattern found";

    public static final String FLAG_CASE_INSENSITIVE = "-i";
    public static final String FLAG_COUNT_ONLY = "-c";

    public static final boolean DEFAULT_CASE_SENSITIVITY = false;
    public static final boolean DEFAULT_COUNT_ONLY = false;

    private boolean isCaseInsensitive = DEFAULT_CASE_SENSITIVITY;
    private boolean isCountOfLinesOnly = DEFAULT_COUNT_ONLY;

    @Override
    public String grepFromFiles(String pattern, Boolean isCaseInsensitive, Boolean isCountOfLinesOnly, String... fileNames) throws Exception {
        Pattern grepPattern;
        if (isCaseInsensitive) {
            grepPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        } else {
            grepPattern = Pattern.compile(pattern);
        }

        StringBuilder sb = new StringBuilder();
        int matchCount;

        for (String fileName : fileNames) {
            matchCount = 0;
            try {
                InputStream fis = IOUtils.openInputStream(fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line;
                while ((line = br.readLine()) != null) {
                    Matcher matcher = grepPattern.matcher(line);
                    if (matcher.find()) {
                        if (isCountOfLinesOnly) {
                            matchCount++;
                        } else {
                            sb.append(fileName + ":");
                            sb.append(line);
                            sb.append(System.lineSeparator());
                        }
                    }
                }
                IOUtils.closeInputStream(fis);
                if (isCountOfLinesOnly) {
                    sb.append(fileName + ":" + matchCount);
                    sb.append(System.lineSeparator());
                }
            } catch (ShellException se) {
                System.out.println(new GrepException(fileName + ": " + ERR_READING_FILE).getMessage());
            }
        }

        return sb.toString();
    }

    @Override
    public String grepFromStdin(String pattern, Boolean isCaseInsensitive, Boolean isCountOfLinesOnly, InputStream stdin) throws Exception {
        Pattern grepPattern;
        if (isCaseInsensitive) {
            grepPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        } else {
            grepPattern = Pattern.compile(pattern);
        }

        StringBuilder sb = new StringBuilder();
        int matchCount = 0;
        String line;
        BufferedReader bf = new BufferedReader(new InputStreamReader(stdin));

        while ((line = bf.readLine()) != null) {
            if (line.equals("")) {
                break;
            }
            Matcher matcher = grepPattern.matcher(line);
            if (matcher.find()) {
                if (isCountOfLinesOnly) {
                    matchCount++;
                } else {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
            }
        }
        if (isCountOfLinesOnly) {
            return String.valueOf(matchCount) + System.lineSeparator();
        }
        else {
            return sb.toString();
        }
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {

        if (args == null) {
            throw new GrepException(ERR_NULL_STREAMS);
        }

        if (stdout == null) {
            throw new GrepException(ERR_NULL_STREAMS);
        }
        if (stdin == null) {
            stdin = System.in;
        }

        try {
            ArrayList<String> processedArgs = processArgs(args);
            if (processedArgs.size() == 0) {
                throw new GrepException(ERR_NO_PATTERN);
            } else if (processedArgs.size() == 1) {
                stdout.write(grepFromStdin(processedArgs.get(0), isCaseInsensitive, isCountOfLinesOnly, stdin).getBytes());
            } else {
                String pattern = processedArgs.remove(0);
                String [] fileNames = processedArgs.toArray(new String[0]);
                stdout.write(grepFromFiles(pattern, isCaseInsensitive, isCountOfLinesOnly, fileNames).getBytes());
            }
        } catch (PatternSyntaxException pse) {
            throw new GrepException(ERR_INVALID_PATTERN);
        } catch (Exception e) {
            throw new GrepException(e.getMessage());
        }
    }

    private ArrayList<String> processArgs(String[] args) throws Exception {
        ArrayList<String> processedArgs = new ArrayList<String>();
        for (int i = 0; i < args.length; i++) {

            if (args[i].startsWith("-")) {
                if (args[i].equals(FLAG_CASE_INSENSITIVE)) {
                    isCaseInsensitive = true;
                } else if (args[i].equals(FLAG_COUNT_ONLY)) {
                    isCountOfLinesOnly = true;
                } else {
                    throw new Exception("Invalid Flag");
                }
            } else {
                processedArgs.add(args[i]);
            }
        }
        return processedArgs;
    }
}
