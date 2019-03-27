package sg.edu.nus.comp.cs4218m1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtils {

    /**
     * ShellException.java
     **/

    public static final String INVALID_IO_STREAM = "invalid input stream";

    /**
     * SystemUtils.java
     **/

    private static final String OPERATING_SYSTEM = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

    /**
     *
     * @return true if OPERATING_SYSTEM is windows
     */
    public static boolean isWindows() {
        return (OPERATING_SYSTEM.indexOf("win") >= 0);
    }
    /**
     * StringUtils.java
     **/
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
    public static final String REGEX_FILE_SEP = isWindows() ? "\\\\" : "/";
    public static final String CHARSET_UTF8 = "UTF-8";


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

    /**
     * Non existent folder
     */
    public static final String NON_EXISTENT_DIR = pathToTestDataSubdir("non-existent");
    public static final String NULL_STREAM = "null_stream";
    public static final String NULL_STRING= "null_string";

    /**
     * IOUtils.java
     **/

    /**
     * Open an inputStream based on the file name.
     *
     * @param fileName String containing file name.
     * @return InputStream of file opened.
     * @throws ShellException If file destination is inaccessible.
     */
    public static InputStream openInputStream(String fileName) throws ShellException, FileNotFoundException {
        String resolvedFileName = resolveFilePath(fileName).toString();

        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream(new File(resolvedFileName));

        return fileInputStream;
    }

    /**
     * Open an outputStream based on the file name.
     *
     * @param fileName String containing file name.
     * @return OutputStream of file opened.
     * @throws ShellException If file destination is inaccessible.
     */
    public static OutputStream openOutputStream(String fileName) throws ShellException, FileNotFoundException {
        String resolvedFileName = resolveFilePath(fileName).toString();

        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream(new File(resolvedFileName));

        return fileOutputStream;
    }

    /**
     * Close an inputStream. If inputStream provided is System.in or null, it will be ignored.
     *
     * @param inputStream InputStream to be closed.
     * @throws ShellException If inputStream cannot be closed successfully.
     */
    public static void closeInputStream(InputStream inputStream) throws ShellException, IOException {
        if (inputStream == System.in || inputStream == null) {
            return;
        }

        inputStream.close();

    }

    /**
     * Close an outputStream. If outputStream provided is System.out or null, it will be ignored.
     *
     * @param outputStream OutputStream to be closed.
     * @throws ShellException If outputStream cannot be closed successfully.
     */
    public static void closeOutputStream(OutputStream outputStream) throws ShellException, IOException {
        if (outputStream == System.out || outputStream == null) {
            return;
        }

        outputStream.close();
    }

    /**
     * Resolves relative file path with respect to working directory
     * @param fileName
     * @return resolved file path
     */
    public static Path resolveFilePath(String fileName) {
        Path currentDirectory = Paths.get(Environment.currentDirectory);
        return currentDirectory.resolve(fileName);
    }

    /**
     * @param arg argument
     * @return an InputStream where input is argument
     */
    public static InputStream stringToInputStream(String arg) throws IOException {
        if(arg == null) {
            throw new IOException(NULL_STRING);
        }

        return new ByteArrayInputStream(arg.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @param inputStrings variable length argument of strings
     * @return an InputStream where input is arguments joined together by system line separator
     */
    public static InputStream stringsToInputStream(String... inputStrings) {
        String inputString = Stream
                .of(inputStrings)
                .collect(Collectors.joining(STRING_NEWLINE));
        return new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Converts input from stream into String
     *
     * @param inputStream input stream
     * @return String from input stream
     * @throws IOException
     */
    public static String stringFromInputStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IOException(NULL_STREAM);
        }

        String newLine = STRING_NEWLINE;

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString(CHARSET_UTF8).replaceAll("\\r\\n", STRING_NEWLINE);
    }

    /**
     * Converts input from stream into list of String representing lines
     *
     * @param inputStream input stream
     * @return String from input stream
     * @throws IOException
     */
    public static String[] stringsFromInputStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IOException(NULL_STREAM);
        }

        String string = stringFromInputStream(inputStream);
        if(string.isEmpty()) {
            return new String[]{};
        }

        return string.split(STRING_NEWLINE);
    }

    /**
     * Writes string to file
     * @param filename
     * @param content
     * @return
     * @throws IOException
     */
    public static File createAndWriteToFile(String filename, String content) throws IOException {
        File file = new File(filename);
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.close();

        return file;
    }

    /**
     * Writes string to file
     * @param filePath
     * @param content
     * @return
     * @throws IOException
     */
    public static File createAndWriteToFile(Path filePath, String content) throws IOException {
        File file = filePath.toFile();
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        writer.close();

        return file;
    }

    /**
     * TestUtils.java
     */

    /**
     * For random generation
     */
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    /**
     * Test data folder
     */
    public static final String TEST_DATA_DIR = System.getProperty("user.dir") + File.separator + "resources";

    /**
     * @param subDir
     * @return sub-directory path from test data folder
     */
    public static String pathToTestDataSubdir(String subDir) {
        return TEST_DATA_DIR + File.separator + subDir;
    }

    /**
     * Asserts that expected arr is equal to list.
     *
     * @param arr
     * @param res List that will be converted to an array before assertion
     */
    public static void assertArrayEqualsList(String[] arr, List<String> res) {
        if (arr == null) {
            assertEquals(arr, res);
        } else {
            Assertions.assertArrayEquals(arr, res.toArray(new String[]{}));
        }
    }

    @Test
    public void testPathToTestDataDirExists() {
        File file = new File(TEST_DATA_DIR);
        assertTrue(file.isDirectory());
        assertTrue(file.exists());
    }

    /**
     * Tests equality assertion of array and list
     */
    @Test
    public void arrayAndListAreEqual() {
        String hello = "HELLO", two = "2222";
        assertArrayEqualsList(null, null);
        ArrayList<String> strings = new ArrayList<String>();
        assertArrayEqualsList(new String[]{}, strings);
        strings.add(hello);
        assertArrayEqualsList(new String[]{hello}, strings);
        strings.add(two);
        assertArrayEqualsList(new String[]{hello, two}, strings);
        assertThrows(AssertionFailedError.class, () -> {
            assertArrayEqualsList(new String[]{hello}, strings);
        });
    }

    /**
     * Test generate random string
     */
    @Test
    public void testGenerateRandomString() {
        int length = 12351;
        assertEquals(length, generateRandomString(length).length());
    }

    /**
     * Generate randomString
     */
    public static String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(CHARACTERS.charAt(rnd.nextInt(CHARACTERS.length())));
        }
        return builder.toString();
    }
}
