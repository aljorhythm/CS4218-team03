package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.ExitException;
import sg.edu.nus.comp.cs4218.impl.app.ExitApplication;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExitApplicationTest {

    ExitApplication exitApplication;

    @BeforeEach
    void setUp() {
        exitApplication = new ExitApplication();
    }

    @Test
    void testExit_DefaultInput_Success() {
        String[] args_test = new String[0];
        assertThrows(ExitException.class, () -> {
            exitApplication.run(args_test, System.in, System.out);
        });
    }
}