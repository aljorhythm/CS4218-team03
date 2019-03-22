package sg.edu.nus.comp.cs4218.impl.util;

public class SystemUtils {
    private static String OS = System.getProperty("os.name").toLowerCase();

    /**
     *
     * @return true if OS is windows
     */
    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    /**
     *
     * @return true if OS is macs
     */
    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    /**
     *
     * @return true if OS is unix
     */
    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    /**
     *
     * @return true if OS is solaris
     */
    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

}
