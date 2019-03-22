package sg.edu.nus.comp.cs4218m1.impl.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.DirectoryStructureTest;
import sg.edu.nus.comp.cs4218.impl.util.RegexArgument;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegexArgumentTest extends DirectoryStructureTest {

    private static String originalWorkingDirectory;

    /**
     * Set working directory for tests
     */
    @BeforeAll
    static void setWorkingDirectory() {
        originalWorkingDirectory = Environment.currentDirectory;
        Environment.currentDirectory = testRootDir;
    }

    /**
     * Revert working directory
     */
    @AfterAll
    static void revertWorkingDirectory() {
        Environment.currentDirectory = originalWorkingDirectory;
    }
    /**
     * test if *.txt expansion is correct
     *
     * @throws AbstractApplicationException
     * @throws ShellException
     * @throws IOException
     */
    @Test()
    void expandAllTxtFiles() throws ShellException, IOException {
        RegexArgument arg = new RegexArgument();
        arg.appendAsterisk();
        arg.append('.');
        arg.append('t');
        arg.append('x');
        arg.append('t');
        List<String> globbed = arg.globFiles();
        String[] expected = Arrays.stream(allTestRootFiles).filter(s -> s.endsWith(".txt")).toArray(String[]::new);
        TestUtils.assertArrayEqualsList(expected, globbed);
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
        RegexArgument arg = new RegexArgument();
        arg.appendAsterisk();
        List<String> globbed = arg.globFiles();
        TestUtils.assertArrayEqualsList(allTestRootFiles, globbed);
    }

    /**
     * Construct regex argument
     */
    @Test
    void construction() {
        RegexArgument arg = new RegexArgument();
        assertEquals("", arg.toString());
    }

    /**
     * Append characters to regex argument
     */
    @Test
    void append() {
        RegexArgument arg = new RegexArgument();
        arg.append('s');
        arg.append('1');
        assertEquals("s1", arg.toString());
    }

    /**
     * Append asterisk to regex argument
     */
    @Test
    void appendAsterisk() {
        RegexArgument arg = new RegexArgument();
        arg.appendAsterisk();
        assertEquals("*", arg.toString());
    }

    /**
     * Append string and asterisk to regex argument
     */
    @Test
    void append_appendAsterisk() {
        RegexArgument arg = new RegexArgument();
        arg.appendAsterisk();
        arg.append('s');
        assertEquals("*s", arg.toString());
    }

    /**
     * Should merge members properly
     */
    @Test
    void merge() {
        RegexArgument arg = new RegexArgument();
        arg.appendAsterisk();
        arg.append('s');
        RegexArgument arg2 = new RegexArgument();
        arg.append('s');
        arg.merge(arg2);
        assertEquals("*ss", arg.toString());
    }

    /**
     * Argument is empty
     */
    @Test
    void isEmptyTrue() {
        RegexArgument argument = new RegexArgument();
        assertTrue(argument.isEmpty());
    }

    /**
     * Argument is not empty
     */
    @Test
    void isEmptyFalsePlain() {
        RegexArgument argument = new RegexArgument();
        argument.append('x');
        assertFalse(argument.isEmpty());
    }

    /**
     * Argument representing regex is not empty
     */
    @Test
    void isEmptyFalseRegex() {
        RegexArgument argument = new RegexArgument("^");
        assertFalse(argument.isEmpty());
    }
}