package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Joakim Boman on 2019-02-18.
 */
class WcApplicationTest {
    InputStream defaultInputStream;
    InputStream oneLineInputStream;
    InputStream emptyInputStream;

    @BeforeEach
    void setUp() throws IOException {
        defaultInputStream = mock(InputStream.class);
        when(defaultInputStream.read()).thenReturn(97, 98, 32, 97, 98, 99, 10, 98, 32, 99, 99, 99, -1);
        oneLineInputStream = mock(InputStream.class);
        when(oneLineInputStream.read()).thenReturn(64, 65, 32, 88, 89, 90, -1);
        emptyInputStream = mock(InputStream.class);
        when(emptyInputStream.read()).thenReturn(-1);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testCountFromFiles_Default_Succes() {

    }

    @Test
    void testCountFromStdin_DefaultOutPutAll_Success() throws Exception {
        WcApplication wcApp = new WcApplication();
        assertEquals("2 4 12", wcApp.countFromStdin(true, true, true, defaultInputStream));
    }

    @Test
    void testCountFromStdin_EmptyOutPutAll_Success() throws Exception {
        WcApplication wcApp = new WcApplication();
        assertEquals("0 0 0", wcApp.countFromStdin(true, true, true, emptyInputStream));
    }

    @Test
    void testCountFromStdin_NoLinesWanted_Success() throws Exception {
        WcApplication wcApp = new WcApplication();
        assertEquals("4 12", wcApp.countFromStdin(true, false, true, defaultInputStream));
    }

    @Test
    void testCountFromStdin_NoWordsWanted_Success() throws Exception {
        WcApplication wcApp = new WcApplication();
        assertEquals("2 12", wcApp.countFromStdin(true, false, true, defaultInputStream));
    }

    @Test
    void testCountFromStdin_NoBytessWanted_Success() throws Exception {
        WcApplication wcApp = new WcApplication();
        assertEquals("2 4", wcApp.countFromStdin(true, false, true, defaultInputStream));
    }

    @Test
    void testRun_DefaultOutPutAll_Success() throws AbstractApplicationException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WcApplication wcApp = new WcApplication();
        String[] emptyStringArray = new String[0];
        wcApp.run(emptyStringArray, defaultInputStream, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals("2 4 12", new String(byteArray));
    }
}