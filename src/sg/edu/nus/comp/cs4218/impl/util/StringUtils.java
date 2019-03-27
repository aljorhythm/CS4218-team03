package sg.edu.nus.comp.cs4218.impl.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.StringUtilException.ERR_INVALID_REPLACEMENT_RULE;

@SuppressWarnings({"PMD.PreserveStackTrace", "PMD.LongVariable", "PMD.ExcessiveMethodLength",
        "PMD.InefficientEmptyStringCheck"})
public final class StringUtils {
    public static final String STRING_NEWLINE = System.lineSeparator();
    public static final String STRING_CURR_DIR = ".";
    public static final String STRING_PARENT_DIR = "..";
    public static final char CHAR_FILE_SEP = File.separatorChar;
    public static final char CHAR_TAB = '\t';
    public static final char CHAR_NEWLINE = '\n';
    public static final char CHAR_SPACE = ' ';
    public static final char CHAR_DOUBLE_QUOTE = '"';
    public static final char CHAR_SINGLE_QUOTE = '\'';
    public static final char CHAR_BACK_QUOTE = '`';
    public static final char CHAR_REDIR_INPUT = '<';
    public static final char CHAR_REDIR_OUTPUT = '>';
    public static final char CHAR_PIPE = '|';
    public static final char CHAR_SEMICOLON = ';';
    public static final char CHAR_ASTERISK = '*';
    public static final char CHAR_FLAG_PREFIX = '-';

    private StringUtils() {
    }

    /**
     * Check if string contains only whitespace
     *
     * @param str String to be checked
     * @return true under any one of the 3 conditions:
     * 1. string is null
     * 2. string is empty
     * 3. string contains only whitespace
     */
    public static boolean isBlank(String str) {
        if (str == null || str.equals("")) {
            return true;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Performs c * n (as in python)
     *
     * @param character char to be multiplied
     * @param num       if num is < 0, num is assumed to be 0
     * @return String result of c * n
     */
    public static String multiplyChar(char character, int num) {
        int length = num <= 0 ? 0 : num;
        char[] arr = new char[length];
        Arrays.fill(arr, character);
        return new String(arr);
    }

    /**
     * Tokenize a string delimited by whitespace
     *
     * @param str String to be tokenized
     * @return String array containing the tokens
     */
    public static String[] tokenize(String str) {
        if (isBlank(str)) {
            return new String[0];
        }

        return str.trim().split("\\s+");
    }

    /**
     * parse a string to retrieve the double value contained in the string as its first word.
     * the double is recognized if the string begins with optional blanks,
     * an optional ‘-’ sign, and zero or more digits, or a decimal-point character.
     * neither a leading ‘+’ nor exponential notation is recognized.
     *
     * @param str String to be parsed
     * @return a double contained in the string as its first word.
     * 0.0 if the string does not contain a double as its first word.
     */
    public static double parseDoubleContainedInString (String str) {
        if (str == null || str.equals("")) {
            return 0.0;
        }

        char firstChar = str.charAt(0);
        if (!(firstChar == ' ' || firstChar == '-' || firstChar == '.' || Character.isDigit(firstChar))) {
            return 0.0;
        }

        int index = str.length();
        while (index >= 1) {
            String subStr = str.substring(0, index);
            try {
                if (Double.valueOf(subStr).getClass().equals(Double.class)) {

                    if (Double.parseDouble(subStr) == 0.0) {
                        return 0.0;
                    }

                    if(subStr.contains("e") || subStr.contains("E")
                            || subStr.contains("f") || subStr.contains("F")) {
                        index--;
                    } else {
                        return Double.valueOf(subStr);
                    }
                }
            } catch (NumberFormatException nfe) {
                index--;
            }
        }
        return  0.0;
    }

    public static String removeLeadingWhiteSpaces(String input) {
        return input.replaceAll("^\\s+", "");
    }

    static class StringUtilException extends Exception {

        public static final String ERR_INVALID_REPLACEMENT_RULE = "Invalid replacement rule";

        String err;
        public StringUtilException(String err){
            this.err = err;
        }
    }

    public static ArrayList<String> parseReplacementRule(String replacementRule) throws StringUtilException {
        ArrayList<String> result;

        String trimmedReplacementRule = removeLeadingWhiteSpaces(replacementRule);

        if (!trimmedReplacementRule.startsWith("s")) {
            throw new StringUtilException(ERR_INVALID_REPLACEMENT_RULE);
        }

        if (trimmedReplacementRule.length() < 2) {
            throw new StringUtilException(ERR_INVALID_REPLACEMENT_RULE);
        }

        char [] replacementRuleCharsArray = trimmedReplacementRule.toCharArray();
        char delimiter = trimmedReplacementRule.charAt(1);

        //substitute pattern can not be delimited by newline or backslash
        if (delimiter == '\\' || delimiter == System.lineSeparator().charAt(0)) {
            throw new StringUtilException(ERR_INVALID_REPLACEMENT_RULE);
        }

        int delimiterCount = 0;
        for (int i = 0; i < replacementRuleCharsArray.length; i++) {
            if (replacementRuleCharsArray[i] == delimiter) {
                delimiterCount++;
            }
        }
        if (delimiterCount < 3) {
            throw new StringUtilException(ERR_INVALID_REPLACEMENT_RULE);
        }

        result = splitByDelimiter(trimmedReplacementRule, delimiter);
        switch (result.size()) {
            case 3:
                //regex cannot be empty
                if (result.get(1).equals("")) {
                    throw new StringUtilException(ERR_INVALID_REPLACEMENT_RULE);
                }
                result.add("1");
                break;
            case 4:
                //regex cannot be empty
                if (result.get(1).equals("")) {
                    throw new StringUtilException(ERR_INVALID_REPLACEMENT_RULE);
                }
                String replacementIndex = result.get(3).trim();
                if (replacementIndex.startsWith("0") || replacementIndex.startsWith("-")
                        || replacementIndex.startsWith("+")) {
                    throw new StringUtilException(ERR_INVALID_REPLACEMENT_RULE);
                }
                try {
                    int replacementIndexInt = Integer.parseInt(replacementIndex);
                    if (replacementIndexInt < 1) {
                        throw new StringUtilException(ERR_INVALID_REPLACEMENT_RULE);
                    }
                } catch (NumberFormatException nfe) {
                    throw new StringUtilException(ERR_INVALID_REPLACEMENT_RULE);
                }
                break;
                default:
                    throw new StringUtilException(ERR_INVALID_REPLACEMENT_RULE);
        }
        return result;
    }

    private static ArrayList<String> splitByDelimiter(String input, char delimiter) {
        ArrayList<String> result = new ArrayList<String>();
        int substringStartIndex = 0;
        int delimiterCount = 3;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == delimiter) {
                result.add(input.substring(substringStartIndex, i));
                substringStartIndex = i + 1;
                delimiterCount -= 1;
                if (delimiterCount == 0) {
                    break;
                }
            }
        }
        String lastSlice = input.substring(substringStartIndex);
        if (!lastSlice.trim().isEmpty()) {
            result.add(lastSlice.trim());
        }
        return result;
    }
}
