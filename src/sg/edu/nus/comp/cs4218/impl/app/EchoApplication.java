package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.EchoInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHARSET_UTF8;

public class EchoApplication implements EchoInterface {
    public static final String FAIL_ECHO = "fail_echo";
    public static final String FAIL_ECHO_WRITE = "fail_echo_write";

    @Override
    public String constructResult(String... args) throws Exception {
        return null;
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        String result;
        try {
            result = this.constructResult(args);
        } catch (Exception e) {
            throw new AbstractApplicationException(FAIL_ECHO){};
        }

        try {
            stdout.write(result.getBytes(CHARSET_UTF8));
        } catch (IOException e) {
            throw new AbstractApplicationException(FAIL_ECHO_WRITE){};
        }
    }
}
