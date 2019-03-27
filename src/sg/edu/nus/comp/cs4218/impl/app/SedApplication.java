package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.SedInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.SedException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SedApplication implements SedInterface {

    private static String[] sedArgs;
    private static String regexp;
    private static String replacement;
    private static int replacementIndex;
    private static String inputFile;
    private static String replaced;
    private static final Pattern REPLACE_REGEX = Pattern
            .compile("^s");

    /**
     * Runs the sed application with arguments.
     * Assumption: arguments must be supplied by user
     *
     * @param args   arguments supplied by user, cannot be empty
     * @param stdin  An InputStream
     * @param stdout An OutputStream
     * @throws SedException
     */
    public void run(final String[] args, final InputStream stdin, final OutputStream stdout) throws AbstractApplicationException {
        regexp = "";
        replacement = "";
        replacementIndex = 1;
        inputFile = null;

        if (args.length == 0 && stdin == null) {
            throw new SedException(ShellImpl.MISSING_STREAM);
        }
        initialiseArguments(args);
        try {
            if (inputFile == null) {
                replaced = replaceSubstringInStdin(regexp, replacement, replacementIndex, stdin);
            } else {
                replaced = replaceSubstringInFile(regexp, replacement, replacementIndex, inputFile);
            }
        } catch (SedException sed) {
            throw sed;
        } catch (Exception e) {
            throw new SedException(e, e.getMessage());
        }

        try {
            stdout.write(replaced.getBytes());
        } catch (IOException e) {
            throw new SedException(e, e.getMessage());
        }
    }


    /**
     * Parse and initialise the sed arguments
     * Assumption: arguments must be supplied by user
     *
     * @param args arguments supplied by user, cannot be empty
     * @throws SedException
     */
    private void initialiseArguments(final String... args) throws SedException {

        if (args.length > 2) {
            throw new SedException(SedException.TOO_MANY_ARGS);
        }

        if (args.length == 0) {
            throw new SedException(SedException.EMPTY_ARGUMENTS);
        }

        final Matcher matcher = REPLACE_REGEX.matcher(args[0]);
        if (!matcher.find()) {
            throw new SedException(SedException.INVALID_CMD);
        }

        try {
            sedArgs = getSedArguments(args[0].substring(1));
        } catch (StringIndexOutOfBoundsException e) {
            throw new SedException(e, SedException.BAD_FLAG);
        }
        regexp = sedArgs[1];
        if (regexp == null || regexp.isEmpty()) {
            throw new SedException(SedException.REGEX_EMPTY);
        }
        replacement = sedArgs[2];
        replacementIndex = 1;
        if (sedArgs.length > 3 && !sedArgs[3].isEmpty()) {
            try {
                replacementIndex = Integer.valueOf(sedArgs[3]);
            } catch (NumberFormatException e) {
                throw new SedException(e, SedException.INVALID_INDEX);
            }
            if (replacementIndex <= 0) {
                throw new SedException(SedException.INVALID_INDEX);
            }
        }

        inputFile = args.length == 2 ? args[1] : null;

    }

    /**
     * Get the arguments (regexp, replacement, replacementIndex) for sed
     *
     * @param rule supplied by user, cannot be empty
     * @throws SedException
     */
    private String[] getSedArguments(final String rule) throws SedException {

        final String separator = rule.substring(0, 1);
        final int count = rule.split(Pattern.quote(separator)).length;
        if (count < 3) {
            throw new SedException(SedException.INSUFFICIENT_ARGS);
        }
        return rule.split(Pattern.quote(separator));
    }

    /**
     * Find the nth match of the pattern p in src
     *
     * @param nth     index to match
     * @param pattern pattern to match
     * @param src     charsequence where match is to be found
     * @throws SedException
     */
    private MatchResult findNthOccurence(final int nth, final Pattern pattern, final CharSequence src) {
        final Matcher matcher = pattern.matcher(src);
        for (int i = 0; i < nth; i++) {
            matcher.find();
        }
        return matcher;
    }

    /**
     * Replace and save the replaced content to output buffer for writing out later
     *
     * @param line             original string where replacement is to be made
     * @param regexp           regexp to replace
     * @param replacement      new string to replace with
     * @param replacementIndex Only replace the xth match of the regexp
     * @param outputBuffer     where to write the output
     * @throws SedException
     */
    private void replaceAndSaveToOutputBuffer
    (final String line, final String regexp, final String replacement,
     final int replacementIndex, final StringBuffer outputBuffer) {
        MatchResult matchResult;
        matchResult = findNthOccurence(replacementIndex, Pattern.compile(regexp), line);
        try {
            final String resultline = line.substring(0, matchResult.start()) + replacement + line.substring(matchResult.end());
            outputBuffer.append(resultline).append(StringUtils.STRING_NEWLINE);
        } catch (IllegalStateException e) {
            outputBuffer.append(line).append(StringUtils.STRING_NEWLINE);
        }

    }

    @Override
    public String replaceSubstringInFile
            (final String regexp, final String replacement, final int replacementIndex, final String fileName) throws SedException, IOException {
        BufferedReader file = null;
        try {
            file = Files.newBufferedReader(Paths.get(fileName));
            final StringBuffer outputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                replaceAndSaveToOutputBuffer(line, regexp, replacement, replacementIndex, outputBuffer);
            }
            file.close();
            return outputBuffer.toString();
        } catch (Exception e) {
            throw new SedException(e, SedException.PROB_FILE + e.getMessage());
        } finally {
            if (file != null) {
                file.close();
            }
        }
    }

    @Override
    public String replaceSubstringInStdin
            (final String regexp, final String replacement, final int replacementIndex, final InputStream stdin) throws SedException, IOException {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(stdin));
            final StringBuffer outputBuffer = new StringBuffer();
            String line;

            while ((line = input.readLine()) != null) {
                replaceAndSaveToOutputBuffer(line, regexp, replacement, replacementIndex, outputBuffer);
            }
            input.close();
            return outputBuffer.toString();

        } catch (Exception e) {
            throw new SedException(e, SedException.PROB_STDIN + e.getMessage());
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

}