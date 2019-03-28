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
import static sg.edu.nus.comp.cs4218m1.TestUtils.CHAR_ASTERISK;
import static sg.edu.nus.comp.cs4218m1.TestUtils.STRING_ASTERISK;

class RegexArgumentTest extends DirectoryStructureTest {

    private static String oriWorkingDir;

    /**
     * Set working directory for tests
     */
    @BeforeAll
    static void setWorkingDirectory() {
        oriWorkingDir = Environment.currentDirectory;
        Environment.currentDirectory = testRootDir;
    }

    /**
     * Revert working directory
     */
    @AfterAll
    static void revertWorkingDirectory() {
        Environment.currentDirectory = oriWorkingDir;
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
        String[] expected = Arrays
                .stream(allTestRootFiles)
                .filter(s -> s.endsWith(".txt"))
                .toArray(String[]::new);
        TestUtils.assertArrayEqualsList(expected, globbed);
    }

    /**
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

    @Test()
    void expandSubDirectoryInSubDirectory() {
        RegexArgument arg = new RegexArgument();
        System.out.println("*");
        String glob = TestUtils.NON_EXISTENT_DIR;
        for (char s : glob.toCharArray()) {
            if (s == CHAR_ASTERISK) {
                arg.appendAsterisk();
            } else {
                arg.append(s);
            }
        }
        List<String> globbed = arg.globFiles();
        System.out.println(Arrays.toString(globbed.toArray(new String[]{})));
    }

    /**
     * Should return glob pattern if no files are found
     *
     * @throws AbstractApplicationException
     * @throws ShellException
     * @throws IOException
     */
    @Test()
    void expandNonExistentFile() throws AbstractApplicationException, ShellException, IOException {
        RegexArgument arg = new RegexArgument();
        String glob = TestUtils.NON_EXISTENT_DIR;
        for (char s : glob.toCharArray()) {
            arg.append(s);
        }
        List<String> globbed = arg.globFiles();
        assertEquals(Arrays.asList(new String[]{glob}), globbed);
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
        assertEquals(STRING_ASTERISK, arg.toString());
    }

    /**
     * Append string and asterisk to regex argument
     */
    @Test
    void appendAndAppendAsterisk() {
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