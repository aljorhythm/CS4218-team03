package sg.edu.nus.comp.cs4218.impl.cmd;

/**
 * A Sequence Command is a sub-command consisting of two Commands separated with a semicolon.
 *
 * <p>
 * <b>Command format:</b> <code>&lt;Command&gt; ; &lt;Command&gt;</code>
 * </p>
 *
public class SequenceCommand implements Command {
    private final List<Command> commands;

    public SequenceCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void evaluate(InputStream stdin, OutputStream stdout)
            throws AbstractApplicationException, ShellException {
        ExitException exitException = null;
        List<String> outputLines = new LinkedList<>();

        for (Command command : commands) {
            try {
                OutputStream outputStream = new ByteArrayOutputStream();
                command.evaluate(stdin, outputStream);

                String outputLine = outputStream.toString();
                if (!outputLine.isEmpty()) {
                    outputLines.add(outputLine);
                }
            } catch (ExitException e) {
                exitException = e;

            } catch (AbstractApplicationException || ShellException e) {
                outputLines.add(e.getMessage() + STRING_NEWLINE);
            }
        }

        for (String outputLine : outputLines) {
            try {
                stdout.write(outputLine.getBytes());
            } catch (IOException e) {
                throw new ShellException(e.getMessage());
            }
        }

        if (exitException != null) {
            throw exitException;
        }
    }

    @Override
    public void terminate() {
        // Unused for now
    }

    public List<Command> getCommands() {
        return commands;
    }
}
