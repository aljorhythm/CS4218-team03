package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestUtils {
    /**
     * Test data folder
     */
    public static final String pathToTestDataDir = System.getProperty("user.dir") + File.separator + "test" + File.separator +
            "sg" + File.separator + "edu" + File.separator + "nus" + File.separator + "comp" + File.separator +
            "cs4218";

    /**
     *
     * @param subDir
     * @return sub-directory path from test data folder
     */
    public static String pathToTestDataSubdir(String subDir) {
        return pathToTestDataDir + File.separator + subDir;
    }

    @BeforeAll
    public static void init() {
        System.out.println(pathToTestDataDir);
        System.out.println(pathToTestDataSubdir("subtest_folder"));
    }

    @Test
    public void test() {}
}
