package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.app.WcApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WcApplicationTest {
    WcApplication wcApp;
    InputStream defaultIStream;
    InputStream emptyIStream;

    @BeforeEach
    void setUp() throws IOException {
        wcApp = new WcApplication();
        defaultIStream = IOUtils.stringToInputStream("ab abc" + StringUtils.STRING_NEWLINE + "b ccc");
        emptyIStream = IOUtils.stringToInputStream("");
    }

    @Test
    void testGetDateNullInputFailure() {
        assertThrows(WcException.class, () -> {wcApp.countFromStdin(true, true, true, null);});
    }

    @Test
    void testCountFromStdinEmptyInputSuccess() throws Exception {
        assertEquals("0 0 0", wcApp.countFromStdin(true, true, true, emptyIStream));
    }

    @Test
    void testCountFromStdinDefaultOutPutAllSuccess() throws Exception {
        assertEquals("1 4 12", wcApp.countFromStdin(true, true, true, defaultIStream));
    }

    @Test
    void testCountFromStdinNoBytessWantedSuccess() throws Exception {
        assertEquals("1 4", wcApp.countFromStdin(false, true, true, defaultIStream));
    }

    @Test
    void testCountFromStdinOnlyLinesSuccess() throws Exception {
        assertEquals("1", wcApp.countFromStdin(false, true, false, defaultIStream));
    }

    @Test
    void testCountFromStdinNoWordsWantedSuccess() throws Exception {
        assertEquals("1 12", wcApp.countFromStdin(true, true, false, defaultIStream));
    }

    @Test
    void testCountFromStdinOnlyBytesSuccess() throws Exception {
        assertEquals("12", wcApp.countFromStdin(true, false, false, defaultIStream));
    }

    @Test
    void testCountFromStdinNoOutPutWantedSuccess() throws Exception {
        assertEquals("", wcApp.countFromStdin(false, false, false, defaultIStream));
    }

    @Test
    void testCountFromStdinNoLinesWantedSuccess() throws Exception {
        assertEquals("4 12", wcApp.countFromStdin(true, false, true, defaultIStream));
    }

    @Test
    void testCountFromStdinOnlyWordsSuccess() throws Exception {
        assertEquals("4", wcApp.countFromStdin(false, false, true, defaultIStream));
    }
}