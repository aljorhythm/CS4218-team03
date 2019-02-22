package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import sg.edu.nus.comp.cs4218.impl.app.ExitApplication;

class ExitApplicationTest {

    ExitApplication exitApplication;

    @BeforeEach
    void setUp() {
        exitApplication = new ExitApplication();
    }

    @AfterEach
    void tearDown() {
    }
}