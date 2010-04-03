package org.jailsframework.generators;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:21:35 AM
 */
public class Migration {
    private File migrationFile;
    private String migrationFileNameWithTimeStamp;

    public Migration(String migrationFileName) {
        this.migrationFileNameWithTimeStamp = getMigrationFileNameWithTimeStamp(migrationFileName);
    }

    public boolean generate() {
        try {
            migrationFile = new File(getMigrationsPath(),
                    migrationFileNameWithTimeStamp.concat(".java"));
            migrationFile.createNewFile();
            writeMigrationContent();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void writeMigrationContent() throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        Template template = velocityEngine.getTemplate("\\src\\org\\jailsframework\\generators\\templates\\migrations.vm");
        VelocityContext context = new VelocityContext();
        context.put("migrationFileName", migrationFileNameWithTimeStamp);
        context.put("package", getMigrationPackage());
        FileWriter fileWriter = new FileWriter(migrationFile);
        template.merge(context, fileWriter);
        fileWriter.flush();
        fileWriter.close();

    }

    private String getMigrationPackage() {
        return System.getProperty("APP_NAME").concat(".db.migrate");
    }

    private String getMigrationFileNameWithTimeStamp(String migrationFileName) {
        return "Migration_" + getCurrentTimeStamp() + "_".concat(migrationFileName);
    }

    private long getCurrentTimeStamp() {
        return new Date().getTime();
    }

    private String getMigrationsPath() {
        return System.getProperty("JAILS_ROOT").concat("\\db\\migrate");
    }
}
