package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.SedInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.SedException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.StringUtilException;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"PMD.ShortVariable", "PMD.LongVariable", "PMD.ExcessiveMethodLength"})
public class SedApplication implements SedInterface {
    public static final String ERR_NULL_ARGS = "Null Args";
    public static final String ERR_INVALID_ARGS = "Invalid Args";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_READING_FILE = "Could not read file";
    public static final String ERR_READING_STDIN = "Could not read from stdin";
    public static final String ERR_INVALID_REPLACEMENT_RULE = "Invalid replacement rule";


    @Override
    public String replaceSubstringInFile(String regexp, String replacement, int replacementIndex, String fileName) throws Exception {
        ArrayList<String> lineList = new ArrayList<String>();
        try  {
            InputStream fis = IOUtils.openInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                lineList.add(line);
            }
            IOUtils.closeInputStream(fis);
        } catch (ShellException se) {
            throw new SedException(se.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        for (String line :lineList) {
            sb.append(replaceLine(regexp, replacement, replacementIndex, line) + System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String replaceSubstringInStdin(String regexp, String replacement, int replacementIndex, InputStream stdin) throws Exception {
        ArrayList<String> lineList = new ArrayList<String>();
        String line;
        BufferedReader bf = new BufferedReader(new InputStreamReader(stdin));
        while ((line = bf.readLine()) != null) {
            if (line.equals("")) {
                break;
            }
            lineList.add(line);
        }

        StringBuilder sb = new StringBuilder();
        for (String lineRead :lineList) {
            sb.append(replaceLine(regexp, replacement, replacementIndex, lineRead) + System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
//        for (String arg : args) {
//            System.out.println(arg);
//        }

        if (args == null) {
            throw new SedException(ERR_NULL_ARGS);
        }
        if (stdout == null) {
            throw new SedException(ERR_NULL_STREAMS);
        }
        if (stdin == null) {
            stdin = System.in;
        }

        switch (args.length) {
            //sed
            case 0:
                try {
                    stdout.write(returnAllLinesFromStdin(stdin).getBytes());
                } catch (IOException e) {
                    throw new SedException(e.getMessage());
                }
                break;

            //sed "s/a/b/"
            case 1:
                if (args[0].trim().equals("")) {
                    try {
                        stdout.write(returnAllLinesFromStdin(stdin).getBytes());
                    } catch (IOException e) {
                        throw new SedException(e.getMessage());
                    }
                } else {
                    try {
                        ArrayList<String> parsedReplacementRule = StringUtils.parseReplacementRule(args[0]);
                        if (parsedReplacementRule.size() != 4) {
                            throw new SedException(ERR_INVALID_REPLACEMENT_RULE);
                        }
                        String result = replaceSubstringInStdin(parsedReplacementRule.get(1),
                                parsedReplacementRule.get(2),
                                Integer.parseInt(parsedReplacementRule.get(3)),
                                stdin);
                        stdout.write(result.getBytes());
                    } catch (StringUtilException sue) {
                        throw new SedException(sue.getMessage());
                    } catch (IOException ioe) {
                            throw new SedException(ioe.getMessage());
                    } catch (Exception e) {
                        throw new SedException(e.getMessage());
                    }
                }
                break;

            //sed "s/a/b/" sed.txt
            case 2:
                if (args[0].trim().equals("")) {
                    try {
                        stdout.write(returnAllLinesFromFile(args[1]).getBytes());
                    } catch (IOException e) {
                        throw new SedException(ERR_READING_FILE);
                    }
                } else {
                    try {
                        ArrayList<String> parsedReplacementRule = StringUtils.parseReplacementRule(args[0]);
                        if (parsedReplacementRule.size() != 4) {
                            throw new SedException(ERR_INVALID_REPLACEMENT_RULE);
                        }
                        String result = replaceSubstringInFile(parsedReplacementRule.get(1),
                                parsedReplacementRule.get(2),
                                Integer.parseInt(parsedReplacementRule.get(3)),
                                args[1]);
                        stdout.write(result.getBytes());
                    } catch (StringUtilException sue) {
                        throw new SedException(sue.getMessage());
                    } catch (IOException ioe) {
                        throw new SedException(ioe.getMessage());
                    } catch (SedException se) {
                        throw se;
                    } catch (Exception e) {
                        throw new SedException(e.getMessage());
                    }
                }
                break;

                default:
                    throw new SedException(ERR_INVALID_ARGS);

        }
    }

    private String replaceLine(String regexp, String replacement, int replacementIndex, String line) throws Exception {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(line);
        StringBuffer strBuilder = new StringBuffer(line.length());

        if (replacementIndex < 1) {
            throw new SedException(ERR_INVALID_REPLACEMENT_RULE);
        }

        for (int i=0; i<replacementIndex - 1; i++) {
            if (!matcher.find()) {
                break;
            }
        }

        if (matcher.find()) {
            matcher.appendReplacement(strBuilder, Matcher.quoteReplacement(replacement));
        }

        matcher.appendTail(strBuilder);
        return strBuilder.toString();
    }

    private String returnAllLinesFromFile(String fileName) throws SedException {
        StringBuilder sb = new StringBuilder();

        try  {
            InputStream fis = IOUtils.openInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.lineSeparator());
            }
            IOUtils.closeInputStream(fis);
        } catch (ShellException se) {
            throw new SedException(se.getMessage());
        } catch (IOException e) {
            throw new SedException(ERR_READING_FILE);
        }

        return sb.toString();
    }

    private String returnAllLinesFromStdin(InputStream stdin) throws SedException {
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(stdin));
            while ((line = bf.readLine()) != null) {
                if (line.equals("")) {
                    break;
                }
                sb.append(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new SedException(ERR_READING_STDIN);
        }

        return sb.toString();
    }
}
