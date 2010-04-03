package org.jailsframework.generators;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:21:35 AM
 */
public class Migration {
    private String migrationFileName;

    public Migration(String migrationFileName) {
        this.migrationFileName = migrationFileName;
    }

    public boolean generate() {
        try {
            File migrationFile = new File(System.getProperty("JAILS_ROOT").concat("\\db\\migrate"), new Date().getTime() + "_".concat(migrationFileName).concat(".java"));
            migrationFile.createNewFile();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
