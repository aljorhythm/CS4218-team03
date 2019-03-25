package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;
import sg.edu.nus.comp.cs4218.impl.app.WcApplication;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

public class PipeGrepWcTest {

    GrepApplication grepApplication;
    WcApplication wcApplication;
    CallCommand wcCommand;
    CallCommand wcCommand2;
    CallCommand grepCommand;
    PipeCommand pipeCommand;

    private File file1;
    private File file2;
    private File file3;
    private String answer1;
    private String answer2;
    private String answer3;

    private static final String FILE_NAME1 = "file1.txt";
    private static final String FILE_NAME2 = "file2.txt";
    private static final String FILE_NAME3 = "file3.txt";
    private static final String LINE1 = "test1 normal";
    private static final String LINE2 = "test2 normal %$^#@^&@*";
    private static final String LINE3 = "\\2314\\324.6;213[135";
    private static final String FILE_CONTENT1 = "file1 has 2 lines" + StringUtils.STRING_NEWLINE + LINE1;
    private static final String FILE_CONTENT2 = "file2 has 3 lines" + StringUtils.STRING_NEWLINE +
            LINE1 + StringUtils.STRING_NEWLINE + LINE2;
    private static final String FILE_CONTENT3 = "file3 has 4 lines" + StringUtils.STRING_NEWLINE +
            LINE1 + StringUtils.STRING_NEWLINE + LINE2 + StringUtils.STRING_NEWLINE + LINE3;

    @BeforeEach
    void setUp() throws IOException {
        grepApplication = new GrepApplication();
        wcApplication = new WcApplication();
        file1 = createAndWriteToFile(FILE_NAME1, FILE_CONTENT1);
        file2 = createAndWriteToFile(FILE_NAME2, FILE_CONTENT2);
        file3 = createAndWriteToFile(FILE_NAME3, FILE_CONTENT3);
        answer1 = "2 6 29 " + file1.getAbsolutePath() + StringUtils.STRING_NEWLINE;
        answer2 = LINE1 + StringUtils.STRING_NEWLINE + LINE2 + StringUtils.STRING_NEWLINE;
        answer3 = "3 " + file2.getAbsolutePath() + StringUtils.STRING_NEWLINE;
    }

    @AfterEach
    void tearDown() {
        file1.delete();
        file2.delete();
        file3.delete();
    }

    private File createAndWriteToFile(String filename, String content) throws IOException {
        File file = new File(filename);
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName()));
        writer.write(content);
        writer.close();

        return file;
    }

    @Test
    void testPipeGrepWcFile1() throws UnsupportedEncodingException {
        InputStream inputStream = new ByteArrayInputStream("test data ".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<CallCommand> commands = new ArrayList<>();
        List<String> argForGrep = new ArrayList<>();
        List<String> argForWc = new ArrayList<>();
        argForGrep.add("grep");
        argForGrep.add("test");
        argForGrep.add(file1.getAbsolutePath());
        argForWc.add("wc");
        argForWc.add(file1.getAbsolutePath());
        grepCommand = new CallCommand(argForGrep, new ArgumentResolver(), new ApplicationRunner());
        wcCommand = new CallCommand(argForWc, new ArgumentResolver(), new ApplicationRunner());
        commands.add(grepCommand);
        commands.add(wcCommand);
        pipeCommand = new PipeCommand(commands);
        try {
            pipeCommand.evaluate(inputStream,outputStream);
        } catch (AbstractApplicationException | ShellException e) {
            e.printStackTrace();
        }
        String actual = outputStream.toString(CHARSET_UTF8);
        assertEquals(answer1,actual);
    }

    @Test
    void testPipeWcGrepFile2() throws UnsupportedEncodingException {
        InputStream inputStream = new ByteArrayInputStream("test data ".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<CallCommand> commands = new ArrayList<>();
        List<String> argForGrep = new ArrayList<>();
        List<String> argForWc = new ArrayList<>();
        argForGrep.add("grep");
        argForGrep.add("test");
        argForGrep.add(file2.getAbsolutePath());
        argForWc.add("wc");
        argForWc.add(file2.getAbsolutePath());
        grepCommand = new CallCommand(argForGrep, new ArgumentResolver(), new ApplicationRunner());
        wcCommand = new CallCommand(argForWc, new ArgumentResolver(), new ApplicationRunner());
        commands.add(wcCommand);
        commands.add(grepCommand);
        pipeCommand = new PipeCommand(commands);
        try {
            pipeCommand.evaluate(inputStream,outputStream);
        } catch (AbstractApplicationException | ShellException e) {
            e.printStackTrace();
        }
        String actual = outputStream.toString(CHARSET_UTF8);
        assertEquals(answer2,actual);
    }

    @Test
    void testPipeWcGrepWcFile3() throws UnsupportedEncodingException {
        InputStream inputStream = new ByteArrayInputStream("test data ".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<CallCommand> commands = new ArrayList<>();
        List<String> argForGrep = new ArrayList<>();
        List<String> argForWc1 = new ArrayList<>();
        List<String> argForWc2 = new ArrayList<>();
        argForGrep.add("grep");
        argForGrep.add("test");
        argForGrep.add(file3.getAbsolutePath());
        argForWc1.add("wc");
        argForWc1.add(file3.getAbsolutePath());
        argForWc2.add("wc");
        argForWc2.add("-l");
        argForWc2.add(file2.getAbsolutePath());
        grepCommand = new CallCommand(argForGrep, new ArgumentResolver(), new ApplicationRunner());
        wcCommand = new CallCommand(argForWc1, new ArgumentResolver(), new ApplicationRunner());
        wcCommand2 = new CallCommand(argForWc2, new ArgumentResolver(), new ApplicationRunner());
        commands.add(wcCommand);
        commands.add(grepCommand);
        commands.add(wcCommand2);
        pipeCommand = new PipeCommand(commands);
        try {
            pipeCommand.evaluate(inputStream,outputStream);
        } catch (AbstractApplicationException | ShellException e) {
            e.printStackTrace();
        }
        String actual = outputStream.toString(CHARSET_UTF8);
        assertEquals(answer3,actual);
    }
}
