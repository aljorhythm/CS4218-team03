package sg.edu.nus.comp.cs4218.bugfixes;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.app.SortApplication;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SortTest {
    String sortPATH = "resources/sortTest/sort1.txt";
    SortApplication sortApplication = new SortApplication();

    @Test
    public void sortForUncaughtException(){
        String[] args = {"-n", "sort1.txt"};
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        assertThrows(SortException.class, ()-> sortApplication.run(args, System.in, stdout));
    }

    @Test
    public void runFailureInvalidFlagDash() {
        String[] args = {"-", sortPATH};
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        assertThrows(SortException.class, () ->
                sortApplication.run(args, System.in, stdout), "");
    }

    @Test
    public void runSuccessOneFileFFlag() throws
            AbstractApplicationException {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        String expectedResult = "&&**" +
                System.lineSeparator()+
                "0" + System.lineSeparator()+
                "0a" + System.lineSeparator()+
                "1" + System.lineSeparator()+
                "10" + System.lineSeparator()+
                "10a" + System.lineSeparator()+
                "1a" + System.lineSeparator()+
                "2" + System.lineSeparator()+
                "2a" + System.lineSeparator()+
                "AABB" + System.lineSeparator()+
                "aabb" + System.lineSeparator()+
                "ABCD" + System.lineSeparator()+
                "abcd";
        String[] args = {"-f", sortPATH};
        sortApplication.run(args, System.in, stdout);
        assertEquals(expectedResult,
                stdout.toString());
    }

}
