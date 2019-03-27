package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.WcInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

import static sg.edu.nus.comp.cs4218.impl.ShellImpl.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.ShellImpl.FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.ShellImpl.MISSING_STREAM;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FLAG_PREFIX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class WcApplication implements WcInterface {

    private static final int NUM_ARGUMENTS = 3;
    private static final char IS_BYTES = 'c';
    private static final char IS_LINES = 'l';
    private static final char IS_WORDS = 'w';
    private static final int IS_BYTES_INDEX = 0;
    private static final int IS_LINES_INDEX = 1;
    private static final int IS_WORDS_INDEX = 2;

    @Override
    public String countFromFiles(Boolean isBytes, Boolean isLines, Boolean isWords, String... fileName) throws Exception {
        int sumLines = 0;
        int sumWords = 0;
        long sumBytes = 0;
        int validFileCounter = 0;
        StringJoiner resultJoiner = new StringJoiner(STRING_NEWLINE);
        StringJoiner errFiles = new StringJoiner(STRING_NEWLINE);

        for (String f : fileName) {
            try {
                StringJoiner fileContent = new StringJoiner(STRING_NEWLINE);
                BufferedReader reader = new BufferedReader(new FileReader(f));
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.add(line);
                }
                int numLines = getNumLines(fileContent.toString());
                int numWords = getNumWords(fileContent.toString());
                long numBytes = getNumBytes(f);
                sumBytes += numBytes;
                sumLines += numLines;
                sumWords += numWords;
                resultJoiner.add(formatResult(isBytes, isLines, isWords, numBytes, numLines, numWords) + " " + f);
                reader.close();
                validFileCounter++;
            } catch (FileNotFoundException e) {
                errFiles.add("wc: " + f + ": " + FILE_NOT_FOUND);
            }
        }

        if (validFileCounter > 1) {
            resultJoiner.add(formatResult(isBytes, isLines, isWords, sumBytes, sumLines, sumWords) + " total");
        }
        String result = "";
        if (!StringUtils.isBlank(errFiles.toString())) {
            result = result + errFiles.toString();
        }
        if (!StringUtils.isBlank(resultJoiner.toString())) {
            if (!StringUtils.isBlank(errFiles.toString())) {
                result = result + STRING_NEWLINE;
            }
            result = result + resultJoiner.toString();
        }
        return result;
    }

    @Override
    public String countFromStdin(Boolean isBytes, Boolean isLines, Boolean isWords, InputStream stdin) throws Exception {
        if (stdin == null) {
            throw new Exception(MISSING_STREAM);
        }
        int numLines;
        int numWords;
        long numBytes;
        String line;
        StringJoiner stdinContent = new StringJoiner(STRING_NEWLINE);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdin));

        while ((line = bufferedReader.readLine()) != null) {
            stdinContent.add(line);
        }
        bufferedReader.close();
        numLines = getNumLines(stdinContent.toString());
        numWords = getNumWords(stdinContent.toString());
        numBytes = stdinContent.toString().getBytes().length;

        return formatResult(isBytes, isLines, isWords, numBytes, numLines, numWords);
    }

    /**
     * Runs the wc application with the specified arguments.
     *
     * @param args   Array of arguments for the application. Each array element is the path to a
     *               file. If no files are specified stdin is used.
     * @param stdin  An InputStream. The input for the command is read from this InputStream if no
     *               files are specified.
     * @param stdout An OutputStream. The output of the command is written to this OutputStream.
     * @throws WcException If the file(s) specified do not exist or are unreadable, or an invalid argument is given.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        try {
            boolean[] wcArgs = getWcArguments(args);
            String[] inputFiles = getInputFiles(args);
            String result;

            if (stdin == null && args.length == 0) {
                throw new Exception(MISSING_STREAM);
            }

            if (inputFiles.length > 0) {
                result = countFromFiles(wcArgs[IS_BYTES_INDEX], wcArgs[IS_LINES_INDEX], wcArgs[IS_WORDS_INDEX],
                        inputFiles);
            } else {
                result = countFromStdin(wcArgs[IS_BYTES_INDEX], wcArgs[IS_LINES_INDEX], wcArgs[IS_WORDS_INDEX], stdin);
            }
            result = result + STRING_NEWLINE;
            stdout.write(result.getBytes());
        } catch (Exception e) {
            throw new WcException(e, e.getMessage());
        }
    }

    /**
     * Checks if the arguments -clw or -c -l -w etc. appear in the args provided by user.
     *
     * @param args supplied by user.
     * @return a bool array where true for index if the argument associated with the index is present, false otherwise.
     */
    private boolean[] getWcArguments(String... args) throws Exception {
        boolean[] wcArguments = new boolean[NUM_ARGUMENTS];

        for (String s : args) {
            char[] arg = s.toCharArray();
            if (arg[0] == CHAR_FLAG_PREFIX) {
                arg = Arrays.copyOfRange(arg, 1, arg.length);
                for (char c : arg) {
                    switch (c) {
                        case IS_BYTES:
                            wcArguments[IS_BYTES_INDEX] = true;
                            break;
                        case IS_LINES:
                            wcArguments[IS_LINES_INDEX] = true;
                            break;
                        case IS_WORDS:
                            wcArguments[IS_WORDS_INDEX] = true;
                            break;
                        default:
                            throw new Exception(ERR_SYNTAX);
                    }
                }
            }
        }
        return wcArguments;
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

    /**
     * Formats the result given the following parameters supplied.
     *
     * @param isBytes  true if "-c" was an argument
     * @param isLines  true if "-l" was an argument
     * @param isWords  true if "-w" was an argument
     * @param numBytes number of Bytes to print
     * @param numLines number of Lines to print
     * @param numWords number of Words to print
     * @return formatted result
     */
    private String formatResult(Boolean isBytes, Boolean isLines, Boolean isWords,
                                long numBytes, int numLines, int numWords) {
        StringJoiner resultJoiner = new StringJoiner(" ");
        if (isLines) {
            resultJoiner.add(Integer.toString(numLines));
        }
        if (isWords) {
            resultJoiner.add(Integer.toString(numWords));
        }
        if (isBytes) {
            resultJoiner.add(Long.toString(numBytes));
        }
        if (StringUtils.isBlank(resultJoiner.toString())) {
            resultJoiner.add(numLines + " " + numWords + " " + numBytes);
        }

        return resultJoiner.toString();
    }


    /**
     * Counts the number of bytes of a file with the given fileName
     *
     * @param fileName of file to count bytes
     * @return number of bytes of file
     */
    private long getNumBytes(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        return file.length();
    }

    /**
     * Counts the number of lines in a given String.
     * A line is defined as a string of characters delimited by a <newline> character.
     * Characters beyond the final <newline> character will not be included in the line count.
     *
     * @param content String containing the content to count the number of lines
     * @return number of lines in content
     */
    private int getNumLines(String content) {
        if (content == null || content.isEmpty()) {
            return 0;
        }
        String normalized = content.replaceAll("\\r\\n", STRING_NEWLINE);
        normalized = normalized.replaceAll("\\n", STRING_NEWLINE);
        int lines = 0;
        int pos = 0;
        while ((pos = normalized.indexOf(STRING_NEWLINE, pos) + 1) != 0) {
            lines++;
        }
        return lines;
    }

    /**
     * Counts the number of words in a given String.
     * A word is defined as a string of characters delimited by white space characters.
     *
     * @param content String containing the content to count the number of words
     * @return number of words in content
     */
    private int getNumWords(String content) {
        int count = 0;
        String[] words = content.split("\\s+");
        for (String word : words) {
            if (!StringUtils.isBlank(word)) {
                count++;
            }
        }
        return count;
    }

}
