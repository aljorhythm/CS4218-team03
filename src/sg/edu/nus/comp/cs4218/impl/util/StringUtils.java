package sg.edu.nus.comp.cs4218.impl.util;

import java.io.File;
import java.util.Arrays;

public final class StringUtils {
    public static final String STRING_NEWLINE = System.lineSeparator();
    public static final String STRING_CURR_DIR = ".";
    public static final String STRING_PARENT_DIR = "..";
    public static final char CHAR_FILE_SEP = File.separatorChar;
    public static final char CHAR_TAB = '\t';
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
        String temp = str.trim();
        return temp.isEmpty();
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
}
