package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.ExitException;

import static org.junit.jupiter.api.Assertions.*;

class ExitApplicationTest {

    ExitApplication exitApplication;

    @BeforeEach
    void setUp() {
        exitApplication = new ExitApplication();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testExit_errorSimpleInput_Failure(){
        String[] args_test = new String[1];
        args_test[0] = "Exit";
        assertThrows(ExitException.class,() -> {exitApplication.run(args_test,System.in,System.out);});
    }

    @Test
    void testExit_errorMultipleInput_Failure(){
        String[] args_test = new String[2];
        args_test[0] = "exit";
        args_test[1] = "exit";
        assertThrows(ExitException.class,() -> {exitApplication.run(args_test,System.in,System.out);});
    }
}