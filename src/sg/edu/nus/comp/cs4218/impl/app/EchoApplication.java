package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.EchoInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.EchoException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

public class EchoApplication implements EchoInterface {
    public static final String FAIL_ECHO = "fail_echo";
    public static final String FAIL_ECHO_WRITE = "fail_echo_write";
    public static final String FAIL_ECHO_EMPTY = "fail_echo_empty_params";

    @Override
    public String constructResult(String... args) throws EchoException {
        if(args == null) {
            throw new EchoException(FAIL_ECHO_EMPTY);
        }
        return String.join(" ", args);
    }

    /**
     * Runs the echo application with the specified arguments.
     *
     * @param args   Array of arguments for the application, consists of some strings.
     * @param stdin  An InputStream. Not used.
     * @param stdout An OutputStream. Not used.
     *
     * @throws EchoException If the shell doesn't work.
     */
    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws EchoException {
        if(stdout == null){
            throw new EchoException("Null OutputStream");
        }
        String result;
        try {
            result = this.constructResult(args);
        } catch (AbstractApplicationException e) {
            throw new EchoException(FAIL_ECHO);//NOPMD
        }

        if(stdout == null) {
            return;
        }

        try {
            stdout.write(result.getBytes(CHARSET_UTF8));
        } catch (IOException e) {
            throw new EchoException(FAIL_ECHO_WRITE);//NOPMD
        }
    }
}
