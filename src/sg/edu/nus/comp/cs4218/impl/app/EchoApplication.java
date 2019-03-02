package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.EchoInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.EchoException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EchoApplication implements EchoInterface {
    @Override
    public String constructResult(String... args) throws EchoException {
        StringBuilder res = new StringBuilder();
        for (int i=0;i<args.length;i++){
            if(i == args.length - 1){
                res.append(args[i]);
            }
            else{
                res.append(args[i]).append(" ");
            }
        }
        return res.toString();
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
        if(!args[0].equals("echo") || args.length <= 1){
            throw new EchoException("Invalid syntax.");
        }
        String[] args_n = new String[args.length-1];
        for(int i=0;i<args_n.length;i++) {
            args_n[i] = args[i + 1];
        }
        try {
            stdout.write((constructResult(args_n)+"\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
