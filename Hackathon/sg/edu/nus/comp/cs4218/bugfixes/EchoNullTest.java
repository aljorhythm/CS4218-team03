package sg.edu.nus.comp.cs4218.bugfixes;


import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.EchoException;
import sg.edu.nus.comp.cs4218.impl.app.EchoApplication;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class EchoNullTest {
    EchoApplication echo = new EchoApplication();


    @Test
    public void runSuccessNoArgument() throws EchoException{
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        String exceptedResult = "";
        String[] args = {};
        echo.run(args,System.in, stdout);
        assertEquals(exceptedResult, stdout.toString());
    }

    @Test
    public void runThrowsExceptionNullStdout() {
        String[] args = {"hello"};
        assertThrows(EchoException.class, () ->
                echo.run(args, System.in, null));
    }

}
