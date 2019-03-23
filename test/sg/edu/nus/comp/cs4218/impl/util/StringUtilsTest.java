package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    /**
     * String is blank
     */
    @Test
    void isBlank() {
        String str = "";
        assertTrue(StringUtils.isBlank(str));
    }

    /**
     * String is not blank
     */
    @Test
    void isBlankFalse() {
        String str = "asd";
        assertFalse(StringUtils.isBlank(str));
    }

    /**
     * Character should be multiplied
     */
    @Test
    void multiplyChar() {
        char chr = 'c';
        String expected = "ccccc";
        assertEquals(expected, StringUtils.multiplyChar(chr, 5));
    }

    /**
     * String should be tokenized
     */
    @Test
    void tokenize() {
        String[] tokens = StringUtils.tokenize("asd 123 \\* '*'");
        String[] expected = {"asd", "123", "\\*", "'*'"};
        assertArrayEquals(expected, tokens);
    }

    /**
     * String is representation of integer
     */
    @Test
    void isNumbericTrue() {
        String str = "123";
        assertTrue(StringUtils.isNumberic(str));
    }

    /**
     * String is representation of integer
     */
    @Test
    void isNumbericTrueNegative() {
        String str = "-123";
        assertTrue(StringUtils.isNumberic(str));
    }

    /**
     * String is not a representation of integer
     */
    @Test
    void isNumbericFalse() {
        String str = "abc";
        assertFalse(StringUtils.isNumberic(str));
        str = "";
        assertFalse(StringUtils.isNumberic(str));
    }

    /**
     * First word in string is a representation of integer
     */
    @Test
    void getFirstNum() {
        String str = "123 asdas";
        assertEquals(123, StringUtils.getFirstNum(str));
    }

    /**
     * First word in string is a representation of negative integer
     */
    @Test
    void getFirstNumNegative() {
        String str = "-123 asdas";
        assertEquals(-123, StringUtils.getFirstNum(str));
    }

    /**
     * First word in string is not representation of integer
     */
    @Test
    void getFirstNumError() {
        String str = "asdas";
        assertThrows(Exception.class, () -> {
            StringUtils.getFirstNum(str);
        });
    }

    /**
     * Strings should be prepended with "13"
     */
    @Test
    void prependStringToStrings() {
        String[] strs = {"abc", "cde"};
        String prepend = "13";
        String[] expected = {"13abc", "13cde"};
        assertArrayEquals(expected, StringUtils.prependStringToStrings(prepend, strs));
    }
}