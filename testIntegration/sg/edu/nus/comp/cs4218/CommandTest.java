package sg.edu.nus.comp.cs4218;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;
import sg.edu.nus.comp.cs4218m1.TestUtils;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.IOUtils.stringsToInputStream;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

@SuppressWarnings({"PMD.LongVariable"})
public class CommandTest {

    public static final String WORK_DIR_NAME = TestUtils.pathToTestDataSubdir("CommandTest")+ File.separator;
    public static final String SORT_FILE_NAME = WORK_DIR_NAME + "sortTest.txt";
    public static final String CAT_GREP_FILE_NAME = WORK_DIR_NAME + "catGrepTest.txt";

    public static String callCommandString1 = "echo 'hello'";
    public static String echoExpect = "hello" + STRING_NEWLINE;
    public static String parsedEchoExpect = "echo'hello'";
    public static String callCommandString2 = "sort " + SORT_FILE_NAME;
    public static String sortString = String.join(STRING_NEWLINE, new String[]{"abc", "aac", "cde", "bcd"});
    public static String sortExpect = String.join(STRING_NEWLINE, new String[]{"aac", "abc", "bcd", "cde"})+STRING_NEWLINE;
    public static String parsedSortExpect = "sort"+SORT_FILE_NAME;
    public static String callCommandString3 = "pwd";
    public static String pwdExpect = Environment.currentDirectory+STRING_NEWLINE;
    public static String pipeCommandString1 = "cat "+CAT_GREP_FILE_NAME+" | grep \"sort\"";
    public static String pipeCommandString2 = "cat "+CAT_GREP_FILE_NAME+" | grep \"so\"";
    public static String parsedCatGrepExpect1 = "cat"+CAT_GREP_FILE_NAME+"grep\"sort\"";
    public static String catGrepString = String.join(STRING_NEWLINE,new String[]{"sort test1","sor test2","sort test3","so test4"});
    public static String catGrepExpect1 = String.join(STRING_NEWLINE,new String[]{"sort test1","sort test3"})+STRING_NEWLINE;
    public static String catGrepExpect2 = String.join(STRING_NEWLINE,new String[]{"sort test1","sor test2","sort test3","so test4"})+STRING_NEWLINE;

    @BeforeAll
    static void setup() throws FileNotFoundException, IOException {
        File sortFile = new File(SORT_FILE_NAME);
        FileOutputStream fileOutputStream = new FileOutputStream(sortFile);
        fileOutputStream.write(sortString.getBytes());
        fileOutputStream.close();

        File catFile = new File(CAT_GREP_FILE_NAME);
        FileOutputStream fileOutputStream1 = new FileOutputStream(catFile);
        fileOutputStream1.write(catGrepString.getBytes());
        fileOutputStream1.close();
    }

    /**
     * Tests running of shell with input and output
     */
    void assertInputOutput(String commands, String expectedLines) throws AbstractApplicationException, ShellException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = stringsToInputStream(commands);
        Command command = CommandBuilder.parseCommand(commands, new ApplicationRunner());
        command.evaluate(inputStream,outputStream);
        String actual = outputStream.toString();
        String expected = String.join(STRING_NEWLINE, expectedLines);
        assertEquals(expected, actual);
    }

    /**
     * Tests running the CommandBuilder with input and output
     */
    void assertCallCommand(String cmdType, String commandString, String expect) throws ShellException{
        Command finalCommand;
        finalCommand = CommandBuilder.parseCommand(commandString,new ApplicationRunner());
        if ("callCommand".equals(cmdType)){
            finalCommand = (CallCommand)finalCommand;
            List<String> stringList = ((CallCommand) finalCommand).getArgsList();
            String actual = "";
            for (String str : stringList){
                actual = actual+str;
            }
            assertEquals(expect,actual);
        }
        else if ("pipeCommand".equals(cmdType)){
            finalCommand = (PipeCommand)finalCommand;
            List<CallCommand> commandsList = ((PipeCommand) finalCommand).getCallCommands();
            String actual = "";
            for (CallCommand callCommand: commandsList){
                for (String str : callCommand.getArgsList()){
                    actual = actual + str;
                }
            }
            assertEquals(expect,actual);
        }
    }

    /**
     * Test for callCommand in echo
     */
    @Test
    void callCommandTest1()throws Exception{
        assertInputOutput(callCommandString1,echoExpect);
    }

    /**
     * Test for callCommand in sort
     */
    @Test
    void callCommandTest2()throws Exception{
        assertInputOutput(callCommandString2,sortExpect);
    }

    /**
     * Test for callCommand in pwd
     */
    @Test
    void callCommandTest3() throws Exception{
        assertInputOutput(callCommandString3,pwdExpect);
    }

    /**
     * Test for pipeCommand in cat|grep
     */
    @Test
    void pipeCommandTest1() throws Exception{
        assertInputOutput(pipeCommandString1,catGrepExpect1);
    }

    /**
     * Test for pipeCommand in cat|grep second version
     */
    @Test
    void pipeCommandTest2() throws Exception{
        assertInputOutput(pipeCommandString2,catGrepExpect2);
    }

    /**
     * Test for callCommand1 in CommandBuilder
     */
    @Test
    void callCommandBuilderTest1() throws Exception{
        assertCallCommand("callCommand",callCommandString1,parsedEchoExpect);
    }

    /**
     * Test for callCommand2 in CommandBuilder
     */
    @Test
    void callCommandBuilderTest2() throws Exception{
        assertCallCommand("callCommand", callCommandString2, parsedSortExpect);
    }


}
