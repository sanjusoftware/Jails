package org.jailsframework.util;

import java.io.File;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:51:30 PM
 */
public class FileUtil {

    public static boolean deleteDirRecursively(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                if (!deleteDirRecursively(new File(dir, child))) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
