/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.exception.SedException;
import sg.edu.nus.comp.cs4218.impl.FileIOTestHelper;
import sg.edu.nus.comp.cs4218.impl.StringsToArrayHelper;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_ASTERISK;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_FILE_SEP;

class SedApplicationTest {

    public static final String UT_FILE_1_NAME = "sedUnitTest1.txt";
    public static final String UT_FILE_2_NAME = "sedUnitTest2.txt";
    public static final String UT_FILE_3_NAME = "sedUnitTest3.txt";
    public static final String UT_FILE_4_NAME = "sedUnitTest4.txt";
    public static final String FILE_STDOUT_TEST = "sedStdOut.txt";
    public static final String UT_NE_FILE_NAME = "sedUnitTestFileNotExist.txt";
    public static final String EMPTY_FILE_NAME = "sedUnitTestEmptyFile.txt";
    public static final String SED_FOLDER_NAME = "sedUnitTestFolder";

    public static final String UT_FILE_1_TEXT = "This file with single line for sed unit test. There is nothing special with this file actually.";
    public static final String UT_FILE_2_TEXT = "This file has a pattern < > we want to find.";
    public static final String UT_FILE_3_TEXT = "This file has a pattern < > we want to find < >, but need more< > of it.";
    public static final String UT_FILE_4_TEXT = "First line has one occurrence of the pattern < >." + StringUtils.STRING_NEWLINE +
            "Second line has a pattern < > we want to find < >, but need more< > of it.";
    public static final String FILE_2_FORMAT = "This file has a pattern %s we want to find.";
    public static final String FILE_3_FORMAT = "This file has a pattern < > we want to find %s, but need more< > of it.";
    public static final String FILE_4_FORMAT = "First line has one occurrence of the pattern < >." + StringUtils.STRING_NEWLINE +
            "Second line has a pattern < > we want to find < >, but need more%s of it.";

    public static final String STRING_ALPHA = "AllALPHAbets";
    public static final String STRING_NUM = "0123456789";
    public static final String STRING_NON_ALPHA = "?><{})(*&^%";
    public static final String STRING_ALPHA_NUM = "numbers4218";
    public static final String STRING_ALPNONALP = "Alphabets and :\"(&)(*) non-alphanumeric!!";
    public static final String STRING_NUMNONALP = "89()  >100? ";
    public static final String STRING_ALL_CHAR = "This (1) string will contain all!! characters 909?";

    // R as in REPLACEMENT
    public static final String R_STRING_FORMAT = "s/^/%s/";
    public static final String R_STRING_PART = "s/^/";
    public static final String DEFAULT_REGEXP = "^";
    public static final String PATTERN_TO_FIND = "< >";
    public static final String EMPTY_STRING = "";

    public static final int DEF_REPLACE_INDEX = 1;

    public static SedApplication sedApplication;

    @BeforeAll
    static void setUpBeforeEach() throws IOException {
        BufferedWriter writer1 = new BufferedWriter(new PrintWriter(UT_FILE_1_NAME));
        writer1.write(UT_FILE_1_TEXT);
        writer1.flush();
        writer1.close();

        BufferedWriter writer2 = new BufferedWriter(new PrintWriter(EMPTY_FILE_NAME));
        writer2.write(EMPTY_STRING);
        writer2.flush();
        writer2.close();

        BufferedWriter writer3 = new BufferedWriter(new PrintWriter(UT_FILE_2_NAME));
        writer3.write(UT_FILE_2_TEXT);
        writer3.flush();
        writer3.close();

        BufferedWriter writer4 = new BufferedWriter(new PrintWriter(UT_FILE_3_NAME));
        writer4.write(UT_FILE_3_TEXT);
        writer4.flush();
        writer4.close();

        BufferedWriter writer5 = new BufferedWriter(new PrintWriter(UT_FILE_4_NAME));
        writer5.write(UT_FILE_4_TEXT);
        writer5.flush();
        writer5.close();

        File directory = new File(SED_FOLDER_NAME);
        directory.mkdir();
    }

    @AfterAll
    static void tearDownAfterEach() {
        FileIOTestHelper.deleteMultipleFiles(UT_FILE_1_NAME, EMPTY_FILE_NAME,
                UT_FILE_2_NAME, UT_FILE_3_NAME, UT_FILE_4_NAME, SED_FOLDER_NAME);
    }

    @BeforeEach
    void setUp() {
        sedApplication = spy(new SedApplication());
    }

    @AfterEach
    void tearDown() {
        File file = new File(FILE_STDOUT_TEST);
        if (file.exists()) {
            file.delete();
        }
    }

    /*******************************************************************
     Start of tests for run method within sedApplication
     ******************************************************************/

    @Test
    void runReplacementStringAllAlphabetsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(String.format(R_STRING_FORMAT, STRING_ALPHA),
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runReplacementStringAllNumbersTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(String.format(R_STRING_FORMAT, STRING_NUM),
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_NUM, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runReplacementStringAllNonAlphanumericTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(String.format(R_STRING_FORMAT, STRING_NON_ALPHA),
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_NON_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runReplacementStringAlphabetsAndNumbersTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(String.format(R_STRING_FORMAT, STRING_ALPHA_NUM),
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA_NUM, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runReplacementStringAlphabetsAndNonAlphanumericTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(String.format(R_STRING_FORMAT, STRING_ALPNONALP),
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPNONALP, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runReplacementStringNumbersAndNonAlphanumericTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(String.format(R_STRING_FORMAT, STRING_NUMNONALP),
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_NUMNONALP, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runReplacementStringAllCharactersTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(String.format(R_STRING_FORMAT, STRING_ALL_CHAR),
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALL_CHAR, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runNumericalDelimiter0Test() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("s0^0" + STRING_ALPHA + "0",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runNumericalDelimiter5Test() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("s5^5" + STRING_ALPHA + "5",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runNumericalDelimiter9Test() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("s9^9" + STRING_ALPHA + "9",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runAlphabeticalDelimiterSmallaTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("sa^a" + STRING_ALPHA + "a",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runAlphabeticalDelimiterCapitalATest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("sA^A" + STRING_NUM + "A",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_NUM, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runAlphabeticalDelimiterSmallzTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("sz^z" + STRING_ALPHA + "z",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runAlphabeticalDelimiterCapitalZTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("sZ^Z" + STRING_ALPHA + "Z",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runNonAlphanumericDelimiterSpaceCharacterTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("s ^ " + STRING_ALPHA + " ",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runNonAlphanumericDelimiterVerticalBarTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("s|^|" + STRING_ALPHA + "|",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runNonAlphanumericDelimiterAsteriskTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("s*^*" + STRING_ALPHA + CHAR_ASTERISK,
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runNonAlphanumericDelimiterSingleQuoteTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("s'^'" + STRING_ALPHA + "'",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, DEF_REPLACE_INDEX, UT_FILE_1_NAME);
    }

    @Test
    void runFirstArgumentNotSTest1() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("t/^/" + STRING_ALPHA + STRING_FILE_SEP,
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        assertThrows(SedException.class, () -> sedApplication.run(args, null, fos));
    }

    @Test
    void runFirstArgumentNotSTest2() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("S/^/" + STRING_ALPHA + STRING_FILE_SEP,
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        assertThrows(SedException.class, () -> sedApplication.run(args, null, fos));
    }

    @Test
    void runSecondArgumentMissingTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray("s//" + STRING_ALPHA + STRING_FILE_SEP,
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        assertThrows(SedException.class, () -> sedApplication.run(args, null, fos));
    }

    @Test
    void runFourthArgumentZeroTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(R_STRING_PART + STRING_ALPHA + "/0",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        assertThrows(SedException.class, () -> sedApplication.run(args, null, fos));
    }

    @Test
    void runFourthArgumentNegativeOneTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(R_STRING_PART + STRING_ALPHA + "/-1",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        assertThrows(SedException.class, () -> sedApplication.run(args, null, fos));
    }

    @Test
    void runFourthArgumentNegativeFiveTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(R_STRING_PART + STRING_ALPHA + "/-5",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        assertThrows(SedException.class, () -> sedApplication.run(args, null, fos));
    }

    @Test
    void runFourthArgumentAsteriskTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(R_STRING_PART + STRING_ALPHA + "/*",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        assertThrows(SedException.class, () -> sedApplication.run(args, null, fos));
    }

    @Test
    void runFourthArgumentAlphabetSTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(R_STRING_PART + STRING_ALPHA + "/s",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        assertThrows(SedException.class, () -> sedApplication.run(args, null, fos));
    }

    @Test
    void runExtraDelimitersTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(R_STRING_PART + STRING_ALPHA + "// ",
                UT_FILE_1_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        assertThrows(SedException.class, () -> sedApplication.run(args, null, fos));
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void runMoreThanOneFileArgumentTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(R_STRING_PART + STRING_ALPHA + STRING_FILE_SEP,
                UT_FILE_1_NAME, UT_FILE_2_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        sedApplication.run(args, null, fos);
        verify(sedApplication, Mockito.times(1)).replaceSubstringInFile(DEFAULT_REGEXP,
                STRING_ALPHA, -1, UT_FILE_1_NAME);
    }
     */

    @Test
    void runFileAndFolderArgumentTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(R_STRING_PART + STRING_ALPHA + STRING_FILE_SEP,
                SED_FOLDER_NAME, UT_FILE_2_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        assertThrows(SedException.class, () -> sedApplication.run(args, null, fos));
    }

    /**************************************************************************
     Start of tests for replaceSubstringInFile method within sedApplication
     *************************************************************************/

    @Test
    void replaceSubstringInFileSubstringAllAlphabetTest() throws Exception {
        String expectedOutput = STRING_ALPHA + UT_FILE_1_TEXT;
        String actualOutput = sedApplication.replaceSubstringInFile(DEFAULT_REGEXP, STRING_ALPHA,
                DEF_REPLACE_INDEX, UT_FILE_1_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileSubstringAllNumbersTest() throws Exception {
        String expectedOutput = STRING_NUM + UT_FILE_1_TEXT;
        String actualOutput = sedApplication.replaceSubstringInFile(DEFAULT_REGEXP, STRING_NUM,
                DEF_REPLACE_INDEX, UT_FILE_1_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileSubstringAllNoneAlphanumericTest() throws Exception {
        String expectedOutput = STRING_NON_ALPHA + UT_FILE_1_TEXT;
        String actualOutput = sedApplication.replaceSubstringInFile(DEFAULT_REGEXP, STRING_NON_ALPHA,
                DEF_REPLACE_INDEX, UT_FILE_1_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileSubstringAlphabetAndNumbersTest() throws Exception {
        String expectedOutput = STRING_ALPHA_NUM + UT_FILE_1_TEXT;
        String actualOutput = sedApplication.replaceSubstringInFile(DEFAULT_REGEXP, STRING_ALPHA_NUM,
                DEF_REPLACE_INDEX, UT_FILE_1_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileSubstringAlphabetAndNoneAlphanumericTest() throws Exception {
        String expectedOutput = STRING_ALPNONALP + UT_FILE_1_TEXT;
        String actualOutput = sedApplication.replaceSubstringInFile(DEFAULT_REGEXP, STRING_ALPNONALP,
                DEF_REPLACE_INDEX, UT_FILE_1_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileSubstringNumbersAndNoneAlphanumericTest() throws Exception {
        String expectedOutput = STRING_NUMNONALP + UT_FILE_1_TEXT;
        String actualOutput = sedApplication.replaceSubstringInFile(DEFAULT_REGEXP, STRING_NUMNONALP,
                DEF_REPLACE_INDEX, UT_FILE_1_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileSubstringAllCharactersTest() throws Exception {
        String expectedOutput = STRING_ALL_CHAR + UT_FILE_1_TEXT;
        String actualOutput = sedApplication.replaceSubstringInFile(DEFAULT_REGEXP, STRING_ALL_CHAR,
                DEF_REPLACE_INDEX, UT_FILE_1_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileFileNotExistTest() {
        assertThrows(SedException.class, () -> sedApplication.replaceSubstringInFile(DEFAULT_REGEXP, STRING_ALL_CHAR,
                DEF_REPLACE_INDEX, UT_NE_FILE_NAME));

    }

    @Test
    void replaceSubstringInFileFolderGivenTest() {
        assertThrows(SedException.class, () -> sedApplication.replaceSubstringInFile(DEFAULT_REGEXP, STRING_ALL_CHAR,
                DEF_REPLACE_INDEX, SED_FOLDER_NAME));

    }

    @Test
    void replaceSubstringInFileEmptyFileTest() throws Exception {
        // Because file is empty, the default regex cannot detect any suitable pattern
        String expectedOutput = EMPTY_STRING;
        String actualOutput = sedApplication.replaceSubstringInFile(DEFAULT_REGEXP, STRING_ALPHA,
                DEF_REPLACE_INDEX, EMPTY_FILE_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileDefaultReplacementIndexFoundPatternTest() throws Exception {
        String expectedOutput = String.format(FILE_2_FORMAT, STRING_ALPHA);
        String actualOutput = sedApplication.replaceSubstringInFile(PATTERN_TO_FIND, STRING_ALPHA,
                DEF_REPLACE_INDEX, UT_FILE_2_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileExplicitReplacementIndex1FoundPatternTest() throws Exception {
        String expectedOutput = String.format(FILE_2_FORMAT, STRING_ALPHA);
        String actualOutput = sedApplication.replaceSubstringInFile(PATTERN_TO_FIND, STRING_ALPHA,
                1, UT_FILE_2_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileExplicitReplacementIndex2FoundPatternTest() throws Exception {
        String expectedOutput = String.format(FILE_3_FORMAT, STRING_ALPHA);
        String actualOutput = sedApplication.replaceSubstringInFile(PATTERN_TO_FIND, STRING_ALPHA,
                2, UT_FILE_3_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInFileExplicitReplacementIndex3Test() throws Exception {
        String expectedOutput = String.format(FILE_4_FORMAT, STRING_ALPHA);
        String actualOutput = sedApplication.replaceSubstringInFile(PATTERN_TO_FIND, STRING_ALPHA,
                3, UT_FILE_4_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    /**************************************************************************
     Start of tests for replaceSubstringInStdin method within sedApplication
     *************************************************************************/


    @Test
    void replaceSubstringInStdinSubstringAllAlphabetTest() throws Exception {
        String expectedOutput = STRING_ALPHA + UT_FILE_1_TEXT;

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_1_NAME));

        String actualOutput = sedApplication.replaceSubstringInStdin(DEFAULT_REGEXP, STRING_ALPHA,
                DEF_REPLACE_INDEX, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinSubstringAllNumbersTest() throws Exception {
        String expectedOutput = STRING_NUM + UT_FILE_1_TEXT;

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_1_NAME));

        String actualOutput = sedApplication.replaceSubstringInStdin(DEFAULT_REGEXP, STRING_NUM,
                DEF_REPLACE_INDEX, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinSubstringAllNoneAlphanumericTest() throws Exception {
        String expectedOutput = STRING_NON_ALPHA + UT_FILE_1_TEXT;

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_1_NAME));

        String actualOutput = sedApplication.replaceSubstringInStdin(DEFAULT_REGEXP, STRING_NON_ALPHA,
                DEF_REPLACE_INDEX, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinSubstringAlphabetAndNumbersTest() throws Exception {
        String expectedOutput = STRING_ALPHA_NUM + UT_FILE_1_TEXT;

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_1_NAME));

        String actualOutput = sedApplication.replaceSubstringInStdin(DEFAULT_REGEXP, STRING_ALPHA_NUM,
                DEF_REPLACE_INDEX, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinSubstringAlphabetAndNoneAlphanumericTest() throws Exception {
        String expectedOutput = STRING_ALPNONALP + UT_FILE_1_TEXT;

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_1_NAME));

        String actualOutput = sedApplication.replaceSubstringInStdin(DEFAULT_REGEXP, STRING_ALPNONALP,
                DEF_REPLACE_INDEX, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinSubstringNumbersAndNoneAlphanumericTest() throws Exception {
        String expectedOutput = STRING_NUMNONALP + UT_FILE_1_TEXT;

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_1_NAME));

        String actualOutput = sedApplication.replaceSubstringInStdin(DEFAULT_REGEXP, STRING_NUMNONALP,
                DEF_REPLACE_INDEX, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinSubstringAllCharactersTest() throws Exception {
        String expectedOutput = STRING_ALL_CHAR + UT_FILE_1_TEXT;

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_1_NAME));

        String actualOutput = sedApplication.replaceSubstringInStdin(DEFAULT_REGEXP, STRING_ALL_CHAR,
                DEF_REPLACE_INDEX, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinEmptyFileTest() throws Exception {
        // Because file is empty, the default regex cannot detect any suitable pattern
        String expectedOutput = EMPTY_STRING;

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(EMPTY_FILE_NAME));
        String actualOutput = sedApplication.replaceSubstringInStdin(DEFAULT_REGEXP, STRING_ALPHA,
                DEF_REPLACE_INDEX, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinClosedStdinTest() throws Exception {

        // Open inputStream for replaceSubstringInStdin and close it
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(EMPTY_FILE_NAME));
        bis.close();
        assertThrows(SedException.class, () -> sedApplication.replaceSubstringInStdin(DEFAULT_REGEXP, STRING_ALPHA,
                DEF_REPLACE_INDEX, bis));
    }

    @Test
    void replaceSubstringInStdinDefaultReplacementIndexFoundPatternTest() throws Exception {
        String expectedOutput = String.format(FILE_2_FORMAT, STRING_ALPHA);

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_2_NAME));
        String actualOutput = sedApplication.replaceSubstringInStdin(PATTERN_TO_FIND, STRING_ALPHA,
                DEF_REPLACE_INDEX, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinExplicitReplacementIndex1FoundPatternTest() throws Exception {
        String expectedOutput = String.format(FILE_2_FORMAT, STRING_ALPHA);

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_2_NAME));
        String actualOutput = sedApplication.replaceSubstringInStdin(PATTERN_TO_FIND, STRING_ALPHA,
                1, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinExplicitReplacementIndex2FoundPatternTest() throws Exception {
        String expectedOutput = String.format(FILE_3_FORMAT, STRING_ALPHA);

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_3_NAME));
        String actualOutput = sedApplication.replaceSubstringInStdin(PATTERN_TO_FIND, STRING_ALPHA,
                2, bis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void replaceSubstringInStdinExplicitReplacementIndex3Test() throws Exception {
        String expectedOutput = String.format(FILE_4_FORMAT, STRING_ALPHA);

        // Open inputStream for replaceSubstringInStdin
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(UT_FILE_4_NAME));
        String actualOutput = sedApplication.replaceSubstringInStdin(PATTERN_TO_FIND, STRING_ALPHA,
                3, bis);

        assertEquals(expectedOutput, actualOutput);
    }


}
