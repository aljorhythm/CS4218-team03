package sg.edu.nus.comp.cs4218m1.impl.cmd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static sg.edu.nus.comp.cs4218m1.TestUtils.CHAR_DOUBLE_QUOTE;
import static sg.edu.nus.comp.cs4218m1.TestUtils.CHAR_SINGLE_QUOTE;

class QuotingTest {
    Command command;
    String cmdStringSingle = "echo \'test\'";
    String cmdStringDouble = "echo \"test\"";
    String cmdStrSingleOne = "echo \'test";
    String cmdStrDoubleOne = "echo \"test";
    String cmdStringBackOne = "echo `test";
    ByteArrayOutputStream baoStream = new ByteArrayOutputStream(1024);
    private static final ApplicationRunner MOCK_APP_RUNNER = mock(ApplicationRunner.class);
    private static final String W1_UNQUOTED = "asdbasdb asdsa";
    private static final String W1_QUOTED = String.format(CHAR_SINGLE_QUOTE + "%s" + CHAR_SINGLE_QUOTE, W1_UNQUOTED);
    private static final String W1_DOUBLE_QUOTED = String.format(CHAR_DOUBLE_QUOTE + "%s" + CHAR_DOUBLE_QUOTE, W1_UNQUOTED);

    @Test
    void testSingleQuotes() throws ShellException, AbstractApplicationException, IOException {
        ArgumentResolver resolver = new ArgumentResolver();
        List<String> resolved = resolver.resolveOneArgument(W1_QUOTED, MOCK_APP_RUNNER);
        String[] expected = new String[]{W1_UNQUOTED};
        TestUtils.assertArrayEqualsList(expected, resolved);
    }

    @Test
    void testDoubleQuotes() throws ShellException, AbstractApplicationException, IOException {
        ArgumentResolver resolver = new ArgumentResolver();
        List<String> resolved = resolver.resolveOneArgument(W1_DOUBLE_QUOTED, MOCK_APP_RUNNER);
        String[] expected = new String[]{W1_UNQUOTED};
        TestUtils.assertArrayEqualsList(expected, resolved);
    }

    @Test
    void testSingleQuoteFailure() throws ShellException, AbstractApplicationException, IOException {
        Assertions.fail("todo");
    }

    @Test
    void testDoubleQuoteFailure() throws ShellException, AbstractApplicationException, IOException {
        Assertions.fail("todo");
    }

    @Test
    void testBackQuoteFailure() throws ShellException, AbstractApplicationException, IOException {
        Assertions.fail("todo");
    }
}