package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.CatException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatApplicationTest {
    CatApplication catApplication;
    InputStream defaultIStream;
    InputStream emptyIStream;
    String testFileName1 = "catContent1.txt";
    String testFileName2 = "catContent2.txt";
    String testFile1Content = "test string for\ntesting cat\nwith junit 5.\n";
    String testFile2Content = "another file, number 2\nsecond for testing that\ncat \nworks like \nit should\n. 1234 %&#!$@\n";

    @BeforeEach
    public void setUp() throws Exception {
        catApplication = new CatApplication();
        defaultIStream = mock(InputStream.class);
        when(defaultIStream.read()).thenReturn(97, 98, 32, 97, 98, 99, 10, 98, 32, 99, 99, 99, -1);
        emptyIStream = mock(InputStream.class);
        when(emptyIStream.read()).thenReturn(-1);
    }

    @Test
    public void testCatStdinNullInputFailure() {
        assertThrows(CatException.class, () -> {catApplication.catStdin(null);});
    }

    @Test
    public void testCatStdinEmptyInputSuccess() throws CatException {
        assertEquals("", catApplication.catStdin(emptyIStream));
    }

    @Test
    public void testCatStdinDefaultInputSuccess() throws CatException {
        assertEquals("ab abc\nb ccc", catApplication.catStdin(defaultIStream));
    }

    @Test
    public void testCatFilesNullInputFailure() {
        assertThrows(CatException.class, () -> {catApplication.catFiles(null);});
    }

    @Test
    public void testCatFilesNonExistingFileFailure() {
        assertThrows(CatException.class, () -> {catApplication.catFiles("wrong.txt");});
    }

    @Test
    public void testCatFilesEmptyInputSuccess() throws CatException {
        assertEquals("", catApplication.catFiles("catEmpty.txt"));
    }

    @Test
    public void testCatFilesDefaultInputSuccess() throws CatException {
        assertEquals(testFile1Content, catApplication.catFiles(testFileName1));
    }

    @Test
    public void testCatFilesTwoInputFilesSuccess() throws CatException {
        assertEquals(testFile1Content + testFile2Content,
                catApplication.catFiles(testFileName1, testFileName2));
    }

    @Test
    public void testRunNullArgsFailure() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        assertThrows(CatException.class, () -> {catApplication.run(null, emptyIStream, baos);});
    }

    @Test
    public void testRunNullInputStreamFailure() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] emptyStringArray = new String[0];
        assertThrows(CatException.class, () -> {catApplication.run(emptyStringArray, null, baos);});
    }

    @Test
    public void testRunNullOutputStreamFailure() {
        String[] emptyStringArray = new String[0];
        assertThrows(CatException.class, () -> {catApplication.run(emptyStringArray, emptyIStream, null);});
    }

    @Test
    public void testRunNoArgsSuccess() throws CatException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] emptyArgs = new String[0];
        catApplication.run(emptyArgs, defaultIStream, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals("ab abc\nb ccc", new String(byteArray));
    }

    @Test
    public void testRunOneArgSuccess() throws CatException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[1];
        args[0] = testFileName1;
        catApplication.run(args, null, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals(testFile1Content, new String(byteArray));
    }

    @Test
    public void testRunTwoArgSuccess() throws CatException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] args = new String[2];
        args[0] = testFileName1;
        args[1] = testFileName2;
        catApplication.run(args, null, baos);
        byte[] byteArray = baos.toByteArray();
        assertEquals(testFile1Content + testFile2Content, new String(byteArray));
    }

}
