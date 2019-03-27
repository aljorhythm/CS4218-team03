package sg.edu.nus.comp.cs4218.impl.cmd;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.*;
import java.util.List;

@SuppressWarnings("PMD.OverrideBothEqualsAndHashcode")

/**
 * A Pipe Command is a sub-command consisting of two Call Commands separated with a pipe,
 * or a Pipe Command and a Call Command separated with a pipe.
 *
 * <p>
 * <b>Command format:</b> <code>&lt;Call&gt; | &lt;Call&gt; or &lt;Pipe&gt; | &lt;Call&gt;</code>
 * </p>
 */
public class PipeCommand implements Command {
    private final List<CallCommand> callCommands;

    public PipeCommand(List<CallCommand> callCommands) {
        this.callCommands = callCommands;
    }

    @Override
    public void evaluate(InputStream stdin, OutputStream stdout)
            throws ShellException {
        AbstractApplicationException absAppException = null;
        ShellException shellException = null;

        InputStream nextInputStream = stdin;
        OutputStream nextOutputStream;

        for (int i = 0; i < callCommands.size(); i++) {
            CallCommand callCommand = callCommands.get(i);

            if (shellException != null) {
                callCommand.terminate();
                continue;
            }

            try {
                nextOutputStream = new ByteArrayOutputStream();
                callCommand.evaluate(nextInputStream, nextOutputStream);
                nextInputStream = new ByteArrayInputStream(
                        ((ByteArrayOutputStream) nextOutputStream).toByteArray());
            } catch (ShellException e) {
                shellException = e;
            } catch (Exception e) {
                shellException = new ShellException(e.getMessage());
            }
        }
        if (shellException != null) {
            throw shellException;
        }
        try {
            int len;
            byte[] buffer = new byte[1024];
            while((len = nextInputStream.read(buffer)) != -1) {
                stdout.write(buffer, 0, len);
            }
        } catch (IOException e) {
            shellException = new ShellException(e.getMessage());
            throw shellException;
        }
    }

    @Override
    public void terminate() {
        // Unused for now
    }

    public List<CallCommand> getCallCommands() {
        return callCommands;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof PipeCommand && ((PipeCommand) object).callCommands.equals(this.callCommands);
    }
}
