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
                         String... fileName) throws Exception{
        List<String> list = new ArrayList<String>();
        for (String file: fileName){
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String string;
            while ((string = bufferedReader.readLine()) != null){
                if (isFirstWordNumber){
                    if (string.length()>0){
                        string = string.split(" ")[0];
                    }
                }
                if (isCaseIndependent){
                    string = string.toUpperCase();
                }
                list.add(string);
            }
            bufferedReader.close();
            fileReader.close();
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 1;
                int index = 0;
                while (index < o1.length() && index < o2.length()){
                    if (o1.charAt(index) >= o2.charAt(index)) continue;
                    else {
                        result = -1;
                        break;
                    }
                }
                return result;
            }
        });
        if (isReverseOrder){
            Collections.reverse(list);
        }
        StringBuilder string = new StringBuilder();
        for (String str : list){
            string = string.append(str).append(StringUtils.STRING_NEWLINE);
        }
        return string.toString();
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
                         InputStream stdin) throws IOException,SortException{
        List<String> list = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdin));
        String temp;
        while ((temp = bufferedReader.readLine()) != null){
            if (isFirstWordNumber){
                if (!StringUtils.isNumberic(temp.split(" ")[0])) throw new SortException("");
            }
            list.add(temp);
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = o1.length()>=o2.length()?1:-1;
                int index = 0;
                int wordLength = Math.min(o1.length(),o2.length());
                if (isFirstWordNumber){
                    if (StringUtils.getFirstNum(o1)>StringUtils.getFirstNum(o2))
                        return 1;
                    else if (StringUtils.getFirstNum(o1)<StringUtils.getFirstNum(o2))
                        return -1;
                }
                while (index < wordLength){
                    char charOfO1 = o1.charAt(index), charOfO2 = o2.charAt(index);
                    if (isCaseIndependent){
                        charOfO1 = String.valueOf(charOfO1).toUpperCase().charAt(0);
                        charOfO2 = String.valueOf(charOfO2).toUpperCase().charAt(0);
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
        if (isReverseOrder){
            Collections.reverse(list);
        }
        return String.join(StringUtils.STRING_NEWLINE,list.toArray(new String[list.size()]));
    }

    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        boolean firstWord = false,reverseOrder = false, caseIndependent = false, useFile = false;
        ArrayList<String> files = new ArrayList<>();
        for (String arg : args){
            if (arg.charAt(0) == '-'){
                if (arg.contains("n")) firstWord = true;
                if (arg.contains("r")) reverseOrder = true;
                if (arg.contains("f")) caseIndependent = true;
            }
            else {
                useFile = true;
                files.add(arg);
            }
        }
        if (useFile){
            try {
                String[] file = new String[files.size()];
                for (int i = 0 ; i < file.length; i++){
                    file[i] = files.get(i);
                }
                sortFromFiles(firstWord,reverseOrder,caseIndependent,file);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            try {
                sortFromStdin(firstWord,reverseOrder,caseIndependent,stdin);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
