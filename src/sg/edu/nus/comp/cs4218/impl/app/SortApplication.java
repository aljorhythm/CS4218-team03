package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.SortInterface;
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
                         String... fileName) throws SortException {
        List<String> list = new ArrayList<>();
        for (String fileString: fileName){
            File file = new File(fileString);
            if (!file.exists()){
                throw new SortException("File not exist");
            }
            try {
                FileReader fileReader = new FileReader(fileString);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String string;
                while ((string = bufferedReader.readLine()) != null) {
                    list.add(string);
                }
                bufferedReader.close();
                fileReader.close();
            } catch (FileNotFoundException e) {
                throw (SortException) new SortException("File not found!").initCause(e);
            } catch (IOException e) {
                throw (SortException) new SortException("IO not working!").initCause(e);
            }
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
                         InputStream stdin) throws IOException,SortException{
        List<String> list = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdin));
        String temp;
        while ((temp = bufferedReader.readLine()) != null){
            list.add(temp);
        }
        sortList(list, isFirstWordNumber, isCaseIndependent);
        if (isReverseOrder){
            Collections.reverse(list);
        }
        return String.join(StringUtils.STRING_NEWLINE,list.toArray(new String[0]));
    }

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws SortException {
        if (args == null) {
            throw new SortException("Argument is null!");
        }
        boolean firstWord = false,reverseOrder = false, caseIndependent = false, useFile = false;
        ArrayList<String> files = new ArrayList<>();
        for (String arg : args){
            if (arg.charAt(0) == '-'){
                if (arg.length() == 1){
                    throw new SortException("invalid arg");
                }
                for (int iterator = 1; iterator < arg.length(); iterator++){
                    if (arg.charAt(iterator) == 'n') {
                        firstWord = true;
                    }
                    else if (arg.charAt(iterator) == 'r') {
                        reverseOrder = true;
                    }
                    else if (arg.charAt(iterator) == 'f') {
                        caseIndependent = true;
                    }
                    else {
                        throw new SortException("invalid arg");
                    }
                }
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
                String result = sortFromFiles(firstWord,reverseOrder,caseIndependent,file);
                stdout.write(result.getBytes());
            }catch (Exception e){
                throw (SortException) new SortException("File not exist").initCause(e);
            }
        }
        else {
            try {
                String result = sortFromStdin(firstWord,reverseOrder,caseIndependent,stdin);
                stdout.write(result.getBytes());
            }catch (Exception e){
                throw (SortException) new SortException("").initCause(e);
            }
        }
    }

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    public static void sortList(List list, boolean isFirstWordNumber, boolean isCaseIndependent){
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String string1, String string2) {
                int result = string1.length()-string2.length();
                int index = 0;
                if (string1.length() == 0 && string2.length() == 0){
                    return 0;
                }
                else if (string2.length() == 0){
                    return 1;
                }
                else if (string1.length() == 0){
                    return -1;
                }
                if (isFirstWordNumber){
                    String tempString1 = string1.split(" ")[0];
                    String tempString2 = string2.split(" ")[0];
                    if (StringUtils.isNumberic(tempString1) && StringUtils.isNumberic(tempString2)){
                        Integer numForString1 = Integer.parseInt(tempString1);
                        Integer numForString2 = Integer.parseInt(tempString2);
                        if (numForString1.compareTo(numForString2) != 0){
                            return numForString1.compareTo(numForString2);
                        }
                    }
                }
                int wordLength = Math.min(string1.length(),string2.length());
                while (index < wordLength){
                    char charOfO1 = string1.charAt(index), charOfO2 = string2.charAt(index);
                    int typeOfStr1 = StringUtils.getCharacterType(charOfO1), typeOfStr2 = StringUtils.getCharacterType(charOfO2);
                    if (typeOfStr1 > typeOfStr2 && typeOfStr2 < 3){
                        return 1;
                    }
                    else if (typeOfStr1 < typeOfStr2 && typeOfStr1 < 3){
                        return -1;
                    }
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
                index = 0;
                if (result == 0 && isCaseIndependent){
                    while (index < wordLength){
                        char charOfO1 = string1.charAt(index), charOfO2 = string2.charAt(index);
                        if (charOfO1 > charOfO2) {
                            return 1;
                        }
                        else if (charOfO1 < charOfO2){
                            return -1;
                        }
                        index++;
                    }
                }
                return result;
            }
        });
    }
}
