package org.jailsframework.loaders;

import org.jailsframework.database.DatabaseFactory;
import org.jailsframework.database.IDatabase;
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
    private Properties prop;
    private static DatabaseConfiguration dbConfig;
    private IDatabase database;
    private JailsProject project;

    public static DatabaseConfiguration getInstance(JailsProject project) {
        if (dbConfig == null) {
            dbConfig = new DatabaseConfiguration(project);
        }
        return dbConfig;
    }

    private DatabaseConfiguration(JailsProject project) {
        this.project = project;
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

    public IDatabase getDatabase() {
        if (database == null) {
            database = DatabaseFactory.getDatabase(getProperty("adapter"),
                    getProperty("url"),
                    getProperty("driver"),
                    getProperty("name"),
                    getProperty("user"),
                    getProperty("password"));
        }
        return database;
    }

    private String getProperty(String property) {
        return prop.getProperty(project.getEnvironment().concat("." + property));
    }
}
