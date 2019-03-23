package sg.edu.nus.comp.cs4218.exception;

public class CdException extends AbstractApplicationException {

    public static final String ERR_IS_NOT_DIR = "Not a directory: %s";
    public static final String ERR_NO_SUCH_DIR = "%s: No such file or directory";
    public static final String ERR_NO_ARGS = "No arguments";
    public static final String ERR_NULL_ARGS = "Null arguments";
    public static final String ERR_ARGS_LENGTH = "cd only takes one argument";

    private static final long serialVersionUID = -4730922172179294678L;

    public CdException(String message) {
        super("cd: " + message);
    }
}