/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.impl.app.ExitApplication;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Permission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;

class ExitApplicationTest {

    private static ExitApplication application;
    private static InputStream mockIs;
    private static OutputStream mockOs;

    private static final String EXIT_CODE = "0";

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

    @BeforeAll
    static void setUp() {
        application = spy(new ExitApplication());
        mockIs = Mockito.mock(InputStream.class);
        mockOs = Mockito.mock(OutputStream.class);
        System.setSecurityManager(new NoExitSecurityManager());
    }

    @AfterAll
    static void tearDown() {
        System.setSecurityManager(null);
    }

    @Test
    void testTerminateExecution() {
        Exception actualException = assertThrows(Exception.class, () -> application.terminateExecution());
        assertEquals(EXIT_CODE, actualException.getMessage());
    }

    @Test
    void testRunExitWithoutArguments() {
        String[] args = new String[]{};

        Exception actualException = assertThrows(SecurityException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(EXIT_CODE, actualException.getMessage());
    }

    @Test
    void testRunExitWithSingleWordArgument() {
        String[] args = new String[]{"exit"};

        Exception actualException = assertThrows(SecurityException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(EXIT_CODE, actualException.getMessage());
    }

    @Test
    void testRunExitWithSingleNumericArgument() {
        String[] args = new String[]{"-1"};

        Exception actualException = assertThrows(SecurityException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(EXIT_CODE, actualException.getMessage());
    }

    @Test
    void testRunExitWithMultipleArguments() {
        String[] args = new String[]{"1", "noExit", "exit123"};

        Exception actualException = assertThrows(SecurityException.class, () -> application.run(args, mockIs, mockOs));
        assertEquals(EXIT_CODE, actualException.getMessage());
    }

    @Test
    void testRunExitWithNullStdIn() {
        String[] args = new String[]{};

        Exception actualException = assertThrows(SecurityException.class, () ->
                application.run(args, null, mockOs));
        assertEquals(EXIT_CODE, actualException.getMessage());
    }

    @Test
    void testRunExitWithNullStdOut() {
        String[] args = new String[]{};

        Exception actualException = assertThrows(SecurityException.class, () ->
                application.run(args, mockIs, null));
        assertEquals(EXIT_CODE, actualException.getMessage());
    }
}
