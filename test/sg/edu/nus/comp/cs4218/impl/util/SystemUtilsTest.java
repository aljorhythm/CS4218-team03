package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemUtilsTest {

    /**
     * Checks logical equivalency of file separator and operating system
     */
    @Test
    void shouldReturnRespectiveFileSeparator() {
        if(SystemUtils.isMac()) {
            assertEquals("/", StringUtils.STRING_FILE_SEP);
        } else if (SystemUtils.isUnix()) {
            assertEquals("/", StringUtils.STRING_FILE_SEP);
        } else if(SystemUtils.isWindows()) {
            assertEquals("\\", StringUtils.STRING_FILE_SEP);
        } else {
            Assertions.fail("OS not handled");
        }
    }
}