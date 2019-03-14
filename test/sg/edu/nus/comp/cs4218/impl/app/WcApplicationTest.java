package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class WcApplicationTest {
    WcApplication wcApp;
    InputStream defaultInputStream;
    InputStream emptyInputStream;

    @BeforeEach
    void setUp() throws IOException {
        wcApp = new WcApplication();
        defaultInputStream = IOUtils.stringToInputStream("ab abc\nb ccc");
        emptyInputStream = IOUtils.stringToInputStream("");
    }

    @Test
    void testGetDateNullInputFailure() {
        assertThrows(WcException.class, () -> {wcApp.countFromStdin(true, true, true, null);});
    }

    @Test
    void testCountFromStdinEmptyInputSuccess() throws Exception {
        assertEquals("0 0 0", wcApp.countFromStdin(true, true, true, emptyInputStream));
    }

    @Test
    void testCountFromStdinDefaultOutPutAllSuccess() throws Exception {
        assertEquals("2 4 11", wcApp.countFromStdin(true, true, true, defaultInputStream));
    }

    @Test
    void testCountFromStdinNoBytessWantedSuccess() throws Exception {
        assertEquals("2 4", wcApp.countFromStdin(false, true, true, defaultInputStream));
    }

    @Test
    void testCountFromStdinOnlyLinesSuccess() throws Exception {
        assertEquals("2", wcApp.countFromStdin(false, true, false, defaultInputStream));
    }

    @Test
    void testCountFromStdinNoWordsWantedSuccess() throws Exception {
        assertEquals("2 11", wcApp.countFromStdin(true, true, false, defaultInputStream));
    }

    @Test
    void testCountFromStdinOnlyBytesSuccess() throws Exception {
        assertEquals("11", wcApp.countFromStdin(true, false, false, defaultInputStream));
    }

    @Test
    void testCountFromStdinNoOutPutWantedSuccess() throws Exception {
        assertEquals("", wcApp.countFromStdin(false, false, false, defaultInputStream));
    }

    @Test
    void testCountFromStdinNoLinesWantedSuccess() throws Exception {
        assertEquals("4 11", wcApp.countFromStdin(true, false, true, defaultInputStream));
    }

    @Test
    void testCountFromStdinOnlyWordsSuccess() throws Exception {
        assertEquals("4", wcApp.countFromStdin(false, false, true, defaultInputStream));
    }
}