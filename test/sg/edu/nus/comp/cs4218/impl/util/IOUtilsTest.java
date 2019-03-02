package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
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
     * Tests conversion of string into InputStream
     */
    @Test
    void stringToInputStream() {
        String stringData = "abcde12345  ";
        InputStream inputStream = IOUtils.stringToInputStream(stringData);
        String actual = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining(STRING_NEWLINE));
        assertEquals(actual, stringData);
    }

    /**
     * Tests conversion of an iterable of strings into InputStream
     */
    @Test
    void stringsToInputStream() {
        String[] strings = {"abcde", "12345", "asdasd"};

        InputStream inputStream = IOUtils.stringsToInputStream(strings);
        String actual = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining(STRING_NEWLINE));

        String stringsJoined = String.join(STRING_NEWLINE, strings);
        assertEquals(actual, stringsJoined);
    }

    @Test
    void stringFromInputStream() throws IOException {
        String[] strings = {"abcde", "12345", "asdasd"};
        String inputString = String.join(STRING_NEWLINE, strings);
        InputStream inputStream = IOUtils.stringToInputStream(inputString);
        String actual = IOUtils.stringFromInputStream(inputStream);
        String expected = inputString;
        assertEquals(expected, actual);
    }

    @Test
    void stringsFromInputStream() throws IOException {
        String[] strings = {"abcde", "12345", "asdasd"};
        String inputString = String.join(STRING_NEWLINE, strings);
        InputStream inputStream = IOUtils.stringToInputStream(inputString);
        List<String> actualArray = IOUtils.stringsFromInputStream(inputStream);
        String[] expected = strings;
        assertArrayEquals(expected, actualArray.toArray());
    }
}