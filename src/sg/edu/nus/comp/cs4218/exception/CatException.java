package sg.edu.nus.comp.cs4218.exception;

@SuppressWarnings({"PMD.LongVariable", "PMD.DataClass"})
public class CatException extends AbstractApplicationException {

    public static final String ERR_IS_DIR = "This is a directory";
    public static final String ERR_RD_FILE = "Could not read file";
    public static final String ERR_RD_STREAM = "Could not read stream";
    public static final String ERR_NULL_STREAMS = "Null Pointer Exception";
    public static final String ERR_NULL_FILENAME = "null input file provided";
    public static final String ERR_NULL_OS = "null output stream provided";
    public static final String ERR_GENERAL = "Exception Caught";
    public static final String ERR_NULL_ARGS = "null arguments";
    public static final String ERR_WRITE_STREAM = "Could not write to output stream";
    public static final String ERR_FILE_NOT_FOUND = "No such file or directory";
    public static final String ERR_NO_PERM = "Permission denied";

    private static final long serialVersionUID = 2333796686823942499L;

    public CatException(String message) {
        super("cat: " + message);
    }
}