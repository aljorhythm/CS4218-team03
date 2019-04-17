package sg.edu.nus.comp.cs4218.impl.cmd;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218.impl.util.IORedirectionHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static sg.edu.nus.comp.cs4218.impl.ShellImpl.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

/**
 * A Call Command is a sub-command consisting of at least one non-keyword or quoted.
 *
 * <p>
 * <b>Command format:</b> <code>(&lt;non-keyword&gt; or &lt;quoted&gt;) *</code>
 * </p>
 */
public class CallCommand implements Command {
//    private static final String ERR_WRITE_OUTPUT_STREAM = "write_output_stream";
    private final List<String> argsList;
    private final ApplicationRunner appRunner;
    private final ArgumentResolver argumentResolver;

    public CallCommand(List<String> argsList, ArgumentResolver argumentResolver, ApplicationRunner appRunner) {
        this.argsList = argsList;
        this.argumentResolver = argumentResolver;
        this.appRunner = appRunner;
    }

    @Override
    public void evaluate(InputStream stdin, OutputStream stdout)
            throws AbstractApplicationException, ShellException {
        if (argsList == null || argsList.isEmpty()) {
            throw new ShellException(ERR_SYNTAX);
        }

        // Handle IO redirection
        IORedirectionHandler redirHandler = new IORedirectionHandler(argsList, stdin, stdout, argumentResolver);
        try{
            redirHandler.extractRedirOptions(appRunner);
        } catch (IOException e){
            e.printStackTrace();
        }
        List<String> noRedirArgsList = redirHandler.getNoRedirArgsList();
        InputStream inputStream = redirHandler.getInputStream();
        OutputStream outputStream = redirHandler.getOutputStream();

        // Handle quoting + globing + command substitution
        List<String> parsedArgsList = argumentResolver.parseArguments(noRedirArgsList, appRunner);
        if (!parsedArgsList.isEmpty()) {
            String app = argsList.get(0);
//            String app = parsedArgsList.remove(0);
            appRunner.runApp(app, parsedArgsList.toArray(new String[]{}), inputStream, outputStream);
        }
        if (outputStream.toString().length() > 0) {
            try {
                outputStream.write(STRING_NEWLINE.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void terminate() {
        // Unused for now
    }

    public List<String> getArgsList() {
        return argsList;
    }
}
