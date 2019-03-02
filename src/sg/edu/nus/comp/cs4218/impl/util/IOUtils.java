package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public final class IOUtils {
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
    public static InputStream stringToInputStream(String arg) {
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
     * @param in input stream
     * @return String from input stream
     * @throws IOException
     */
    public static String stringFromInputStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String string;
        if(!reader.ready()) {
            string = "";
        }else {
            string = reader
                    .lines()
                    .collect(Collectors.joining(STRING_NEWLINE));
        }
        return string;
    }

    /**
     * Converts input from stream into list of String representing lines
     * @param in input stream
     * @return String from input stream
     * @throws IOException
     */
    public static List<String> stringsFromInputStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        ArrayList<String> out = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            out.add(line);
        }
        return out;
    }
}
