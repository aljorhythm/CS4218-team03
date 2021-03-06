package sg.edu.nus.comp.cs4218.impl.cmd;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.*;
import java.util.List;

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
            throws AbstractApplicationException, ShellException {
        AbstractApplicationException absAppException = null;
        ShellException shellException = null;

        InputStream nextInputStream = stdin;
        OutputStream nextOutputStream;

        for (int i = 0; i < callCommands.size(); i++) {
            boolean isLastCommand = i == callCommands.size() - 1;

            CallCommand callCommand = callCommands.get(i);

            if (absAppException != null || shellException != null) {
                callCommand.terminate();
                continue;
            }

            try {
                if (isLastCommand) {
                    nextOutputStream = stdout;
                } else {
                    nextOutputStream = new ByteArrayOutputStream();
                }
                callCommand.evaluate(nextInputStream, nextOutputStream);
                nextInputStream = new ByteArrayInputStream(
                        ((ByteArrayOutputStream) nextOutputStream).toByteArray());
            } catch (AbstractApplicationException e) {
                absAppException = e;
            } catch (ShellException e) {
                shellException = e;
            }
        }

        if (absAppException != null) {
            throw absAppException;
        }
        if (shellException != null) {
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
}
