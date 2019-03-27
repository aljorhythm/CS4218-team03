package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.SortInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.NumberAwareStringComparator;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FLAG_PREFIX;

public class SortApplication implements SortInterface {

    private static Boolean isFirstWordNumber;
    private static Boolean isReverseOrder;
    private static Boolean isCaseIndependent;
    private static String[] inputFiles;
    private static String sorted;

    private static final Pattern SORT_REGEX = Pattern
            .compile("(-?n)|(-?r)|(-?f)");
    private static final Pattern INVALID_REGEX = Pattern
            .compile("[^nfr\\-\\s]");

    /**
     * Runs the sort application with arguments.
     * Assumption: arguments must be supplied by user
     *
     * @param args   arguments supplied by user, cannot be empty
     * @param stdin  An InputStream
     * @param stdout An OutputStream
     * @throws SortException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {

        isFirstWordNumber = false;
        isReverseOrder = false;
        isCaseIndependent = false;
        inputFiles = null;

        if (args.length == 0 && stdin == null) {
            throw new SortException(ShellImpl.MISSING_STREAM);
        }
        initialiseArguments(args);
        if (inputFiles == null || inputFiles.length == 0) {
            sorted = sortFromStdin(isFirstWordNumber, isReverseOrder, isCaseIndependent, stdin);
        } else {
            sorted = sortFromFiles(isFirstWordNumber, isReverseOrder, isCaseIndependent, inputFiles);
        }

        try {
            stdout.write(sorted.getBytes());
        } catch (Exception e) {
            throw new SortException(e, e.getMessage());
        }
    }

    /**
     * Parse and initialise the sort arguments
     * Assumption: arguments must be supplied by user
     *
     * @param args arguments supplied by user, cannot be empty
     * @throws SortException
     */
    private void initialiseArguments(String... args) throws SortException {

        if (args.length == 0) {
            return;
        }

        ArrayList<String> fileList = new ArrayList<>();

        for (String s : args) {
            char[] arg = s.toCharArray();
            if (arg[0] == CHAR_FLAG_PREFIX) {
                Matcher matcher = INVALID_REGEX.matcher(s);
                if (matcher.find()) {
                    throw new SortException(SortException.INVALID_CMD);
                }
                List<Character> parameters;
                parameters = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

                if (parameters.contains('n')) {
                    isFirstWordNumber = true;
                }
                if (parameters.contains('r')) {
                    isReverseOrder = true;
                }
                if (parameters.contains('f')) {
                    isCaseIndependent = true;
                }
            } else {
                fileList.add(s);
            }
        }
        inputFiles = fileList.toArray(new String[0]);
    }


    /**
     * Checks if input file(s) is provided by the user.
     *
     * @param args supplied by user.
     * @return a String array of file names.
     */
    private String[] getInputFiles(String... args) {
        ArrayList<String> inputFiles = new ArrayList<>();

        for (String s : args) {
            char[] arg = s.toCharArray();
            if (arg[0] != CHAR_FLAG_PREFIX) {
                inputFiles.add(s);
            }
        }
        return inputFiles.toArray(new String[0]);
    }

    @Override
    public String sortFromFiles(Boolean isFirstWordNum, Boolean isReverseOdr, Boolean isCaseIdpt, String... fileName) throws SortException {

        BufferedReader file;
        String line;
        ArrayList<String> lines = new ArrayList<String>();

        if (isFirstWordNum != null) {
            isFirstWordNumber = isFirstWordNum;
        }
        if (isReverseOdr != null) {
            isReverseOrder = isReverseOdr;
        }
        if (isCaseIdpt != null) {
            isCaseIndependent = isCaseIdpt;
        }
        if (fileName != null) {
            inputFiles = getInputFiles(fileName);
        }

        try {
            for (String f : fileName) {
                file = Files.newBufferedReader(Paths.get(f));
                try {
                    while ((line = file.readLine()) != null) {
                        lines.add(line);
                    }
                    file.close();
                } catch (Exception e) {
                    throw new SortException(e, SortException.PROB_SORT_FILE + e.getMessage());
                } finally {
                    if (file != null) {
                        file.close();
                    }
                }
            }
            sortInputs(lines);
        } catch (Exception e) {
            throw new SortException(e, SortException.PROB_SORT_FILE + e.getMessage());
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : lines) {
            stringBuilder.append(s);
            stringBuilder.append(StringUtils.STRING_NEWLINE);
        }
        return stringBuilder.toString();
    }

    @Override
    public String sortFromStdin(Boolean isFirstWordNum, Boolean isReverseOdr, Boolean
            isCaseIdpt, InputStream stdin) throws SortException {
        BufferedReader input = new BufferedReader(new InputStreamReader(stdin));
        String line;
        ArrayList<String> lines = new ArrayList<String>();

        if (isFirstWordNum != null) {
            isFirstWordNumber = isFirstWordNum;
        }
        if (isReverseOdr != null) {
            isReverseOrder = isReverseOdr;
        }
        if (isCaseIdpt != null) {
            isCaseIndependent = isCaseIdpt;
        }

        try {
            while ((line = input.readLine()) != null) {
                lines.add(line);
            }
            input.close();
            sortInputs(lines);
        } catch (Exception e) {
            throw new SortException(e, SortException.PROB_SORT_STDIN + e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new SortException(e, SortException.PROB_SORT_STDIN + e.getMessage());
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : lines) {
            stringBuilder.append(s);
            stringBuilder.append(StringUtils.STRING_NEWLINE);
        }
        return stringBuilder.toString();
    }

    /**
     * return sorted results
     *
     * @param lines ArrayList containing the lines needed to be sorted
     */
    private void sortInputs(ArrayList<String> lines) {
        if (isCaseIndependent) {
            lines.sort(String::compareToIgnoreCase);
        }
        if (isFirstWordNumber) {
            Collections.sort(lines, new NumberAwareStringComparator());
        }
        if (!isCaseIndependent && ! isFirstWordNumber){
            Collections.sort(lines);
        }
        if (isReverseOrder) {
            Collections.reverse(lines);
        }
    }
}
