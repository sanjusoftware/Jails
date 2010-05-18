package org.jailsframework.generators;

import org.jailsframework.util.FileUtil;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
            String migrationFileNameWithTimeStamp = getMigrationFileNameWithTimeStamp(version, migrationFileName);
            File migrationFile = new File(project.getMigrationsPath(),
                    migrationFileNameWithTimeStamp.concat(".java"));
            FileUtil.createFile(migrationFile);
            writeContent(migrationFile, getSubstitutions(version, migrationFileNameWithTimeStamp));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected String getTemplateName() {
        return "\\migration.vm";
    }

    private Map<String, String> getSubstitutions(long version, String migrationFileNameWithTimeStamp) {
        Map<String, String> substitutions = new HashMap<String, String>();
        substitutions.put("migrationFileNameWithTimeStamp", migrationFileNameWithTimeStamp);
        substitutions.put("package", project.getMigrationPackage());
        substitutions.put("version", version + "L");
        return substitutions;
    }

    private String getMigrationFileNameWithTimeStamp(long version, String migrationFileName) {
        return "Migration_" + version + "_".concat(migrationFileName);
    }

    private long getMigrationVersion() {
        return new Date().getTime();
    }

}
