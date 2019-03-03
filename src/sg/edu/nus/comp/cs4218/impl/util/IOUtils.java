package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public final class IOUtils {
    public static String NULL_STREAM = "null_stream";
    public static String NULL_STRING= "null_string";

    private IOUtils() {
    }

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

        return result.toString(CHARSET_UTF8).replaceAll("\n\r", STRING_NEWLINE);
    }

    /**
     * Converts input from stream into list of String representing lines
     *
     * @param in input stream
     * @return String from input stream
     * @throws IOException
     */
    public static String[] stringsFromInputStream(InputStream in) throws IOException {
        if (in == null) {
            throw new IOException(NULL_STREAM);
        }

        String string = stringFromInputStream(in);
        if(string.isEmpty()) {
            return new String[]{};
        }

        return string.split(STRING_NEWLINE);
    }
}
