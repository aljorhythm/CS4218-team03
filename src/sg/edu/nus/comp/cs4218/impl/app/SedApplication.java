package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.SedInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.SedException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

public class SedApplication implements SedInterface{
    public static final String FAIL_SED_WRITE = "fail_sed_write";
    public static final String FILE_NOT_EXIST = "File doesn't exist.";
    public static final String FILE_NULL = "File is null.";

    @Override
    public String replaceSubstringInFile(String regexp, String replacement, int replacementIndex,
                                  String fileName) throws SedException{
        File file = new File(fileName);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new SedException(FILE_NOT_EXIST);//NOPMD
        }
        List<String> resList = null;
        try {
            resList = replace(inputStream,regexp,replacement,replacementIndex);
        } catch (IOException e) {
            throw new SedException(FILE_NULL);//NOPMD
        }
        return String.join(StringUtils.STRING_NEWLINE,resList);
    }

    @Override
    public String replaceSubstringInStdin(String regexp, String replacement, int replacementIndex,
                                          InputStream stdin) throws SedException{
        List<String> resList = null;
        try {
            resList = replace(stdin,regexp,replacement,replacementIndex);
        } catch (IOException e) {
            throw new SedException(FILE_NULL);//NOPMD
        }
        return String.join(StringUtils.STRING_NEWLINE,resList);
    }

    /**
     * use this function to delete those redundant codes
     * @param inputStream, file or stdin
     * @param regexp following the calling function
     * @param replacement following the calling function
     * @param replacementIndex following the calling function
     * @return
     * @throws IOException
     */
    public static List<String> replace(InputStream inputStream, String regexp, String replacement, int replacementIndex) throws IOException {
        Pattern pattern = Pattern.compile(regexp);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> resList = new ArrayList<>();
        String str;
        while((str = bufferedReader.readLine()) != null)
        {
            StringBuilder newStr = new StringBuilder(str);
            Matcher matcher = pattern.matcher(str);
            int count = replacementIndex;
            while(matcher.find()){
                count--;
                if(count == 0){
                    newStr.replace(matcher.start(),matcher.end(),replacement);
                    break;
                }
            }
            resList.add(newStr.toString());
        }

        //close
        inputStream.close();
        return resList;
    }

    /**
     * Runs the sed application with the specified arguments.
     * Assumption: The application must take in one or two args. (sed without args is not supported)
     *
     * @param args   Array of arguments for the application.e.g sed command file. command should look like "s\dwa\dwa\2"
     * @param stdin  An InputStream.
     * @param stdout An OutputStream.
     * 
     * @throws SedException
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        if(args.length < 1 || args.length > 3 || args[0].charAt(0) != 's'){
            throw new SedException("Invalid syntax.");
        }
        String splitSymbol = args[0].substring(1,2);
        if(isSpecialSymbol(splitSymbol)) {
            splitSymbol = String.format("\\%s", splitSymbol);
        }
        String[] argsForRegex = args[0].split(splitSymbol);
        if(argsForRegex.length < 3 || argsForRegex.length > 4){
            throw new SedException("Invalid syntax.");
        }
        int replacementIndex = 1;
        String regexp = argsForRegex[1];
        if(regexp.isEmpty()){
            throw new SedException("Missing args.");
        }
        String replacement = argsForRegex[2];
        if(argsForRegex.length == 4){
            String index = argsForRegex[3];
            if(isInteger(index) && Integer.parseInt(index) > 0) {
                replacementIndex = Integer.parseInt(index);
            }
            else{
                throw new SedException("Invalid syntax.");
            }
        }
        if(args.length == 2){
            String fileName = args[1];
            try {
                String res = replaceSubstringInFile(regexp,replacement,replacementIndex,fileName);
                stdout.write(res.getBytes(CHARSET_UTF8));
            } catch (Exception e) {
                throw new AbstractApplicationException(FAIL_SED_WRITE){};//NOPMD
            }
        }
        else{
            if(stdin == null){
                throw new SedException("stdin is missing.");
            }
            String res;
            try {
                res = replaceSubstringInStdin(regexp,replacement,replacementIndex,stdin);
                stdout.write(res.getBytes(CHARSET_UTF8));
            } catch (Exception e) {
                throw new AbstractApplicationException(FAIL_SED_WRITE){};//NOPMD
            }
        }
    }

    /**
     * judge if a string could represent an integer.
     * @param str, argsForRegex[3]
     * @return isInteger
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * judge if a string has some special symbols.
     * @param str, splitSymbol
     * @return isSpecialSymbol
     */
    public static boolean isSpecialSymbol(String str){
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
