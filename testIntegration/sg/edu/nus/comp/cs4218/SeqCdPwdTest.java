package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.app.CdApplication;
import sg.edu.nus.comp.cs4218.impl.app.PwdApplication;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeqCdPwdTest {
    CdApplication cdApplication;
    PwdApplication pwdApplication;
    String pathToTestDir = TestUtils.TEST_DATA_DIR + File.separator + "seqCdPwdTestDir" + File.separator;
    String tempDir = pathToTestDir + "seqCdPwdTest";
    SequenceCommand sequenceCommand;
    CallCommand cdCommand;
    CallCommand pwdCommand;

    @BeforeEach
    void setUp() throws IOException {
        File tempFile = new File(tempDir);
        tempFile.mkdirs();
    }


    @AfterEach
    void tearDown() throws IOException {
        File tempFile = new File(tempDir);
        deleteDirectory(tempFile, tempFile.listFiles());
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
        OutputStream outputStream = new ByteArrayOutputStream();
        List<Command> commands = new ArrayList<>();
        List<String> argForCd = new ArrayList<>();
        List<String> argForPwd = new ArrayList<>();
        argForCd.add("cd");
        argForCd.add(tempDir);
        argForPwd.add("pwd");
        cdCommand = new CallCommand(argForCd, new ApplicationRunner());
        pwdCommand = new CallCommand(argForPwd, new ApplicationRunner());
        commands.add(cdCommand);
        commands.add(pwdCommand);
        sequenceCommand = new SequenceCommand(commands);
        try {
            sequenceCommand.evaluate(inputStream,outputStream);
        } catch (AbstractApplicationException | ShellException e) {
            e.printStackTrace();
        }
        String actual = ((ByteArrayOutputStream) outputStream).toString(StandardCharsets.UTF_8);
        assertEquals(actual, expected);
    }
}
