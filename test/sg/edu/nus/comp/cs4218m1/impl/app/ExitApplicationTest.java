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
    void testExitDefaultInputSuccess() {
        assertThrows(ExitException.class, () -> {
            exitApplication.run(new String[0], System.in, System.out);
        });
    }
}