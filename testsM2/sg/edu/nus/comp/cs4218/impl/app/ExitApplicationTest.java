/**
 * From Team 02
 */
package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sg.edu.nus.comp.cs4218.exception.ExitException;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Permission;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;

class ExitApplicationTest {

    private static ExitApplication application;
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
        assertThrows(ExitException.class, () -> application.terminateExecution());
    }

    @Test
    void testRunExitWithoutArguments() {
        String[] args = new String[]{};
        assertThrows(ExitException.class, () -> application.run(args, mockIs, mockOs));
    }

    @Test
    void testRunExitWithSingleWordArgument() {
        String[] args = new String[]{"exit"};
        assertThrows(ExitException.class, () -> application.run(args, mockIs, mockOs));
    }

    @Test
    void testRunExitWithSingleNumericArgument() {
        String[] args = new String[]{"-1"};
        assertThrows(ExitException.class, () -> application.run(args, mockIs, mockOs));
    }

    @Test
    void testRunExitWithMultipleArguments() {
        String[] args = new String[]{"1", "noExit", "exit123"};
        assertThrows(ExitException.class, () -> application.run(args, mockIs, mockOs));
    }

    @Test
    void testRunExitWithNullStdIn() {
        String[] args = new String[]{};
        assertThrows(ExitException.class, () ->
                application.run(args, null, mockOs));
    }

    @Test
    void testRunExitWithNullStdOut() {
        String[] args = new String[]{};
        assertThrows(ExitException.class, () ->
                application.run(args, mockIs, null));
    }
}
