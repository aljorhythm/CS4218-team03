package sg.edu.nus.comp.cs4218m1.impl.cmd;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_DOUBLE_QUOTE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_SINGLE_QUOTE;

class QuotingTest {
    private static final ApplicationRunner MOCK_APP_RUNNER = mock(ApplicationRunner.class);
    private static final String WORD_UNQUOTED = "test for quoting";
    private static final String WORD_QUOTED = String.format(CHAR_SINGLE_QUOTE + "%s" + CHAR_SINGLE_QUOTE, WORD_UNQUOTED);
    private static final String WORD_DOUBLE_QUOTED = String.format(CHAR_DOUBLE_QUOTE + "%s" + CHAR_DOUBLE_QUOTE, WORD_UNQUOTED);
    private static final String WORD_MIX_QUOTED1 = String.format(CHAR_DOUBLE_QUOTE + CHAR_SINGLE_QUOTE + "%s" + CHAR_SINGLE_QUOTE + CHAR_DOUBLE_QUOTE, WORD_UNQUOTED);
    private static final String WORD_MIX_QUOTED2 = String.format(CHAR_SINGLE_QUOTE + CHAR_DOUBLE_QUOTE + "%s" + CHAR_DOUBLE_QUOTE + CHAR_SINGLE_QUOTE, WORD_UNQUOTED);
    private static final String WORD_ERROR_QUOTED = String.format(CHAR_SINGLE_QUOTE  + "%s" + CHAR_DOUBLE_QUOTE, WORD_UNQUOTED);
    
    @Test
    void testUnquoted() throws ShellException, AbstractApplicationException {
        ArgumentResolver resolver = new ArgumentResolver();
        List<String> resolved = resolver.resolveOneArgument(WORD_UNQUOTED, MOCK_APP_RUNNER);
        String[] expected = new String[]{WORD_UNQUOTED};
        TestUtils.assertArrayEqualsList(expected, resolved);
    }

    @Test
    void testSingleQuotes() throws ShellException, AbstractApplicationException {
        ArgumentResolver resolver = new ArgumentResolver();
        List<String> resolved = resolver.resolveOneArgument(WORD_QUOTED, MOCK_APP_RUNNER);
        String[] expected = new String[]{WORD_UNQUOTED};
        TestUtils.assertArrayEqualsList(expected, resolved);
    }

    @Test
    void testDoubleQuotes() throws ShellException, AbstractApplicationException {
        ArgumentResolver resolver = new ArgumentResolver();
        List<String> resolved = resolver.resolveOneArgument(WORD_DOUBLE_QUOTED, MOCK_APP_RUNNER);
        String[] expected = new String[]{WORD_UNQUOTED};
        TestUtils.assertArrayEqualsList(expected, resolved);
    }

    @Test
    void testMixQuotes1() throws ShellException, AbstractApplicationException {
        ArgumentResolver resolver = new ArgumentResolver();
        List<String> resolved = resolver.resolveOneArgument(WORD_MIX_QUOTED1, MOCK_APP_RUNNER);
        String[] expected = new String[]{WORD_QUOTED};
        TestUtils.assertArrayEqualsList(expected, resolved);
    }

    @Test
    void testMixQuotes2() throws ShellException, AbstractApplicationException {
        ArgumentResolver resolver = new ArgumentResolver();
        List<String> resolved = resolver.resolveOneArgument(WORD_MIX_QUOTED2, MOCK_APP_RUNNER);
        String[] expected = new String[]{WORD_DOUBLE_QUOTED};
        TestUtils.assertArrayEqualsList(expected, resolved);
    }

    @Test
    void testErrorSingleQuote() throws ShellException, AbstractApplicationException {
        ArgumentResolver resolver = new ArgumentResolver();
        assertThrows(ShellException.class, () -> {
            resolver.resolveOneArgument(WORD_ERROR_QUOTED, MOCK_APP_RUNNER);
        });
    }
}