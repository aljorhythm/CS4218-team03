package sg.edu.nus.comp.cs4218m1.impl.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.exception.ExitException;
import sg.edu.nus.comp.cs4218.impl.app.ExitApplication;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Permission;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;

class ExitApplicationTest {

    ExitApplication exitApplication;
    private static InputStream mockIs;
    private static OutputStream mockOs;

    /**
     * Mock SecurityManager that prevents SecurityManager from calling System.exit()
     * Throws SecurityException when checkExit(int status) is called
     */
    private static class NoExitSecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            //No restrictions
        }

        @Override
        public void checkPermission(Permission perm, Object context) {
            //No restrictions
        }

        @Override
        public void checkExit(int status) {
            super.checkExit(status);
            throw new SecurityException(Integer.toString(status));
        }
    }

    @BeforeEach
    void setUp() {
        mockIs = Mockito.mock(InputStream.class);
        mockOs = Mockito.mock(OutputStream.class);
        exitApplication = spy(new ExitApplication());
        System.setSecurityManager(new NoExitSecurityManager());
    }

    @AfterAll
    static void tearDown() {
        System.setSecurityManager(null);
    }

    @Test
    void testExitDefaultInputSuccess() {
        Exception actualException = assertThrows(ExitException.class, () -> {
            exitApplication.run(new String[0], mockIs, mockOs);
        });
        // please add the related exception messages here.
//        assertEquals(EXIT_SUCCESS, actualException.getMessage());
    }

    @Test
    void testExitWithNullStdIn() {
        String[] args = new String[]{};
        Exception actualException = assertThrows(ExitException.class, () ->
                exitApplication.run(args, null, mockOs));
        // please add the related exception messages here.
//        assertEquals(NULL_INPUT, actualException.getMessage());
    }

    @Test
    void testExitWithNullStdOut() {
        String[] args = new String[]{};
        Exception actualException = assertThrows(ExitException.class, () ->
                exitApplication.run(args, mockIs, null));
        // please add the related exception messages here.
//        assertEquals(NULL_OUTPUT, actualException.getMessage());
    }
}