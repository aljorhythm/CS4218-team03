package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static sg.edu.nus.comp.cs4218m1.TestUtils.STRING_FILE_SEP;
import static sg.edu.nus.comp.cs4218m1.TestUtils.prependStringToStrings;

/**
 * Utility for tests that use directory structure
 */
@SuppressWarnings({"PMD.LongVariable", "PMD.VariableNamingConventions", "PMD."})
public class DirectoryStructureTest {//NOPMD
    /**
     * Directory for resources used in this test
     **/
    protected static String testRootDir = TestUtils.pathToTestDataSubdir("dirStructureTests");

    /**
     * All text files found in resource directory
     **/
    protected static String[] rootTxtFiles = prependStringToStrings(testRootDir + STRING_FILE_SEP, new String[]{"txt1.txt", "txt2.txt", "txt3.txt"});

    /**
     * All files that have no extensions
     **/
    protected static String[] rootNonExtended = prependStringToStrings(testRootDir + STRING_FILE_SEP, new String[]{"all1", "all2", "all3"});

    protected static String subDir = testRootDir + STRING_FILE_SEP + "subdir";
    protected static String subDir_filesub = subDir + STRING_FILE_SEP + "filesub";
    ;
    protected static String subDir_subfile = subDir + STRING_FILE_SEP + "subfile";
    protected static String subDir_subfile_txt = subDir + STRING_FILE_SEP + "subfile.txt";

    protected static String subDir1 = testRootDir + STRING_FILE_SEP + "subdir1";
    protected static String subDir1_subfile_txt = subDir1 + STRING_FILE_SEP + "subfile.txt";

    protected static String subDir10 = testRootDir + STRING_FILE_SEP + "subdir10";
    protected static String subDir10_subfile_txt = subDir10 + STRING_FILE_SEP + "subfile.txt";

    protected static String[] allSubDirFiles = {
            subDir_filesub,
            subDir_subfile,
            subDir_subfile_txt,
            subDir1_subfile_txt,
            subDir10_subfile_txt
    };

    /**
     * All direct descendent files found in resource root directory
     **/
    protected static String[] allTestRootFiles = Stream
            .of(rootNonExtended, rootTxtFiles, new String[]{subDir, subDir1, subDir10})
            .flatMap(s -> Arrays.stream(s))
            .sorted()
            .toArray(String[]::new);


    /**
     * List of files in test root
     */
    protected static List<String> allTestRootFilesList = Arrays.asList(allTestRootFiles);
}
