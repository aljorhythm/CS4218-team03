package sg.edu.nus.comp.cs4218;

/**
 * Specifies environment variables for shell
 */
public final class Environment {//NOPMD

    /**
     * Java VM does not support changing the current working directory.
     * For this reason, we use Environment.currentDirectory instead.
     */
    public static volatile String currentDirectory = System.getProperty("user.dir");

    private Environment() {
    }

}
