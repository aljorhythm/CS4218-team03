package sg.edu.nus.comp.cs4218.impl.app;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_SPACE;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import sg.edu.nus.comp.cs4218.app.EchoInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.EchoException;

public class EchoApplication implements EchoInterface {
    public static final String ERR_NULL_ARGS = "Argument is null";
    public static final String ERR_NULL_STDOUT = "OutputStream is null";

    @Override
    public String constructResult(String... args) {
        return String.join(Character.toString(CHAR_SPACE), args);
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws EchoException {
        if (args == null) {
            throw new EchoException(ERR_NULL_ARGS);
        }

        if (stdout == null) {
            throw new EchoException(ERR_NULL_STDOUT);
        }

        String res = constructResult(args);
        PrintWriter out = new PrintWriter(stdout);
        out.println(res);
        out.flush();
    }
}
