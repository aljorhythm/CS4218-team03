package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isBlank() {
        String str = "";
        assertTrue(StringUtils.isBlank(str));
    }

    @Test
    void isBlankFalse() {
        String str = "asd";
        assertFalse(StringUtils.isBlank(str));
    }

    @Test
    void multiplyChar() {
        char chr = 'c';
        String expected = "ccccc";
        assertEquals(expected, StringUtils.multiplyChar(chr, 5));
    }

    @Test
    void tokenize() {
        String[] tokens = StringUtils.tokenize("asd 123 \\* '*'");
        String[] expected = {"asd", "123", "\\*", "'*'"};
        assertArrayEquals(expected, tokens);
    }

    @Test
    void isNumbericTrue() {
        String str = "123";
        assertTrue(StringUtils.isNumberic(str));
    }

    @Test
    void isNumbericFalse() {
        String str = "abc";
        assertFalse(StringUtils.isNumberic(str));
        str = "";
        assertFalse(StringUtils.isNumberic(str));
    }

    @Test
    void getFirstNum() {
        String str = "123 asdas";
        assertEquals(123, StringUtils.getFirstNum(str));
    }

    @Test
    void getFirstNumNegative() {
        String str = "-123 asdas";
        assertEquals(-123, StringUtils.getFirstNum(str));
    }

    @Test
    void getFirstNumError() {
        String str = "asdas";
        assertThrows(Exception.class, () -> {
            StringUtils.getFirstNum(str);
        });
    }

    @Test
    void prependStringToStrings() {
        String[] strs = {"abc", "cde"};
        String prepend = "13";
        String[] expected = {"13abc", "13cde"};
        assertArrayEquals(expected, StringUtils.prependStringToStrings(prepend, strs));
    }
}