package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

class IOUtilsTest {

    @Test
    void openInputStream() {
        fail("TODO");
    }

    @Test
    void openOutputStream() {
        fail("TODO");
    }

    @Test
    void closeInputStream() {
        fail("TODO");
    }

    @Test
    void closeOutputStream() {
        fail("TODO");
    }

    @Test
    void resolveFilePath() {
        fail("TODO");
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
        assertThrows(IOException.class, ()->{
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
     * @throws IOException
     */
    @Test
    void stringFromInputStream_empty() throws IOException {
        InputStream inputStream = mock(InputStream.class);
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
        assertThrows(IOException.class, ()->{
            IOUtils.stringFromInputStream(null);
        });
    }

    /**
     * Convert mocked InputStream into String
     */
    @Test
    void stringFromInputStream_oneChar() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        when(inputStream.read()).thenReturn(97, -1);
        String actual = IOUtils.stringFromInputStream(inputStream);
        String expected = "a";
        assertEquals(expected, actual);
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
        InputStream inputStream = mock(InputStream.class);
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
        assertThrows( IOException.class, ()->{
            IOUtils.stringsFromInputStream(inputStream);
        });
    }
}