/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 * Date: Apr 3, 2010
 * Time: 9:15:36 AM
 */
package org.jailsframework.loaders;

import org.jailsframework.database.Database;
import org.jailsframework.database.DatabaseFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfiguration {
    private static Properties prop;
    private static DatabaseConfiguration dbConfig = new DatabaseConfiguration();
    private static String env;
    private Database database;

    public static DatabaseConfiguration getInstance() {
        return dbConfig;
    }

    private DatabaseConfiguration() {
        loadDatabaseProperties();
        setEnvironment();
    }

    private void setEnvironment() {
        env = System.getProperty("JAILS_ENV");
    }

    private void loadDatabaseProperties() {

        String propertiesFile = System.getProperty("JAILS_ROOT").concat("\\config\\database.properties");
        try {
            prop = new Properties();
            prop.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            System.out.println("Could not load the property file");
            e.printStackTrace();
        }
    }

    public Database getDatabase() {
        if (database == null) {
            database = new DatabaseFactory(getAdapterName()).getDatabase(getDatabaseName(),
                    getUserName(),
                    getPassword());
        }
        return database;
    }

    private String getPassword() {
        return prop.getProperty(env.concat(".password"));
    }

    private String getUserName() {
        return prop.getProperty(env.concat(".username"));
    }

    private String getDatabaseName() {
        return prop.getProperty(env.concat(".database"));
    }

    private String getAdapterName() {
        return prop.getProperty(env.concat(".adapter"));
    }
}
