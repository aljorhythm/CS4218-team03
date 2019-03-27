package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218m1.TestUtils.STRING_FILE_SEP;

class SystemUtilTest {

    /**
     * Checks logical equivalency of file separator and operating system
     */
    @Test
    void shouldReturnRespectiveFileSeparator() {
        if(SystemUtil.isMac()) {
            assertEquals("/", STRING_FILE_SEP);
        } else if (SystemUtil.isUnix()) {
            assertEquals("/", STRING_FILE_SEP);
        } else if(SystemUtil.isWindows()) {
            assertEquals("\\", STRING_FILE_SEP);
        } else {
            Assertions.fail("OS not handled");
        }
    }
}