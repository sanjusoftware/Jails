package org.jailsframework.util;

import java.io.File;
import java.io.IOException;

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

    public static boolean makeDirectory(String dir) {
        boolean dirCreated = new File(dir).mkdir();
        if (!dirCreated) {
            System.out.println("Error creating dir = " + dir);
        }
        return dirCreated;
    }

    public static boolean createFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating the file = " + file.getAbsolutePath());
            e.printStackTrace();
            return false;
        }
    }
}
