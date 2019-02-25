package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.PwdException;

import static org.junit.jupiter.api.Assertions.*;

class PwdApplicationTest {
    PwdApplication pwdApplication;

    @BeforeEach
    void setUp() {
        pwdApplication = new PwdApplication();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAbsolutePath_success() throws PwdException {
        assertEquals(Environment.currentDirectory ,pwdApplication.getAbsolutePath());
    }
}