/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl.app;import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.FileIOTestHelper;
import sg.edu.nus.comp.cs4218.impl.StringsToArrayHelper;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class WcApplicationTest {

    public static final String FILE_ALPHA_NUM = "This file contains alphabets as well as numbers. " +
            "A B C Y Z 1 2 3 0 9";
    // WS = White Space
    public static final String FILE_ALPHA_WS = "This file will have a few whitespaces with alphabets." +
            "\n\tTab here.\n     Random spaces             here.";
    public static final String FILE_ALPHA_OTHERS = "This file will have the other characters with alphabets." +
            " . / , [ ] { }; ().   \"   \"   ";
    public static final String FILE_NUM_WS = "1   2\n   345   66   6 \t78 60    \n\n\t\n5";
    public static final String FILE_NUM_OTHERS = "1 2 < 3 > <3> 44<55>66 44 < 55 > 66";
    public static final String FILE_WS_OTHERS = "   ()\n\t&*543\n\n    \n   \n\n    :./ ' >?<";
    public static final String FILE_ALL_CHAR = "This will be the file that will contain everything.\n" +
            "This will be a long space     .\n <34>\nHere come a few Other characters: .;'/,[\"[ ] &&   &\n" +
            "Here comes a line with no newline at the end.";

    public static final String ALPHA_NUM_NAME = "wcUnitTest1.txt";
    public static final String ALPHA_WS_NAME = "wcUnitTest2.txt";
    public static final String ALPHA_OTHER_NAME = "wcUnitTest3.txt";
    public static final String NUM_WS_NAME = "wcUnitTest4.txt";
    public static final String NUM_OTHERS_NAME = "wcUnitTest5.txt";
    public static final String WS_OTHERS_NAME = "wcUnitTest6.txt";
    public static final String ALL_CHAR_NAME = "wcUnitTest7.txt";
    // MULP = Multiple
    public static final String MULP_FILE_TOTAL = "total";
    public static final String NO_EXIST_FILE_1 = "noSuchFile1.txt";
    public static final String NO_EXIST_FILE_2 = "noSuchFile2.txt";
    public static final String FILE_STDOUT_TEST = "wcStdOut.txt";
    public static final String WC_FOLDER_NAME_1 = "wcUnitTestFolder";

    public static final String ONE_ARG_FORMAT = "%s %s";
    public static final String TWO_ARG_FORMAT = "%s %s %s";
    public static final String THREE_ARG_FORMAT = "%s %s %s %s";

    public static final String SHOW_LINE = "-l";
    public static final String SHOW_WORD = "-w";
    public static final String SHOW_BYTE = "-c";
    public static final String SHOW_LINE_WORD = "-lw";
    // R stands for reverse order
    public static final String SHOW_LINE_WORD_R = "-wl";
    public static final String SHOW_LINE_BYTE = "-lc";
    public static final String SHOW_LINE_BYTE_R = "-cl";
    public static final String SHOW_WORD_BYTE = "-wc";
    public static final String SHOW_WORD_BYTE_R = "-cw";
    // COMB = Combination
    public static final String SHOW_ALL_COMB_1 = "-clw";
    public static final String SHOW_ALL_COMB_2 = "-wlc";
    public static final String SHOW_ALL_COMB_3 = "-cwl";
    public static final String SHOW_ALL_COMB_4 = "-wcl";
    public static final String SHOW_ALL_COMB_5 = "-lwc";
    public static final String SHOW_ALL_COMB_6 = "-lcw";
    public static final String ILLEGAL_ARGUMENT = "-V";
    public static final String ILLEGAL_ARG_COMB = "-cVl";

    // WC = Word Count, LC = Line Count, BC = Byte Count
    public static final int ALPHA_NUM_WC = 18;
    public static final int ALPHA_NUM_LC = 1;
    public static final int ALPHA_NUM_BC = 68;
    public static final int ALPHA_WS_WC = 14;
    public static final int ALPHA_WS_LC = 3;
    public static final int ALPHA_WS_BC = 99;
    public static final int ALPHA_OTHERS_WC = 19;
    public static final int ALPHA_OTHERS_LC = 1;
    public static final int ALPHA_OTHERS_BC = 86;
    public static final int NUM_WS_WC = 8;
    public static final int NUM_WS_LC = 5;
    public static final int NUM_WS_BC = 33;
    public static final int NUM_OTHERS_WC = 12;
    public static final int NUM_OTHERS_LC = 1;
    public static final int NUM_OTHERS_BC = 35;
    public static final int WS_OTHERS_WC = 5;
    public static final int WS_OTHERS_LC = 7;
    public static final int WS_OTHERS_BC = 31;
    public static final int ALL_CHAR_WC = 37;
    public static final int ALL_CHAR_LC = 5;
    public static final int ALL_CHAR_BC = 183;

    public static final String SPACE_CHARACTER = " ";
    // NSFOD = No Such File Or Directory
    public static final String WC_NSFOD_FORMAT = "wc: %s: No such file or directory.";
    public static final String WC_ILLEGAL_OPTION = "illegal option -- %s\nusage: wcApplication [-clw] [file ...]";
    public static final String OPTION_MATCH = "-.*";
    // DTRY = Directory
    public static final String IS_A_DTRY_FORMAT = "%s: Is a directory.";


    public static final String SPACE_CHAR = " ";
    public static final String NEWLINE_CHAR = StringUtils.STRING_NEWLINE;

    public static WcApplication wcApplication;


    @BeforeAll
    static void setUp() {
        // Create a few files in the PWD for wcApplication function to read
        try {
            BufferedWriter writer1 = new BufferedWriter(new PrintWriter(ALPHA_NUM_NAME));
            writer1.write(FILE_ALPHA_NUM);
            writer1.flush();
            writer1.close();

            BufferedWriter writer2 = new BufferedWriter(new PrintWriter(ALPHA_WS_NAME));
            writer2.write(FILE_ALPHA_WS);
            writer2.flush();
            writer2.close();

            BufferedWriter writer3 = new BufferedWriter(new PrintWriter(ALPHA_OTHER_NAME));
            writer3.write(FILE_ALPHA_OTHERS);
            writer3.flush();
            writer3.close();

            BufferedWriter writer4 = new BufferedWriter(new PrintWriter(NUM_WS_NAME));
            writer4.write(FILE_NUM_WS);
            writer4.flush();
            writer4.close();

            BufferedWriter writer5 = new BufferedWriter(new PrintWriter(NUM_OTHERS_NAME));
            writer5.write(FILE_NUM_OTHERS);
            writer5.flush();
            writer5.close();

            BufferedWriter writer6 = new BufferedWriter(new PrintWriter(WS_OTHERS_NAME));
            writer6.write(FILE_WS_OTHERS);
            writer6.flush();
            writer6.close();

            BufferedWriter writer7 = new BufferedWriter(new PrintWriter(ALL_CHAR_NAME));
            writer7.write(FILE_ALL_CHAR);
            writer7.flush();
            writer7.close();

            File directory1 = new File(WC_FOLDER_NAME_1);
            directory1.mkdir();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() {
        // Delete the created files when testing is done.
        FileIOTestHelper.deleteMultipleFiles(ALPHA_NUM_NAME, ALPHA_WS_NAME,
                ALPHA_OTHER_NAME, NUM_WS_NAME, NUM_OTHERS_NAME,
                WS_OTHERS_NAME, ALL_CHAR_NAME, WC_FOLDER_NAME_1);

    }

    @BeforeEach
    void setUpBeforeEach() {
        wcApplication = spy(new WcApplication());
    }

    @AfterEach
    void tearDownAfterEach() {
        File file = new File(FILE_STDOUT_TEST);
        if (file.exists()) {
            file.delete();
        }
    }

    /**************************************************************************
     Start of tests for run method within wcApplication
     *************************************************************************/

    @Test
    void runShowOnlyLineTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(false, true, false, ALL_CHAR_NAME);

    }

    @Test
    void runShowOnlyWordTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_WORD, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(false, false, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowOnlyByteTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_BYTE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, false, false, ALL_CHAR_NAME);
    }

    @Test
    void runShowLineAndWordTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE, SHOW_WORD, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(false, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowLineAndWordInReverseOrderTest() throws Exception {

        // Give the arguments -l and -w in reverse order
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_WORD, SHOW_LINE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(false, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowLineAndWordCombinedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE_WORD, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(false, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowLineAndWordCombinedReverseOrderTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE_WORD_R, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(false, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowLineAndByteTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE, SHOW_BYTE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, false, ALL_CHAR_NAME);
    }


    @Test
    void runShowLineAndByteInReverseOrderTest() throws Exception {

        // Give the arguments -c and -l in reverse order
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_BYTE, SHOW_LINE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, false, ALL_CHAR_NAME);
    }

    @Test
    void runShowLineAndByteCombinedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE_BYTE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, false, ALL_CHAR_NAME);
    }

    @Test
    void runShowLineAndByteCombinedReverseOrderTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE_BYTE_R, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, false, ALL_CHAR_NAME);
    }

    @Test
    void runShowWordAndByteTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_WORD, SHOW_BYTE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, false, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowWordAndByteInReverseOrderTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_BYTE, SHOW_WORD, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, false, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowWordAndByteCombinedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_WORD_BYTE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, false, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowWordAndByteCombinedReverseOrderTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_WORD_BYTE_R, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, false, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowAllTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE, SHOW_WORD, SHOW_BYTE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowAllReverseTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_BYTE, SHOW_WORD, SHOW_LINE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowAllCombinedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_ALL_COMB_1, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowAllCombinedTwoTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_ALL_COMB_2, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowAllCombinedOrderThreeTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_ALL_COMB_3, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowAllCombinedOrderFourTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_ALL_COMB_4, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowAllCombinedOrderFiveTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_ALL_COMB_5, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowAllCombinedOrderSixTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_ALL_COMB_6, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true, ALL_CHAR_NAME);
    }

    @Test
    void runShowAllMultipleFilesTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_BYTE, SHOW_WORD, SHOW_LINE,
                ALL_CHAR_NAME, WS_OTHERS_NAME, NUM_OTHERS_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true,
                ALL_CHAR_NAME, WS_OTHERS_NAME, NUM_OTHERS_NAME);
    }

    @Test
    void runMultipleFilesDuplicateFilesTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_BYTE, SHOW_WORD, SHOW_LINE,
                ALL_CHAR_NAME, WS_OTHERS_NAME, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true,
                ALL_CHAR_NAME, WS_OTHERS_NAME, ALL_CHAR_NAME);
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void runMultipleFilesWithFolderTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_BYTE, SHOW_WORD, SHOW_LINE,
                ALL_CHAR_NAME, WC_FOLDER_NAME_1, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true,
                ALL_CHAR_NAME, WC_FOLDER_NAME_1, ALL_CHAR_NAME);
    }
     */

    @Test
    void runShowAllCombinedMultipleFilesTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_ALL_COMB_1, ALL_CHAR_NAME,
                WS_OTHERS_NAME, NUM_OTHERS_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true,
                ALL_CHAR_NAME, WS_OTHERS_NAME, NUM_OTHERS_NAME);
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void runWithSingleFileArgumentInBetweenOtherArgumentsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE, ALL_CHAR_NAME, SHOW_WORD, SHOW_BYTE);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(false, true, false,
                ALL_CHAR_NAME, SHOW_WORD, SHOW_BYTE);
    }
     */

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void runWithSingleFileArgumentInFrontOfOtherArgumentsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ALL_CHAR_NAME, SHOW_LINE, SHOW_WORD, SHOW_BYTE);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true,
                ALL_CHAR_NAME, SHOW_LINE, SHOW_WORD, SHOW_BYTE);
    }
    */

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void runWithMultipleFileArgumentsInBetweenOtherArgumentsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE, WS_OTHERS_NAME, SHOW_WORD,
                ALL_CHAR_NAME, SHOW_BYTE);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(false, true, false,
                WS_OTHERS_NAME, SHOW_WORD, ALL_CHAR_NAME, SHOW_BYTE);
    }
     */

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void runWithMultipleFileArgumentsInFrontOfOtherArgumentsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(WS_OTHERS_NAME,
                ALL_CHAR_NAME, SHOW_WORD, SHOW_LINE, SHOW_BYTE);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true,
                WS_OTHERS_NAME,
                ALL_CHAR_NAME, SHOW_WORD, SHOW_LINE, SHOW_BYTE);
    }
     */

    @Test
    void runCallCountFromStdinTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_WORD, SHOW_LINE, SHOW_BYTE);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);

        // Create a stdin for to pass to run method
        FileInputStream fis = new FileInputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, fis, fos);
        verify(wcApplication, Mockito.times(1)).countFromStdin(true, true, true, fis);
    }

    @Test
    void runCheckStdoutTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ALL_CHAR_NAME);
        String expectedOutput = String.format(THREE_ARG_FORMAT, ALL_CHAR_LC,
                ALL_CHAR_WC, ALL_CHAR_BC, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);
        wcApplication.run(args, null, fos);

        // Extract result from stdout file
        String actualOutput = FileIOTestHelper.extractAndConcatenate(FILE_STDOUT_TEST);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void runWithIllegalArgumentInFrontOfOtherArgumentsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ILLEGAL_ARGUMENT, SHOW_LINE, SHOW_WORD, SHOW_BYTE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);

        // Since the option is illegal, using it will lead to WcException being thrown
        assertThrows(WcException.class, () -> wcApplication.run(args, null, fos));

    }

    @Test
    void runWithIllegalArgumentNotInFrontOfOtherArgumentsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE, ILLEGAL_ARGUMENT, SHOW_WORD, SHOW_BYTE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);

        // Since the option is illegal, using it will lead to WcException being thrown
        assertThrows(WcException.class, () -> wcApplication.run(args, null, fos));

    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void runWithIllegalArgumentNotInFrontOfFileInFrontOfOtherArgumentsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ALL_CHAR_NAME, ILLEGAL_ARGUMENT, SHOW_LINE, SHOW_WORD, SHOW_BYTE);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);

        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true,
                ALL_CHAR_NAME, ILLEGAL_ARGUMENT, SHOW_LINE, SHOW_WORD, SHOW_BYTE);

    }
    */

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void runWithIllegalArgumentNotInFrontOfFileNotInFrontOfOtherArgumentsTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ALL_CHAR_NAME, SHOW_LINE, ILLEGAL_ARGUMENT, SHOW_WORD, SHOW_BYTE);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);

        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true,
                ALL_CHAR_NAME, SHOW_LINE, ILLEGAL_ARGUMENT, SHOW_WORD, SHOW_BYTE);

    }
     */

    @Test
    void runWithIllegalArgumentCombinedInFrontOfFileTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(ILLEGAL_ARG_COMB, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);

        // Since the option is illegal, using it will lead to WcException being thrown
        assertThrows(WcException.class, () -> wcApplication.run(args, null, fos));

    }

    @Test
    void runWithRepeatedArgumentSideBySideTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE, SHOW_LINE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);

        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(false, true, false,
                ALL_CHAR_NAME);
    }

    @Test
    void runWithRepeatedArgumentSeparatedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_LINE, SHOW_BYTE, SHOW_LINE, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);

        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, false,
                ALL_CHAR_NAME);
    }

    @Test
    void runWithRepeatedCombinedArgumentSideBySideTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_ALL_COMB_1, SHOW_ALL_COMB_1, ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);

        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true,
                ALL_CHAR_NAME);
    }

    @Test
    void runWithRepeatedCombinedArgumentSeparatedTest() throws Exception {
        String[] args = StringsToArrayHelper.appendStringsToArray(SHOW_ALL_COMB_1, SHOW_BYTE, SHOW_ALL_COMB_1,
                ALL_CHAR_NAME);

        // Create the file for run to write into
        FileOutputStream fos = new FileOutputStream(FILE_STDOUT_TEST);

        wcApplication.run(args, null, fos);
        verify(wcApplication, Mockito.times(1)).countFromFiles(true, true, true,
                ALL_CHAR_NAME);
    }


    /**************************************************************************
     Start of tests for countFromStdin method within wcApplication
     *************************************************************************/


    @Test
    void countFromStdinAlphabetsAndNumbersTest() throws Exception {
        String expectedOutput = String.format(TWO_ARG_FORMAT, ALPHA_NUM_LC,
                ALPHA_NUM_WC, ALPHA_NUM_BC);

        String actualOutput = wcApplication.countFromStdin(true, true, true,
                new FileInputStream(ALPHA_NUM_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinAlphabetsAndWhitespaceTest() throws Exception {
        String expectedOutput = String.format(TWO_ARG_FORMAT, ALPHA_WS_LC,
                ALPHA_WS_WC, ALPHA_WS_BC);

        String actualOutput = wcApplication.countFromStdin(true, true, true,
                new FileInputStream(ALPHA_WS_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinAlphabetsAndOthersTest() throws Exception {
        String expectedOutput = String.format(TWO_ARG_FORMAT, ALPHA_OTHERS_LC,
                ALPHA_OTHERS_WC, ALPHA_OTHERS_BC);

        String actualOutput = wcApplication.countFromStdin(true, true, true,
                new FileInputStream(ALPHA_OTHER_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinNumberAndWhitespaceTest() throws Exception {
        String expectedOutput = String.format(TWO_ARG_FORMAT, NUM_WS_LC,
                NUM_WS_WC, NUM_WS_BC);

        String actualOutput = wcApplication.countFromStdin(true, true, true,
                new FileInputStream(NUM_WS_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinNumbersAndOthersTest() throws Exception {
        String expectedOutput = String.format(TWO_ARG_FORMAT, NUM_OTHERS_LC,
                NUM_OTHERS_WC, NUM_OTHERS_BC);

        String actualOutput = wcApplication.countFromStdin(true, true, true,
                new FileInputStream(NUM_OTHERS_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinWhitespaceAndOthersTest() throws Exception {
        String expectedOutput = String.format(TWO_ARG_FORMAT, WS_OTHERS_LC,
                WS_OTHERS_WC, WS_OTHERS_BC);

        String actualOutput = wcApplication.countFromStdin(true, true, true,
                new FileInputStream(WS_OTHERS_NAME));

        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void countFromStdinShowOnlyLineTest() throws Exception {
        String expectedOutput = String.valueOf(ALL_CHAR_LC);

        String actualOutput = wcApplication.countFromStdin(false, true, false,
                new FileInputStream(ALL_CHAR_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinShowOnlyWordTest() throws Exception {
        String expectedOutput = String.valueOf(ALL_CHAR_WC);

        String actualOutput = wcApplication.countFromStdin(false, false, true,
                new FileInputStream(ALL_CHAR_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinShowOnlyByteTest() throws Exception {
        String expectedOutput = String.valueOf(ALL_CHAR_BC);

        String actualOutput = wcApplication.countFromStdin(true, false, false,
                new FileInputStream(ALL_CHAR_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinShowLineAndWordTest() throws Exception {
        String expectedOutput = ALL_CHAR_LC + SPACE_CHAR + ALL_CHAR_WC;

        String actualOutput = wcApplication.countFromStdin(false, true, true,
                new FileInputStream(ALL_CHAR_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinShowWordAndByteTest() throws Exception {
        String expectedOutput = ALL_CHAR_WC + SPACE_CHAR + ALL_CHAR_BC;

        String actualOutput = wcApplication.countFromStdin(true, false, true,
                new FileInputStream(ALL_CHAR_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinShowLineAndByteTest() throws Exception {
        String expectedOutput = ALL_CHAR_LC + SPACE_CHAR + ALL_CHAR_BC;

        String actualOutput = wcApplication.countFromStdin(true, true, false,
                new FileInputStream(ALL_CHAR_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinShowAllTest() throws Exception {
        String expectedOutput = ALL_CHAR_LC + SPACE_CHAR + ALL_CHAR_WC
                + SPACE_CHAR + ALL_CHAR_BC;

        String actualOutput = wcApplication.countFromStdin(true, true, true,
                new FileInputStream(ALL_CHAR_NAME));

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromStdinClosedStdinTest() throws Exception {

        // Create an inputstream and close it
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(ALL_CHAR_NAME));
        bis.close();

        assertThrows(WcException.class, () -> wcApplication.countFromStdin(true, true, true, bis));
    }


    /**************************************************************************
     Start of tests for countFromFile method within wcApplication
     *************************************************************************/

    // Pairwise Testing carried out for the content type
    // There are 4 ranges: Alphabets, Numbers, Whitespaces and Others
    // All possible pairs are tested.
    // Characters after the last newline not counted as a line is also tested.
    // A word is defined as a contiguous string of non-whitespace characters.
    @Test
    void countFromFileAlphabetsAndNumbersTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, ALPHA_NUM_LC,
                ALPHA_NUM_WC, ALPHA_NUM_BC, ALPHA_NUM_NAME);

        String actualOutput = wcApplication.countFromFiles(true, true, true, ALPHA_NUM_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromFileAlphabetsAndWhitespaceTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, ALPHA_WS_LC,
                ALPHA_WS_WC, ALPHA_WS_BC, ALPHA_WS_NAME);

        String actualOutput = wcApplication.countFromFiles(true, true, true, ALPHA_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromFileAlphabetsAndOthersTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, ALPHA_OTHERS_LC,
                ALPHA_OTHERS_WC, ALPHA_OTHERS_BC, ALPHA_OTHER_NAME);

        String actualOutput = wcApplication.countFromFiles(true, true, true, ALPHA_OTHER_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromFileNumberAndWhitespaceTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, NUM_WS_LC,
                NUM_WS_WC, NUM_WS_BC, NUM_WS_NAME);

        String actualOutput = wcApplication.countFromFiles(true, true, true, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromFileNumbersAndOthersTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, NUM_OTHERS_LC,
                NUM_OTHERS_WC, NUM_OTHERS_BC, NUM_OTHERS_NAME);

        String actualOutput = wcApplication.countFromFiles(true, true, true, NUM_OTHERS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromFileWhitespaceAndOthersTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, WS_OTHERS_LC,
                WS_OTHERS_WC, WS_OTHERS_BC, WS_OTHERS_NAME);

        String actualOutput = wcApplication.countFromFiles(true, true, true, WS_OTHERS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void countFromFileShowOnlyLineTest() throws Exception {
        String expectedOutput = ALL_CHAR_LC + SPACE_CHAR + ALL_CHAR_NAME;
        String actualOutput = wcApplication.countFromFiles(false, true, false, ALL_CHAR_NAME);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromFileShowOnlyWordTest() throws Exception {
        String expectedOutput = ALL_CHAR_WC + SPACE_CHAR + ALL_CHAR_NAME;
        String actualOutput = wcApplication.countFromFiles(false, false, true, ALL_CHAR_NAME);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromFileShowOnlyByteTest() throws Exception {
        String expectedOutput = ALL_CHAR_BC + SPACE_CHAR + ALL_CHAR_NAME;
        String actualOutput = wcApplication.countFromFiles(true, false, false, ALL_CHAR_NAME);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromFileShowLineAndWordTest() throws Exception {
        String expectedOutput = ALL_CHAR_LC + SPACE_CHAR
                + ALL_CHAR_WC + SPACE_CHAR + ALL_CHAR_NAME;
        String actualOutput = wcApplication.countFromFiles(false, true, true, ALL_CHAR_NAME);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromFileShowWordAndByteTest() throws Exception {
        String expectedOutput = ALL_CHAR_WC + SPACE_CHAR
                + ALL_CHAR_BC + SPACE_CHAR + ALL_CHAR_NAME;
        String actualOutput = wcApplication.countFromFiles(true, false, true, ALL_CHAR_NAME);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromFileShowLineAndByteTest() throws Exception {
        String expectedOutput = ALL_CHAR_LC + SPACE_CHAR
                + ALL_CHAR_BC + SPACE_CHAR + ALL_CHAR_NAME;
        String actualOutput = wcApplication.countFromFiles(true, true, false, ALL_CHAR_NAME);
        assertEquals(expectedOutput, actualOutput);
    }

    // Will be using three files to test, namely
    // 1) ALL_CHAR_NAME
    // 2) ALPHA_WS_NAME
    // 3) NUM_WS_NAME

    @Test
    void countFromMultipleFileShowOnlyLineTest() throws Exception {
        String expectedOutput = String.format(ONE_ARG_FORMAT, ALL_CHAR_LC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(ONE_ARG_FORMAT, ALPHA_WS_LC, ALPHA_WS_NAME)
                + NEWLINE_CHAR + String.format(ONE_ARG_FORMAT, NUM_WS_LC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(ONE_ARG_FORMAT, calculateTotal(ALL_CHAR_LC,
                ALPHA_WS_LC, NUM_WS_LC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(false, true, false,
                ALL_CHAR_NAME, ALPHA_WS_NAME, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromMultipleFileShowOnlyWordTest() throws Exception {
        String expectedOutput = String.format(ONE_ARG_FORMAT, ALL_CHAR_WC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(ONE_ARG_FORMAT, ALPHA_WS_WC, ALPHA_WS_NAME)
                + NEWLINE_CHAR + String.format(ONE_ARG_FORMAT, NUM_WS_WC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(ONE_ARG_FORMAT, calculateTotal(ALL_CHAR_WC,
                ALPHA_WS_WC, NUM_WS_WC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(false, false, true,
                ALL_CHAR_NAME, ALPHA_WS_NAME, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromMultipleFileShowOnlyByteTest() throws Exception {
        String expectedOutput = String.format(ONE_ARG_FORMAT, ALL_CHAR_BC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(ONE_ARG_FORMAT, ALPHA_WS_BC, ALPHA_WS_NAME)
                + NEWLINE_CHAR + String.format(ONE_ARG_FORMAT, NUM_WS_BC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(ONE_ARG_FORMAT, calculateTotal(ALL_CHAR_BC,
                ALPHA_WS_BC, NUM_WS_BC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(true, false, false,
                ALL_CHAR_NAME, ALPHA_WS_NAME, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromMultipleFileShowLineAndWordTest() throws Exception {
        String expectedOutput = String.format(TWO_ARG_FORMAT, ALL_CHAR_LC, ALL_CHAR_WC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(TWO_ARG_FORMAT, ALPHA_WS_LC, ALPHA_WS_WC, ALPHA_WS_NAME)
                + NEWLINE_CHAR + String.format(TWO_ARG_FORMAT, NUM_WS_LC, NUM_WS_WC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(TWO_ARG_FORMAT, calculateTotal(ALL_CHAR_LC,
                ALPHA_WS_LC, NUM_WS_LC), calculateTotal(ALL_CHAR_WC,
                ALPHA_WS_WC, NUM_WS_WC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(false, true, true,
                ALL_CHAR_NAME, ALPHA_WS_NAME, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromMultipleFileShowWordAndByteTest() throws Exception {
        String expectedOutput = String.format(TWO_ARG_FORMAT, ALL_CHAR_WC, ALL_CHAR_BC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(TWO_ARG_FORMAT, ALPHA_WS_WC, ALPHA_WS_BC, ALPHA_WS_NAME)
                + NEWLINE_CHAR + String.format(TWO_ARG_FORMAT, NUM_WS_WC, NUM_WS_BC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(TWO_ARG_FORMAT, calculateTotal(ALL_CHAR_WC,
                ALPHA_WS_WC, NUM_WS_WC), calculateTotal(ALL_CHAR_BC,
                ALPHA_WS_BC, NUM_WS_BC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(true, false, true,
                ALL_CHAR_NAME, ALPHA_WS_NAME, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromMultipleFileShowLineAndByteTest() throws Exception {
        String expectedOutput = String.format(TWO_ARG_FORMAT, ALL_CHAR_LC, ALL_CHAR_BC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(TWO_ARG_FORMAT, ALPHA_WS_LC, ALPHA_WS_BC, ALPHA_WS_NAME)
                + NEWLINE_CHAR + String.format(TWO_ARG_FORMAT, NUM_WS_LC, NUM_WS_BC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(TWO_ARG_FORMAT, calculateTotal(ALL_CHAR_LC,
                ALPHA_WS_LC, NUM_WS_LC), calculateTotal(ALL_CHAR_BC,
                ALPHA_WS_BC, NUM_WS_BC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(true, true, false,
                ALL_CHAR_NAME, ALPHA_WS_NAME, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void countFromMultipleFileShowAllTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, ALL_CHAR_LC, ALL_CHAR_WC, ALL_CHAR_BC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, ALPHA_WS_LC, ALPHA_WS_WC, ALPHA_WS_BC, ALPHA_WS_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, NUM_WS_LC, NUM_WS_WC, NUM_WS_BC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, calculateTotal(ALL_CHAR_LC,
                ALPHA_WS_LC, NUM_WS_LC), calculateTotal(ALL_CHAR_WC,
                ALPHA_WS_WC, NUM_WS_WC), calculateTotal(ALL_CHAR_BC,
                ALPHA_WS_BC, NUM_WS_BC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(true, true, true,
                ALL_CHAR_NAME, ALPHA_WS_NAME, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void countFromFileNotExist() throws Exception {
        String expectedOutput = String.format(WC_NSFOD_FORMAT, NO_EXIST_FILE_1);
        String actualOutput = wcApplication.countFromFiles(true, true, true, NO_EXIST_FILE_1);

        assertEquals(expectedOutput, actualOutput);
    }
    */

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void countFromFileSingleFolder() throws Exception {
        String expectedOutput = String.format(IS_A_DTRY_FORMAT, WC_FOLDER_NAME_1);
        String actualOutput = wcApplication.countFromFiles(true, true, true, WC_FOLDER_NAME_1);

        assertEquals(expectedOutput, actualOutput);
    }
     */

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void countFromMultipleFilesOneNotExistTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, ALL_CHAR_LC, ALL_CHAR_WC, ALL_CHAR_BC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, ALPHA_WS_LC, ALPHA_WS_WC, ALPHA_WS_BC, ALPHA_WS_NAME)
                + NEWLINE_CHAR + String.format(WC_NSFOD_FORMAT, NO_EXIST_FILE_1)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, NUM_WS_LC, NUM_WS_WC, NUM_WS_BC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, calculateTotal(ALL_CHAR_LC,
                ALPHA_WS_LC, NUM_WS_LC), calculateTotal(ALL_CHAR_WC,
                ALPHA_WS_WC, NUM_WS_WC), calculateTotal(ALL_CHAR_BC,
                ALPHA_WS_BC, NUM_WS_BC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(true, true, true,
                ALL_CHAR_NAME, ALPHA_WS_NAME, NO_EXIST_FILE_1, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }
     */

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void countFromMultipleFilesTwoNotExistTest() throws Exception {
        String expectedOutput = String.format(WC_NSFOD_FORMAT, NO_EXIST_FILE_2)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, ALL_CHAR_LC, ALL_CHAR_WC, ALL_CHAR_BC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, ALPHA_WS_LC, ALPHA_WS_WC, ALPHA_WS_BC, ALPHA_WS_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, NUM_WS_LC, NUM_WS_WC, NUM_WS_BC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(WC_NSFOD_FORMAT, NO_EXIST_FILE_1)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, calculateTotal(ALL_CHAR_LC,
                ALPHA_WS_LC, NUM_WS_LC), calculateTotal(ALL_CHAR_WC,
                ALPHA_WS_WC, NUM_WS_WC), calculateTotal(ALL_CHAR_BC,
                ALPHA_WS_BC, NUM_WS_BC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(true, true, true,
                NO_EXIST_FILE_2, ALL_CHAR_NAME, ALPHA_WS_NAME, NUM_WS_NAME, NO_EXIST_FILE_1);

        assertEquals(expectedOutput, actualOutput);
    }
     */

    @Test
    void countFromMultipleFileDuplicateFilesTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, ALL_CHAR_LC, ALL_CHAR_WC, ALL_CHAR_BC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, ALL_CHAR_LC, ALL_CHAR_WC, ALL_CHAR_BC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, NUM_WS_LC, NUM_WS_WC, NUM_WS_BC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, calculateTotal(ALL_CHAR_LC,
                ALL_CHAR_LC, NUM_WS_LC), calculateTotal(ALL_CHAR_WC,
                ALL_CHAR_WC, NUM_WS_WC), calculateTotal(ALL_CHAR_BC,
                ALL_CHAR_BC, NUM_WS_BC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(true, true, true,
                ALL_CHAR_NAME, ALL_CHAR_NAME, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void countFromMultipleFileOneFolderTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, ALL_CHAR_LC, ALL_CHAR_WC, ALL_CHAR_BC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(IS_A_DTRY_FORMAT, WC_FOLDER_NAME_1)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, NUM_WS_LC, NUM_WS_WC, NUM_WS_BC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, calculateTotal(ALL_CHAR_LC,
                NUM_WS_LC), calculateTotal(ALL_CHAR_WC,
                NUM_WS_WC), calculateTotal(ALL_CHAR_BC,
                NUM_WS_BC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(true, true, true,
                ALL_CHAR_NAME, WC_FOLDER_NAME_1, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }
     */

    /** COMMENTED OUT SINCE TEST IS WRONG
    @Test
    void countFromMultipleFileSomeNotExistOneFolderTest() throws Exception {
        String expectedOutput = String.format(THREE_ARG_FORMAT, ALL_CHAR_LC, ALL_CHAR_WC, ALL_CHAR_BC, ALL_CHAR_NAME)
                + NEWLINE_CHAR + String.format(WC_NSFOD_FORMAT, NO_EXIST_FILE_1)
                + NEWLINE_CHAR + String.format(IS_A_DTRY_FORMAT, WC_FOLDER_NAME_1)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, NUM_WS_LC, NUM_WS_WC, NUM_WS_BC, NUM_WS_NAME)
                + NEWLINE_CHAR + String.format(THREE_ARG_FORMAT, calculateTotal(ALL_CHAR_LC,
                NUM_WS_LC), calculateTotal(ALL_CHAR_WC,
                NUM_WS_WC), calculateTotal(ALL_CHAR_BC,
                NUM_WS_BC), MULP_FILE_TOTAL);

        String actualOutput = wcApplication.countFromFiles(true, true, true,
                ALL_CHAR_NAME, NO_EXIST_FILE_1, WC_FOLDER_NAME_1, NUM_WS_NAME);

        assertEquals(expectedOutput, actualOutput);
    }
    */

    /**
     * Help to sum up a sequence of integers
     *
     * @param toSum The sequence of integers to sum up
     * @return The sum of the sequence of integers
     */
    private int calculateTotal(int... toSum) {
        int sum = 0;
        for (int value : toSum) {
            sum += value;
        }
        return sum;
    }
}
