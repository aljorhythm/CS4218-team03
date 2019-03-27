package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.EchoInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.EchoException;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EchoApplication implements EchoInterface {

    private static final String STRING_EMPTY = "";

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
        try{
            String print = constructResult(args);
            stdout.write(print.getBytes());
        }  catch (IOException ioe) {
            throw new EchoException(ioe, "Echo input/output is wrong");
        } catch (NullPointerException ne) {
            throw new EchoException(ne, "Empty arguments");
        }


    }

    @Override
    public String constructResult(String[] args) throws EchoException {
        StringBuilder stringBuilder = new StringBuilder();

        if (args == null) {
            throw new EchoException("Empty arguments");
        }
        if (args.length == 0) {
            return STRING_EMPTY;
        }

        for(int i = 0; i < args.length; ++i){
            stringBuilder.append(args[i]);
            if(i != args.length - 1) {
                stringBuilder.append(CHAR_SPACE);
            }
        }
        stringBuilder.append(STRING_NEWLINE);

        return stringBuilder.toString();
    }
}
