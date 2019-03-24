package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

class IORedirectionHandlerTest {
    private static String tempDir;
    private static Path tempDirPath;

    /**
     * Create temp directory and files
     * @param tempDir
     */
    @BeforeAll
    static void initTempDir(@TempDir Path tempDirPath) {
        IORedirectionHandlerTest.tempDirPath = tempDirPath;
        IORedirectionHandlerTest.tempDir = tempDirPath.toAbsolutePath().toString();
    }

    @Test
    void extractRedirOptions() {
    }

    @Test
    void getNoRedirArgsList() {
    }
}