package energybox;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author Rihards Polis
 * Linkoping University
 */
public class OSTools
{
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String getOS() 
    {
        if (isWindows()) 
            OS = "Windows";
        else if (isMac()) 
            OS = "Mac";
        else if (isUnix()) 
            OS = "Linux";
        else if (isSolaris()) 
            OS = "Solaris";
        else 
            OS = "error";
        return OS;
    }
    public static boolean isWindows() { return (OS.indexOf("win") >= 0); }
    public static boolean isMac() { return (OS.indexOf("mac") >= 0); }
    public static boolean isUnix() { return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ); }
    public static boolean isSolaris() { return (OS.indexOf("sunos") >= 0); }
    
    public static void addDirectory(String s) throws IOException {
    try {
        // This enables the java.library.path to be modified at runtime
        // From a Sun engineer at forums.sun.com
        Field field = ClassLoader.class.getDeclaredField("usr_paths");
        field.setAccessible(true);
        String[] paths = (String[])field.get(null);
        for (int i = 0; i < paths.length; i++) {
            if (s.equals(paths[i])) {
                return;
            }
        }
        String[] tmp = new String[paths.length+1];
        System.arraycopy(paths,0,tmp,0,paths.length);
        tmp[paths.length] = s;
        field.set(null,tmp);
        System.setProperty("java.library.path", System.getProperty("java.library.path") + File.pathSeparator + s);
    } catch (IllegalAccessException e) {
        throw new IOException("Failed to get permissions to set library path");
    } catch (NoSuchFieldException e) {
        throw new IOException("Failed to get field handle to set library path");
    }
}
}
