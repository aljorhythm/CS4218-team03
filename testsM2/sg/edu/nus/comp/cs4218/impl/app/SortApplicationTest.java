/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl.app;import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.FileIOTestHelper;
import sg.edu.nus.comp.cs4218.impl.SortApplicationSortingHelper;
import sg.edu.nus.comp.cs4218.impl.StringsToArrayHelper;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SortApplicationTest {


    public static final String ALL_CHAR_NAME = "sortUnitTest1.txt";
    // SAC = Small And Capital
    public static final String SAC_LETTER_NAME = "sortUnitTest2.txt";
    public static final String SLETTER_NUM_NAME = "sortUnitTest3.txt";
    // SL means Small Letter
    public static final String SL_OTHERS_NAME = "sortUnitTest4.txt";
    public static final String CLETTER_NUM_NAME = "sortUnitTest5.txt";
    // CL means Capital Letter
    public static final String CL_OTHERS_NAME = "sortUnitTest6.txt";
    public static final String NUM_OTHERS_NAME = "sortUnitTest7.txt";
    public static final String STDOUT_NAME = "sortUnitTestStdout.txt";
    public static final String FILE_NOT_EXIST = "fileThatDoesNotExist.txt";

    public static final String ALL_CHAR = "a\nA\nb\n10\n0\n2\n$\n#\n%\n"
            + "\nabc8\nabc5\nABCD\nABC9";
    public static final String SAC_LETTER = "c\na\nb\nabc\naba\nabb\nC\nA\nB\nABC\nABA\n" +
            "ABB\naBcDa\naBcDA";
    public static final String SLETTER_AND_NUM = "c\na\nb\nabc\naba\nabb\n0\n1\n10\n8\n9\nab8\nab0";
    public static final String SL_OTHERS = "c\na\nb\nabc\naba\nabb\n.\n/\n?\n\n?]\n?[\n?^&\n"
            + "ab8\nab0\nab(\n?>ab\n?>aB";
    public static final String CLETTER_NUM = "C\nA\nB\n1\n0\n2\n22\n10\nABC\nABD\nAB2\nAB0\n"
            + "AB01\nAB10\n10C\n10A\n10C5B\n10C5A";
    public static final String CL_OTHERS = "C\nA\nB\n)\n+\n%\n))\nABC\nABD\nAB,\nAB#\n"
            + "AB,#\nAB#,\n,#C\n,#A\n,#C;B\n,#C;A";
    public static final String NUM_OTHERS = "3\n1\n9\n)\n+\n%\n))\n33\n11\n"
            + "99\n10>\n10<\n10*&\n,#8\n,#2\n,#8;5\n" +
            ",#8;2";

    public static final String OPTION_NUMERIC = "-n";
    public static final String OPTION_REVERSE = "-r";
    public static final String OPTION_UCASE = "-f";
    public static final String OPTION_NUM_REV = "-nr";
    public static final String OPTION_NUM_REV_R = "-rn";
    public static final String OPTION_NUM_UCASE = "-nf";
    public static final String OPTION_NUM_UC_R = "-fn";
    public static final String OPTION_REV_UCASE = "-rf";
    public static final String OPTION_REV_UC_R = "-fr";
    public static final String OPTION_ALL_1 = "-nfr";
    public static final String OPTION_ALL_2 = "-nrf";
    public static final String OPTION_ALL_3 = "-fnr";
    public static final String OPTION_ALL_4 = "-frn";
    public static final String OPTION_ALL_5 = "-rnf";
    public static final String OPTION_ALL_6 = "-rfn";
    public static final String OPTION_INVALID = "-B";
    public static final String OPTION_INVALID_C = "-nBr";
    public static final String OPTION_EXTRA_C = "nfn";


    public static SortApplication sortApplication;

    @BeforeEach
    void setUp() throws IOException {
        BufferedWriter writer1 = new BufferedWriter(new PrintWriter(ALL_CHAR_NAME));
        writer1.write(ALL_CHAR);
        writer1.flush();
        writer1.close();

        BufferedWriter writer2 = new BufferedWriter(new PrintWriter(SLETTER_NUM_NAME));
        writer2.write(SLETTER_AND_NUM);
        writer2.flush();
        writer2.close();

        BufferedWriter writer3 = new BufferedWriter(new PrintWriter(SAC_LETTER_NAME));
        writer3.write(SAC_LETTER);
        writer3.flush();
        writer3.close();

        BufferedWriter writer4 = new BufferedWriter(new PrintWriter(SL_OTHERS_NAME));
        writer4.write(SL_OTHERS);
        writer4.flush();
        writer4.close();

        BufferedWriter writer5 = new BufferedWriter(new PrintWriter(CLETTER_NUM_NAME));
        writer5.write(CLETTER_NUM);
        writer5.flush();
        writer5.close();

        BufferedWriter writer6 = new BufferedWriter(new PrintWriter(CL_OTHERS_NAME));
        writer6.write(CL_OTHERS);
        writer6.flush();
        writer6.close();

        BufferedWriter writer7 = new BufferedWriter(new PrintWriter(NUM_OTHERS_NAME));
        writer7.write(NUM_OTHERS);
        writer7.flush();
        writer7.close();
    }

    @AfterEach
    void tearDown() {
        FileIOTestHelper.deleteMultipleFiles(ALL_CHAR_NAME, SLETTER_NUM_NAME,
                SAC_LETTER_NAME, SL_OTHERS_NAME, CLETTER_NUM_NAME,
                CL_OTHERS_NAME, NUM_OTHERS_NAME);
    }

    @AfterAll
    static void tearDownAfterEachTest() {
        File file = new File(STDOUT_NAME);
        if (file.exists()) {
            file.delete();
            file.delete();
        }
    }

    @BeforeAll
    static void setUpBeforeEachTest() {
        sortApplication = spy(new SortApplication());
    }

    /*******************************************************************
     Start of tests for run method within sortApplication
     ******************************************************************/

    @Test
    void runOptionNothingTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyNumericTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, false, false, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyReverseTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_REVERSE, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(false, true, false, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyUppercaseTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_UCASE, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(false, false, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyNumericAndReverseTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, OPTION_REVERSE, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, false, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyNumericAndReverseReverseOrderTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_REVERSE, OPTION_NUMERIC, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, false, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyNumericAndReverseConnectedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUM_REV, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, false, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyNumericAndReverseReverseOrderConnectedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUM_REV_R, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, false, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyNumericAndUppercaseTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, OPTION_UCASE, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, false, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyNumericAndUppercaseReverseOrderTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_UCASE, OPTION_NUMERIC, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, false, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyNumericAndUppercaseConnectedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUM_UCASE, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, false, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyNumericAndUppercaseReverseOrderConnectedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUM_UC_R, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, false, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyReverseAndUppercaseTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_REVERSE, OPTION_UCASE, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(false, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyReverseAndUppercaseReverseOrderTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_UCASE, OPTION_REVERSE, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(false, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyReverseAndUppercaseConnectedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_REV_UCASE, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(false, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionOnlyReverseAndUppercaseReverseOrderConnectedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_REV_UC_R, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(false, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionAllConnected1Test() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_ALL_1, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionAllConnected2Test() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_ALL_2, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionAllConnected3Test() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_ALL_3, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionAllConnected4Test() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_ALL_4, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionAllConnected5Test() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_ALL_5, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runOptionAllConnected6Test() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_ALL_6, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runFileInFrontOfOptionsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ALL_CHAR_NAME, OPTION_NUMERIC,
                OPTION_REVERSE, OPTION_UCASE);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runFileInBetweenOptionsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, ALL_CHAR_NAME,
                OPTION_REVERSE, OPTION_UCASE);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runMultipleFilesSingleOptionTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, ALL_CHAR_NAME,
                SLETTER_NUM_NAME, SAC_LETTER_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, false, false,
                ALL_CHAR_NAME, SLETTER_NUM_NAME, SAC_LETTER_NAME);
    }

    @Test
    void runMultipleFilesTwoOptionsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, OPTION_REVERSE, ALL_CHAR_NAME,
                SLETTER_NUM_NAME, SAC_LETTER_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, false,
                ALL_CHAR_NAME, SLETTER_NUM_NAME, SAC_LETTER_NAME);
    }

    @Test
    void runMultipleFilesThreeOptionsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, OPTION_REVERSE, OPTION_UCASE, ALL_CHAR_NAME,
                SLETTER_NUM_NAME, SAC_LETTER_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true,
                ALL_CHAR_NAME, SLETTER_NUM_NAME, SAC_LETTER_NAME);
    }

    @Test
    void runMultipleFilesWithDuplicatesTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, OPTION_REVERSE, OPTION_UCASE, ALL_CHAR_NAME,
                ALL_CHAR_NAME, SAC_LETTER_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true,
                ALL_CHAR_NAME, ALL_CHAR_NAME, SAC_LETTER_NAME);
    }

    @Test
    void runMultipleFilesNoOptionTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ALL_CHAR_NAME,
                SLETTER_NUM_NAME, SAC_LETTER_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true,
                ALL_CHAR_NAME, SLETTER_NUM_NAME, SAC_LETTER_NAME);
    }

    @Test
    void runMultipleFilesInFrontOfOptionsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ALL_CHAR_NAME,
                SLETTER_NUM_NAME, SAC_LETTER_NAME, OPTION_NUMERIC, OPTION_REV_UCASE, OPTION_UCASE);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true,
                ALL_CHAR_NAME, SLETTER_NUM_NAME, SAC_LETTER_NAME);
    }

    @Test
    void runMultipleFilesMixedWithOptionsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ALL_CHAR_NAME, OPTION_NUMERIC,
                SLETTER_NUM_NAME, OPTION_REVERSE, OPTION_UCASE, SAC_LETTER_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, true,
                ALL_CHAR_NAME, SLETTER_NUM_NAME, SAC_LETTER_NAME);
    }

    @Test
    void runUseStdinTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, OPTION_REVERSE, OPTION_UCASE);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);

        // Open stdin to feed run method
        FileInputStream fis = new FileInputStream(ALL_CHAR_NAME);
        sortApplication.run(args, fis, fos);
        verify(sortApplication, Mockito.times(1)).sortFromStdin(true,
                true, true, fis);
    }

    @Test
    void runFileSingleInvalidOptionTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_INVALID, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        assertThrows(SortException.class, () -> sortApplication.run(args, null, fos));
    }

    @Test
    void runFileSingleInvalidOptionBetweenValidOptionsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, OPTION_INVALID,
                OPTION_REVERSE, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        assertThrows(SortException.class, () -> sortApplication.run(args, null, fos));
    }

    @Test
    void runFileSingleInvalidOptionAfterFileTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC,
                OPTION_REVERSE, ALL_CHAR_NAME, OPTION_INVALID);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        assertThrows(SortException.class, () -> sortApplication.run(args, null, fos));
    }

    @Test
    void runFileSingleInvalidOptionCombinedWithValidOptionsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_INVALID_C, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        assertThrows(SortException.class, () -> sortApplication.run(args, null, fos));
    }

    @Test
    void runFileExtraValidOptionsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_NUMERIC, OPTION_REVERSE,
                OPTION_NUMERIC, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, true, false,
                ALL_CHAR_NAME);
    }

    @Test
    void runFileExtraValidOptionsCombinedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(OPTION_EXTRA_C, ALL_CHAR_NAME);

        // Create the FileOutputStream for run to write into
        FileOutputStream fos = new FileOutputStream(STDOUT_NAME);
        sortApplication.run(args, null, fos);
        verify(sortApplication, Mockito.times(1)).sortFromFiles(true, false, true,
                ALL_CHAR_NAME);
    }

    /*******************************************************************
     Start of tests for sortFromFiles method within sortApplication
     ******************************************************************/

    @Test
    void sortFromFilesSmallLetterAndCapitalLetterTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(SAC_LETTER_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(false, false,
                false, SAC_LETTER_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesSmallLetterAndNumbersTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(SLETTER_NUM_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(false, false,
                false, SLETTER_NUM_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesSmallLetterAndOthersTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(SL_OTHERS_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(false, false,
                false, SL_OTHERS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesCapitalLetterAndNumbersTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(CLETTER_NUM_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(false, false,
                false, CLETTER_NUM_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesCapitalLetterAndOthersTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(CL_OTHERS_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(false, false,
                false, CL_OTHERS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesNumbersAndOthersTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(NUM_OTHERS_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(false, false,
                false, NUM_OTHERS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesAllCharacterClassTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(false, false,
                false, ALL_CHAR_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesReverseOnlyTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                true, false);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(false, true,
                false, ALL_CHAR_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesNumericOnlyTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, true,
                false, false);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(true, false,
                false, ALL_CHAR_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesCaseInsensitiveOnlyTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, true);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(false,
                false, true, ALL_CHAR_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesReversedAndNumericalTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, true,
                true, false);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(true,
                true, false, ALL_CHAR_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesReversedAndCaseInsensitiveTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                true, true);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(false,
                true, true, ALL_CHAR_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesNumericAndCaseInsensitiveTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, true,
                false, true);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(true,
                false, true, ALL_CHAR_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesAllOptionsTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, true,
                true, true);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(true,
                true, true, ALL_CHAR_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromMultipleUniqueFilesAllOptionsTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME,
                NUM_OTHERS_NAME, SL_OTHERS_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, true,
                true, true);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(true,
                true, true, ALL_CHAR_NAME,
                NUM_OTHERS_NAME, SL_OTHERS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromMultipleFilesWithDuplicatesAllOptionsTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME,
                NUM_OTHERS_NAME, ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, true,
                true, true);
        String expectedOutput = sortingHelper.sort();
        String actualOutput = sortApplication.sortFromFiles(true,
                true, true, ALL_CHAR_NAME,
                NUM_OTHERS_NAME, ALL_CHAR_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromFilesDoesNotExistTest() {
        assertThrows(SortException.class, () -> sortApplication.sortFromFiles(true,
                true, true, FILE_NOT_EXIST));
    }

    @Test
    void sortFromMultipleFilesOneDoesNotExistTest() {
        assertThrows(SortException.class, () -> sortApplication.sortFromFiles(true,
                true, true, ALL_CHAR_NAME,
                FILE_NOT_EXIST, SL_OTHERS_NAME));
    }

    /*******************************************************************
     Start of tests for sortFromStdin method within sortApplication
     ******************************************************************/


    @Test
    void sortFromStdinSmallLetterAndCapitalLetterTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(SAC_LETTER_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(SAC_LETTER_NAME);
        String actualOutput = sortApplication.sortFromStdin(false, false,
                false, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinSmallLetterAndNumbersTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(SLETTER_NUM_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(SLETTER_NUM_NAME);
        String actualOutput = sortApplication.sortFromStdin(false, false,
                false, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinSmallLetterAndOthersTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(SL_OTHERS_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(SL_OTHERS_NAME);
        String actualOutput = sortApplication.sortFromStdin(false, false,
                false, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinCapitalLetterAndNumbersTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(CLETTER_NUM_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(CLETTER_NUM_NAME);
        String actualOutput = sortApplication.sortFromStdin(false, false,
                false, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinCapitalLetterAndOthersTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(CL_OTHERS_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(CL_OTHERS_NAME);
        String actualOutput = sortApplication.sortFromStdin(false, false,
                false, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinNumbersAndOthersTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(NUM_OTHERS_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, false);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(NUM_OTHERS_NAME);
        String actualOutput = sortApplication.sortFromStdin(false, false,
                false, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinReverseOnlyTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                true, false);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(ALL_CHAR_NAME);
        String actualOutput = sortApplication.sortFromStdin(false,
                true, false, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinNumericOnlyTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, true,
                false, false);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(ALL_CHAR_NAME);
        String actualOutput = sortApplication.sortFromStdin(true,
                false, false, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinCaseInsensitiveOnlyTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                false, true);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(ALL_CHAR_NAME);
        String actualOutput = sortApplication.sortFromStdin(false,
                false, true, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinReversedAndNumericalTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, true,
                true, false);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(ALL_CHAR_NAME);
        String actualOutput = sortApplication.sortFromStdin(true,
                true, false, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinReversedAndCaseInsensitiveTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, false,
                true, true);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(ALL_CHAR_NAME);
        String actualOutput = sortApplication.sortFromStdin(false,
                true, true, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinNumericAndCaseInsensitiveTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, true,
                false, true);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(ALL_CHAR_NAME);
        String actualOutput = sortApplication.sortFromStdin(true,
                false, true, fis);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void sortFromStdinAllOptionsTest() throws Exception {
        String[] toSort = FileIOTestHelper.extractAsStringArray(ALL_CHAR_NAME);
        SortApplicationSortingHelper sortingHelper = new SortApplicationSortingHelper(toSort, true,
                true, true);
        String expectedOutput = sortingHelper.sort();

        // Open an InputStream to act as stdin
        FileInputStream fis = new FileInputStream(ALL_CHAR_NAME);
        String actualOutput = sortApplication.sortFromStdin(true,
                true, true, fis);

        assertEquals(expectedOutput, actualOutput);
    }


}
