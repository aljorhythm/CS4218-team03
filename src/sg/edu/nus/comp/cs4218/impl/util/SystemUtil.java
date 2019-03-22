package sg.edu.nus.comp.cs4218.impl.util;

import java.util.Locale;

public final class SystemUtil {

    private static final String OPERATING_SYSTEM = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

    /**
     *
     * @return true if OPERATING_SYSTEM is windows
     */
    public static boolean isWindows() {
        return (OPERATING_SYSTEM.indexOf("win") >= 0);
    }

    /**
     *
     * @return true if OPERATING_SYSTEM is macs
     */
    public static boolean isMac() {
        return (OPERATING_SYSTEM.indexOf("mac") >= 0);
    }

    /**
     *
     * @return true if OPERATING_SYSTEM is unix
     */
    public static boolean isUnix() {
        return (OPERATING_SYSTEM.indexOf("nix") >= 0 || OPERATING_SYSTEM.indexOf("nux") >= 0 || OPERATING_SYSTEM.indexOf("aix") > 0 );
    }

    /**
     *
     * @return true if OPERATING_SYSTEM is solaris
     */
    public static boolean isSolaris() {
        return (OPERATING_SYSTEM.indexOf("sunos") >= 0);
    }

    /**
     * Utility class
     */
    private SystemUtil() {}
}
