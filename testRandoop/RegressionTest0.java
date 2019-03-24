import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.app.MkdirApplicationTest.deleteDirectory;

@SuppressWarnings("PMD")
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test013");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        try {
            java.lang.String str11 = grepApplication0.grepFromFiles("This is a directory", (java.lang.Boolean) true, (java.lang.Boolean) false, strArray7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray7);
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test015");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        try {
            findApplication0.run(strArray8, inputStream12, outputStream13);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: output stream is null");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray8);
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test017");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        try {
            sortApplication0.run(strArray8, inputStream12, outputStream13);
            fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 0");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
        }
        assertNotNull(strArray8);
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test018");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        try {
            sedApplication0.run(strArray8, inputStream12, outputStream13);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray8);
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test023");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        try {
            java.lang.String str11 = grepApplication0.grepFromFiles("fail_sed_write", (java.lang.Boolean) false, (java.lang.Boolean) false, strArray7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray7);
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test024");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication4 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication6 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication6.run(strArray9, inputStream10, outputStream11);
        java.lang.String str13 = findApplication4.findFolderContent("", strArray9);
        try {
            java.lang.String str14 = grepApplication0.grepFromFiles("File is null.", (java.lang.Boolean) true, (java.lang.Boolean) false, strArray9);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray9);
        assertTrue(str13.equals(""));
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test025");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        java.lang.String str10 = findApplication1.findFolderContent("", strArray6);
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        try {
            findApplication0.run(strArray6, inputStream11, outputStream12);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: output stream is null");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str10.equals(""));
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test027");
        sg.edu.nus.comp.cs4218.impl.app.DateApplication dateApplication0 = new sg.edu.nus.comp.cs4218.impl.app.DateApplication();
        java.util.HashMap<java.lang.String, java.lang.String> strMap1 = null;
        try {
            dateApplication0.initMap(strMap1);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test028");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Could not read stream" };
        try {
            java.lang.String str7 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray6);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray6);
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test030");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sortApplication0.sortFromStdin((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test032");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        mkdirApplication2.run(strArray5, inputStream6, outputStream7);
        java.lang.String str9 = findApplication0.findFolderContent("", strArray5);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication10 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray13 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream14 = null;
        java.io.OutputStream outputStream15 = null;
        mkdirApplication10.run(strArray13, inputStream14, outputStream15);
        java.io.InputStream inputStream17 = null;
        java.io.OutputStream outputStream18 = null;
        try {
            findApplication0.run(strArray13, inputStream17, outputStream18);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: output stream is null");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray5);
        assertTrue(str9.equals(""));
        assertNotNull(strArray13);
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test034");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        try {
            grepApplication0.run(strArray8, inputStream12, outputStream13);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: output stream is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray8);
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test035");
        sg.edu.nus.comp.cs4218.impl.app.DateApplication dateApplication0 = new sg.edu.nus.comp.cs4218.impl.app.DateApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        try {
            dateApplication0.run(strArray8, inputStream12, outputStream13);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.DateException; message: date: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.DateException e) {
        }
        assertNotNull(strArray8);
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test040");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        try {
            java.lang.String str6 = catApplication0.catFiles(strArray4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(strArray4);
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test044");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        try {
            findApplication0.run(strArray4, inputStream6, outputStream7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: output stream is null");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray4);
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test045");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        java.lang.String str10 = findApplication1.findFolderContent("", strArray6);
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        try {
            sortApplication0.run(strArray6, inputStream11, outputStream12);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: ");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str10.equals(""));
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test048");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        mkdirApplication2.run(strArray5, inputStream6, outputStream7);
        java.lang.String str9 = findApplication0.findFolderContent("", strArray5);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication11 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray18 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        mkdirApplication11.run(strArray18, inputStream19, outputStream20);
        try {
            java.lang.String str22 = findApplication0.findFolderContent("File doesn't exist.", strArray18);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: nonExistentFolder: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray5);
        assertTrue(str9.equals(""));
        assertNotNull(strArray18);
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test049");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        try {
            sortApplication0.run(strArray4, inputStream6, outputStream7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: ");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray4);
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test050");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication4 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication7 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication9 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray12 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream13 = null;
        java.io.OutputStream outputStream14 = null;
        mkdirApplication9.run(strArray12, inputStream13, outputStream14);
        java.lang.String str16 = findApplication7.findFolderContent("", strArray12);
        java.lang.String str17 = lsApplication4.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray12);
        try {
            java.lang.String str18 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray12);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray12);
        assertTrue(str16.equals(""));
        assertTrue(str17.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test052");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        try {
            catApplication0.run(strArray4, inputStream6, outputStream7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(strArray4);
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test053");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("Terminate process.", "hi!", (int) (short) 100, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test057");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray11 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        mkdirApplication4.run(strArray11, inputStream12, outputStream13);
        try {
            java.lang.String str15 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray11);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray11);
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test058");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication3 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication5.run(strArray8, inputStream9, outputStream10);
        java.lang.String str12 = findApplication3.findFolderContent("", strArray8);
        java.lang.String str13 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication14 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication14.createFolder(strArray17);
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        try {
            lsApplication0.run(strArray17, inputStream19, outputStream20);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        assertNotNull(strArray8);
        assertTrue(str12.equals(""));
        assertTrue(str13.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray17);
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test059");
        boolean boolean1 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.isSpecialSymbol("Could not read stream");
        assertTrue(boolean1 == true);
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test060");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication1 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication4 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication6 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication6.run(strArray9, inputStream10, outputStream11);
        java.lang.String str13 = findApplication4.findFolderContent("", strArray9);
        java.lang.String str14 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray9);
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        try {
            catApplication0.run(strArray9, inputStream15, outputStream16);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(strArray9);
        assertTrue(str13.equals(""));
        assertTrue(str14.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test061");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        try {
            java.lang.String str11 = grepApplication0.grepFromFiles("fail_echo_write", (java.lang.Boolean) false, (java.lang.Boolean) false, strArray7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray7);
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test063");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("Could not read file", "", (int) '4', inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test064");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = wcApplication0.countFromStdin((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test065");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication1 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        java.lang.String str11 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray7);
        try {
            java.lang.String str12 = catApplication0.catFiles(strArray7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(strArray7);
        assertTrue(str11.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test067");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication12 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication15 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray20 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        mkdirApplication17.run(strArray20, inputStream21, outputStream22);
        java.lang.String str24 = findApplication15.findFolderContent("", strArray20);
        java.lang.String str25 = lsApplication12.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        java.io.InputStream inputStream26 = null;
        java.io.OutputStream outputStream27 = null;
        mkdirApplication1.run(strArray20, inputStream26, outputStream27);
        try {
            java.lang.String str29 = catApplication0.catFiles(strArray20);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(strArray8);
        assertNotNull(strArray20);
        assertTrue(str24.equals(""));
        assertTrue(str25.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test074");
        sg.edu.nus.comp.cs4218.impl.app.DateApplication dateApplication0 = new sg.edu.nus.comp.cs4218.impl.app.DateApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication1 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication4 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication6 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication6.run(strArray9, inputStream10, outputStream11);
        java.lang.String str13 = findApplication4.findFolderContent("", strArray9);
        java.lang.String str14 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray9);
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        try {
            dateApplication0.run(strArray9, inputStream15, outputStream16);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.DateException; message: date: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.DateException e) {
        }
        assertNotNull(strArray9);
        assertTrue(str13.equals(""));
        assertTrue(str14.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test075");
        sg.edu.nus.comp.cs4218.impl.app.ExitApplication exitApplication0 = new sg.edu.nus.comp.cs4218.impl.app.ExitApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication1 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        java.lang.String str11 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray7);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        try {
            exitApplication0.run(strArray7, inputStream12, outputStream13);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.ExitException; message: exit: Terminate process.");
        } catch (sg.edu.nus.comp.cs4218.exception.ExitException e) {
        }
        assertNotNull(strArray7);
        assertTrue(str11.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test077");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication13 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication16 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication18 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray21 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        mkdirApplication18.run(strArray21, inputStream22, outputStream23);
        java.lang.String str25 = findApplication16.findFolderContent("", strArray21);
        java.lang.String str26 = lsApplication13.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray21);
        java.io.InputStream inputStream27 = null;
        java.io.OutputStream outputStream28 = null;
        mkdirApplication2.run(strArray21, inputStream27, outputStream28);
        java.lang.String str30 = echoApplication1.constructResult(strArray21);
        java.io.InputStream inputStream31 = null;
        java.io.OutputStream outputStream32 = null;
        try {
            wcApplication0.run(strArray21, inputStream31, outputStream32);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
        assertNotNull(strArray9);
        assertNotNull(strArray21);
        assertTrue(str25.equals(""));
        assertTrue(str26.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str30.equals("null output stream provided fail_echo_write"));
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test079");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication1 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication4 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication6 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication6.run(strArray9, inputStream10, outputStream11);
        java.lang.String str13 = findApplication4.findFolderContent("", strArray9);
        java.lang.String str14 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray9);
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        try {
            cdApplication0.run(strArray9, inputStream15, outputStream16);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: cd only takes one argument");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
        assertNotNull(strArray9);
        assertTrue(str13.equals(""));
        assertTrue(str14.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test080");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInFile("hi!", "null output stream provided:\r\n\r\n\r\nfail_echo_write:", 100, "File is null.");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: File doesn't exist.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test081");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication13 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication16 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication18 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray21 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        mkdirApplication18.run(strArray21, inputStream22, outputStream23);
        java.lang.String str25 = findApplication16.findFolderContent("", strArray21);
        java.lang.String str26 = lsApplication13.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray21);
        java.io.InputStream inputStream27 = null;
        java.io.OutputStream outputStream28 = null;
        mkdirApplication2.run(strArray21, inputStream27, outputStream28);
        java.lang.String str30 = echoApplication1.constructResult(strArray21);
        java.io.InputStream inputStream31 = null;
        java.io.OutputStream outputStream32 = null;
        try {
            grepApplication0.run(strArray21, inputStream31, outputStream32);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: output stream is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray9);
        assertNotNull(strArray21);
        assertTrue(str25.equals(""));
        assertTrue(str26.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str30.equals("null output stream provided fail_echo_write"));
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test085");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication3 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication5.run(strArray8, inputStream9, outputStream10);
        java.lang.String str12 = findApplication3.findFolderContent("", strArray8);
        java.lang.String str13 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication14 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication16 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray19 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication16.run(strArray19, inputStream20, outputStream21);
        java.lang.String str23 = findApplication14.findFolderContent("", strArray19);
        java.io.InputStream inputStream24 = null;
        java.io.OutputStream outputStream25 = null;
        try {
            lsApplication0.run(strArray19, inputStream24, outputStream25);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        assertNotNull(strArray8);
        assertTrue(str12.equals(""));
        assertTrue(str13.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray19);
        assertTrue(str23.equals(""));
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test086");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication4 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication6 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication6.createFolder(strArray9);
        java.lang.String str11 = findApplication4.findFolderContent("fail_sed_write", strArray9);
        try {
            java.lang.String str12 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray9);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
        assertNotNull(strArray9);
        assertTrue(str11.equals(""));
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test087");
        sg.edu.nus.comp.cs4218.impl.app.ExitApplication exitApplication0 = new sg.edu.nus.comp.cs4218.impl.app.ExitApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication13 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication16 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication18 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray21 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        mkdirApplication18.run(strArray21, inputStream22, outputStream23);
        java.lang.String str25 = findApplication16.findFolderContent("", strArray21);
        java.lang.String str26 = lsApplication13.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray21);
        java.io.InputStream inputStream27 = null;
        java.io.OutputStream outputStream28 = null;
        mkdirApplication2.run(strArray21, inputStream27, outputStream28);
        java.lang.String str30 = echoApplication1.constructResult(strArray21);
        java.io.InputStream inputStream31 = null;
        java.io.OutputStream outputStream32 = null;
        try {
            exitApplication0.run(strArray21, inputStream31, outputStream32);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.ExitException; message: exit: Terminate process.");
        } catch (sg.edu.nus.comp.cs4218.exception.ExitException e) {
        }
        assertNotNull(strArray9);
        assertNotNull(strArray21);
        assertTrue(str25.equals(""));
        assertTrue(str26.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str30.equals("null output stream provided fail_echo_write"));
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test089");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("Null Pointer Exception", (java.lang.Boolean) false, (java.lang.Boolean) false, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test090");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        try {
            java.lang.String str11 = grepApplication0.grepFromFiles("null arguments", (java.lang.Boolean) true, (java.lang.Boolean) true, strArray7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray7);
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test091");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Could not read stream", "Exception Caught", "fail_echo_write", "null output stream provided fail_echo_write", "null arguments", "fail_echo_empty_params" };
        try {
            java.lang.String str10 = lsApplication0.listFolderContent((java.lang.Boolean) true, (java.lang.Boolean) true, strArray9);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.LsException; message: ls: File does not exist, make sure the path is correct!");
        } catch (sg.edu.nus.comp.cs4218.exception.LsException e) {
        }
        assertNotNull(strArray9);
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test092");
        sg.edu.nus.comp.cs4218.impl.app.ExitApplication exitApplication0 = new sg.edu.nus.comp.cs4218.impl.app.ExitApplication();
        try {
            exitApplication0.terminateExecution();
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.ExitException; message: exit: Terminate process.");
        } catch (sg.edu.nus.comp.cs4218.exception.ExitException e) {
        }
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test093");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Null Pointer Exception", "This is a directory", "Terminate process.", "Could not write to output stream" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        try {
            grepApplication0.run(strArray5, inputStream6, outputStream7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: output stream is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray5);
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test094");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("Terminate process.", (java.lang.Boolean) true, (java.lang.Boolean) false, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test095");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("fail_echo", (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test096");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        try {
            cdApplication0.changeToDirectory("null output stream provided:\r\n\r\n\r\nfail_echo_write:");
            fail("Expected exception of type java.nio.file.InvalidPathException; message: Illegal char <:> at index 27: null output stream provided:\r\n\r\n\r\nfail_echo_write:\\");
        } catch (java.nio.file.InvalidPathException e) {
        }
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test097");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        java.lang.Class<?> wildcardClass12 = mkdirApplication1.getClass();
        java.lang.String[] strArray17 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication1.run(strArray17, inputStream18, outputStream19);
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        try {
            lsApplication0.run(strArray17, inputStream21, outputStream22);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        assertNotNull(strArray8);
        assertNotNull(wildcardClass12);
        assertNotNull(strArray17);
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test098");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.lang.Class<?> wildcardClass1 = catApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication2 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        java.lang.String str11 = findApplication2.findFolderContent("", strArray7);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        try {
            catApplication0.run(strArray7, inputStream12, outputStream13);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray7);
        assertTrue(str11.equals(""));
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test099");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication3 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication5.run(strArray8, inputStream9, outputStream10);
        java.lang.String str12 = findApplication3.findFolderContent("", strArray8);
        java.lang.String str13 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication14 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication16 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray19 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication16.run(strArray19, inputStream20, outputStream21);
        java.lang.String str23 = findApplication14.findFolderContent("", strArray19);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication25 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray32 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream33 = null;
        java.io.OutputStream outputStream34 = null;
        mkdirApplication25.run(strArray32, inputStream33, outputStream34);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication36 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication39 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication41 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray44 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream45 = null;
        java.io.OutputStream outputStream46 = null;
        mkdirApplication41.run(strArray44, inputStream45, outputStream46);
        java.lang.String str48 = findApplication39.findFolderContent("", strArray44);
        java.lang.String str49 = lsApplication36.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray44);
        java.io.InputStream inputStream50 = null;
        java.io.OutputStream outputStream51 = null;
        mkdirApplication25.run(strArray44, inputStream50, outputStream51);
        java.lang.String str53 = findApplication14.findFolderContent("Could not read stream", strArray44);
        java.io.InputStream inputStream54 = null;
        java.io.OutputStream outputStream55 = null;
        try {
            lsApplication0.run(strArray44, inputStream54, outputStream55);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        assertNotNull(strArray8);
        assertTrue(str12.equals(""));
        assertTrue(str13.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray19);
        assertTrue(str23.equals(""));
        assertNotNull(strArray32);
        assertNotNull(strArray44);
        assertTrue(str48.equals(""));
        assertTrue(str49.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str53.equals(""));
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test100");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        java.lang.String[] strArray1 = null;
        java.io.InputStream inputStream2 = null;
        java.io.OutputStream outputStream3 = null;
        try {
            echoApplication0.run(strArray1, inputStream2, outputStream3);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: Empty arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
        }
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test101");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        java.lang.String str10 = findApplication1.findFolderContent("", strArray6);
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        try {
            sedApplication0.run(strArray6, inputStream11, outputStream12);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str10.equals(""));
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test102");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        try {
            sedApplication0.run(strArray4, inputStream6, outputStream7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray4);
    }

    @Test
    public void test103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test103");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("Null Pointer Exception", "Null Pointer Exception", (int) '4', inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test106");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication4.createFolder(strArray7);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication9 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray12 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream13 = null;
        java.io.OutputStream outputStream14 = null;
        mkdirApplication9.run(strArray12, inputStream13, outputStream14);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication16 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication18 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray21 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        mkdirApplication18.run(strArray21, inputStream22, outputStream23);
        java.lang.String str25 = findApplication16.findFolderContent("", strArray21);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication27 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray34 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream35 = null;
        java.io.OutputStream outputStream36 = null;
        mkdirApplication27.run(strArray34, inputStream35, outputStream36);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication38 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication41 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication43 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray46 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream47 = null;
        java.io.OutputStream outputStream48 = null;
        mkdirApplication43.run(strArray46, inputStream47, outputStream48);
        java.lang.String str50 = findApplication41.findFolderContent("", strArray46);
        java.lang.String str51 = lsApplication38.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray46);
        java.io.InputStream inputStream52 = null;
        java.io.OutputStream outputStream53 = null;
        mkdirApplication27.run(strArray46, inputStream52, outputStream53);
        java.lang.String str55 = findApplication16.findFolderContent("Could not read stream", strArray46);
        java.io.InputStream inputStream56 = null;
        java.io.OutputStream outputStream57 = null;
        mkdirApplication9.run(strArray46, inputStream56, outputStream57);
        java.io.InputStream inputStream59 = null;
        java.io.OutputStream outputStream60 = null;
        mkdirApplication4.run(strArray46, inputStream59, outputStream60);
        try {
            java.lang.String str62 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray46);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray7);
        assertNotNull(strArray12);
        assertNotNull(strArray21);
        assertTrue(str25.equals(""));
        assertNotNull(strArray34);
        assertNotNull(strArray46);
        assertTrue(str50.equals(""));
        assertTrue(str51.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str55.equals(""));
    }

    @Test
    public void test107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test107");
        boolean boolean1 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.isSpecialSymbol("null arguments");
        assertTrue(boolean1 == true);
    }

    @Test
    public void test108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test108");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        try {
            java.lang.String str13 = findApplication0.findFolderContent("", strArray9);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: nonExistentFolder: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray9);
    }

    @Test
    public void test109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test109");
        sg.edu.nus.comp.cs4218.impl.app.PwdApplication pwdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PwdApplication();
        java.lang.String str1 = pwdApplication0.getAbsolutePath();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        java.io.InputStream inputStream13 = null;
        java.io.OutputStream outputStream14 = null;
        try {
            pwdApplication0.run(strArray9, inputStream13, outputStream14);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PwdException; message: pwd: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.PwdException e) {
        }
        assertNotNull(strArray9);
    }

    @Test
    public void test110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test110");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        java.lang.String str10 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray6);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication11 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication12 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray19 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication12.run(strArray19, inputStream20, outputStream21);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication23 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication26 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication28 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray31 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication28.run(strArray31, inputStream32, outputStream33);
        java.lang.String str35 = findApplication26.findFolderContent("", strArray31);
        java.lang.String str36 = lsApplication23.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray31);
        java.io.InputStream inputStream37 = null;
        java.io.OutputStream outputStream38 = null;
        mkdirApplication12.run(strArray31, inputStream37, outputStream38);
        java.lang.String str40 = echoApplication11.constructResult(strArray31);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication41 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray48 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream49 = null;
        java.io.OutputStream outputStream50 = null;
        mkdirApplication41.run(strArray48, inputStream49, outputStream50);
        java.io.InputStream inputStream52 = null;
        java.io.OutputStream outputStream53 = null;
        echoApplication11.run(strArray48, inputStream52, outputStream53);
        java.io.InputStream inputStream55 = null;
        java.io.OutputStream outputStream56 = null;
        try {
            lsApplication0.run(strArray48, inputStream55, outputStream56);
            fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 0");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str10.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray19);
        assertNotNull(strArray31);
        assertTrue(str35.equals(""));
        assertTrue(str36.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str40.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray48);
    }

    @Test
    public void test111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test111");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication3.createFolder(strArray6);
        java.lang.String str8 = findApplication1.findFolderContent("fail_sed_write", strArray6);
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        try {
            wcApplication0.run(strArray6, inputStream9, outputStream10);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str8.equals(""));
    }

    @Test
    public void test114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test114");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication12 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication15 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray20 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        mkdirApplication17.run(strArray20, inputStream21, outputStream22);
        java.lang.String str24 = findApplication15.findFolderContent("", strArray20);
        java.lang.String str25 = lsApplication12.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        java.io.InputStream inputStream26 = null;
        java.io.OutputStream outputStream27 = null;
        mkdirApplication1.run(strArray20, inputStream26, outputStream27);
        java.io.InputStream inputStream29 = null;
        java.io.OutputStream outputStream30 = null;
        try {
            sedApplication0.run(strArray20, inputStream29, outputStream30);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray8);
        assertNotNull(strArray20);
        assertTrue(str24.equals(""));
        assertTrue(str25.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test115");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("null output stream provided fail_echo_write", (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test116");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication12 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication15 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray20 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        mkdirApplication17.run(strArray20, inputStream21, outputStream22);
        java.lang.String str24 = findApplication15.findFolderContent("", strArray20);
        java.lang.String str25 = lsApplication12.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        java.io.InputStream inputStream26 = null;
        java.io.OutputStream outputStream27 = null;
        mkdirApplication1.run(strArray20, inputStream26, outputStream27);
        java.lang.String str29 = echoApplication0.constructResult(strArray20);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication30 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray37 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream38 = null;
        java.io.OutputStream outputStream39 = null;
        mkdirApplication30.run(strArray37, inputStream38, outputStream39);
        java.io.InputStream inputStream41 = null;
        java.io.OutputStream outputStream42 = null;
        echoApplication0.run(strArray37, inputStream41, outputStream42);
        java.lang.String[] strArray44 = null;
        try {
            java.lang.String str45 = echoApplication0.constructResult(strArray44);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: fail_echo_empty_params");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
        }
        assertNotNull(strArray8);
        assertNotNull(strArray20);
        assertTrue(str24.equals(""));
        assertTrue(str25.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str29.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray37);
    }

    @Test
    public void test117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test117");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication11 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication12 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray19 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication12.run(strArray19, inputStream20, outputStream21);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication23 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication26 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication28 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray31 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication28.run(strArray31, inputStream32, outputStream33);
        java.lang.String str35 = findApplication26.findFolderContent("", strArray31);
        java.lang.String str36 = lsApplication23.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray31);
        java.io.InputStream inputStream37 = null;
        java.io.OutputStream outputStream38 = null;
        mkdirApplication12.run(strArray31, inputStream37, outputStream38);
        java.lang.String str40 = echoApplication11.constructResult(strArray31);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication41 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray48 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream49 = null;
        java.io.OutputStream outputStream50 = null;
        mkdirApplication41.run(strArray48, inputStream49, outputStream50);
        java.io.InputStream inputStream52 = null;
        java.io.OutputStream outputStream53 = null;
        echoApplication11.run(strArray48, inputStream52, outputStream53);
        mkdirApplication4.createFolder(strArray48);
        try {
            java.lang.String str56 = grepApplication0.grepFromFiles("Could not write to output stream", (java.lang.Boolean) false, (java.lang.Boolean) true, strArray48);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray7);
        assertNotNull(strArray19);
        assertNotNull(strArray31);
        assertTrue(str35.equals(""));
        assertTrue(str36.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str40.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray48);
    }

    @Test
    public void test118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test118");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.String[] strArray1 = null;
        java.io.InputStream inputStream2 = null;
        java.io.OutputStream outputStream3 = null;
        try {
            sortApplication0.run(strArray1, inputStream2, outputStream3);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test122");
        sg.edu.nus.comp.cs4218.impl.app.ExitApplication exitApplication0 = new sg.edu.nus.comp.cs4218.impl.app.ExitApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        mkdirApplication1.run(strArray4, inputStream5, outputStream6);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication8 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication9 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray16 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream17 = null;
        java.io.OutputStream outputStream18 = null;
        mkdirApplication9.run(strArray16, inputStream17, outputStream18);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication20 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication23 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication25 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray28 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream29 = null;
        java.io.OutputStream outputStream30 = null;
        mkdirApplication25.run(strArray28, inputStream29, outputStream30);
        java.lang.String str32 = findApplication23.findFolderContent("", strArray28);
        java.lang.String str33 = lsApplication20.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray28);
        java.io.InputStream inputStream34 = null;
        java.io.OutputStream outputStream35 = null;
        mkdirApplication9.run(strArray28, inputStream34, outputStream35);
        java.lang.String str37 = echoApplication8.constructResult(strArray28);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication38 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray45 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream46 = null;
        java.io.OutputStream outputStream47 = null;
        mkdirApplication38.run(strArray45, inputStream46, outputStream47);
        java.io.InputStream inputStream49 = null;
        java.io.OutputStream outputStream50 = null;
        echoApplication8.run(strArray45, inputStream49, outputStream50);
        mkdirApplication1.createFolder(strArray45);
        java.io.InputStream inputStream53 = null;
        java.io.OutputStream outputStream54 = null;
        try {
            exitApplication0.run(strArray45, inputStream53, outputStream54);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.ExitException; message: exit: Terminate process.");
        } catch (sg.edu.nus.comp.cs4218.exception.ExitException e) {
        }
        assertNotNull(strArray4);
        assertNotNull(strArray16);
        assertNotNull(strArray28);
        assertTrue(str32.equals(""));
        assertTrue(str33.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str37.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray45);
    }

    @Test
    public void test124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test124");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication6 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication6.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication13 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication15 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray18 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        mkdirApplication15.run(strArray18, inputStream19, outputStream20);
        java.lang.String str22 = findApplication13.findFolderContent("", strArray18);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication24 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray31 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication24.run(strArray31, inputStream32, outputStream33);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication35 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication38 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication40 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray43 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream44 = null;
        java.io.OutputStream outputStream45 = null;
        mkdirApplication40.run(strArray43, inputStream44, outputStream45);
        java.lang.String str47 = findApplication38.findFolderContent("", strArray43);
        java.lang.String str48 = lsApplication35.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray43);
        java.io.InputStream inputStream49 = null;
        java.io.OutputStream outputStream50 = null;
        mkdirApplication24.run(strArray43, inputStream49, outputStream50);
        java.lang.String str52 = findApplication13.findFolderContent("Could not read stream", strArray43);
        java.io.InputStream inputStream53 = null;
        java.io.OutputStream outputStream54 = null;
        mkdirApplication6.run(strArray43, inputStream53, outputStream54);
        java.io.InputStream inputStream56 = null;
        java.io.OutputStream outputStream57 = null;
        mkdirApplication1.run(strArray43, inputStream56, outputStream57);
        java.io.InputStream inputStream59 = null;
        java.io.OutputStream outputStream60 = null;
        try {
            wcApplication0.run(strArray43, inputStream59, outputStream60);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
        assertNotNull(strArray4);
        assertNotNull(strArray9);
        assertNotNull(strArray18);
        assertTrue(str22.equals(""));
        assertNotNull(strArray31);
        assertNotNull(strArray43);
        assertTrue(str47.equals(""));
        assertTrue(str48.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str52.equals(""));
    }

    @Test
    public void test125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test125");
        java.util.List list0 = null;
        try {
            sg.edu.nus.comp.cs4218.impl.app.SortApplication.sortList(list0, true, false);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test126");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = wcApplication0.countFromStdin((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
    }

    @Test
    public void test133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test133");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication6 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication6.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication13 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication15 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray18 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        mkdirApplication15.run(strArray18, inputStream19, outputStream20);
        java.lang.String str22 = findApplication13.findFolderContent("", strArray18);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication24 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray31 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication24.run(strArray31, inputStream32, outputStream33);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication35 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication38 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication40 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray43 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream44 = null;
        java.io.OutputStream outputStream45 = null;
        mkdirApplication40.run(strArray43, inputStream44, outputStream45);
        java.lang.String str47 = findApplication38.findFolderContent("", strArray43);
        java.lang.String str48 = lsApplication35.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray43);
        java.io.InputStream inputStream49 = null;
        java.io.OutputStream outputStream50 = null;
        mkdirApplication24.run(strArray43, inputStream49, outputStream50);
        java.lang.String str52 = findApplication13.findFolderContent("Could not read stream", strArray43);
        java.io.InputStream inputStream53 = null;
        java.io.OutputStream outputStream54 = null;
        mkdirApplication6.run(strArray43, inputStream53, outputStream54);
        java.io.InputStream inputStream56 = null;
        java.io.OutputStream outputStream57 = null;
        mkdirApplication1.run(strArray43, inputStream56, outputStream57);
        java.io.InputStream inputStream59 = null;
        java.io.OutputStream outputStream60 = null;
        try {
            sedApplication0.run(strArray43, inputStream59, outputStream60);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray4);
        assertNotNull(strArray9);
        assertNotNull(strArray18);
        assertTrue(str22.equals(""));
        assertNotNull(strArray31);
        assertNotNull(strArray43);
        assertTrue(str47.equals(""));
        assertTrue(str48.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str52.equals(""));
    }

    @Test
    public void test134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test134");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        mkdirApplication1.run(strArray4, inputStream5, outputStream6);
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        try {
            cdApplication0.run(strArray4, inputStream8, outputStream9);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: cd only takes one argument");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
        assertNotNull(strArray4);
    }

    @Test
    public void test135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test135");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("File doesn't exist.", "hi!", (-1), inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test142");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication3.createFolder(strArray6);
        java.lang.String str8 = findApplication1.findFolderContent("fail_sed_write", strArray6);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication10 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray13 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication10.createFolder(strArray13);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication15 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray18 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        mkdirApplication15.run(strArray18, inputStream19, outputStream20);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication22 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication24 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray27 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream28 = null;
        java.io.OutputStream outputStream29 = null;
        mkdirApplication24.run(strArray27, inputStream28, outputStream29);
        java.lang.String str31 = findApplication22.findFolderContent("", strArray27);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication33 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray40 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream41 = null;
        java.io.OutputStream outputStream42 = null;
        mkdirApplication33.run(strArray40, inputStream41, outputStream42);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication44 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication47 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication49 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray52 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream53 = null;
        java.io.OutputStream outputStream54 = null;
        mkdirApplication49.run(strArray52, inputStream53, outputStream54);
        java.lang.String str56 = findApplication47.findFolderContent("", strArray52);
        java.lang.String str57 = lsApplication44.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray52);
        java.io.InputStream inputStream58 = null;
        java.io.OutputStream outputStream59 = null;
        mkdirApplication33.run(strArray52, inputStream58, outputStream59);
        java.lang.String str61 = findApplication22.findFolderContent("Could not read stream", strArray52);
        java.io.InputStream inputStream62 = null;
        java.io.OutputStream outputStream63 = null;
        mkdirApplication15.run(strArray52, inputStream62, outputStream63);
        java.io.InputStream inputStream65 = null;
        java.io.OutputStream outputStream66 = null;
        mkdirApplication10.run(strArray52, inputStream65, outputStream66);
        java.lang.Class<?> wildcardClass68 = strArray52.getClass();
        java.lang.String str69 = findApplication1.findFolderContent("", strArray52);
        java.io.InputStream inputStream70 = null;
        java.io.OutputStream outputStream71 = null;
        try {
            grepApplication0.run(strArray52, inputStream70, outputStream71);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: output stream is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str8.equals(""));
        assertNotNull(strArray13);
        assertNotNull(strArray18);
        assertNotNull(strArray27);
        assertTrue(str31.equals(""));
        assertNotNull(strArray40);
        assertNotNull(strArray52);
        assertTrue(str56.equals(""));
        assertTrue(str57.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str61.equals(""));
        assertNotNull(wildcardClass68);
        assertTrue(str69.equals(""));
    }

    @Test
    public void test150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test150");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        try {
            wcApplication0.run(strArray4, inputStream6, outputStream7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
        assertNotNull(strArray4);
    }

    @Test
    public void test161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test161");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication12 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication15 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray20 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        mkdirApplication17.run(strArray20, inputStream21, outputStream22);
        java.lang.String str24 = findApplication15.findFolderContent("", strArray20);
        java.lang.String str25 = lsApplication12.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        java.io.InputStream inputStream26 = null;
        java.io.OutputStream outputStream27 = null;
        mkdirApplication1.run(strArray20, inputStream26, outputStream27);
        java.io.InputStream inputStream29 = null;
        java.io.OutputStream outputStream30 = null;
        try {
            sortApplication0.run(strArray20, inputStream29, outputStream30);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: ");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray8);
        assertNotNull(strArray20);
        assertTrue(str24.equals(""));
        assertTrue(str25.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }


    @Test
    public void test164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test164");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("Could not read stream", (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test167");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication3.createFolder(strArray6);
        java.lang.String str8 = findApplication1.findFolderContent("fail_sed_write", strArray6);
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        try {
            cdApplication0.run(strArray6, inputStream9, outputStream10);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: cd only takes one argument");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str8.equals(""));
    }

    @Test
    public void test168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test168");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.lang.Class<?> wildcardClass1 = catApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        java.lang.Class<?> wildcardClass13 = mkdirApplication2.getClass();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication14 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication15 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray22 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream23 = null;
        java.io.OutputStream outputStream24 = null;
        mkdirApplication15.run(strArray22, inputStream23, outputStream24);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication26 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication29 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication31 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray34 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream35 = null;
        java.io.OutputStream outputStream36 = null;
        mkdirApplication31.run(strArray34, inputStream35, outputStream36);
        java.lang.String str38 = findApplication29.findFolderContent("", strArray34);
        java.lang.String str39 = lsApplication26.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray34);
        java.io.InputStream inputStream40 = null;
        java.io.OutputStream outputStream41 = null;
        mkdirApplication15.run(strArray34, inputStream40, outputStream41);
        java.lang.String str43 = echoApplication14.constructResult(strArray34);
        java.io.InputStream inputStream44 = null;
        java.io.OutputStream outputStream45 = null;
        mkdirApplication2.run(strArray34, inputStream44, outputStream45);
        java.io.InputStream inputStream47 = null;
        java.io.OutputStream outputStream48 = null;
        try {
            catApplication0.run(strArray34, inputStream47, outputStream48);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray9);
        assertNotNull(wildcardClass13);
        assertNotNull(strArray22);
        assertNotNull(strArray34);
        assertTrue(str38.equals(""));
        assertTrue(str39.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str43.equals("null output stream provided fail_echo_write"));
    }

    @Test
    public void test169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test169");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        try {
            grepApplication0.run(strArray4, inputStream6, outputStream7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: output stream is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray4);
    }

    @Test
    public void test171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test171");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.lang.String[] strArray1 = null;
        java.io.InputStream inputStream2 = null;
        java.io.OutputStream outputStream3 = null;
        try {
            sedApplication0.run(strArray1, inputStream2, outputStream3);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test172");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication1 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication11 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication14 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication14.run(strArray17, inputStream18, outputStream19);
        java.lang.String str21 = lsApplication11.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray17);
        mkdirApplication4.createFolder(strArray17);
        java.lang.String str23 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray17);
        java.io.InputStream inputStream24 = null;
        java.io.OutputStream outputStream25 = null;
        try {
            wcApplication0.run(strArray17, inputStream24, outputStream25);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
        assertNotNull(strArray7);
        assertNotNull(strArray17);
        assertTrue(str21.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str23.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test173");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        java.lang.String str10 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray6);
        java.lang.String[] strArray13 = null;
        try {
            java.lang.String str14 = lsApplication0.listFolderContent((java.lang.Boolean) true, (java.lang.Boolean) false, strArray13);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.LsException; message: ls: Input arguments cannot be null!");
        } catch (sg.edu.nus.comp.cs4218.exception.LsException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str10.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test174");
        sg.edu.nus.comp.cs4218.impl.app.ExitApplication exitApplication0 = new sg.edu.nus.comp.cs4218.impl.app.ExitApplication();
        java.lang.String[] strArray1 = null;
        java.io.InputStream inputStream2 = null;
        java.io.OutputStream outputStream3 = null;
        try {
            exitApplication0.run(strArray1, inputStream2, outputStream3);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.ExitException; message: exit: Terminate process.");
        } catch (sg.edu.nus.comp.cs4218.exception.ExitException e) {
        }
    }

    @Test
    public void test175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test175");
        sg.edu.nus.comp.cs4218.impl.app.DateApplication dateApplication0 = new sg.edu.nus.comp.cs4218.impl.app.DateApplication();
        try {
            java.lang.String str2 = dateApplication0.getDate("null arguments");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.DateException; message: date: Invalid format. Date format must start with '+'");
        } catch (sg.edu.nus.comp.cs4218.exception.DateException e) {
        }
    }

    @Test
    public void test176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test176");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication5.run(strArray8, inputStream9, outputStream10);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication12 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication15 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray18 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        mkdirApplication15.run(strArray18, inputStream19, outputStream20);
        java.lang.String str22 = lsApplication12.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray18);
        mkdirApplication5.createFolder(strArray18);
        try {
            java.lang.String str24 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray18);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray8);
        assertNotNull(strArray18);
        assertTrue(str22.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test177");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("Terminate process.", "Could not read stream", (int) (byte) -1, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test178");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray11 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        mkdirApplication4.run(strArray11, inputStream12, outputStream13);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication15 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray22 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream23 = null;
        java.io.OutputStream outputStream24 = null;
        mkdirApplication15.run(strArray22, inputStream23, outputStream24);
        java.lang.Class<?> wildcardClass26 = mkdirApplication15.getClass();
        java.lang.String[] strArray31 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication15.run(strArray31, inputStream32, outputStream33);
        java.io.InputStream inputStream35 = null;
        java.io.OutputStream outputStream36 = null;
        mkdirApplication4.run(strArray31, inputStream35, outputStream36);
        try {
            java.lang.String str38 = grepApplication0.grepFromFiles("", (java.lang.Boolean) false, (java.lang.Boolean) false, strArray31);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray11);
        assertNotNull(strArray22);
        assertNotNull(wildcardClass26);
        assertNotNull(strArray31);
    }

    @Test
    public void test179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test179");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        mkdirApplication2.run(strArray5, inputStream6, outputStream7);
        java.lang.String str9 = findApplication0.findFolderContent("", strArray5);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication11 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray18 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        mkdirApplication11.run(strArray18, inputStream19, outputStream20);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication22 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication25 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication27 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray30 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream31 = null;
        java.io.OutputStream outputStream32 = null;
        mkdirApplication27.run(strArray30, inputStream31, outputStream32);
        java.lang.String str34 = findApplication25.findFolderContent("", strArray30);
        java.lang.String str35 = lsApplication22.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray30);
        java.io.InputStream inputStream36 = null;
        java.io.OutputStream outputStream37 = null;
        mkdirApplication11.run(strArray30, inputStream36, outputStream37);
        java.lang.String str39 = findApplication0.findFolderContent("Could not read stream", strArray30);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication40 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication41 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray48 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream49 = null;
        java.io.OutputStream outputStream50 = null;
        mkdirApplication41.run(strArray48, inputStream49, outputStream50);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication52 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication55 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication57 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray60 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream61 = null;
        java.io.OutputStream outputStream62 = null;
        mkdirApplication57.run(strArray60, inputStream61, outputStream62);
        java.lang.String str64 = findApplication55.findFolderContent("", strArray60);
        java.lang.String str65 = lsApplication52.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray60);
        java.io.InputStream inputStream66 = null;
        java.io.OutputStream outputStream67 = null;
        mkdirApplication41.run(strArray60, inputStream66, outputStream67);
        java.lang.String str69 = echoApplication40.constructResult(strArray60);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication70 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray77 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream78 = null;
        java.io.OutputStream outputStream79 = null;
        mkdirApplication70.run(strArray77, inputStream78, outputStream79);
        java.io.InputStream inputStream81 = null;
        java.io.OutputStream outputStream82 = null;
        echoApplication40.run(strArray77, inputStream81, outputStream82);
        java.io.InputStream inputStream84 = null;
        java.io.OutputStream outputStream85 = null;
        try {
            findApplication0.run(strArray77, inputStream84, outputStream85);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: output stream is null");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray5);
        assertTrue(str9.equals(""));
        assertNotNull(strArray18);
        assertNotNull(strArray30);
        assertTrue(str34.equals(""));
        assertTrue(str35.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str39.equals(""));
        assertNotNull(strArray48);
        assertNotNull(strArray60);
        assertTrue(str64.equals(""));
        assertTrue(str65.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str69.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray77);
    }

    @Test
    public void test182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test182");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        java.io.InputStream inputStream5 = null;
        try {
            java.lang.String str6 = sortApplication0.sortFromStdin((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream5);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        assertNotNull(wildcardClass1);
    }

    @Test
    public void test183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test183");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sortApplication0.sortFromStdin((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test184");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        cdApplication0.changeToDirectory("");
        try {
            cdApplication0.changeToDirectory("fail_echo");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: C:\\fail_echo: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
    }

    @Test
    public void test186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test186");
        java.io.InputStream inputStream0 = null;
        try {
            java.util.List<java.lang.String> strList4 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.replace(inputStream0, "File is null.", "", 100);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test188");
        sg.edu.nus.comp.cs4218.impl.app.PwdApplication pwdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PwdApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        java.lang.String str10 = findApplication1.findFolderContent("", strArray6);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication12 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication14 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication14.run(strArray17, inputStream18, outputStream19);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication21 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication24 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray27 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream28 = null;
        java.io.OutputStream outputStream29 = null;
        mkdirApplication24.run(strArray27, inputStream28, outputStream29);
        java.lang.String str31 = lsApplication21.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray27);
        mkdirApplication14.createFolder(strArray27);
        java.lang.String str33 = findApplication12.findFolderContent("null output stream provided fail_echo_write", strArray27);
        java.lang.Class<?> wildcardClass34 = strArray27.getClass();
        java.lang.String str35 = findApplication1.findFolderContent("null output stream provided fail_echo_write", strArray27);
        java.io.InputStream inputStream36 = null;
        java.io.OutputStream outputStream37 = null;
        try {
            pwdApplication0.run(strArray27, inputStream36, outputStream37);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PwdException; message: pwd: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.PwdException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str10.equals(""));
        assertNotNull(strArray17);
        assertNotNull(strArray27);
        assertTrue(str31.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str33.equals(""));
        assertNotNull(wildcardClass34);
        assertTrue(str35.equals(""));
    }

    @Test
    public void test192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test192");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        cdApplication0.changeToDirectory("");
        try {
            cdApplication0.changeToDirectory("fail_sed_write");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: C:\\fail_sed_write: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
    }

    @Test
    public void test193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test193");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication5.createFolder(strArray8);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication10 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray13 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream14 = null;
        java.io.OutputStream outputStream15 = null;
        mkdirApplication10.run(strArray13, inputStream14, outputStream15);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication17 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication19 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray22 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream23 = null;
        java.io.OutputStream outputStream24 = null;
        mkdirApplication19.run(strArray22, inputStream23, outputStream24);
        java.lang.String str26 = findApplication17.findFolderContent("", strArray22);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication28 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray35 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream36 = null;
        java.io.OutputStream outputStream37 = null;
        mkdirApplication28.run(strArray35, inputStream36, outputStream37);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication39 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication42 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication44 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray47 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream48 = null;
        java.io.OutputStream outputStream49 = null;
        mkdirApplication44.run(strArray47, inputStream48, outputStream49);
        java.lang.String str51 = findApplication42.findFolderContent("", strArray47);
        java.lang.String str52 = lsApplication39.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray47);
        java.io.InputStream inputStream53 = null;
        java.io.OutputStream outputStream54 = null;
        mkdirApplication28.run(strArray47, inputStream53, outputStream54);
        java.lang.String str56 = findApplication17.findFolderContent("Could not read stream", strArray47);
        java.io.InputStream inputStream57 = null;
        java.io.OutputStream outputStream58 = null;
        mkdirApplication10.run(strArray47, inputStream57, outputStream58);
        java.io.InputStream inputStream60 = null;
        java.io.OutputStream outputStream61 = null;
        mkdirApplication5.run(strArray47, inputStream60, outputStream61);
        try {
            java.lang.String str63 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray47);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray8);
        assertNotNull(strArray13);
        assertNotNull(strArray22);
        assertTrue(str26.equals(""));
        assertNotNull(strArray35);
        assertNotNull(strArray47);
        assertTrue(str51.equals(""));
        assertTrue(str52.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str56.equals(""));
    }

    @Test
    public void test198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test198");
        sg.edu.nus.comp.cs4218.impl.app.PwdApplication pwdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PwdApplication();
        java.lang.String str1 = pwdApplication0.getAbsolutePath();
        java.lang.String str2 = pwdApplication0.getAbsolutePath();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        mkdirApplication3.run(strArray10, inputStream11, outputStream12);
        java.lang.Class<?> wildcardClass14 = mkdirApplication3.getClass();
        java.lang.String[] strArray19 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication3.run(strArray19, inputStream20, outputStream21);
        java.io.InputStream inputStream23 = null;
        java.io.OutputStream outputStream24 = null;
        try {
            pwdApplication0.run(strArray19, inputStream23, outputStream24);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PwdException; message: pwd: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.PwdException e) {
        }
        assertTrue(str1.equals("C:\\"));
        assertTrue(str2.equals("C:\\"));
        assertNotNull(strArray10);
        assertNotNull(wildcardClass14);
        assertNotNull(strArray19);
    }

    @Test
    public void test200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test200");
        boolean boolean1 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.isSpecialSymbol("File is null.");
        assertTrue(boolean1 == true);
    }

    @Test
    public void test209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test209");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication4 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray12 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream13 = null;
        java.io.OutputStream outputStream14 = null;
        mkdirApplication5.run(strArray12, inputStream13, outputStream14);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication16 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication19 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication21 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray24 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream25 = null;
        java.io.OutputStream outputStream26 = null;
        mkdirApplication21.run(strArray24, inputStream25, outputStream26);
        java.lang.String str28 = findApplication19.findFolderContent("", strArray24);
        java.lang.String str29 = lsApplication16.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray24);
        java.io.InputStream inputStream30 = null;
        java.io.OutputStream outputStream31 = null;
        mkdirApplication5.run(strArray24, inputStream30, outputStream31);
        java.lang.String str33 = echoApplication4.constructResult(strArray24);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication34 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray41 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream42 = null;
        java.io.OutputStream outputStream43 = null;
        mkdirApplication34.run(strArray41, inputStream42, outputStream43);
        java.io.InputStream inputStream45 = null;
        java.io.OutputStream outputStream46 = null;
        echoApplication4.run(strArray41, inputStream45, outputStream46);
        try {
            java.lang.String str48 = grepApplication0.grepFromFiles("Could not read stream", (java.lang.Boolean) true, (java.lang.Boolean) true, strArray41);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray12);
        assertNotNull(strArray24);
        assertTrue(str28.equals(""));
        assertTrue(str29.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str33.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray41);
    }

    @Test
    public void test214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test214");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.lang.Class<?> wildcardClass1 = catApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        mkdirApplication2.run(strArray5, inputStream6, outputStream7);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication9 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication11 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray14 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        mkdirApplication11.run(strArray14, inputStream15, outputStream16);
        java.lang.String str18 = findApplication9.findFolderContent("", strArray14);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication20 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray27 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream28 = null;
        java.io.OutputStream outputStream29 = null;
        mkdirApplication20.run(strArray27, inputStream28, outputStream29);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication31 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication34 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication36 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray39 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream40 = null;
        java.io.OutputStream outputStream41 = null;
        mkdirApplication36.run(strArray39, inputStream40, outputStream41);
        java.lang.String str43 = findApplication34.findFolderContent("", strArray39);
        java.lang.String str44 = lsApplication31.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray39);
        java.io.InputStream inputStream45 = null;
        java.io.OutputStream outputStream46 = null;
        mkdirApplication20.run(strArray39, inputStream45, outputStream46);
        java.lang.String str48 = findApplication9.findFolderContent("Could not read stream", strArray39);
        java.io.InputStream inputStream49 = null;
        java.io.OutputStream outputStream50 = null;
        mkdirApplication2.run(strArray39, inputStream49, outputStream50);
        java.io.InputStream inputStream52 = null;
        java.io.OutputStream outputStream53 = null;
        try {
            catApplication0.run(strArray39, inputStream52, outputStream53);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray5);
        assertNotNull(strArray14);
        assertTrue(str18.equals(""));
        assertNotNull(strArray27);
        assertNotNull(strArray39);
        assertTrue(str43.equals(""));
        assertTrue(str44.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str48.equals(""));
    }


    @Test
    public void test222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test222");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication4 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication7 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        mkdirApplication7.run(strArray10, inputStream11, outputStream12);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication14 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray20 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        mkdirApplication17.run(strArray20, inputStream21, outputStream22);
        java.lang.String str24 = lsApplication14.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        mkdirApplication7.createFolder(strArray20);
        java.lang.String str26 = lsApplication4.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        try {
            java.lang.String str27 = grepApplication0.grepFromFiles("null arguments", (java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray10);
        assertNotNull(strArray20);
        assertTrue(str24.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str26.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test223");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        java.lang.String str10 = findApplication1.findFolderContent("", strArray6);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication12 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray15 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream16 = null;
        java.io.OutputStream outputStream17 = null;
        mkdirApplication12.run(strArray15, inputStream16, outputStream17);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication19 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication22 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray25 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream26 = null;
        java.io.OutputStream outputStream27 = null;
        mkdirApplication22.run(strArray25, inputStream26, outputStream27);
        java.lang.String str29 = lsApplication19.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray25);
        mkdirApplication12.createFolder(strArray25);
        java.lang.String str31 = findApplication1.findFolderContent("null input file provided", strArray25);
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        try {
            catApplication0.run(strArray25, inputStream32, outputStream33);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str10.equals(""));
        assertNotNull(strArray15);
        assertNotNull(strArray25);
        assertTrue(str29.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str31.equals(""));
    }

    @Test
    public void test234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test234");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("null input file provided", "Null Pointer Exception", (int) (byte) 1, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test235");
        boolean boolean1 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.isInteger("fail_echo_write");
        assertTrue(boolean1 == false);
    }

    @Test
    public void test241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test241");
        sg.edu.nus.comp.cs4218.impl.app.DateApplication dateApplication0 = new sg.edu.nus.comp.cs4218.impl.app.DateApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        java.lang.String str10 = findApplication1.findFolderContent("", strArray6);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication12 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray19 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication12.run(strArray19, inputStream20, outputStream21);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication23 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication26 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication28 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray31 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication28.run(strArray31, inputStream32, outputStream33);
        java.lang.String str35 = findApplication26.findFolderContent("", strArray31);
        java.lang.String str36 = lsApplication23.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray31);
        java.io.InputStream inputStream37 = null;
        java.io.OutputStream outputStream38 = null;
        mkdirApplication12.run(strArray31, inputStream37, outputStream38);
        java.lang.String str40 = findApplication1.findFolderContent("Could not read stream", strArray31);
        java.io.InputStream inputStream41 = null;
        java.io.OutputStream outputStream42 = null;
        try {
            dateApplication0.run(strArray31, inputStream41, outputStream42);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.DateException; message: date: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.DateException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str10.equals(""));
        assertNotNull(strArray19);
        assertNotNull(strArray31);
        assertTrue(str35.equals(""));
        assertTrue(str36.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str40.equals(""));
    }

    @Test
    public void test242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test242");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication10 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication13 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray16 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream17 = null;
        java.io.OutputStream outputStream18 = null;
        mkdirApplication13.run(strArray16, inputStream17, outputStream18);
        java.lang.String str20 = lsApplication10.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray16);
        mkdirApplication3.createFolder(strArray16);
        java.lang.String str22 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray16);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication23 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication24 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray31 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication24.run(strArray31, inputStream32, outputStream33);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication35 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication38 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication40 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray43 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream44 = null;
        java.io.OutputStream outputStream45 = null;
        mkdirApplication40.run(strArray43, inputStream44, outputStream45);
        java.lang.String str47 = findApplication38.findFolderContent("", strArray43);
        java.lang.String str48 = lsApplication35.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray43);
        java.io.InputStream inputStream49 = null;
        java.io.OutputStream outputStream50 = null;
        mkdirApplication24.run(strArray43, inputStream49, outputStream50);
        java.lang.String str52 = echoApplication23.constructResult(strArray43);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication53 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray60 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream61 = null;
        java.io.OutputStream outputStream62 = null;
        mkdirApplication53.run(strArray60, inputStream61, outputStream62);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication64 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray71 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream72 = null;
        java.io.OutputStream outputStream73 = null;
        mkdirApplication64.run(strArray71, inputStream72, outputStream73);
        java.lang.Class<?> wildcardClass75 = mkdirApplication64.getClass();
        java.lang.String[] strArray80 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream81 = null;
        java.io.OutputStream outputStream82 = null;
        mkdirApplication64.run(strArray80, inputStream81, outputStream82);
        java.io.InputStream inputStream84 = null;
        java.io.OutputStream outputStream85 = null;
        mkdirApplication53.run(strArray80, inputStream84, outputStream85);
        java.io.InputStream inputStream87 = null;
        java.io.OutputStream outputStream88 = null;
        echoApplication23.run(strArray80, inputStream87, outputStream88);
        java.io.InputStream inputStream90 = null;
        java.io.OutputStream outputStream91 = null;
        try {
            lsApplication0.run(strArray80, inputStream90, outputStream91);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        assertNotNull(strArray6);
        assertNotNull(strArray16);
        assertTrue(str20.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str22.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray31);
        assertNotNull(strArray43);
        assertTrue(str47.equals(""));
        assertTrue(str48.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str52.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray60);
        assertNotNull(strArray71);
        assertNotNull(wildcardClass75);
        assertNotNull(strArray80);
    }

    @Test
    public void test245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test245");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("null input file provided", "Exception Caught", (int) 'a', inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test246");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("Could not read stream", (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test248");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        cdApplication0.changeToDirectory("");
        try {
            cdApplication0.changeToDirectory("null output stream provided");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: C:\\null output stream provided: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
    }

    @Test
    public void test249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test249");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("", (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test252");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        java.lang.Class<?> wildcardClass12 = mkdirApplication1.getClass();
        java.lang.Class<?> wildcardClass13 = mkdirApplication1.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication14 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication14.run(strArray17, inputStream18, outputStream19);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication21 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication22 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray29 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream30 = null;
        java.io.OutputStream outputStream31 = null;
        mkdirApplication22.run(strArray29, inputStream30, outputStream31);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication33 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication36 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication38 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray41 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream42 = null;
        java.io.OutputStream outputStream43 = null;
        mkdirApplication38.run(strArray41, inputStream42, outputStream43);
        java.lang.String str45 = findApplication36.findFolderContent("", strArray41);
        java.lang.String str46 = lsApplication33.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray41);
        java.io.InputStream inputStream47 = null;
        java.io.OutputStream outputStream48 = null;
        mkdirApplication22.run(strArray41, inputStream47, outputStream48);
        java.lang.String str50 = echoApplication21.constructResult(strArray41);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication51 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray58 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream59 = null;
        java.io.OutputStream outputStream60 = null;
        mkdirApplication51.run(strArray58, inputStream59, outputStream60);
        java.io.InputStream inputStream62 = null;
        java.io.OutputStream outputStream63 = null;
        echoApplication21.run(strArray58, inputStream62, outputStream63);
        mkdirApplication14.createFolder(strArray58);
        java.io.InputStream inputStream66 = null;
        java.io.OutputStream outputStream67 = null;
        mkdirApplication1.run(strArray58, inputStream66, outputStream67);
        java.io.InputStream inputStream69 = null;
        java.io.OutputStream outputStream70 = null;
        try {
            sortApplication0.run(strArray58, inputStream69, outputStream70);
            fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 0");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
        }
        assertNotNull(strArray8);
        assertNotNull(wildcardClass12);
        assertNotNull(wildcardClass13);
        assertNotNull(strArray17);
        assertNotNull(strArray29);
        assertNotNull(strArray41);
        assertTrue(str45.equals(""));
        assertTrue(str46.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str50.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray58);
    }

    @Test
    public void test253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test253");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        java.io.InputStream inputStream5 = null;
        try {
            java.lang.String str6 = sortApplication0.sortFromStdin((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream5);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        assertNotNull(wildcardClass1);
    }

    @Test
    public void test256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test256");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("", "null input file provided", 100, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test257");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.lang.Class<?> wildcardClass1 = catApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication2 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication11 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication14 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication14.run(strArray17, inputStream18, outputStream19);
        java.lang.String str21 = lsApplication11.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray17);
        mkdirApplication4.createFolder(strArray17);
        java.lang.String str23 = findApplication2.findFolderContent("null output stream provided fail_echo_write", strArray17);
        try {
            java.lang.String str24 = catApplication0.catFiles(strArray17);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray7);
        assertNotNull(strArray17);
        assertTrue(str21.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str23.equals(""));
    }

    @Test
    public void test258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test258");
        sg.edu.nus.comp.cs4218.impl.app.PwdApplication pwdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PwdApplication();
        java.lang.String str1 = pwdApplication0.getAbsolutePath();
        java.lang.Class<?> wildcardClass2 = pwdApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication3 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication5.run(strArray8, inputStream9, outputStream10);
        java.lang.String str12 = findApplication3.findFolderContent("", strArray8);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication14 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication16 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray19 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication16.run(strArray19, inputStream20, outputStream21);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication23 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication26 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray29 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream30 = null;
        java.io.OutputStream outputStream31 = null;
        mkdirApplication26.run(strArray29, inputStream30, outputStream31);
        java.lang.String str33 = lsApplication23.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray29);
        mkdirApplication16.createFolder(strArray29);
        java.lang.String str35 = findApplication14.findFolderContent("null output stream provided fail_echo_write", strArray29);
        java.lang.Class<?> wildcardClass36 = strArray29.getClass();
        java.lang.String str37 = findApplication3.findFolderContent("null output stream provided fail_echo_write", strArray29);
        java.io.InputStream inputStream38 = null;
        java.io.OutputStream outputStream39 = null;
        try {
            pwdApplication0.run(strArray29, inputStream38, outputStream39);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PwdException; message: pwd: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.PwdException e) {
        }
        assertTrue(str1.equals("C:\\"));
        assertNotNull(wildcardClass2);
        assertNotNull(strArray8);
        assertTrue(str12.equals(""));
        assertNotNull(strArray19);
        assertNotNull(strArray29);
        assertTrue(str33.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str35.equals(""));
        assertNotNull(wildcardClass36);
        assertTrue(str37.equals(""));
    }

    @Test
    public void test259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test259");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication4 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication7 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        mkdirApplication7.run(strArray10, inputStream11, outputStream12);
        java.lang.String str14 = lsApplication4.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray10);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray24 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream25 = null;
        java.io.OutputStream outputStream26 = null;
        mkdirApplication17.run(strArray24, inputStream25, outputStream26);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication28 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray35 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream36 = null;
        java.io.OutputStream outputStream37 = null;
        mkdirApplication28.run(strArray35, inputStream36, outputStream37);
        java.lang.Class<?> wildcardClass39 = mkdirApplication28.getClass();
        java.lang.String[] strArray44 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream45 = null;
        java.io.OutputStream outputStream46 = null;
        mkdirApplication28.run(strArray44, inputStream45, outputStream46);
        java.io.InputStream inputStream48 = null;
        java.io.OutputStream outputStream49 = null;
        mkdirApplication17.run(strArray44, inputStream48, outputStream49);
        java.lang.String str51 = lsApplication4.listFolderContent((java.lang.Boolean) true, (java.lang.Boolean) true, strArray44);
        try {
            java.lang.String str52 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray44);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
        assertNotNull(strArray10);
        assertTrue(str14.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray24);
        assertNotNull(strArray35);
        assertNotNull(wildcardClass39);
        assertNotNull(strArray44);
        assertTrue(str51.equals("This is a directory\r\nCould not read stream\r\nNull Pointer Exception\r\nException Caught"));
    }

    @Test
    public void test262() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test262");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication10 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication13 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray16 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream17 = null;
        java.io.OutputStream outputStream18 = null;
        mkdirApplication13.run(strArray16, inputStream17, outputStream18);
        java.lang.String str20 = lsApplication10.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray16);
        mkdirApplication3.createFolder(strArray16);
        java.lang.String str22 = findApplication1.findFolderContent("null output stream provided fail_echo_write", strArray16);
        java.lang.Class<?> wildcardClass23 = strArray16.getClass();
        try {
            java.lang.String str24 = catApplication0.catFiles(strArray16);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(strArray6);
        assertNotNull(strArray16);
        assertTrue(str20.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str22.equals(""));
        assertNotNull(wildcardClass23);
    }

    @Test
    public void test263() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test263");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication2.createFolder(strArray5);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication7 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication8 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray15 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream16 = null;
        java.io.OutputStream outputStream17 = null;
        mkdirApplication8.run(strArray15, inputStream16, outputStream17);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication19 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication22 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication24 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray27 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream28 = null;
        java.io.OutputStream outputStream29 = null;
        mkdirApplication24.run(strArray27, inputStream28, outputStream29);
        java.lang.String str31 = findApplication22.findFolderContent("", strArray27);
        java.lang.String str32 = lsApplication19.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray27);
        java.io.InputStream inputStream33 = null;
        java.io.OutputStream outputStream34 = null;
        mkdirApplication8.run(strArray27, inputStream33, outputStream34);
        java.lang.String str36 = echoApplication7.constructResult(strArray27);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication37 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray44 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream45 = null;
        java.io.OutputStream outputStream46 = null;
        mkdirApplication37.run(strArray44, inputStream45, outputStream46);
        java.io.InputStream inputStream48 = null;
        java.io.OutputStream outputStream49 = null;
        echoApplication7.run(strArray44, inputStream48, outputStream49);
        java.io.InputStream inputStream51 = null;
        java.io.OutputStream outputStream52 = null;
        mkdirApplication2.run(strArray44, inputStream51, outputStream52);
        java.io.InputStream inputStream54 = null;
        java.io.OutputStream outputStream55 = null;
        try {
            sortApplication0.run(strArray44, inputStream54, outputStream55);
            fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 0");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray5);
        assertNotNull(strArray15);
        assertNotNull(strArray27);
        assertTrue(str31.equals(""));
        assertTrue(str32.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str36.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray44);
    }

    @Test
    public void test267() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test267");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        cdApplication0.changeToDirectory("");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication3 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication5 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication7 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication7.createFolder(strArray10);
        java.lang.String str12 = findApplication5.findFolderContent("fail_sed_write", strArray10);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication14 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication14.createFolder(strArray17);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication19 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray22 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream23 = null;
        java.io.OutputStream outputStream24 = null;
        mkdirApplication19.run(strArray22, inputStream23, outputStream24);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication26 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication28 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray31 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication28.run(strArray31, inputStream32, outputStream33);
        java.lang.String str35 = findApplication26.findFolderContent("", strArray31);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication37 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray44 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream45 = null;
        java.io.OutputStream outputStream46 = null;
        mkdirApplication37.run(strArray44, inputStream45, outputStream46);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication48 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication51 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication53 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray56 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream57 = null;
        java.io.OutputStream outputStream58 = null;
        mkdirApplication53.run(strArray56, inputStream57, outputStream58);
        java.lang.String str60 = findApplication51.findFolderContent("", strArray56);
        java.lang.String str61 = lsApplication48.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray56);
        java.io.InputStream inputStream62 = null;
        java.io.OutputStream outputStream63 = null;
        mkdirApplication37.run(strArray56, inputStream62, outputStream63);
        java.lang.String str65 = findApplication26.findFolderContent("Could not read stream", strArray56);
        java.io.InputStream inputStream66 = null;
        java.io.OutputStream outputStream67 = null;
        mkdirApplication19.run(strArray56, inputStream66, outputStream67);
        java.io.InputStream inputStream69 = null;
        java.io.OutputStream outputStream70 = null;
        mkdirApplication14.run(strArray56, inputStream69, outputStream70);
        java.lang.Class<?> wildcardClass72 = strArray56.getClass();
        java.lang.String str73 = findApplication5.findFolderContent("", strArray56);
        java.lang.String str74 = findApplication3.findFolderContent("", strArray56);
        java.io.InputStream inputStream75 = null;
        java.io.OutputStream outputStream76 = null;
        try {
            cdApplication0.run(strArray56, inputStream75, outputStream76);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: cd only takes one argument");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
        assertNotNull(strArray10);
        assertTrue(str12.equals(""));
        assertNotNull(strArray17);
        assertNotNull(strArray22);
        assertNotNull(strArray31);
        assertTrue(str35.equals(""));
        assertNotNull(strArray44);
        assertNotNull(strArray56);
        assertTrue(str60.equals(""));
        assertTrue(str61.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str65.equals(""));
        assertNotNull(wildcardClass72);
        assertTrue(str73.equals(""));
        assertTrue(str74.equals(""));
    }

    @Test
    public void test280() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test280");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        mkdirApplication2.run(strArray5, inputStream6, outputStream7);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication9 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication12 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray15 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream16 = null;
        java.io.OutputStream outputStream17 = null;
        mkdirApplication12.run(strArray15, inputStream16, outputStream17);
        java.lang.String str19 = lsApplication9.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray15);
        mkdirApplication2.createFolder(strArray15);
        java.lang.String[] strArray24 = new java.lang.String[] { "null output stream provided", "null arguments", "null output stream provided" };
        java.io.InputStream inputStream25 = null;
        java.io.OutputStream outputStream26 = null;
        mkdirApplication2.run(strArray24, inputStream25, outputStream26);
        java.io.InputStream inputStream28 = null;
        java.io.OutputStream outputStream29 = null;
        try {
            sortApplication0.run(strArray24, inputStream28, outputStream29);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: ");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray5);
        assertNotNull(strArray15);
        assertTrue(str19.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray24);
    }

    @Test
    public void test281() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test281");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("Could not read file", (java.lang.Boolean) true, (java.lang.Boolean) false, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test291() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test291");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        java.io.InputStream inputStream5 = null;
        try {
            java.lang.String str6 = sortApplication0.sortFromStdin((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, inputStream5);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        assertNotNull(wildcardClass1);
    }

    @Test
    public void test295() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test295");
        boolean boolean1 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.isSpecialSymbol("null output stream provided");
        assertTrue(boolean1 == true);
    }

    @Test
    public void test297() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test297");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication10 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication13 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray16 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream17 = null;
        java.io.OutputStream outputStream18 = null;
        mkdirApplication13.run(strArray16, inputStream17, outputStream18);
        java.lang.String str20 = lsApplication10.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray16);
        mkdirApplication3.createFolder(strArray16);
        java.lang.String str22 = findApplication1.findFolderContent("null output stream provided fail_echo_write", strArray16);
        java.lang.Class<?> wildcardClass23 = strArray16.getClass();
        java.io.InputStream inputStream24 = null;
        java.io.OutputStream outputStream25 = null;
        try {
            grepApplication0.run(strArray16, inputStream24, outputStream25);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: output stream is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray6);
        assertNotNull(strArray16);
        assertTrue(str20.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str22.equals(""));
        assertNotNull(wildcardClass23);
    }

    @Test
    public void test300() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test300");
        sg.edu.nus.comp.cs4218.impl.app.DateApplication dateApplication0 = new sg.edu.nus.comp.cs4218.impl.app.DateApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication13 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication16 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication18 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray21 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        mkdirApplication18.run(strArray21, inputStream22, outputStream23);
        java.lang.String str25 = findApplication16.findFolderContent("", strArray21);
        java.lang.String str26 = lsApplication13.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray21);
        java.io.InputStream inputStream27 = null;
        java.io.OutputStream outputStream28 = null;
        mkdirApplication2.run(strArray21, inputStream27, outputStream28);
        java.lang.String str30 = echoApplication1.constructResult(strArray21);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication31 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray38 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream39 = null;
        java.io.OutputStream outputStream40 = null;
        mkdirApplication31.run(strArray38, inputStream39, outputStream40);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication42 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray49 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream50 = null;
        java.io.OutputStream outputStream51 = null;
        mkdirApplication42.run(strArray49, inputStream50, outputStream51);
        java.lang.Class<?> wildcardClass53 = mkdirApplication42.getClass();
        java.lang.String[] strArray58 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream59 = null;
        java.io.OutputStream outputStream60 = null;
        mkdirApplication42.run(strArray58, inputStream59, outputStream60);
        java.io.InputStream inputStream62 = null;
        java.io.OutputStream outputStream63 = null;
        mkdirApplication31.run(strArray58, inputStream62, outputStream63);
        java.io.InputStream inputStream65 = null;
        java.io.OutputStream outputStream66 = null;
        echoApplication1.run(strArray58, inputStream65, outputStream66);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication68 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray71 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication68.createFolder(strArray71);
        java.lang.String str73 = echoApplication1.constructResult(strArray71);
        java.io.InputStream inputStream74 = null;
        java.io.OutputStream outputStream75 = null;
        try {
            dateApplication0.run(strArray71, inputStream74, outputStream75);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.DateException; message: date: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.DateException e) {
        }
        assertNotNull(strArray9);
        assertNotNull(strArray21);
        assertTrue(str25.equals(""));
        assertTrue(str26.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str30.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray38);
        assertNotNull(strArray49);
        assertNotNull(wildcardClass53);
        assertNotNull(strArray58);
        assertNotNull(strArray71);
        assertTrue(str73.equals("fail_echo_write hi!"));
    }

    @Test
    public void test301() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test301");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = wcApplication0.countFromStdin((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
    }

    @Test
    public void test303() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test303");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("null output stream provided fail_echo_write", "", (int) (short) 10, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test304() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test304");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        mkdirApplication2.run(strArray5, inputStream6, outputStream7);
        java.lang.String str9 = findApplication0.findFolderContent("", strArray5);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication11 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray14 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        mkdirApplication11.run(strArray14, inputStream15, outputStream16);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication18 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication21 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray24 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream25 = null;
        java.io.OutputStream outputStream26 = null;
        mkdirApplication21.run(strArray24, inputStream25, outputStream26);
        java.lang.String str28 = lsApplication18.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray24);
        mkdirApplication11.createFolder(strArray24);
        java.lang.String str30 = findApplication0.findFolderContent("null input file provided", strArray24);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication31 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray38 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream39 = null;
        java.io.OutputStream outputStream40 = null;
        mkdirApplication31.run(strArray38, inputStream39, outputStream40);
        java.lang.Class<?> wildcardClass42 = mkdirApplication31.getClass();
        java.lang.String[] strArray47 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream48 = null;
        java.io.OutputStream outputStream49 = null;
        mkdirApplication31.run(strArray47, inputStream48, outputStream49);
        java.io.InputStream inputStream51 = null;
        java.io.OutputStream outputStream52 = null;
        try {
            findApplication0.run(strArray47, inputStream51, outputStream52);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: output stream is null");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray5);
        assertTrue(str9.equals(""));
        assertNotNull(strArray14);
        assertNotNull(strArray24);
        assertTrue(str28.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str30.equals(""));
        assertNotNull(strArray38);
        assertNotNull(wildcardClass42);
        assertNotNull(strArray47);
    }

    @Test
    public void test307() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test307");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("null arguments", (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test308() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test308");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication4 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication7 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        mkdirApplication7.run(strArray10, inputStream11, outputStream12);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication14 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray20 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        mkdirApplication17.run(strArray20, inputStream21, outputStream22);
        java.lang.String str24 = lsApplication14.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        mkdirApplication7.createFolder(strArray20);
        java.lang.String str26 = lsApplication4.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication29 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication31 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray34 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream35 = null;
        java.io.OutputStream outputStream36 = null;
        mkdirApplication31.run(strArray34, inputStream35, outputStream36);
        java.lang.String str38 = findApplication29.findFolderContent("", strArray34);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication40 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray43 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream44 = null;
        java.io.OutputStream outputStream45 = null;
        mkdirApplication40.run(strArray43, inputStream44, outputStream45);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication47 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication50 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray53 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream54 = null;
        java.io.OutputStream outputStream55 = null;
        mkdirApplication50.run(strArray53, inputStream54, outputStream55);
        java.lang.String str57 = lsApplication47.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray53);
        mkdirApplication40.createFolder(strArray53);
        java.lang.String str59 = findApplication29.findFolderContent("null input file provided", strArray53);
        java.lang.String str60 = lsApplication4.listFolderContent((java.lang.Boolean) true, (java.lang.Boolean) true, strArray53);
        try {
            java.lang.String str61 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray53);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray10);
        assertNotNull(strArray20);
        assertTrue(str24.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str26.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray34);
        assertTrue(str38.equals(""));
        assertNotNull(strArray43);
        assertNotNull(strArray53);
        assertTrue(str57.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str59.equals(""));
        assertTrue(str60.equals("null output stream provided\r\nfail_echo_write"));
    }

    @Test
    public void test320() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test320");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        mkdirApplication2.run(strArray5, inputStream6, outputStream7);
        java.lang.String str9 = findApplication0.findFolderContent("", strArray5);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication11 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray18 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        mkdirApplication11.run(strArray18, inputStream19, outputStream20);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication22 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication25 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication27 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray30 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream31 = null;
        java.io.OutputStream outputStream32 = null;
        mkdirApplication27.run(strArray30, inputStream31, outputStream32);
        java.lang.String str34 = findApplication25.findFolderContent("", strArray30);
        java.lang.String str35 = lsApplication22.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray30);
        java.io.InputStream inputStream36 = null;
        java.io.OutputStream outputStream37 = null;
        mkdirApplication11.run(strArray30, inputStream36, outputStream37);
        java.lang.String str39 = findApplication0.findFolderContent("Could not read stream", strArray30);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication40 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication42 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray45 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream46 = null;
        java.io.OutputStream outputStream47 = null;
        mkdirApplication42.run(strArray45, inputStream46, outputStream47);
        java.lang.String str49 = findApplication40.findFolderContent("", strArray45);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication51 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication53 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray56 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream57 = null;
        java.io.OutputStream outputStream58 = null;
        mkdirApplication53.run(strArray56, inputStream57, outputStream58);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication60 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication63 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray66 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream67 = null;
        java.io.OutputStream outputStream68 = null;
        mkdirApplication63.run(strArray66, inputStream67, outputStream68);
        java.lang.String str70 = lsApplication60.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray66);
        mkdirApplication53.createFolder(strArray66);
        java.lang.String str72 = findApplication51.findFolderContent("null output stream provided fail_echo_write", strArray66);
        java.lang.Class<?> wildcardClass73 = strArray66.getClass();
        java.lang.String str74 = findApplication40.findFolderContent("null output stream provided fail_echo_write", strArray66);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication76 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication78 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray81 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication78.createFolder(strArray81);
        java.lang.String str83 = findApplication76.findFolderContent("fail_sed_write", strArray81);
        java.lang.String str84 = findApplication40.findFolderContent("null output stream provided\r\nfail_echo_write", strArray81);
        java.io.InputStream inputStream85 = null;
        java.io.OutputStream outputStream86 = null;
        try {
            findApplication0.run(strArray81, inputStream85, outputStream86);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: output stream is null");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray5);
        assertTrue(str9.equals(""));
        assertNotNull(strArray18);
        assertNotNull(strArray30);
        assertTrue(str34.equals(""));
        assertTrue(str35.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str39.equals(""));
        assertNotNull(strArray45);
        assertTrue(str49.equals(""));
        assertNotNull(strArray56);
        assertNotNull(strArray66);
        assertTrue(str70.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str72.equals(""));
        assertNotNull(wildcardClass73);
        assertTrue(str74.equals(""));
        assertNotNull(strArray81);
        assertTrue(str83.equals(""));
        assertTrue(str84.equals(""));
    }

    @Test
    public void test321() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test321");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.lang.Class<?> wildcardClass1 = catApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication13 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray20 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        mkdirApplication13.run(strArray20, inputStream21, outputStream22);
        java.lang.Class<?> wildcardClass24 = mkdirApplication13.getClass();
        java.lang.String[] strArray29 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream30 = null;
        java.io.OutputStream outputStream31 = null;
        mkdirApplication13.run(strArray29, inputStream30, outputStream31);
        java.io.InputStream inputStream33 = null;
        java.io.OutputStream outputStream34 = null;
        mkdirApplication2.run(strArray29, inputStream33, outputStream34);
        try {
            java.lang.String str36 = catApplication0.catFiles(strArray29);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray9);
        assertNotNull(strArray20);
        assertNotNull(wildcardClass24);
        assertNotNull(strArray29);
    }

    @Test
    public void test324() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test324");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("fail_echo_write:\r\n\r\n\r\nhi!:", "Could not read stream", (int) (short) -1, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test325() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test325");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInFile("null arguments", "null arguments", (int) (byte) 1, "Null Pointer Exception");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: File doesn't exist.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
    }

    @Test
    public void test334() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test334");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        java.lang.String str10 = findApplication1.findFolderContent("", strArray6);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication12 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication14 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication14.run(strArray17, inputStream18, outputStream19);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication21 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication24 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray27 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream28 = null;
        java.io.OutputStream outputStream29 = null;
        mkdirApplication24.run(strArray27, inputStream28, outputStream29);
        java.lang.String str31 = lsApplication21.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray27);
        mkdirApplication14.createFolder(strArray27);
        java.lang.String str33 = findApplication12.findFolderContent("null output stream provided fail_echo_write", strArray27);
        java.lang.Class<?> wildcardClass34 = strArray27.getClass();
        java.lang.String str35 = findApplication1.findFolderContent("null output stream provided fail_echo_write", strArray27);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication37 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication39 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray42 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication39.createFolder(strArray42);
        java.lang.String str44 = findApplication37.findFolderContent("fail_sed_write", strArray42);
        java.lang.String str45 = findApplication1.findFolderContent("null output stream provided\r\nfail_echo_write", strArray42);
        java.io.InputStream inputStream46 = null;
        java.io.OutputStream outputStream47 = null;
        try {
            sortApplication0.run(strArray42, inputStream46, outputStream47);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: ");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str10.equals(""));
        assertNotNull(strArray17);
        assertNotNull(strArray27);
        assertTrue(str31.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str33.equals(""));
        assertNotNull(wildcardClass34);
        assertTrue(str35.equals(""));
        assertNotNull(strArray42);
        assertTrue(str44.equals(""));
        assertTrue(str45.equals(""));
    }

    @Test
    public void test335() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test335");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.lang.String[] strArray1 = null;
        java.io.InputStream inputStream2 = null;
        java.io.OutputStream outputStream3 = null;
        try {
            wcApplication0.run(strArray1, inputStream2, outputStream3);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test339() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test339");
        sg.edu.nus.comp.cs4218.impl.app.DateApplication dateApplication0 = new sg.edu.nus.comp.cs4218.impl.app.DateApplication();
        try {
            java.lang.String str2 = dateApplication0.getDate("Terminate process.");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.DateException; message: date: Invalid format. Date format must start with '+'");
        } catch (sg.edu.nus.comp.cs4218.exception.DateException e) {
        }
    }

    @Test
    public void test340() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test340");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("Terminate process.", "", (int) (byte) 10, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test341() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test341");
        sg.edu.nus.comp.cs4218.impl.app.ExitApplication exitApplication0 = new sg.edu.nus.comp.cs4218.impl.app.ExitApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication12 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication15 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray20 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        mkdirApplication17.run(strArray20, inputStream21, outputStream22);
        java.lang.String str24 = findApplication15.findFolderContent("", strArray20);
        java.lang.String str25 = lsApplication12.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        java.io.InputStream inputStream26 = null;
        java.io.OutputStream outputStream27 = null;
        mkdirApplication1.run(strArray20, inputStream26, outputStream27);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication29 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray32 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication29.createFolder(strArray32);
        java.io.InputStream inputStream34 = null;
        java.io.OutputStream outputStream35 = null;
        mkdirApplication1.run(strArray32, inputStream34, outputStream35);
        java.io.InputStream inputStream37 = null;
        java.io.OutputStream outputStream38 = null;
        try {
            exitApplication0.run(strArray32, inputStream37, outputStream38);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.ExitException; message: exit: Terminate process.");
        } catch (sg.edu.nus.comp.cs4218.exception.ExitException e) {
        }
        assertNotNull(strArray8);
        assertNotNull(strArray20);
        assertTrue(str24.equals(""));
        assertTrue(str25.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray32);
    }

    @Test
    public void test342() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test342");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInFile("File doesn't exist.", "fail_echo", (int) (short) -1, "File doesn't exist.");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: File doesn't exist.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
    }

    @Test
    public void test343() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test343");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("null output stream provided:\r\n\r\n\r\nfail_echo_write:", (java.lang.Boolean) false, (java.lang.Boolean) false, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test346() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test346");
        java.io.InputStream inputStream0 = null;
        try {
            java.util.List<java.lang.String> strList4 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.replace(inputStream0, "", "Could not read stream", (int) (byte) 1);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test347() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test347");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication12 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication15 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray18 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        mkdirApplication15.run(strArray18, inputStream19, outputStream20);
        java.lang.String str22 = lsApplication12.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray18);
        mkdirApplication1.createFolder(strArray18);
        java.io.InputStream inputStream24 = null;
        java.io.OutputStream outputStream25 = null;
        try {
            sedApplication0.run(strArray18, inputStream24, outputStream25);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray8);
        assertNotNull(strArray18);
        assertTrue(str22.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test349() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test349");
        boolean boolean1 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.isInteger("Could not write to output stream");
        assertTrue(boolean1 == false);
    }

    @Test
    public void test350() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test350");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("Null Pointer Exception", "fail_echo_write", (int) (short) 100, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test351() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test351");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("Could not read file", "Could not write to output stream", (int) (short) -1, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test355() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test355");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        cdApplication0.changeToDirectory("");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication3 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication5.run(strArray8, inputStream9, outputStream10);
        java.lang.String str12 = findApplication3.findFolderContent("", strArray8);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication14 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication16 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray19 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication16.run(strArray19, inputStream20, outputStream21);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication23 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication26 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray29 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream30 = null;
        java.io.OutputStream outputStream31 = null;
        mkdirApplication26.run(strArray29, inputStream30, outputStream31);
        java.lang.String str33 = lsApplication23.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray29);
        mkdirApplication16.createFolder(strArray29);
        java.lang.String str35 = findApplication14.findFolderContent("null output stream provided fail_echo_write", strArray29);
        java.lang.Class<?> wildcardClass36 = strArray29.getClass();
        java.lang.String str37 = findApplication3.findFolderContent("null output stream provided fail_echo_write", strArray29);
        java.io.InputStream inputStream38 = null;
        java.io.OutputStream outputStream39 = null;
        try {
            cdApplication0.run(strArray29, inputStream38, outputStream39);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: cd only takes one argument");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
        assertNotNull(strArray8);
        assertTrue(str12.equals(""));
        assertNotNull(strArray19);
        assertNotNull(strArray29);
        assertTrue(str33.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str35.equals(""));
        assertNotNull(wildcardClass36);
        assertTrue(str37.equals(""));
    }

    @Test
    public void test356() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test356");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication1 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication4 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication6 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication6.run(strArray9, inputStream10, outputStream11);
        java.lang.String str13 = findApplication4.findFolderContent("", strArray9);
        java.lang.String str14 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray9);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray20 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication17.createFolder(strArray20);
        java.lang.String str22 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray20);
        java.io.InputStream inputStream23 = null;
        java.io.OutputStream outputStream24 = null;
        try {
            catApplication0.run(strArray20, inputStream23, outputStream24);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(strArray9);
        assertTrue(str13.equals(""));
        assertTrue(str14.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray20);
        assertTrue(str22.equals("fail_echo_write:\r\n\r\n\r\nhi!:"));
    }

    @Test
    public void test357() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test357");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication10 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication13 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray16 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream17 = null;
        java.io.OutputStream outputStream18 = null;
        mkdirApplication13.run(strArray16, inputStream17, outputStream18);
        java.lang.String str20 = lsApplication10.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray16);
        mkdirApplication3.createFolder(strArray16);
        java.lang.String str22 = lsApplication0.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray16);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication23 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication25 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray28 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream29 = null;
        java.io.OutputStream outputStream30 = null;
        mkdirApplication25.run(strArray28, inputStream29, outputStream30);
        java.lang.String str32 = findApplication23.findFolderContent("", strArray28);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication34 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray41 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream42 = null;
        java.io.OutputStream outputStream43 = null;
        mkdirApplication34.run(strArray41, inputStream42, outputStream43);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication45 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication48 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication50 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray53 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream54 = null;
        java.io.OutputStream outputStream55 = null;
        mkdirApplication50.run(strArray53, inputStream54, outputStream55);
        java.lang.String str57 = findApplication48.findFolderContent("", strArray53);
        java.lang.String str58 = lsApplication45.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray53);
        java.io.InputStream inputStream59 = null;
        java.io.OutputStream outputStream60 = null;
        mkdirApplication34.run(strArray53, inputStream59, outputStream60);
        java.lang.String str62 = findApplication23.findFolderContent("Could not read stream", strArray53);
        java.io.InputStream inputStream63 = null;
        java.io.OutputStream outputStream64 = null;
        try {
            lsApplication0.run(strArray53, inputStream63, outputStream64);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        assertNotNull(strArray6);
        assertNotNull(strArray16);
        assertTrue(str20.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str22.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray28);
        assertTrue(str32.equals(""));
        assertNotNull(strArray41);
        assertNotNull(strArray53);
        assertTrue(str57.equals(""));
        assertTrue(str58.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str62.equals(""));
    }

    @Test
    public void test358() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test358");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        try {
            cdApplication0.changeToDirectory("This is a directory");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: C:\\This is a directory: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
    }

    @Test
    public void test359() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test359");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication6 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication7 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray14 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        mkdirApplication7.run(strArray14, inputStream15, outputStream16);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication18 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication21 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication23 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray26 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream27 = null;
        java.io.OutputStream outputStream28 = null;
        mkdirApplication23.run(strArray26, inputStream27, outputStream28);
        java.lang.String str30 = findApplication21.findFolderContent("", strArray26);
        java.lang.String str31 = lsApplication18.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray26);
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication7.run(strArray26, inputStream32, outputStream33);
        java.lang.String str35 = echoApplication6.constructResult(strArray26);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication36 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray43 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream44 = null;
        java.io.OutputStream outputStream45 = null;
        mkdirApplication36.run(strArray43, inputStream44, outputStream45);
        java.io.InputStream inputStream47 = null;
        java.io.OutputStream outputStream48 = null;
        echoApplication6.run(strArray43, inputStream47, outputStream48);
        java.io.InputStream inputStream50 = null;
        java.io.OutputStream outputStream51 = null;
        mkdirApplication1.run(strArray43, inputStream50, outputStream51);
        java.lang.Class<?> wildcardClass53 = strArray43.getClass();
        java.io.InputStream inputStream54 = null;
        java.io.OutputStream outputStream55 = null;
        try {
            sedApplication0.run(strArray43, inputStream54, outputStream55);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray4);
        assertNotNull(strArray14);
        assertNotNull(strArray26);
        assertTrue(str30.equals(""));
        assertTrue(str31.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str35.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray43);
        assertNotNull(wildcardClass53);
    }

    @Test
    public void test360() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test360");
        sg.edu.nus.comp.cs4218.impl.app.DateApplication dateApplication0 = new sg.edu.nus.comp.cs4218.impl.app.DateApplication();
        try {
            java.lang.String str2 = dateApplication0.getDate("null output stream provided\r\nfail_echo_write");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.DateException; message: date: Invalid format. Date format must start with '+'");
        } catch (sg.edu.nus.comp.cs4218.exception.DateException e) {
        }
    }

    @Test
    public void test361() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test361");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        try {
            lsApplication0.run(strArray8, inputStream12, outputStream13);
            fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 0");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
        }
        assertNotNull(strArray8);
    }

    @Test
    public void test368() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test368");
        java.io.InputStream inputStream0 = null;
        try {
            java.util.List<java.lang.String> strList4 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.replace(inputStream0, "", "null output stream provided", (int) (short) 1);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test371() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test371");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication13 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray16 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream17 = null;
        java.io.OutputStream outputStream18 = null;
        mkdirApplication13.run(strArray16, inputStream17, outputStream18);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication20 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication22 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray25 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication22.createFolder(strArray25);
        java.lang.String str27 = findApplication20.findFolderContent("fail_sed_write", strArray25);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication29 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray32 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication29.createFolder(strArray32);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication34 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray37 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream38 = null;
        java.io.OutputStream outputStream39 = null;
        mkdirApplication34.run(strArray37, inputStream38, outputStream39);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication41 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication43 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray46 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream47 = null;
        java.io.OutputStream outputStream48 = null;
        mkdirApplication43.run(strArray46, inputStream47, outputStream48);
        java.lang.String str50 = findApplication41.findFolderContent("", strArray46);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication52 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray59 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream60 = null;
        java.io.OutputStream outputStream61 = null;
        mkdirApplication52.run(strArray59, inputStream60, outputStream61);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication63 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication66 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication68 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray71 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream72 = null;
        java.io.OutputStream outputStream73 = null;
        mkdirApplication68.run(strArray71, inputStream72, outputStream73);
        java.lang.String str75 = findApplication66.findFolderContent("", strArray71);
        java.lang.String str76 = lsApplication63.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray71);
        java.io.InputStream inputStream77 = null;
        java.io.OutputStream outputStream78 = null;
        mkdirApplication52.run(strArray71, inputStream77, outputStream78);
        java.lang.String str80 = findApplication41.findFolderContent("Could not read stream", strArray71);
        java.io.InputStream inputStream81 = null;
        java.io.OutputStream outputStream82 = null;
        mkdirApplication34.run(strArray71, inputStream81, outputStream82);
        java.io.InputStream inputStream84 = null;
        java.io.OutputStream outputStream85 = null;
        mkdirApplication29.run(strArray71, inputStream84, outputStream85);
        java.lang.Class<?> wildcardClass87 = strArray71.getClass();
        java.lang.String str88 = findApplication20.findFolderContent("", strArray71);
        mkdirApplication13.createFolder(strArray71);
        mkdirApplication2.createFolder(strArray71);
        java.lang.String str91 = findApplication0.findFolderContent("Exception Caught", strArray71);
        java.lang.String[] strArray93 = null;
        try {
            java.lang.String str94 = findApplication0.findFolderContent("", strArray93);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: Arguments for findFolderContent are not allowed to be null!");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray9);
        assertNotNull(strArray16);
        assertNotNull(strArray25);
        assertTrue(str27.equals(""));
        assertNotNull(strArray32);
        assertNotNull(strArray37);
        assertNotNull(strArray46);
        assertTrue(str50.equals(""));
        assertNotNull(strArray59);
        assertNotNull(strArray71);
        assertTrue(str75.equals(""));
        assertTrue(str76.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str80.equals(""));
        assertNotNull(wildcardClass87);
        assertTrue(str88.equals(""));
        assertTrue(str91.equals(""));
    }

    @Test
    public void test375() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test375");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication2 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication4.createFolder(strArray7);
        java.lang.String str9 = findApplication2.findFolderContent("fail_sed_write", strArray7);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication11 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray14 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication11.createFolder(strArray14);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication16 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray19 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication16.run(strArray19, inputStream20, outputStream21);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication23 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication25 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray28 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream29 = null;
        java.io.OutputStream outputStream30 = null;
        mkdirApplication25.run(strArray28, inputStream29, outputStream30);
        java.lang.String str32 = findApplication23.findFolderContent("", strArray28);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication34 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray41 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream42 = null;
        java.io.OutputStream outputStream43 = null;
        mkdirApplication34.run(strArray41, inputStream42, outputStream43);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication45 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication48 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication50 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray53 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream54 = null;
        java.io.OutputStream outputStream55 = null;
        mkdirApplication50.run(strArray53, inputStream54, outputStream55);
        java.lang.String str57 = findApplication48.findFolderContent("", strArray53);
        java.lang.String str58 = lsApplication45.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray53);
        java.io.InputStream inputStream59 = null;
        java.io.OutputStream outputStream60 = null;
        mkdirApplication34.run(strArray53, inputStream59, outputStream60);
        java.lang.String str62 = findApplication23.findFolderContent("Could not read stream", strArray53);
        java.io.InputStream inputStream63 = null;
        java.io.OutputStream outputStream64 = null;
        mkdirApplication16.run(strArray53, inputStream63, outputStream64);
        java.io.InputStream inputStream66 = null;
        java.io.OutputStream outputStream67 = null;
        mkdirApplication11.run(strArray53, inputStream66, outputStream67);
        java.lang.Class<?> wildcardClass69 = strArray53.getClass();
        java.lang.String str70 = findApplication2.findFolderContent("", strArray53);
        java.io.InputStream inputStream71 = null;
        java.io.OutputStream outputStream72 = null;
        try {
            sortApplication0.run(strArray53, inputStream71, outputStream72);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: ");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray7);
        assertTrue(str9.equals(""));
        assertNotNull(strArray14);
        assertNotNull(strArray19);
        assertNotNull(strArray28);
        assertTrue(str32.equals(""));
        assertNotNull(strArray41);
        assertNotNull(strArray53);
        assertTrue(str57.equals(""));
        assertTrue(str58.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str62.equals(""));
        assertNotNull(wildcardClass69);
        assertTrue(str70.equals(""));
    }

    @Test
    public void test383() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test383");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication1 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        java.lang.String str11 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray7);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication14 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray21 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        mkdirApplication14.run(strArray21, inputStream22, outputStream23);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication25 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray32 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream33 = null;
        java.io.OutputStream outputStream34 = null;
        mkdirApplication25.run(strArray32, inputStream33, outputStream34);
        java.lang.Class<?> wildcardClass36 = mkdirApplication25.getClass();
        java.lang.String[] strArray41 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream42 = null;
        java.io.OutputStream outputStream43 = null;
        mkdirApplication25.run(strArray41, inputStream42, outputStream43);
        java.io.InputStream inputStream45 = null;
        java.io.OutputStream outputStream46 = null;
        mkdirApplication14.run(strArray41, inputStream45, outputStream46);
        java.lang.String str48 = lsApplication1.listFolderContent((java.lang.Boolean) true, (java.lang.Boolean) true, strArray41);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication51 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication54 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray57 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream58 = null;
        java.io.OutputStream outputStream59 = null;
        mkdirApplication54.run(strArray57, inputStream58, outputStream59);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication61 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication64 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray67 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream68 = null;
        java.io.OutputStream outputStream69 = null;
        mkdirApplication64.run(strArray67, inputStream68, outputStream69);
        java.lang.String str71 = lsApplication61.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray67);
        mkdirApplication54.createFolder(strArray67);
        java.lang.String str73 = lsApplication51.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray67);
        java.lang.String str74 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray67);
        java.io.InputStream inputStream75 = null;
        java.io.OutputStream outputStream76 = null;
        try {
            sedApplication0.run(strArray67, inputStream75, outputStream76);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray7);
        assertTrue(str11.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray21);
        assertNotNull(strArray32);
        assertNotNull(wildcardClass36);
        assertNotNull(strArray41);
        assertTrue(str48.equals("This is a directory\r\nCould not read stream\r\nNull Pointer Exception\r\nException Caught"));
        assertNotNull(strArray57);
        assertNotNull(strArray67);
        assertTrue(str71.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str73.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str74.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test384() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test384");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication13 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication16 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication18 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray21 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        mkdirApplication18.run(strArray21, inputStream22, outputStream23);
        java.lang.String str25 = findApplication16.findFolderContent("", strArray21);
        java.lang.String str26 = lsApplication13.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray21);
        java.io.InputStream inputStream27 = null;
        java.io.OutputStream outputStream28 = null;
        mkdirApplication2.run(strArray21, inputStream27, outputStream28);
        java.lang.String str30 = echoApplication1.constructResult(strArray21);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication31 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray38 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream39 = null;
        java.io.OutputStream outputStream40 = null;
        mkdirApplication31.run(strArray38, inputStream39, outputStream40);
        java.io.InputStream inputStream42 = null;
        java.io.OutputStream outputStream43 = null;
        echoApplication1.run(strArray38, inputStream42, outputStream43);
        java.io.InputStream inputStream45 = null;
        java.io.OutputStream outputStream46 = null;
        try {
            lsApplication0.run(strArray38, inputStream45, outputStream46);
            fail("Expected exception of type java.lang.StringIndexOutOfBoundsException; message: String index out of range: 0");
        } catch (java.lang.StringIndexOutOfBoundsException e) {
        }
        assertNotNull(strArray9);
        assertNotNull(strArray21);
        assertTrue(str25.equals(""));
        assertTrue(str26.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str30.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray38);
    }

    @Test
    public void test386() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test386");
        sg.edu.nus.comp.cs4218.impl.app.PwdApplication pwdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PwdApplication();
        java.lang.String str1 = pwdApplication0.getAbsolutePath();
        java.lang.Class<?> wildcardClass2 = pwdApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        mkdirApplication3.run(strArray10, inputStream11, outputStream12);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication14 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray20 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        mkdirApplication17.run(strArray20, inputStream21, outputStream22);
        java.lang.String str24 = lsApplication14.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray20);
        mkdirApplication3.createFolder(strArray20);
        java.io.InputStream inputStream26 = null;
        java.io.OutputStream outputStream27 = null;
        try {
            pwdApplication0.run(strArray20, inputStream26, outputStream27);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PwdException; message: pwd: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.PwdException e) {
        }
        assertTrue(str1.equals("C:\\"));
        assertNotNull(wildcardClass2);
        assertNotNull(strArray10);
        assertNotNull(strArray20);
        assertTrue(str24.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test388() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test388");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        mkdirApplication1.run(strArray4, inputStream5, outputStream6);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication8 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication10 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication12 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray15 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication12.createFolder(strArray15);
        java.lang.String str17 = findApplication10.findFolderContent("fail_sed_write", strArray15);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication19 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray22 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication19.createFolder(strArray22);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication24 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray27 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream28 = null;
        java.io.OutputStream outputStream29 = null;
        mkdirApplication24.run(strArray27, inputStream28, outputStream29);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication31 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication33 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray36 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream37 = null;
        java.io.OutputStream outputStream38 = null;
        mkdirApplication33.run(strArray36, inputStream37, outputStream38);
        java.lang.String str40 = findApplication31.findFolderContent("", strArray36);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication42 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray49 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream50 = null;
        java.io.OutputStream outputStream51 = null;
        mkdirApplication42.run(strArray49, inputStream50, outputStream51);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication53 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication56 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication58 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray61 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream62 = null;
        java.io.OutputStream outputStream63 = null;
        mkdirApplication58.run(strArray61, inputStream62, outputStream63);
        java.lang.String str65 = findApplication56.findFolderContent("", strArray61);
        java.lang.String str66 = lsApplication53.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray61);
        java.io.InputStream inputStream67 = null;
        java.io.OutputStream outputStream68 = null;
        mkdirApplication42.run(strArray61, inputStream67, outputStream68);
        java.lang.String str70 = findApplication31.findFolderContent("Could not read stream", strArray61);
        java.io.InputStream inputStream71 = null;
        java.io.OutputStream outputStream72 = null;
        mkdirApplication24.run(strArray61, inputStream71, outputStream72);
        java.io.InputStream inputStream74 = null;
        java.io.OutputStream outputStream75 = null;
        mkdirApplication19.run(strArray61, inputStream74, outputStream75);
        java.lang.Class<?> wildcardClass77 = strArray61.getClass();
        java.lang.String str78 = findApplication10.findFolderContent("", strArray61);
        java.lang.String str79 = findApplication8.findFolderContent("", strArray61);
        java.io.InputStream inputStream80 = null;
        java.io.OutputStream outputStream81 = null;
        mkdirApplication1.run(strArray61, inputStream80, outputStream81);
        java.io.InputStream inputStream83 = null;
        java.io.OutputStream outputStream84 = null;
        try {
            sedApplication0.run(strArray61, inputStream83, outputStream84);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray4);
        assertNotNull(strArray15);
        assertTrue(str17.equals(""));
        assertNotNull(strArray22);
        assertNotNull(strArray27);
        assertNotNull(strArray36);
        assertTrue(str40.equals(""));
        assertNotNull(strArray49);
        assertNotNull(strArray61);
        assertTrue(str65.equals(""));
        assertTrue(str66.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str70.equals(""));
        assertNotNull(wildcardClass77);
        assertTrue(str78.equals(""));
        assertTrue(str79.equals(""));
    }

    @Test
    public void test389() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test389");
        sg.edu.nus.comp.cs4218.impl.app.DateApplication dateApplication0 = new sg.edu.nus.comp.cs4218.impl.app.DateApplication();
        try {
            java.lang.String str2 = dateApplication0.getDate("hi!");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.DateException; message: date: Invalid format. Date format must start with '+'");
        } catch (sg.edu.nus.comp.cs4218.exception.DateException e) {
        }
    }

    @Test
    public void test393() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test393");
        sg.edu.nus.comp.cs4218.impl.app.PwdApplication pwdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PwdApplication();
        java.lang.String str1 = pwdApplication0.getAbsolutePath();
        java.lang.Class<?> wildcardClass2 = pwdApplication0.getClass();
        java.lang.String str3 = pwdApplication0.getAbsolutePath();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication4 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication7 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        mkdirApplication7.run(strArray10, inputStream11, outputStream12);
        java.lang.String str14 = lsApplication4.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray10);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication17 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray24 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream25 = null;
        java.io.OutputStream outputStream26 = null;
        mkdirApplication17.run(strArray24, inputStream25, outputStream26);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication28 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray35 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream36 = null;
        java.io.OutputStream outputStream37 = null;
        mkdirApplication28.run(strArray35, inputStream36, outputStream37);
        java.lang.Class<?> wildcardClass39 = mkdirApplication28.getClass();
        java.lang.String[] strArray44 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream45 = null;
        java.io.OutputStream outputStream46 = null;
        mkdirApplication28.run(strArray44, inputStream45, outputStream46);
        java.io.InputStream inputStream48 = null;
        java.io.OutputStream outputStream49 = null;
        mkdirApplication17.run(strArray44, inputStream48, outputStream49);
        java.lang.String str51 = lsApplication4.listFolderContent((java.lang.Boolean) true, (java.lang.Boolean) true, strArray44);
        java.io.InputStream inputStream52 = null;
        java.io.OutputStream outputStream53 = null;
        try {
            pwdApplication0.run(strArray44, inputStream52, outputStream53);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PwdException; message: pwd: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.PwdException e) {
        }
        assertTrue(str1.equals("C:\\"));
        assertNotNull(wildcardClass2);
        assertTrue(str3.equals("C:\\"));
        assertNotNull(strArray10);
        assertTrue(str14.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray24);
        assertNotNull(strArray35);
        assertNotNull(wildcardClass39);
        assertNotNull(strArray44);
        assertTrue(str51.equals("This is a directory\r\nCould not read stream\r\nNull Pointer Exception\r\nException Caught"));
    }

    @Test
    public void test394() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test394");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInFile("Could not read file", "null output stream provided fail_echo_write", (-1), "Exception Caught");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: File doesn't exist.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
    }

    @Test
    public void test395() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test395");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("Could not read file", (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test398() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test398");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.String[] strArray2 = new java.lang.String[] { "Could not read stream" };
        java.io.InputStream inputStream3 = null;
        java.io.OutputStream outputStream4 = null;
        try {
            sortApplication0.run(strArray2, inputStream3, outputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: ");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray2);
    }

    @Test
    public void test399() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test399");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.lang.String[] strArray4 = null;
        try {
            java.lang.String str5 = wcApplication0.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: no argument can be null!");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
    }

    @Test
    public void test400() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test400");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication12 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray19 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication12.run(strArray19, inputStream20, outputStream21);
        java.lang.Class<?> wildcardClass23 = mkdirApplication12.getClass();
        java.lang.String[] strArray28 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream29 = null;
        java.io.OutputStream outputStream30 = null;
        mkdirApplication12.run(strArray28, inputStream29, outputStream30);
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication1.run(strArray28, inputStream32, outputStream33);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication35 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray38 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream39 = null;
        java.io.OutputStream outputStream40 = null;
        mkdirApplication35.run(strArray38, inputStream39, outputStream40);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication42 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication44 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray47 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream48 = null;
        java.io.OutputStream outputStream49 = null;
        mkdirApplication44.run(strArray47, inputStream48, outputStream49);
        java.lang.String str51 = findApplication42.findFolderContent("", strArray47);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication53 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray60 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream61 = null;
        java.io.OutputStream outputStream62 = null;
        mkdirApplication53.run(strArray60, inputStream61, outputStream62);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication64 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication67 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication69 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray72 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream73 = null;
        java.io.OutputStream outputStream74 = null;
        mkdirApplication69.run(strArray72, inputStream73, outputStream74);
        java.lang.String str76 = findApplication67.findFolderContent("", strArray72);
        java.lang.String str77 = lsApplication64.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray72);
        java.io.InputStream inputStream78 = null;
        java.io.OutputStream outputStream79 = null;
        mkdirApplication53.run(strArray72, inputStream78, outputStream79);
        java.lang.String str81 = findApplication42.findFolderContent("Could not read stream", strArray72);
        java.io.InputStream inputStream82 = null;
        java.io.OutputStream outputStream83 = null;
        mkdirApplication35.run(strArray72, inputStream82, outputStream83);
        mkdirApplication1.createFolder(strArray72);
        java.io.InputStream inputStream86 = null;
        java.io.OutputStream outputStream87 = null;
        try {
            wcApplication0.run(strArray72, inputStream86, outputStream87);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
        assertNotNull(strArray8);
        assertNotNull(strArray19);
        assertNotNull(wildcardClass23);
        assertNotNull(strArray28);
        assertNotNull(strArray38);
        assertNotNull(strArray47);
        assertTrue(str51.equals(""));
        assertNotNull(strArray60);
        assertNotNull(strArray72);
        assertTrue(str76.equals(""));
        assertTrue(str77.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str81.equals(""));
    }

    @Test
    public void test407() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test407");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication1.run(strArray8, inputStream9, outputStream10);
        java.lang.Class<?> wildcardClass12 = mkdirApplication1.getClass();
        java.lang.String[] strArray17 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication1.run(strArray17, inputStream18, outputStream19);
        java.io.InputStream inputStream21 = null;
        java.io.OutputStream outputStream22 = null;
        try {
            sortApplication0.run(strArray17, inputStream21, outputStream22);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: ");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray8);
        assertNotNull(wildcardClass12);
        assertNotNull(strArray17);
    }

    @Test
    public void test412() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test412");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        java.lang.Class<?> wildcardClass1 = lsApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication2 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        mkdirApplication5.run(strArray8, inputStream9, outputStream10);
        java.lang.String str12 = lsApplication2.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        java.io.InputStream inputStream13 = null;
        java.io.OutputStream outputStream14 = null;
        try {
            lsApplication0.run(strArray8, inputStream13, outputStream14);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray8);
        assertTrue(str12.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test416() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test416");
        boolean boolean1 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.isInteger("This is a directory Could not read stream Null Pointer Exception Exception Caught");
        assertTrue(boolean1 == false);
    }

    @Test
    public void test417() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test417");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication6 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication6.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication13 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication15 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray18 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        mkdirApplication15.run(strArray18, inputStream19, outputStream20);
        java.lang.String str22 = findApplication13.findFolderContent("", strArray18);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication24 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray31 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication24.run(strArray31, inputStream32, outputStream33);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication35 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication38 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication40 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray43 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream44 = null;
        java.io.OutputStream outputStream45 = null;
        mkdirApplication40.run(strArray43, inputStream44, outputStream45);
        java.lang.String str47 = findApplication38.findFolderContent("", strArray43);
        java.lang.String str48 = lsApplication35.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray43);
        java.io.InputStream inputStream49 = null;
        java.io.OutputStream outputStream50 = null;
        mkdirApplication24.run(strArray43, inputStream49, outputStream50);
        java.lang.String str52 = findApplication13.findFolderContent("Could not read stream", strArray43);
        java.io.InputStream inputStream53 = null;
        java.io.OutputStream outputStream54 = null;
        mkdirApplication6.run(strArray43, inputStream53, outputStream54);
        java.io.InputStream inputStream56 = null;
        java.io.OutputStream outputStream57 = null;
        mkdirApplication1.run(strArray43, inputStream56, outputStream57);
        java.io.InputStream inputStream59 = null;
        java.io.OutputStream outputStream60 = null;
        try {
            cdApplication0.run(strArray43, inputStream59, outputStream60);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: cd only takes one argument");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
        assertNotNull(strArray4);
        assertNotNull(strArray9);
        assertNotNull(strArray18);
        assertTrue(str22.equals(""));
        assertNotNull(strArray31);
        assertNotNull(strArray43);
        assertTrue(str47.equals(""));
        assertTrue(str48.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str52.equals(""));
    }

    @Test
    public void test421() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test421");
        java.io.InputStream inputStream0 = null;
        try {
            java.util.List<java.lang.String> strList4 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.replace(inputStream0, "null output stream provided", "", 100);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test422() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test422");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        mkdirApplication2.run(strArray5, inputStream6, outputStream7);
        java.lang.String str9 = findApplication0.findFolderContent("", strArray5);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication11 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication14 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication16 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray19 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream20 = null;
        java.io.OutputStream outputStream21 = null;
        mkdirApplication16.run(strArray19, inputStream20, outputStream21);
        java.lang.String str23 = findApplication14.findFolderContent("", strArray19);
        java.lang.String str24 = lsApplication11.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray19);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication27 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray30 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication27.createFolder(strArray30);
        java.lang.String str32 = lsApplication11.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray30);
        java.lang.String str33 = findApplication0.findFolderContent("hi!", strArray30);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication34 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray41 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream42 = null;
        java.io.OutputStream outputStream43 = null;
        mkdirApplication34.run(strArray41, inputStream42, outputStream43);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication45 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication48 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication50 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray53 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream54 = null;
        java.io.OutputStream outputStream55 = null;
        mkdirApplication50.run(strArray53, inputStream54, outputStream55);
        java.lang.String str57 = findApplication48.findFolderContent("", strArray53);
        java.lang.String str58 = lsApplication45.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray53);
        java.io.InputStream inputStream59 = null;
        java.io.OutputStream outputStream60 = null;
        mkdirApplication34.run(strArray53, inputStream59, outputStream60);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication62 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray65 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication62.createFolder(strArray65);
        java.io.InputStream inputStream67 = null;
        java.io.OutputStream outputStream68 = null;
        mkdirApplication34.run(strArray65, inputStream67, outputStream68);
        java.io.InputStream inputStream70 = null;
        java.io.OutputStream outputStream71 = null;
        try {
            findApplication0.run(strArray65, inputStream70, outputStream71);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: output stream is null");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray5);
        assertTrue(str9.equals(""));
        assertNotNull(strArray19);
        assertTrue(str23.equals(""));
        assertTrue(str24.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray30);
        assertTrue(str32.equals("fail_echo_write:\r\n\r\n\r\nhi!:"));
        assertTrue(str33.equals(""));
        assertNotNull(strArray41);
        assertNotNull(strArray53);
        assertTrue(str57.equals(""));
        assertTrue(str58.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray65);
    }

    @Test
    public void test423() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test423");
        boolean boolean1 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.isInteger("Could not read file");
        assertTrue(boolean1 == false);
    }

    @Test
    public void test424() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test424");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInFile("fail_echo_write", "hi!", (int) (short) 0, "File is null.");
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: File doesn't exist.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
    }

    @Test
    public void test429() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test429");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("File doesn't exist.", "null output stream provided", (int) (short) -1, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test435() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test435");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication13 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication16 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication18 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray21 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        mkdirApplication18.run(strArray21, inputStream22, outputStream23);
        java.lang.String str25 = findApplication16.findFolderContent("", strArray21);
        java.lang.String str26 = lsApplication13.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray21);
        java.io.InputStream inputStream27 = null;
        java.io.OutputStream outputStream28 = null;
        mkdirApplication2.run(strArray21, inputStream27, outputStream28);
        java.lang.String str30 = echoApplication1.constructResult(strArray21);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication31 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication33 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray36 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream37 = null;
        java.io.OutputStream outputStream38 = null;
        mkdirApplication33.run(strArray36, inputStream37, outputStream38);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication40 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication43 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray46 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream47 = null;
        java.io.OutputStream outputStream48 = null;
        mkdirApplication43.run(strArray46, inputStream47, outputStream48);
        java.lang.String str50 = lsApplication40.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray46);
        mkdirApplication33.createFolder(strArray46);
        java.lang.String str52 = findApplication31.findFolderContent("null output stream provided fail_echo_write", strArray46);
        java.io.InputStream inputStream53 = null;
        java.io.OutputStream outputStream54 = null;
        echoApplication1.run(strArray46, inputStream53, outputStream54);
        java.io.InputStream inputStream56 = null;
        java.io.OutputStream outputStream57 = null;
        try {
            sedApplication0.run(strArray46, inputStream56, outputStream57);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray9);
        assertNotNull(strArray21);
        assertTrue(str25.equals(""));
        assertTrue(str26.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str30.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray36);
        assertNotNull(strArray46);
        assertTrue(str50.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str52.equals(""));
    }

    @Test
    public void test436() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test436");
        sg.edu.nus.comp.cs4218.impl.app.DateApplication dateApplication0 = new sg.edu.nus.comp.cs4218.impl.app.DateApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication1 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication1.createFolder(strArray4);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication6 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication7 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray14 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        mkdirApplication7.run(strArray14, inputStream15, outputStream16);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication18 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication21 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication23 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray26 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream27 = null;
        java.io.OutputStream outputStream28 = null;
        mkdirApplication23.run(strArray26, inputStream27, outputStream28);
        java.lang.String str30 = findApplication21.findFolderContent("", strArray26);
        java.lang.String str31 = lsApplication18.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray26);
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication7.run(strArray26, inputStream32, outputStream33);
        java.lang.String str35 = echoApplication6.constructResult(strArray26);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication36 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray43 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream44 = null;
        java.io.OutputStream outputStream45 = null;
        mkdirApplication36.run(strArray43, inputStream44, outputStream45);
        java.io.InputStream inputStream47 = null;
        java.io.OutputStream outputStream48 = null;
        echoApplication6.run(strArray43, inputStream47, outputStream48);
        java.io.InputStream inputStream50 = null;
        java.io.OutputStream outputStream51 = null;
        mkdirApplication1.run(strArray43, inputStream50, outputStream51);
        java.lang.Class<?> wildcardClass53 = strArray43.getClass();
        java.io.InputStream inputStream54 = null;
        java.io.OutputStream outputStream55 = null;
        try {
            dateApplication0.run(strArray43, inputStream54, outputStream55);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.DateException; message: date: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.DateException e) {
        }
        assertNotNull(strArray4);
        assertNotNull(strArray14);
        assertNotNull(strArray26);
        assertTrue(str30.equals(""));
        assertTrue(str31.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str35.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray43);
        assertNotNull(wildcardClass53);
    }

    @Test
    public void test439() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test439");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray8 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication5.createFolder(strArray8);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication10 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication11 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray18 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream19 = null;
        java.io.OutputStream outputStream20 = null;
        mkdirApplication11.run(strArray18, inputStream19, outputStream20);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication22 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication25 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication27 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray30 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream31 = null;
        java.io.OutputStream outputStream32 = null;
        mkdirApplication27.run(strArray30, inputStream31, outputStream32);
        java.lang.String str34 = findApplication25.findFolderContent("", strArray30);
        java.lang.String str35 = lsApplication22.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray30);
        java.io.InputStream inputStream36 = null;
        java.io.OutputStream outputStream37 = null;
        mkdirApplication11.run(strArray30, inputStream36, outputStream37);
        java.lang.String str39 = echoApplication10.constructResult(strArray30);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication40 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray47 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream48 = null;
        java.io.OutputStream outputStream49 = null;
        mkdirApplication40.run(strArray47, inputStream48, outputStream49);
        java.io.InputStream inputStream51 = null;
        java.io.OutputStream outputStream52 = null;
        echoApplication10.run(strArray47, inputStream51, outputStream52);
        java.io.InputStream inputStream54 = null;
        java.io.OutputStream outputStream55 = null;
        mkdirApplication5.run(strArray47, inputStream54, outputStream55);
        try {
            java.lang.String str57 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray47);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray8);
        assertNotNull(strArray18);
        assertNotNull(strArray30);
        assertTrue(str34.equals(""));
        assertTrue(str35.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str39.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray47);
    }

    @Test
    public void test445() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test445");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication2 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication4 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray12 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream13 = null;
        java.io.OutputStream outputStream14 = null;
        mkdirApplication5.run(strArray12, inputStream13, outputStream14);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication16 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication19 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication21 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray24 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream25 = null;
        java.io.OutputStream outputStream26 = null;
        mkdirApplication21.run(strArray24, inputStream25, outputStream26);
        java.lang.String str28 = findApplication19.findFolderContent("", strArray24);
        java.lang.String str29 = lsApplication16.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray24);
        java.io.InputStream inputStream30 = null;
        java.io.OutputStream outputStream31 = null;
        mkdirApplication5.run(strArray24, inputStream30, outputStream31);
        java.lang.String str33 = echoApplication4.constructResult(strArray24);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication34 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray41 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream42 = null;
        java.io.OutputStream outputStream43 = null;
        mkdirApplication34.run(strArray41, inputStream42, outputStream43);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication45 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray52 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream53 = null;
        java.io.OutputStream outputStream54 = null;
        mkdirApplication45.run(strArray52, inputStream53, outputStream54);
        java.lang.Class<?> wildcardClass56 = mkdirApplication45.getClass();
        java.lang.String[] strArray61 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream62 = null;
        java.io.OutputStream outputStream63 = null;
        mkdirApplication45.run(strArray61, inputStream62, outputStream63);
        java.io.InputStream inputStream65 = null;
        java.io.OutputStream outputStream66 = null;
        mkdirApplication34.run(strArray61, inputStream65, outputStream66);
        java.io.InputStream inputStream68 = null;
        java.io.OutputStream outputStream69 = null;
        echoApplication4.run(strArray61, inputStream68, outputStream69);
        java.lang.String str71 = findApplication2.findFolderContent("fail_echo_write hi!", strArray61);
        java.io.InputStream inputStream72 = null;
        java.io.OutputStream outputStream73 = null;
        try {
            sortApplication0.run(strArray61, inputStream72, outputStream73);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: ");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray12);
        assertNotNull(strArray24);
        assertTrue(str28.equals(""));
        assertTrue(str29.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str33.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray41);
        assertNotNull(strArray52);
        assertNotNull(wildcardClass56);
        assertNotNull(strArray61);
        assertTrue(str71.equals(""));
    }

    @Test
    public void test446() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test446");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        cdApplication0.changeToDirectory("");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication3 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray11 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        mkdirApplication4.run(strArray11, inputStream12, outputStream13);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication15 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication18 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication20 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray23 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream24 = null;
        java.io.OutputStream outputStream25 = null;
        mkdirApplication20.run(strArray23, inputStream24, outputStream25);
        java.lang.String str27 = findApplication18.findFolderContent("", strArray23);
        java.lang.String str28 = lsApplication15.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray23);
        java.io.InputStream inputStream29 = null;
        java.io.OutputStream outputStream30 = null;
        mkdirApplication4.run(strArray23, inputStream29, outputStream30);
        java.lang.String str32 = echoApplication3.constructResult(strArray23);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication33 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication35 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray38 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream39 = null;
        java.io.OutputStream outputStream40 = null;
        mkdirApplication35.run(strArray38, inputStream39, outputStream40);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication42 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication45 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray48 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream49 = null;
        java.io.OutputStream outputStream50 = null;
        mkdirApplication45.run(strArray48, inputStream49, outputStream50);
        java.lang.String str52 = lsApplication42.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray48);
        mkdirApplication35.createFolder(strArray48);
        java.lang.String str54 = findApplication33.findFolderContent("null output stream provided fail_echo_write", strArray48);
        java.io.InputStream inputStream55 = null;
        java.io.OutputStream outputStream56 = null;
        echoApplication3.run(strArray48, inputStream55, outputStream56);
        java.io.InputStream inputStream58 = null;
        java.io.OutputStream outputStream59 = null;
        try {
            cdApplication0.run(strArray48, inputStream58, outputStream59);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: cd only takes one argument");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        }
        assertNotNull(strArray11);
        assertNotNull(strArray23);
        assertTrue(str27.equals(""));
        assertTrue(str28.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str32.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray38);
        assertNotNull(strArray48);
        assertTrue(str52.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str54.equals(""));
    }

    @Test
    public void test447() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test447");
        java.io.InputStream inputStream0 = null;
        try {
            java.util.List<java.lang.String> strList4 = sg.edu.nus.comp.cs4218.impl.app.SedApplication.replace(inputStream0, "Could not write to output stream", "fail_echo_write", 0);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test452() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test452");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication2 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        mkdirApplication3.run(strArray10, inputStream11, outputStream12);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication14 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication17 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication19 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray22 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream23 = null;
        java.io.OutputStream outputStream24 = null;
        mkdirApplication19.run(strArray22, inputStream23, outputStream24);
        java.lang.String str26 = findApplication17.findFolderContent("", strArray22);
        java.lang.String str27 = lsApplication14.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray22);
        java.io.InputStream inputStream28 = null;
        java.io.OutputStream outputStream29 = null;
        mkdirApplication3.run(strArray22, inputStream28, outputStream29);
        java.lang.String str31 = echoApplication2.constructResult(strArray22);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication32 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray39 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream40 = null;
        java.io.OutputStream outputStream41 = null;
        mkdirApplication32.run(strArray39, inputStream40, outputStream41);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication43 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray50 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream51 = null;
        java.io.OutputStream outputStream52 = null;
        mkdirApplication43.run(strArray50, inputStream51, outputStream52);
        java.lang.Class<?> wildcardClass54 = mkdirApplication43.getClass();
        java.lang.String[] strArray59 = new java.lang.String[] { "This is a directory", "Could not read stream", "Null Pointer Exception", "Exception Caught" };
        java.io.InputStream inputStream60 = null;
        java.io.OutputStream outputStream61 = null;
        mkdirApplication43.run(strArray59, inputStream60, outputStream61);
        java.io.InputStream inputStream63 = null;
        java.io.OutputStream outputStream64 = null;
        mkdirApplication32.run(strArray59, inputStream63, outputStream64);
        java.io.InputStream inputStream66 = null;
        java.io.OutputStream outputStream67 = null;
        echoApplication2.run(strArray59, inputStream66, outputStream67);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication69 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray72 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication69.createFolder(strArray72);
        java.lang.String str74 = echoApplication2.constructResult(strArray72);
        java.io.InputStream inputStream75 = null;
        java.io.OutputStream outputStream76 = null;
        try {
            sortApplication0.run(strArray72, inputStream75, outputStream76);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: ");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray10);
        assertNotNull(strArray22);
        assertTrue(str26.equals(""));
        assertTrue(str27.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str31.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray39);
        assertNotNull(strArray50);
        assertNotNull(wildcardClass54);
        assertNotNull(strArray59);
        assertNotNull(strArray72);
        assertTrue(str74.equals("fail_echo_write hi!"));
    }

    @Test
    public void test453() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test453");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.lang.Class<?> wildcardClass1 = sortApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication5 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray12 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream13 = null;
        java.io.OutputStream outputStream14 = null;
        mkdirApplication5.run(strArray12, inputStream13, outputStream14);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication16 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication19 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication21 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray24 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream25 = null;
        java.io.OutputStream outputStream26 = null;
        mkdirApplication21.run(strArray24, inputStream25, outputStream26);
        java.lang.String str28 = findApplication19.findFolderContent("", strArray24);
        java.lang.String str29 = lsApplication16.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray24);
        java.io.InputStream inputStream30 = null;
        java.io.OutputStream outputStream31 = null;
        mkdirApplication5.run(strArray24, inputStream30, outputStream31);
        try {
            java.lang.String str33 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray24);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray12);
        assertNotNull(strArray24);
        assertTrue(str28.equals(""));
        assertTrue(str29.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test454() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test454");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication1 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication4.run(strArray7, inputStream8, outputStream9);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication11 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication14 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication14.run(strArray17, inputStream18, outputStream19);
        java.lang.String str21 = lsApplication11.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray17);
        mkdirApplication4.createFolder(strArray17);
        java.lang.String str23 = lsApplication1.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray17);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication26 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication28 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray31 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream32 = null;
        java.io.OutputStream outputStream33 = null;
        mkdirApplication28.run(strArray31, inputStream32, outputStream33);
        java.lang.String str35 = findApplication26.findFolderContent("", strArray31);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication37 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray40 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream41 = null;
        java.io.OutputStream outputStream42 = null;
        mkdirApplication37.run(strArray40, inputStream41, outputStream42);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication44 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication47 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray50 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream51 = null;
        java.io.OutputStream outputStream52 = null;
        mkdirApplication47.run(strArray50, inputStream51, outputStream52);
        java.lang.String str54 = lsApplication44.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray50);
        mkdirApplication37.createFolder(strArray50);
        java.lang.String str56 = findApplication26.findFolderContent("null input file provided", strArray50);
        java.lang.String str57 = lsApplication1.listFolderContent((java.lang.Boolean) true, (java.lang.Boolean) true, strArray50);
        java.io.InputStream inputStream58 = null;
        java.io.OutputStream outputStream59 = null;
        try {
            wcApplication0.run(strArray50, inputStream58, outputStream59);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
        assertNotNull(strArray7);
        assertNotNull(strArray17);
        assertTrue(str21.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str23.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray31);
        assertTrue(str35.equals(""));
        assertNotNull(strArray40);
        assertNotNull(strArray50);
        assertTrue(str54.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str56.equals(""));
        assertTrue(str57.equals("null output stream provided\r\nfail_echo_write"));
    }

    @Test
    public void test462() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test462");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromFiles("This is a directory Could not read stream Null Pointer Exception Exception Caught", (java.lang.Boolean) true, (java.lang.Boolean) false, strArray4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: filesNames is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test466() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test466");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        mkdirApplication2.run(strArray5, inputStream6, outputStream7);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication9 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication10 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication10.run(strArray17, inputStream18, outputStream19);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication21 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication24 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication26 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray29 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream30 = null;
        java.io.OutputStream outputStream31 = null;
        mkdirApplication26.run(strArray29, inputStream30, outputStream31);
        java.lang.String str33 = findApplication24.findFolderContent("", strArray29);
        java.lang.String str34 = lsApplication21.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray29);
        java.io.InputStream inputStream35 = null;
        java.io.OutputStream outputStream36 = null;
        mkdirApplication10.run(strArray29, inputStream35, outputStream36);
        java.lang.String str38 = echoApplication9.constructResult(strArray29);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication39 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray46 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream47 = null;
        java.io.OutputStream outputStream48 = null;
        mkdirApplication39.run(strArray46, inputStream47, outputStream48);
        java.io.InputStream inputStream50 = null;
        java.io.OutputStream outputStream51 = null;
        echoApplication9.run(strArray46, inputStream50, outputStream51);
        mkdirApplication2.createFolder(strArray46);
        java.io.InputStream inputStream54 = null;
        java.io.OutputStream outputStream55 = null;
        echoApplication1.run(strArray46, inputStream54, outputStream55);
        java.io.InputStream inputStream57 = null;
        java.io.OutputStream outputStream58 = null;
        try {
            sedApplication0.run(strArray46, inputStream57, outputStream58);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray5);
        assertNotNull(strArray17);
        assertNotNull(strArray29);
        assertTrue(str33.equals(""));
        assertTrue(str34.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str38.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray46);
    }

    @Test
    public void test467() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test467");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray11 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        mkdirApplication4.run(strArray11, inputStream12, outputStream13);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication15 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication18 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication20 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray23 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream24 = null;
        java.io.OutputStream outputStream25 = null;
        mkdirApplication20.run(strArray23, inputStream24, outputStream25);
        java.lang.String str27 = findApplication18.findFolderContent("", strArray23);
        java.lang.String str28 = lsApplication15.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray23);
        java.io.InputStream inputStream29 = null;
        java.io.OutputStream outputStream30 = null;
        mkdirApplication4.run(strArray23, inputStream29, outputStream30);
        java.lang.Class<?> wildcardClass32 = mkdirApplication4.getClass();
        java.lang.String[] strArray34 = new java.lang.String[] { "This is a directory Could not read stream Null Pointer Exception Exception Caught" };
        java.io.InputStream inputStream35 = null;
        java.io.OutputStream outputStream36 = null;
        mkdirApplication4.run(strArray34, inputStream35, outputStream36);
        try {
            java.lang.String str38 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray34);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray11);
        assertNotNull(strArray23);
        assertTrue(str27.equals(""));
        assertTrue(str28.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(wildcardClass32);
        assertNotNull(strArray34);
    }

    @Test
    public void test468() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test468");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication4.createFolder(strArray7);
        try {
            java.lang.String str9 = grepApplication0.grepFromFiles("fail_echo_empty_params", (java.lang.Boolean) true, (java.lang.Boolean) false, strArray7);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray7);
    }

    @Test
    public void test469() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test469");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray11 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        mkdirApplication4.run(strArray11, inputStream12, outputStream13);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication15 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication18 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication20 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray23 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream24 = null;
        java.io.OutputStream outputStream25 = null;
        mkdirApplication20.run(strArray23, inputStream24, outputStream25);
        java.lang.String str27 = findApplication18.findFolderContent("", strArray23);
        java.lang.String str28 = lsApplication15.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray23);
        java.io.InputStream inputStream29 = null;
        java.io.OutputStream outputStream30 = null;
        mkdirApplication4.run(strArray23, inputStream29, outputStream30);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication32 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray35 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication32.createFolder(strArray35);
        java.io.InputStream inputStream37 = null;
        java.io.OutputStream outputStream38 = null;
        mkdirApplication4.run(strArray35, inputStream37, outputStream38);
        java.lang.String[] strArray45 = new java.lang.String[] { "null input file provided", "This is a directory\r\nCould not read stream\r\nNull Pointer Exception\r\nException Caught", "fail_sed_write", "null output stream provided", "Exception Caught" };
        mkdirApplication4.createFolder(strArray45);
        try {
            java.lang.String str47 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray45);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray11);
        assertNotNull(strArray23);
        assertTrue(str27.equals(""));
        assertTrue(str28.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray35);
        assertNotNull(strArray45);
    }

    @Test
    public void test470() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test470");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sortApplication0.sortFromStdin((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test471() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test471");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.lang.String[] strArray1 = new java.lang.String[] {};
        java.io.InputStream inputStream2 = null;
        java.io.OutputStream outputStream3 = null;
        try {
            grepApplication0.run(strArray1, inputStream2, outputStream3);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: output stream is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
        assertNotNull(strArray1);
    }

    @Test
    public void test472() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test472");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.lang.Class<?> wildcardClass1 = catApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication2 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication5 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication7 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray10 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream11 = null;
        java.io.OutputStream outputStream12 = null;
        mkdirApplication7.run(strArray10, inputStream11, outputStream12);
        java.lang.String str14 = findApplication5.findFolderContent("", strArray10);
        java.lang.String str15 = lsApplication2.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray10);
        java.io.InputStream inputStream16 = null;
        java.io.OutputStream outputStream17 = null;
        try {
            catApplication0.run(strArray10, inputStream16, outputStream17);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(wildcardClass1);
        assertNotNull(strArray10);
        assertTrue(str14.equals(""));
        assertTrue(str15.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
    }

    @Test
    public void test475() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test475");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("Exception Caught", "This is a directory Could not read stream Null Pointer Exception Exception Caught", (int) 'a', inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test485() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test485");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = grepApplication0.grepFromStdin("null output stream provided", (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream4);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: Stdin is null!");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
        }
    }

    @Test
    public void test490() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test490");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication4 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication4.createFolder(strArray7);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication9 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication10 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication10.run(strArray17, inputStream18, outputStream19);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication21 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication24 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication26 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray29 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream30 = null;
        java.io.OutputStream outputStream31 = null;
        mkdirApplication26.run(strArray29, inputStream30, outputStream31);
        java.lang.String str33 = findApplication24.findFolderContent("", strArray29);
        java.lang.String str34 = lsApplication21.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray29);
        java.io.InputStream inputStream35 = null;
        java.io.OutputStream outputStream36 = null;
        mkdirApplication10.run(strArray29, inputStream35, outputStream36);
        java.lang.String str38 = echoApplication9.constructResult(strArray29);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication39 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray46 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream47 = null;
        java.io.OutputStream outputStream48 = null;
        mkdirApplication39.run(strArray46, inputStream47, outputStream48);
        java.io.InputStream inputStream50 = null;
        java.io.OutputStream outputStream51 = null;
        echoApplication9.run(strArray46, inputStream50, outputStream51);
        java.io.InputStream inputStream53 = null;
        java.io.OutputStream outputStream54 = null;
        mkdirApplication4.run(strArray46, inputStream53, outputStream54);
        try {
            java.lang.String str56 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray46);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: IO not working");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
        }
        assertNotNull(strArray7);
        assertNotNull(strArray17);
        assertNotNull(strArray29);
        assertTrue(str33.equals(""));
        assertTrue(str34.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str38.equals("null output stream provided fail_echo_write"));
        assertNotNull(strArray46);
    }

    @Test
    public void test493() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test493");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication4 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication6 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication6.run(strArray9, inputStream10, outputStream11);
        java.lang.String str13 = findApplication4.findFolderContent("", strArray9);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication15 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication18 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication20 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray23 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream24 = null;
        java.io.OutputStream outputStream25 = null;
        mkdirApplication20.run(strArray23, inputStream24, outputStream25);
        java.lang.String str27 = findApplication18.findFolderContent("", strArray23);
        java.lang.String str28 = lsApplication15.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray23);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication31 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray34 = new java.lang.String[] { "fail_echo_write", "hi!" };
        mkdirApplication31.createFolder(strArray34);
        java.lang.String str36 = lsApplication15.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) true, strArray34);
        java.lang.String str37 = findApplication4.findFolderContent("hi!", strArray34);
        try {
            java.lang.String str38 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray34);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: File not found!");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
        }
        assertNotNull(strArray9);
        assertTrue(str13.equals(""));
        assertNotNull(strArray23);
        assertTrue(str27.equals(""));
        assertTrue(str28.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray34);
        assertTrue(str36.equals("fail_echo_write:\r\n\r\n\r\nhi!:"));
        assertTrue(str37.equals(""));
    }

    @Test
    public void test494() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test494");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        java.io.InputStream inputStream4 = null;
        try {
            java.lang.String str5 = sedApplication0.replaceSubstringInStdin("null output stream provided", "File is null.", 10, inputStream4);
            fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test496() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test496");
        sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication1 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication3 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        mkdirApplication3.run(strArray6, inputStream7, outputStream8);
        java.lang.String str10 = findApplication1.findFolderContent("", strArray6);
        java.lang.Class<?> wildcardClass11 = strArray6.getClass();
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        try {
            sedApplication0.run(strArray6, inputStream12, outputStream13);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException; message: sed: Invalid syntax.");
        } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
        }
        assertNotNull(strArray6);
        assertTrue(str10.equals(""));
        assertNotNull(wildcardClass11);
    }

    @Test
    public void test497() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test497");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray9 = new java.lang.String[] { "Terminate process.", "fail_echo", "File doesn't exist.", "fail_echo", "", "File doesn't exist." };
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        mkdirApplication2.run(strArray9, inputStream10, outputStream11);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication13 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication16 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication18 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray21 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        mkdirApplication18.run(strArray21, inputStream22, outputStream23);
        java.lang.String str25 = findApplication16.findFolderContent("", strArray21);
        java.lang.String str26 = lsApplication13.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray21);
        java.io.InputStream inputStream27 = null;
        java.io.OutputStream outputStream28 = null;
        mkdirApplication2.run(strArray21, inputStream27, outputStream28);
        java.lang.String str30 = echoApplication1.constructResult(strArray21);
        java.io.InputStream inputStream31 = null;
        java.io.OutputStream outputStream32 = null;
        try {
            catApplication0.run(strArray21, inputStream31, outputStream32);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
        }
        assertNotNull(strArray9);
        assertNotNull(strArray21);
        assertTrue(str25.equals(""));
        assertTrue(str26.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str30.equals("null output stream provided fail_echo_write"));
    }

    @Test
    public void test498() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test498");
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication0 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication2 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        mkdirApplication2.run(strArray5, inputStream6, outputStream7);
        java.lang.String str9 = findApplication0.findFolderContent("", strArray5);
        java.lang.Class<?> wildcardClass10 = findApplication0.getClass();
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication11 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication14 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray17 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream18 = null;
        java.io.OutputStream outputStream19 = null;
        mkdirApplication14.run(strArray17, inputStream18, outputStream19);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication21 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication24 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray27 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream28 = null;
        java.io.OutputStream outputStream29 = null;
        mkdirApplication24.run(strArray27, inputStream28, outputStream29);
        java.lang.String str31 = lsApplication21.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray27);
        mkdirApplication14.createFolder(strArray27);
        java.lang.String str33 = lsApplication11.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray27);
        sg.edu.nus.comp.cs4218.impl.app.FindApplication findApplication36 = new sg.edu.nus.comp.cs4218.impl.app.FindApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication38 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray41 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream42 = null;
        java.io.OutputStream outputStream43 = null;
        mkdirApplication38.run(strArray41, inputStream42, outputStream43);
        java.lang.String str45 = findApplication36.findFolderContent("", strArray41);
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication47 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray50 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream51 = null;
        java.io.OutputStream outputStream52 = null;
        mkdirApplication47.run(strArray50, inputStream51, outputStream52);
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication54 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication57 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        java.lang.String[] strArray60 = new java.lang.String[] { "null output stream provided", "fail_echo_write" };
        java.io.InputStream inputStream61 = null;
        java.io.OutputStream outputStream62 = null;
        mkdirApplication57.run(strArray60, inputStream61, outputStream62);
        java.lang.String str64 = lsApplication54.listFolderContent((java.lang.Boolean) false, (java.lang.Boolean) false, strArray60);
        mkdirApplication47.createFolder(strArray60);
        java.lang.String str66 = findApplication36.findFolderContent("null input file provided", strArray60);
        java.lang.String str67 = lsApplication11.listFolderContent((java.lang.Boolean) true, (java.lang.Boolean) true, strArray60);
        java.io.InputStream inputStream68 = null;
        java.io.OutputStream outputStream69 = null;
        try {
            findApplication0.run(strArray60, inputStream68, outputStream69);
            fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.FindException; message: find: output stream is null");
        } catch (sg.edu.nus.comp.cs4218.exception.FindException e) {
        }
        assertNotNull(strArray5);
        assertTrue(str9.equals(""));
        assertNotNull(wildcardClass10);
        assertNotNull(strArray17);
        assertNotNull(strArray27);
        assertTrue(str31.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str33.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertNotNull(strArray41);
        assertTrue(str45.equals(""));
        assertNotNull(strArray50);
        assertNotNull(strArray60);
        assertTrue(str64.equals("null output stream provided:\r\n\r\n\r\nfail_echo_write:"));
        assertTrue(str66.equals(""));
        assertTrue(str67.equals("null output stream provided\r\nfail_echo_write"));
    }
}
