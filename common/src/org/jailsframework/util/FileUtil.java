package org.jailsframework.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

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

    public static boolean emptyDirRecursively(File dir, boolean deleteParent) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                if (!emptyDirRecursively(new File(dir, child), true)) {
                    return false;
                }
            }
        }
        return !deleteParent || dir.delete();
    }

    public static boolean makeDirectory(String dirPath) {
        File dir = new File(dirPath);
        if (dir.exists()) return true;
        boolean dirCreated = dir.mkdir();
        if (!dirCreated) {
            System.out.println("Error creating dir = " + dirPath);
        }
        return dirCreated;
    }

    public static boolean createFile(File file) {
        try {
            if (file.exists()) file.delete();
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating the file = " + file.getAbsolutePath());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createFileWithContent(File file, String content) {
        try {
            createFile(file);
            OutputStreamWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
