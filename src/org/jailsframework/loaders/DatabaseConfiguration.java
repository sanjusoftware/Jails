package org.jailsframework.loaders;

import org.jailsframework.database.Database;
import org.jailsframework.database.DatabaseFactory;
import org.jailsframework.generators.JailsProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 3, 2010
 *          Time: 9:15:36 AM
 */

public class DatabaseConfiguration {
    private static Properties prop;
    private static DatabaseConfiguration dbConfig;
    private Database database;
    private JailsProject project;

    public static DatabaseConfiguration getInstance(JailsProject project) {
        if (dbConfig == null)
            dbConfig = new DatabaseConfiguration(project);
        return dbConfig;
    }

    private DatabaseConfiguration(JailsProject jailsProject) {
        project = jailsProject;
        loadDatabaseProperties();
    }

    private void loadDatabaseProperties() {
        try {
            prop = new Properties();
            prop.load(new FileInputStream(project.getDbPropertiesFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Database getDatabase() {
        if (database == null) {
            database = new DatabaseFactory(
                    getAdapterName()).getDatabase(
                    getDatabaseName(),
                    getUserName(),
                    getPassword());
        }
        return database;
    }

    private String getPassword() {
        return prop.getProperty(project.getEnvironment().concat(".password"));
    }

    private String getUserName() {
        return prop.getProperty(project.getEnvironment().concat(".username"));
    }

    private String getDatabaseName() {
        return prop.getProperty(project.getEnvironment().concat(".database"));
    }

    private String getAdapterName() {
        return prop.getProperty(project.getEnvironment().concat(".adapter"));
    }
}
