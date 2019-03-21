package sg.edu.nus.comp.cs4218m1.impl.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.GlobTest;
import sg.edu.nus.comp.cs4218.impl.util.RegexArgument;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegexArgumentTest extends GlobTest {

    /**
     * test if *.txt expansion is correct
     *
     * @throws AbstractApplicationException
     * @throws ShellException
     * @throws IOException
     */
    @Test()
    void expandAllTxtFiles() throws ShellException, IOException {
        Assertions.fail("todo");
    }

    /**
     * TODO
     * test if a empty file effects the result
     *
     * @throws AbstractApplicationException
     * @throws ShellException
     * @throws IOException
     */
    @Test()
    void expandAllFiles() throws AbstractApplicationException, ShellException, IOException {
        RegexArgument argument = new RegexArgument(testDir + "/*");
        List<String> actual = argument.globFiles();
        assertEquals(allFilesList, actual);
    }

    /**
     * TODO
     * test if the folder effects the result
     *
     * @throws AbstractApplicationException
     * @throws ShellException
     * @throws IOException
     */
    @Test()
    void expandAllFolderFile() throws AbstractApplicationException, ShellException, IOException {
        Assertions.fail("todo");
    }

    @Test
    void construction() {
        RegexArgument arg = new RegexArgument();
        assertEquals("", arg.toString());
    }

    @Test
    void append() {
        RegexArgument arg = new RegexArgument();
        arg.append('s');
        arg.append('1');
        assertEquals("s1", arg.toString());
    }

    @Test
    void appendAsterisk() {
        RegexArgument arg = new RegexArgument();
        arg.appendAsterisk();
        assertEquals("*", arg.toString());
    }

    @Test
    void append_appendAsterisk() {
        RegexArgument arg = new RegexArgument();
        arg.appendAsterisk();
        arg.append('s');
        assertEquals("*s", arg.toString());
    }

    @Test
    void merge() {
        Assertions.fail("todo");
    }

    @Test
    void merge1() {
        Assertions.fail("todo");
    }

    @Test
    void isEmptyTrue() {
        RegexArgument argument = new RegexArgument();
        assertTrue(argument.isEmpty());
    }

    @Test
    void isEmptyFalsePlain() {
        RegexArgument argument = new RegexArgument();
        argument.append('x');
        assertFalse(argument.isEmpty());
    }

    @Test
    void isEmptyFalseRegex() {
        RegexArgument argument = new RegexArgument("^");
        assertFalse(argument.isEmpty());
    }
}