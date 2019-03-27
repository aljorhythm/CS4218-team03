package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.app.CdApplication;
import sg.edu.nus.comp.cs4218.impl.app.PwdApplication;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218m1.TestUtils.CHARSET_UTF8;

public class SeqCdPwdTest {
    CdApplication cdApplication;
    PwdApplication pwdApplication;
    String pathToTestDir = TestUtils.TEST_DATA_DIR + File.separator + "seqCdPwdTestDir" + File.separator;
    String tempDir = pathToTestDir + "seqCdPwdTest";
    SequenceCommand sequenceCommand;
    CallCommand cdCommand;
    CallCommand pwdCommand;

    /**
     * Original working directory before this test class is run
     */
    private static String origWorkingDir;

    @BeforeAll
    public static void createDirectories() throws IOException {
        origWorkingDir = Environment.currentDirectory;
        System.out.println("Original dir was:" + Environment.currentDirectory);
    }

    @BeforeEach
    void setUp() throws IOException {
        File tempFile = new File(tempDir);
        tempFile.mkdirs();
    }


    @AfterEach
    void tearDown() {
        File tempFile = new File(tempDir);
        deleteDirectory(tempFile, tempFile.listFiles());
    }

    @AfterAll
    static void tearDownAll() {
        Environment.currentDirectory = origWorkingDir;
        System.out.println("Current directory reverted to :" + Environment.currentDirectory);
    }
    /**
     * Helper function used to delete the a directory and its content.
     * @param directory The directory to be deleted.
     * @param files The content to be deleted.
     */
    public static void deleteDirectory(File directory, File... files) {
        if(null != files){
            for(int i=0; i< files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i], files[i].listFiles());
                }
                else {
                    files[i].delete();
                }
            }
        }
        if(directory != null) {
            directory.delete();
        }
    }

    @Test
    void testSequenceCmdCdPwd() throws UnsupportedEncodingException {
        String expected = tempDir + StringUtils.STRING_NEWLINE;
        InputStream inputStream = new ByteArrayInputStream("test data ".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<Command> commands = new ArrayList<>();
        List<String> argForCd = new ArrayList<>();
        List<String> argForPwd = new ArrayList<>();
        argForCd.add("cd");
        argForCd.add(tempDir);
        argForPwd.add("pwd");
        cdCommand = new CallCommand(argForCd, new ArgumentResolver(), new ApplicationRunner());
        pwdCommand = new CallCommand(argForPwd, new ArgumentResolver(), new ApplicationRunner());
        commands.add(cdCommand);
        commands.add(pwdCommand);
        sequenceCommand = new SequenceCommand(commands);
        try {
            sequenceCommand.evaluate(inputStream,outputStream);
        } catch (AbstractApplicationException | ShellException e) {
            e.printStackTrace();
        }
        String actual = outputStream.toString(CHARSET_UTF8);
        assertEquals(actual, expected);
    }
}
