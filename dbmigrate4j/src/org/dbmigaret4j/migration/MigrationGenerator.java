package org.dbmigaret4j.migration;

import org.jailsframework.exceptions.JailsException;
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

    private String migrationPath;
    private String migrationPackage;

    public MigrationGenerator(String migrationPath, String migrationPackage) {
        this.migrationPath = migrationPath;
        this.migrationPackage = migrationPackage;
    }

    protected void doGenerate(String migrationFileName) throws Exception {
        long version = getMigrationVersion();
        String migrationFileNameWithTimeStamp = getMigrationFileNameWithTimeStamp(version, migrationFileName);
        File migrationFile = new File(migrationPath, migrationFileNameWithTimeStamp.concat(".java"));
        if (!FileUtil.createFile(migrationFile)) {
            throw new JailsException("Could not generate migration");
        }
        writeContent(migrationFile, getSubstitutions(version, migrationFileNameWithTimeStamp));
    }

    protected String getTemplateName() {
        return "\\migration.vm";
    }

    private Map<String, String> getSubstitutions(long version, String migrationFileNameWithTimeStamp) {
        Map<String, String> substitutions = new HashMap<String, String>();
        substitutions.put("migrationFileNameWithTimeStamp", migrationFileNameWithTimeStamp);
        if(migrationPackage != null) {
            substitutions.put("package", migrationPackage);
        }
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
