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

    @Override
    public String replaceSubstringInFile(String regexp, String replacement, int replacementIndex,
                                  String fileName) throws Exception{
        File file = new File(fileName);
        if(!file.exists()){
            throw new SedException(FILE_NOT_EXIST);
        }
        FileInputStream inputStream = new FileInputStream(fileName);
        List<String> resList = replace(inputStream,regexp,replacement,replacementIndex);
        return String.join(StringUtils.STRING_NEWLINE,resList);
    }

    @Override
    public String replaceSubstringInStdin(String regexp, String replacement, int replacementIndex,
                                          InputStream stdin) throws Exception{
        List<String> resList = replace(stdin,regexp,replacement,replacementIndex);
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
        int temp = 1;
        while(args[0].charAt(temp) == StringUtils.CHAR_SPACE){
            temp++;
        }
        String split = String.format("\\%s", args[0].substring(temp, temp + 1));
        String[] argsForRegex = args[0].split(split);
        if(argsForRegex.length < 3 || argsForRegex.length > 4){
            throw new SedException("Invalid syntax.");
        }
        int replacementIndex = 1;
        String regexp = argsForRegex[1];
        String replacement = argsForRegex[2];
        if(argsForRegex.length == 4){
            if(isInteger(argsForRegex[3])) {
                replacementIndex = Integer.parseInt(argsForRegex[3]);
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
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
