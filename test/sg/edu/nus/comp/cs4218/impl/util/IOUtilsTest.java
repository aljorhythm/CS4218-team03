package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.TestUtils;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class IOUtilsTest {
    /**
     * Stream that can be closed. write(int) and read(int) throws exception when closed.
     */
    static class CloseableOutputMockStream extends OutputStream {
        boolean isClosed = false;

        @Override
        public void write(int b) throws IOException {
            if (isClosed) {
                throw new IOException();
            }
        }

        @Override
        public void close() throws IOException {
            super.close();
            isClosed = true;
        }
    }

    /**
     * Stream that can be closed. write(int) and read(int) throws exception when closed.
     */
    static class CloseableInputMockStream extends InputStream {
        boolean isClosed = false;

        @Override
        public int read() throws IOException {
            if (isClosed) {
                throw new IOException();
            }
            return 1;
        }

        @Override
        public void close() throws IOException {
            super.close();
            isClosed = true;
        }
    }

    String currentDir = Paths
            .get(Environment.currentDirectory)
            .toAbsolutePath()
            .toString();

    static String testDataDir = TestUtils.pathToTestDataSubdir("ioTests");
    static String out1 = testDataDir + CHAR_FILE_SEP + "out1.out";
    static String in1 = testDataDir + CHAR_FILE_SEP + "abc.in";

    @BeforeAll
    static void init() {
        deleteOutputFiles();
    }

    /**
     * Force deletes all output files
     */
    static void deleteOutputFiles() {
        String[] outputFiles = {
                out1
        };

        for (String fileName : outputFiles) {
            forceDelete(fileName);
        }
    }

    /**
     * Deletes a file. Will not throw exceptions.
     * @param fileName
     */
    static void forceDelete(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    @Test
    void openInputStream() throws ShellException, IOException {
        InputStream inputStream = IOUtils.openInputStream(in1);
        byte[] bytes = new byte[5];
        inputStream.read(bytes);
        inputStream.close();
        assertArrayEquals("abcde".getBytes(), bytes);
    }

    @Test
    void openOutputStream() throws ShellException, IOException {
        OutputStream outputStream = IOUtils.openOutputStream(out1);
        outputStream.write(new byte[]{97});
        outputStream.close();
        Scanner scanner = new Scanner(new File(out1));
        assertEquals("a", scanner.nextLine());
    }

    @Test
    void closeInputStream_close() throws ShellException, IOException {
        InputStream stream = new CloseableInputMockStream();
        IOUtils.closeInputStream(stream);
        assertThrows(IOException.class, () -> {
            stream.read();
        });
    }

    @Test
    void closeOutputStream_null_doNothing() throws ShellException, IOException {
        IOUtils.closeOutputStream(null);
    }

    @Test
    void closeOutputStream_close() throws ShellException, IOException {
        OutputStream stream = new CloseableOutputMockStream();
        IOUtils.closeOutputStream(stream);
        assertThrows(IOException.class, () -> {
            stream.write(1);
        });
    }

    @Test
    void resolveFilePath() {
        String fileName = "abc.txt";
        String expected = currentDir + CHAR_FILE_SEP + fileName;
        String actual = IOUtils
                .resolveFilePath(fileName)
                .toString();
        assertEquals(expected, actual);
    }

    /**
     * Tests conversion of string into InputStream.
     * Assumes IOUtils.stringFromInputStream is correct
     */
    @Test
    void stringToInputStream() throws IOException {
        String stringData = "abcde12345  ";
        InputStream inputStream = IOUtils.stringToInputStream(stringData);
        String actual = IOUtils.stringFromInputStream(inputStream);
        assertEquals(stringData, actual);
    }

    /**
     * Should fail converting null string
     */
    @Test
    void stringToInputStream_null() throws IOException {
        String stringData = null;
        assertThrows(IOException.class, () -> {
            InputStream inputStream = IOUtils.stringToInputStream(stringData);
        });
    }

    /**
     * Tests conversion of string into InputStream
     */
    @Test
    void stringToInputStream_empty() throws IOException {
        String stringData = "";
        InputStream inputStream = IOUtils.stringToInputStream(stringData);
        String actual = IOUtils.stringFromInputStream(inputStream);
        assertEquals(stringData, actual);
    }

    /**
     * Tests conversion of an iterable of strings into InputStream
     */
    @Test
    void stringsToInputStream() throws IOException {
        String[] strings = {"abcde", "12345", "asdasd"};
        String expected = String.join(STRING_NEWLINE, strings);
        InputStream inputStream = IOUtils.stringsToInputStream(strings);
        String actual = IOUtils.stringFromInputStream(inputStream);
        assertEquals(expected, actual);
    }

    /**
     * Convert InputStream with line breaks to String
     *
     * @throws IOException
     */
    @Test
    void stringFromInputStream() throws IOException {
        String[] strings = {"abcde", "12345", "asdasd"};
        String inputString = String.join(STRING_NEWLINE, strings);
        InputStream inputStream = IOUtils.stringToInputStream(inputString);
        String actual = IOUtils.stringFromInputStream(inputStream);
        String expected = inputString;
        assertEquals(expected, actual);
    }

    /**
     * Convert empty InputStream to String
     *
     * @throws IOException
     */
    @Test
    void stringFromInputStream_empty() throws IOException {
        InputStream inputStream = mock(InputStream.class, Mockito.CALLS_REAL_METHODS);
        when(inputStream.read()).thenReturn(-1);
        String actual = IOUtils.stringFromInputStream(inputStream);
        String expected = "";
        assertEquals(expected, actual);
    }

    /**
     * Should fail attempt to convert null InputStream to String
     */
    @Test
    void stringFromInputStream_null() {
        InputStream inputStream = null;
        assertThrows(IOException.class, () -> {
            IOUtils.stringFromInputStream(null);
        });
    }

    /**
     * Convert mocked InputStream into String
     */
    @Test
    void stringsFromInputStream() throws IOException {
        String[] strings = {"abcde", "12345", "asdasd"};
        String inputString = String.join(STRING_NEWLINE, strings);
        InputStream inputStream = IOUtils.stringToInputStream(inputString);
        String[] actualArray = IOUtils.stringsFromInputStream(inputStream);
        String[] expected = strings;
        assertArrayEquals(expected, actualArray);
    }

    /**
     * Convert mocked InputStream into String
     */
    @Test
    void stringsFromInputStream_empty() throws IOException {
        InputStream inputStream = mock(InputStream.class, Mockito.CALLS_REAL_METHODS);
        when(inputStream.read()).thenReturn(-1);
        String[] actual = IOUtils.stringsFromInputStream(inputStream);
        String[] expected = new String[]{};
        assertArrayEquals(expected, actual);
    }

    /**
     * Should fail converting null InputStream
     */
    @Test
    void stringsFromInputStream_null() throws IOException {
        InputStream inputStream = null;
        assertThrows(IOException.class, () -> {
            IOUtils.stringsFromInputStream(inputStream);
        });
    }
}