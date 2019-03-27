package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.SortInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.util.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings({"PMD.ShortVariable", "PMD.LongVariable"})
public class SortApplication implements SortInterface {

    public static final String ERR_NULL_ARGS = "Null Args";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_READING_FILE = "Could not read file";
    public static final String ERR_INVALID_FLAG = "Invalid flags";

    public static final boolean DEFAULT_FIRST_WORD_NUMBER = false;
    public static final boolean DEFAULT_REVERSE_ORDER = false;
    public static final boolean DEFAULT_CASE_INDEPENDENT = false;


    private boolean isFirstWordNumber = DEFAULT_FIRST_WORD_NUMBER;
    private boolean isReverseOrder = DEFAULT_REVERSE_ORDER;
    private boolean isCaseIndependent = DEFAULT_CASE_INDEPENDENT;

    @Override
    public String sortFromFiles(Boolean isFirstWordNumber, Boolean isReverseOrder, Boolean isCaseIndependent, String... fileName) throws Exception {

        ArrayList<String> lineList = new ArrayList<String>();
        for (String file : fileName) {
            try  {
                InputStream fis = IOUtils.openInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line;
                while ((line = br.readLine()) != null) {
                    lineList.add(line);
                }
                IOUtils.closeInputStream(fis);
            } catch (ShellException se) {
                System.out.println(new SortException(fileName + ": " + ERR_READING_FILE).getMessage());
            }
        }

        return sortStrList(isFirstWordNumber, isReverseOrder, isCaseIndependent, lineList);
    }

    @Override
    public String sortFromStdin(Boolean isFirstWordNumber, Boolean isReverseOrder, Boolean isCaseIndependent, InputStream stdin) throws Exception {
        ArrayList<String> lineList = new ArrayList<String>();
        String line;

        BufferedReader bf = new BufferedReader(new InputStreamReader(stdin));
        while ((line = bf.readLine()) != null) {
            if (line.equals("")) {
                break;
            }
            lineList.add(line);
        }

        return sortStrList(isFirstWordNumber, isReverseOrder, isCaseIndependent, lineList);
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if (args == null) {
            throw new SortException(ERR_NULL_ARGS);
        }
        if (stdout == null) {
            throw new SortException(ERR_NULL_STREAMS);
        }
        if (stdin == null) {
            stdin = System.in;
        }

        try {
            ArrayList<String> processedArgs = processArgs(args);
            if (processedArgs.size() == 0) {
                stdout.write(sortFromStdin(isFirstWordNumber, isReverseOrder, isCaseIndependent, stdin).getBytes());
            } else {
                String [] fileNames = processedArgs.toArray(new String[0]);
                stdout.write(sortFromFiles(isFirstWordNumber, isReverseOrder, isCaseIndependent, fileNames).getBytes());
            }
        } catch (SortException se) {
            throw se;
        } catch (Exception e) {
            throw new SortException(e.getMessage());
        }
    }

    private String sortStrList(Boolean isFirstWordNumber, Boolean isReverseOrder, Boolean isCaseIndependent, ArrayList<String> lineList) {
        StringBuilder sb = new StringBuilder();

        if (isFirstWordNumber) {
            Collections.sort(lineList, new IsFirstWordNumberComparator());
        } else if (isCaseIndependent && !isFirstWordNumber) {
            Collections.sort(lineList, new IsCaseIndependentComparator());
        } else {
            Collections.sort(lineList);
        }

        if (isReverseOrder) {
            Collections.reverse(lineList);
        }
        for (String sortedLine : lineList) {
            sb.append(sortedLine + System.lineSeparator());
        }

        return sb.toString();
    }


    private ArrayList<String> processArgs(String[] args) throws SortException{
        ArrayList<String> processedArgs = new ArrayList<String>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                char[] charArray = args[i].toCharArray();
                if (charArray.length < 2) throw new SortException(ERR_INVALID_FLAG);
                else {
                    for (int arrayIndex = 1; arrayIndex < charArray.length; arrayIndex ++) {
                        switch (charArray[arrayIndex]) {
                            case 'n' :
                                isFirstWordNumber = true;
                                break;
                            case 'r' :
                                isReverseOrder = true;
                                break;
                            case 'f':
                                isCaseIndependent = true;
                                break;

                                default:
                                    throw new SortException(ERR_INVALID_FLAG);
                        }
                    }
                }

            } else {
                processedArgs.add(args[i]);
            }
        }
        return processedArgs;
    }
}