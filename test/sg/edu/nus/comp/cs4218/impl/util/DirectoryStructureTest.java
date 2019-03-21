package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Utility for tests that use directory structure
 */
public class DirectoryStructureTest {
    /**
     * Directory for resources used in this test
     **/
    protected static String testDir = TestUtils.pathToTestDataSubdir("globTests");

    /**
     * All text files found in resource directory
     **/
    protected static String[] rootTxtFiles = {"txt1.txt", "txt2.txt", "txt3.txt"};

    /**
     * All files that have no extensions
     **/
    protected static String[] rootNonExtended = {"all1", "all2", "all3"};

    /**
     * All files found in resource directory
     **/
    protected static String[] allFiles = Stream
            .concat(Arrays.stream(rootNonExtended), Arrays.stream(rootTxtFiles))
            .toArray(String[]::new);

    /**
     * List of files
     */
    protected static List<String> allFilesList = Arrays.asList(allFiles);
}
