package sg.edu.nus.comp.cs4218.impl.cmd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.app.CatApplication;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;

import java.io.*;

import static sg.edu.nus.comp.cs4218.impl.app.MkdirApplicationTest.deleteDirectory;

class GlobbingTest {
    /**
     * some arguments.
     * need to put into TestUtil
     */
    Command command;
    ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
    String catAllFile = "cat *";
    String catAllTxt = "cat *.txt";
    String catFolderAllTxt = "cat folder/*.txt";
    CatApplication catApplication;
    String pathToTestDir = System.getProperty("user.dir") + File.separator + "test" + File.separator +
            "sg" + File.separator + "edu" + File.separator + "nus" + File.separator + "comp" + File.separator +
            "cs4218m1" + File.separator + "globTestDir" + File.separator;
    String nameA = pathToTestDir + "a.txt";
    String testStringA = "test a\n";
    String nameB = pathToTestDir + "b.txt";
    String testStringB = "test b\n";
    String nameTemp = pathToTestDir + "temp";
    String pathParent = pathToTestDir + "folder" + File.separator;
    String nameAA = pathParent + "aa.txt";
    String testStringAA = "test aa\n";
    String nameBB = pathParent + "bb.txt";
    String testStringBB = "test bb\n";
    String[] initPackages = new String[]{pathToTestDir,pathParent};

    void writeFile(String filename, String fileString) throws IOException {
        File file = new File(filename);
        file.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(fileString);
        out.flush();
        out.close();
    }

    /**
     * use cat(EF1) to test globbing
     * file system
     * folder/aa.txt(test aa) bb.txt(test bb) temp(empty)
     * a.txt(test a) b.txt(test b)
     */
    @BeforeEach
    void setup() throws IOException {
        catApplication = new CatApplication();
        deleteDirectory(null, new File(pathToTestDir).listFiles());
        for (String filename:initPackages) {
            File file = new File(filename);
            file.mkdirs();
        }
        File file = new File(nameTemp);
        file.createNewFile();
        writeFile(nameA,testStringA);
        writeFile(nameB,testStringB);
        writeFile(nameAA,testStringAA);
        writeFile(nameBB,testStringBB);
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteDirectory(null, new File(pathToTestDir).listFiles());
        File f = new File(pathToTestDir + File.separator + "EmptyFileForGitTracking.txt");
        f.createNewFile();
    }

    /**
     * TODO
     * test if cat *.txt show the correct result
     * @throws AbstractApplicationException
     * @throws ShellException
     * @throws IOException
     */
    @Test()
    void evaluate_AllTxtFile() throws AbstractApplicationException, ShellException, IOException {
        command = CommandBuilder.parseCommand(catAllTxt,new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        String actual = baoStream.toString();
        assert(actual.contains(testStringA) && actual.contains(testStringB));
        baoStream.flush();
    }

    /**
     * TODO
     * test if a empty file effects the result
     * @throws AbstractApplicationException
     * @throws ShellException
     * @throws IOException
     */
    @Test()
    void evaluate_AllFile() throws AbstractApplicationException, ShellException, IOException {
        command = CommandBuilder.parseCommand(catAllFile,new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        String actual = baoStream.toString();
        assert(actual.contains(testStringA) && actual.contains(testStringB));
        baoStream.flush();
    }

    /**
     * TODO
     * test if the folder effects the result
     * @throws AbstractApplicationException
     * @throws ShellException
     * @throws IOException
     */
    @Test()
    void evaluate_AllFolderFile() throws AbstractApplicationException, ShellException, IOException {
        command = CommandBuilder.parseCommand(catFolderAllTxt,new ApplicationRunner());
        command.evaluate(System.in,baoStream);
        String actual = baoStream.toString();
        assert(actual.contains(testStringAA) && actual.contains(testStringBB));
        baoStream.flush();
    }

}