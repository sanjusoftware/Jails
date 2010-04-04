package org.jailsframework.util;

import org.jailsframework.generators.JailsProject;

import java.io.File;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 2:50:13 PM
 */
public class TestHelper {

    public static void generateJailsProjectStructure(String path, String projectName) {
        new JailsProject(path, projectName).generateStructure();
    }

    public static void deleteJailsProjectStructure(String path, String projectName) {
        FileUtil.deleteDirRecursively(new File(path + "\\" + projectName));
    }
}
