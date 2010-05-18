package org.jailsframework.generators;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jailsframework.util.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:21:35 AM
 */
public class MigrationGenerator extends AbstractGenerator {

    public MigrationGenerator(JailsProject project) {
        super(project);
    }

    @Override
    public boolean generate(String migrationFileName) {
        try {
            long version = getMigrationVersion();
            String migrationFileNameWithVersion = getMigrationFileNameWithVersion(version, migrationFileName);
            File migrationFile = new File(project.getMigrationsPath(),
                    migrationFileNameWithVersion.concat(".java"));
            FileUtil.createFile(migrationFile);
            writeMigrationContent(migrationFile, migrationFileNameWithVersion, version);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void writeMigrationContent(File migrationFile, String migrationFileNameWithTimeStamp, long version) throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(TEMPLATES_PATH + "\\migration.vm");
        VelocityContext context = new VelocityContext();
        context.put("migrationFileName", migrationFileNameWithTimeStamp);
        context.put("package", project.getMigrationPackage());
        context.put("version", version + "L");
        FileWriter fileWriter = new FileWriter(migrationFile);
        template.merge(context, fileWriter);
        fileWriter.flush();
        fileWriter.close();

    }

    private String getMigrationFileNameWithVersion(long version, String migrationFileName) {
        return "Migration_" + version + "_".concat(migrationFileName);
    }

    private long getMigrationVersion() {
        return new Date().getTime();
    }

}
