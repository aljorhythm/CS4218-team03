package sg.edu.nus.comp.cs4218m1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtils {
    /**
     * Test data folder
     */
    public static final String TEST_DATA_DIR = System.getProperty("user.dir") + File.separator + "resources";

    /**
     * Non existent folder
     */
    public static final String NON_EXISTENT_DIR = pathToTestDataSubdir("non-existent");

    /**
     * @param subDir
     * @return sub-directory path from test data folder
     */
    public static String pathToTestDataSubdir(String subDir) {
        return TEST_DATA_DIR + File.separator + subDir;
    }

    /**
     * Asserts that expected arr is equal to list.
     * @param arr
     * @param res List that will be converted to an array before assertion
     */
    public static void assertArrayEqualsList(String[] arr, List<String> res) {
        if(arr == null) {
            assertEquals(arr ,res);
        } else {
            Assertions.assertArrayEquals(arr, res.toArray(new String[]{}));
        }
    }

    @Test
    public void testPathToTestDataDirExists() {
        File file = new File(TEST_DATA_DIR);
        assertTrue(file.isDirectory());
        assertTrue(file.exists());
    }

    /**
     * Tests equality assertion of array and list
     */
    @Test
    public void arrayAndListAreEqual() {
        String hello = "HELLO", two = "2222";
        assertArrayEqualsList(null, null);
        ArrayList<String> strings = new ArrayList<String>();
        assertArrayEqualsList(new String[]{}, strings);
        strings.add(hello);
        assertArrayEqualsList(new String[]{hello}, strings);
        strings.add(two);
        assertArrayEqualsList(new String[]{hello, two}, strings);
        assertThrows(AssertionFailedError.class, () -> {
            assertArrayEqualsList(new String[]{hello}, strings);
        });
    }

    /**
     * Test generate random string
     */
    @Test
    public void testGenerateRandomString() {
        int length = 12351;
        assertEquals(length, generateRandomString(length));
    }

    /**
     * Generate randomString
     */
    public static String generateRandomString(int length) {
        byte[] array = new byte[length]; // length is bounded by 7
        new java.util.Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
