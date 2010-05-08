package org.jailsframework.generators;

import org.jailsframework.database.IMigration;
import org.jailsframework.exceptions.InvalidPathException;
import org.jailsframework.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static org.jailsframework.util.FileUtil.makeDirectory;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:08:43 AM
 */

public class JailsProject {
    private String root;
    private String migrationsPath;
    private String dbPath;
    private String configPath;
    private String appPath;
    private String modelsPath;
    private String controllersPath;
    private String viewsPath;
    private String helpersPath;
    private String name;
    private File dbPropertiesFile;
    private File migrationsPropertiesFile;
    private String environment;

    public JailsProject(String path, String projectName) {
        this(path, projectName, "development");
    }

    public JailsProject(String path, String projectName, String env) {
        if (!new File(path).exists()) {
            throw new InvalidPathException("The project path : " + path + " is does not exists");
        }
        this.environment = env;
        name = projectName;
        root = path + "\\" + name;
        dbPath = root + "\\db";
        migrationsPath = dbPath + "\\migrate";
        configPath = root + "\\config";
        appPath = root + "\\app";
        modelsPath = appPath + "\\models";
        controllersPath = appPath + "\\controllers";
        viewsPath = appPath + "\\views";
        helpersPath = appPath + "\\helpers";
        dbPropertiesFile = new File(configPath, "database.properties");
        migrationsPropertiesFile = new File(dbPath, "versions.properties");
    }

    public boolean create() {
        return makeDirectory(root) &&
                makeDirectory(appPath) &&
                makeDirectory(modelsPath) &&
                makeDirectory(controllersPath) &&
                makeDirectory(viewsPath) &&
                makeDirectory(helpersPath) &&
                makeDirectory(configPath) &&
                makeDirectory(dbPath) &&
                makeDirectory(migrationsPath) &&
                createDatabasePropertiesFile() &&
                createMigrationPropertiesFile();
    }

    private boolean createMigrationPropertiesFile() {
        try {
            migrationsPropertiesFile.createNewFile();
            FileWriter fileWriter = new FileWriter(migrationsPropertiesFile);
            fileWriter.write("# This file is auto generated. Instead of editing this file, please use the\n" +
                    "# migrations feature of Jails to incrementally modify your database, and\n" +
                    "# then regenerate this version file.\n" +
                    "development=0\n" +
                    "test=0\n" +
                    "production=0");
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean createDatabasePropertiesFile() {
        try {
            dbPropertiesFile.createNewFile();
            FileWriter fileWriter = new FileWriter(dbPropertiesFile);
            fileWriter.write("development.adapter=mysql\n" +
                    "development.database=jails_development\n" +
                    "development.username=root\n" +
                    "development.password=password");
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public File getDbPropertiesFile() {
        return dbPropertiesFile;
    }

    public String getRoot() {
        return root;
    }

    public String getMigrationsPath() {
        return migrationsPath;
    }

    public String getDbPath() {
        return dbPath;
    }

    public String getConfigPath() {
        return configPath;
    }

    public String getAppPath() {
        return appPath;
    }

    public String getModelsPath() {
        return modelsPath;
    }

    public String getControllersPath() {
        return controllersPath;
    }

    public String getViewsPath() {
        return viewsPath;
    }

    public String getHelpersPath() {
        return helpersPath;
    }

    public String getName() {
        return name;
    }

    public String getEnvironment() {
        return environment;
    }

    public void destroy() {
        FileUtil.deleteDirRecursively(new File(root));
    }

    public List<IMigration> getMigrations() {
        List<IMigration> migrations = new ArrayList<IMigration>();
        File[] migrationFiles = new File(migrationsPath).listFiles();
        for (File migrationFile : migrationFiles) {
            migrations.add(instantiate(migrationFile.getName()));
        }
        return migrations;
    }

    private IMigration instantiate(String fileName) {
        try {
            String className = name.toLowerCase().concat(".db.migrate.").concat(fileName.substring(0, fileName.lastIndexOf('.')));
            return (IMigration) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long getCurrentDbVersion() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(migrationsPropertiesFile));
            return new Long(properties.getProperty(environment));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String migrate() {
        Long currentDbVersion = getCurrentDbVersion();
        List<IMigration> migrations = getMigrations();
        Collections.sort(migrations);
        for (IMigration migration : migrations) {
            Long version = migration.getVersion();
            if (currentDbVersion < version) {
                migration.up();
                currentDbVersion = version;
            }
        }
        return currentDbVersion + "";
    }
}
