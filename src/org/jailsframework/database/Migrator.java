package org.jailsframework.database;

import org.jailsframework.generators.JailsProject;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 3:21:55 PM
 */
public class Migrator {
    private JailsProject project;

    public Migrator(JailsProject project) {
        this.project = project;
    }

    public String migrate() {
        Long currentDbVersion = project.getCurrentDbVersion();
        List<IMigration> migrations = project.getMigrations();
        Collections.sort(migrations);
        for (IMigration migration : migrations) {
            Long version = migration.getVersion();
            if (currentDbVersion < version) {
                migration.up();
                currentDbVersion = version;
            }
        }
        project.setCurrentDbVersion(currentDbVersion);
        return currentDbVersion + "";
    }
}
