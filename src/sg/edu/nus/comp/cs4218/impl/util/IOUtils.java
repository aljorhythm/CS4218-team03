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
    public static final String NULL_STREAM = "null_stream";
    public static final String NULL_STRING= "null_string";

    private IOUtils() {
    }

    /**
     * Open an inputStream based on the file name.
     *
     * @param fileName String containing file name.
     * @return InputStream of file opened.
     * @throws FileNotFoundException If file destination is inaccessible.
     */
    public static InputStream openInputStream(String fileName) throws FileNotFoundException {
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
    public static void closeInputStream(InputStream inputStream) throws IOException {
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
}
