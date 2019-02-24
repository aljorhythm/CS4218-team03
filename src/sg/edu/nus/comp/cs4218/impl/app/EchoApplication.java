package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.app.EchoInterface;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.EchoException;

import java.io.InputStream;
import java.io.OutputStream;

public class EchoApplication implements EchoInterface {
    @Override
    public String constructResult(String... args) throws EchoException {
        StringBuilder res = new StringBuilder();
        for (int i=1;i<args.length;i++){
            if(i == args.length - 1){
                res.append(args[i]);
            }
            else{
                res.append(args[i]).append(" ");
            }
        }
        return res.toString();
    }

    @Override
    public void run(String[] args, InputStream stdin, OutputStream stdout) throws EchoException {
        if(!args[0].equals("echo") || args.length <= 1){
            throw new EchoException("Invalid syntax.");
        }
        System.out.println(constructResult(args));
    }
}
