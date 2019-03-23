package sg.edu.nus.comp.cs4218.impl.util;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public final class StringUtils {

    /**
     * System dependent new line
     */
    public static final String STRING_NEWLINE = System.lineSeparator();
    public static final String STRING_CURR_DIR = ".";
    public static final String STRING_PARENT_DIR = "..";

    /**
     * Char representation of file separator
     */
    public static final char CHAR_FILE_SEP = File.separatorChar;

    /**
     * String representation of file separator
     */
    public static final String STRING_FILE_SEP = new String(new char[]{File.separatorChar});
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
    public static final String STRING_ASTERISK = "*";
    public static final char CHAR_SHELL_ARROW = '>';
    public static final char CHAR_FLAG_PREFIX = '-';
    public static final String REGEX_FILE_SEP = SystemUtil.isWindows() ? "\\\\" : "/";
    public static final String CHARSET_UTF8 = "UTF-8";

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
        if (str == null || str.isEmpty()) {
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

        return str
                .trim()
                .split("\\s+");
    }

    /**
     * isNumberic is a num string
     *
     * @param str String to be judge
     * @return boolean
     */
    public static boolean isNumberic(String str) {
        if (isBlank(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * getFirstNum is to get the first num of string
     *
     * @param str String to be get
     * @return int num
     */
    public static int getFirstNum(String str) {
        return Integer.parseInt(str.split(" ")[0]);
    }

    /**
     * getCharacterType is to get the character type to compare
     *
     * @param  cha to be get type
     * @return int num, special character would be 1, number would be 2, capital letter would be 3 and a smaller be 4
     */
    public static int getCharacterType(char cha) {
        if (Character.isLowerCase(cha)) {
            return 4;
        } else if (Character.isUpperCase(cha)) {
            return 3;
        } else if (Character.isDigit(cha)) {
            return 2;
        } else {
            return 1;
        }
    }
    /**
     * Prepends string to all strings
     *
     * @param str
     * @param strings
     * @return array where elements are strings prepended with str
     */
    public static String[] prependStringToStrings(String str, String... strings) {
        return Stream
                .of(strings)
                .map(s -> str + s)
                .toArray(String[]::new);
    }
}
