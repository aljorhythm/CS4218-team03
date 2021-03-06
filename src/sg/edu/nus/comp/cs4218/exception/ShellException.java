package sg.edu.nus.comp.cs4218.exception;

public class ShellException extends Exception {

    private static final long serialVersionUID = -4439395674558704575L;
    public static final String INVALID_IO_STREAM = "invalid input stream";

    public ShellException(String message) {
        super("shell: " + message);
    }
}