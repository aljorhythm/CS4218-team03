package sg.edu.nus.comp.cs4218.exception;

@SuppressWarnings("PMD.DataClass")
public class SedException extends AbstractApplicationException {

    private static final long serialVersionUID = 9105496071275898837L;

    public static final String EMPTY_ARGUMENTS = "Empty arguments.";
    public static final String PROB_FILE = "Problem replacing in file: ";
    public static final String PROB_STDIN = "Problem replacing from stdin: ";
    public static final String TOO_MANY_ARGS = "Too many arguments.";
    public static final String BAD_FLAG = "Bad flag in substitute command.";
    public static final String INSUFFICIENT_ARGS = "Insufficient arguments.";
    public static final String INVALID_CMD = "Invalid command code.";
    public static final String INVALID_INDEX = "Invalid index.";
    public static final String REGEX_EMPTY = "Regex can not be empty.";


    public SedException(String message) {
        super("sed: " + message);
    }
    public SedException(Exception exception, String message) {
        super("sed: " + message);
    }
}