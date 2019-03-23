package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.SortInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.util.*;

public class SortApplication implements SortInterface{
    /**
     * Returns string containing the orders of the lines of the specified file
     * 
     * @param isFirstWordNumber Boolean option to treat the first word of a line as a number
     * @param isReverseOrder    Boolean option to sort in reverse order
     * @param isCaseIndependent Boolean option to perform case-independent sorting
     * @param fileName          Array of String of file names
     * @return
     * @throws Exception
     */
    public String sortFromFiles(Boolean isFirstWordNumber, Boolean isReverseOrder, Boolean isCaseIndependent,
                         String... fileName) throws SortException{
        List<String> list = new ArrayList();
        try {
            for (String file: fileName){
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String string;
                while ((string = bufferedReader.readLine()) != null){
                    if (isFirstWordNumber && !StringUtils.isNumberic(string.split(" ")[0])){
                        throw new SortException("");
                    }
                    list.add(string);
                }
                bufferedReader.close();
                fileReader.close();
            }
        } catch (IOException e) {
            throw (SortException) new SortException("IO failure during sortFromFiles!").initCause(e);
        }

        sortList(list, isFirstWordNumber, isCaseIndependent);
        if (isReverseOrder){
            Collections.reverse(list);
        }
        return String.join(StringUtils.STRING_NEWLINE,list.toArray(new String[0]));
    }

    /**
     * Returns string containing the orders of the lines from the standard input
     *
     * @param isFirstWordNumber Boolean option to treat the first word of a line as a number
     * @param isReverseOrder    Boolean option to sort in reverse order
     * @param isCaseIndependent Boolean option to perform case-independent sorting
     * @param stdin             InputStream containing arguments from Stdin
     * @return
     * @throws Exception
     */
    public String sortFromStdin(Boolean isFirstWordNumber, Boolean isReverseOrder, Boolean isCaseIndependent,
                         InputStream stdin) throws SortException{
        List<String> list = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdin));
        String temp;
        try {
            while ((temp = bufferedReader.readLine()) != null){
                if (isFirstWordNumber && !StringUtils.isNumberic(temp.split(" ")[0])){
                    throw new SortException("");
                }
                list.add(temp);
            }
        } catch (IOException e) {
            throw (SortException) new SortException("IO failure during sortFromStdin").initCause(e);
        }
        sortList(list, isFirstWordNumber, isCaseIndependent);
        if (isReverseOrder){
            Collections.reverse(list);
        }
        return String.join(StringUtils.STRING_NEWLINE,list.toArray(new String[0]));
    }

    public void run(String[] args, InputStream stdin, OutputStream stdout) throws SortException {
        boolean firstWord = false,reverseOrder = false, caseIndependent = false, useFile = false;
        ArrayList<String> files = new ArrayList<>();
        for (String arg : args){
            if (arg.charAt(0) == '-'){
                if (arg.contains("n")) {
                    firstWord = true;
                }
                if (arg.contains("r")) {
                    reverseOrder = true;
                }
                if (arg.contains("f")) {
                    caseIndependent = true;
                }
            }
            else {
                useFile = true;
                files.add(arg);
            }
        }
        if (useFile){
            String[] file = new String[files.size()];
            for (int i = 0 ; i < file.length; i++){
                file[i] = files.get(i);
            }
            sortFromFiles(firstWord,reverseOrder,caseIndependent,file);
        }
        else {
            sortFromStdin(firstWord,reverseOrder,caseIndependent,stdin);
        }
    }
    public static void sortList(List list, boolean isFirstWordNumber, boolean isCaseIndependent){
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String string1, String string2) {
                int result = string1.length()>=string2.length()?1:-1;
                int index = 0;
                int wordLength = Math.min(string1.length(),string2.length());
                if (isFirstWordNumber){
                    if (StringUtils.getFirstNum(string1)>StringUtils.getFirstNum(string2))
                    {
                        return 1;
                    }
                    else if (StringUtils.getFirstNum(string1)<StringUtils.getFirstNum(string2))
                    {
                        return -1;
                    }
                }
                while (index < wordLength){
                    char charOfO1 = string1.charAt(index), charOfO2 = string2.charAt(index);
                    if (isCaseIndependent){
                        Locale defLoc = Locale.getDefault();
                        charOfO1 = String.valueOf(charOfO1).toUpperCase(defLoc).charAt(0);
                        charOfO2 = String.valueOf(charOfO2).toUpperCase(defLoc).charAt(0);
                    }
                    if (charOfO1 > charOfO2) {
                        return 1;
                    }
                    else if (charOfO1 < charOfO2){
                        return -1;
                    }
                    index++;
                }
                return result;
            }
        });
    }
}
