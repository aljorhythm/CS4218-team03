package sg.edu.nus.comp.cs4218.exception;

public class ShellException extends Exception {

    private static final long serialVersionUID = -4439395674558704575L;
    public static final String FILE_INACCESSIBLE= "File destination is inaccessible.";

    public ShellException(String message) {
        super("shell: " + message);
    }

    public ShellException(Exception exception, String message) {
        super("shell: " + message);
    }
}