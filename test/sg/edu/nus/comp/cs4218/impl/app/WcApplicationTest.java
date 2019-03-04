package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.WcException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WcApplicationTest {
    WcApplication wcApp;
    InputStream defaultInputStream;
    InputStream emptyInputStream;

    @BeforeEach
    void setUp() throws IOException {
        wcApp = new WcApplication();
        defaultInputStream = mock(InputStream.class);
        when(defaultInputStream.read()).thenReturn(97, 98, 32, 97, 98, 99, 10, 98, 32, 99, 99, 99, -1);
        emptyInputStream = mock(InputStream.class);
        when(emptyInputStream.read()).thenReturn(-1);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testCountFromFiles_Default_Success() {

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
        assertEquals("2 4 12", wcApp.countFromStdin(true, true, true, defaultInputStream));
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
        assertEquals("2 12", wcApp.countFromStdin(true, true, false, defaultInputStream));
    }

    @Test
    void testCountFromStdinOnlyBytesSuccess() throws Exception {
        assertEquals("2", wcApp.countFromStdin(true, false, false, defaultInputStream));
    }

    @Test
    void testCountFromStdinNoOutPutWantedSuccess() throws Exception {
        assertEquals("", wcApp.countFromStdin(false, false, true, defaultInputStream));
    }

    @Test
    void testCountFromStdinNoLinesWantedSuccess() throws Exception {
        assertEquals("4 12", wcApp.countFromStdin(true, false, true, defaultInputStream));
    }

    @Test
    void testCountFromStdinOnlyWordsSuccess() throws Exception {
        assertEquals("2", wcApp.countFromStdin(false, false, true, defaultInputStream));
    }

    @Test
    void testRunDefaultOutPutAllSuccess() throws AbstractApplicationException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WcApplication wcApp = new WcApplication();
        String[] emptyStringArray = new String[0];
        wcApp.run(emptyStringArray, defaultInputStream, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals("2 4 12", new String(byteArray));
    }
}