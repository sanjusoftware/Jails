package org.jailsframework.generators;

import org.jailsframework.database.IDatabase;
import org.jailsframework.database.migration.IMigration;
import org.jailsframework.exceptions.JailsException;
import org.jailsframework.loaders.DatabaseConfiguration;
import org.jailsframework.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
    private Long currentDbVersion;

    public JailsProject(String path, String projectName) {
        this(path, projectName, "development");
    }

    public JailsProject(String path, String projectName, String env) {
        if (!new File(path).exists()) {
            throw new JailsException("The project path : " + path + " is does not exists");
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
        return FileUtil.makeDirectory(root) &&
                FileUtil.makeDirectory(appPath) &&
                FileUtil.makeDirectory(modelsPath) &&
                FileUtil.makeDirectory(controllersPath) &&
                FileUtil.makeDirectory(viewsPath) &&
                FileUtil.makeDirectory(helpersPath) &&
                FileUtil.makeDirectory(configPath) &&
                FileUtil.makeDirectory(dbPath) &&
                FileUtil.makeDirectory(migrationsPath) &&
                createDatabasePropertiesFile() &&
                createMigrationPropertiesFile();
    }

    public File getDbPropertiesFile() {
        return dbPropertiesFile;
    }

    public String getModelsPath() {
        return modelsPath;
    }

    public String getMigrationsPath() {
        return migrationsPath;
    }

    public String getModelPackage() {
        return name.toLowerCase().concat(".app.models");
    }

    public String getMigrationPackage() {
        return name.toLowerCase().concat(".db.migrate");
    }

    public String getEnvironment() {
        return environment;
    }

    public void destroy() {
        FileUtil.deleteDirRecursively(new File(root));
    }

    protected List<IMigration> getMigrations() {
        List<IMigration> migrations = new ArrayList<IMigration>();
        File[] migrationFiles = new File(migrationsPath).listFiles();
        for (File migrationFile : migrationFiles) {
            IMigration migration = instantiate(migrationFile.getName());
            migration.setDatabase(getDatabase());
            migrations.add(migration);
        }
        return migrations;
    }

    public String migrate() {
        return migrate(null);
    }

    public String migrate(Long toVersion) {
        loadCurrentDbVersion();
        List<IMigration> migrations = getMigrations();
        if (toVersion == null) {
            toVersion = getLatestMigrationVerion(migrations);
        }
        if (currentDbVersion < toVersion) {
            migrateUp(toVersion, migrations);
        } else {
            migrateDown(toVersion, migrations);
        }
        updateCurrentDbVersion();
        return currentDbVersion.toString();
    }

    private Long getLatestMigrationVerion(List<IMigration> migrations) {
        return migrations.get(migrations.size() - 1).getVersion();
    }

    public boolean addMigration(String name) {
        return new MigrationGenerator(this).generate(name);
    }

    public boolean addModel(String modelName) {
        return new ModelGenerator(this).generate(modelName);
    }

    public IDatabase getDatabase() {
        return DatabaseConfiguration.getInstance(this).getDatabase();
    }

    private void migrateDown(Long toVersion, List<IMigration> migrations) {
        for (IMigration migration : migrations) {
            Long version = migration.getVersion();
            if (currentDbVersion > version && version >= toVersion) {
                migration.executeDown();
                currentDbVersion = version;
            }
        }
    }

    private void migrateUp(Long toVersion, List<IMigration> migrations) {
        for (IMigration migration : migrations) {
            Long version = migration.getVersion();
            if (currentDbVersion < version && version <= toVersion) {
                migration.executeUp();
                currentDbVersion = version;
            }
        }
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

    private boolean createMigrationPropertiesFile() {
        try {
            FileUtil.createFile(migrationsPropertiesFile);
            FileWriter fileWriter = new FileWriter(migrationsPropertiesFile);
            fileWriter.write(
                    "# This file is auto generated. Instead of editing this file, please use the\n" +
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
            FileUtil.createFile(dbPropertiesFile);
            FileWriter fileWriter = new FileWriter(dbPropertiesFile);
            fileWriter.write("# This file is auto generated. Please provide the database details in here.\n" +
                    "development.adapter=mysql\n" +
                    "development.url=jdbc:mysql://localhost:3306/\n" +
                    "development.driver=com.mysql.jdbc.Driver\n" +
                    "development.name=jails_development\n" +
                    "development.user=root\n" +
                    "development.password=password\n\n" +
                    "test.adapter=mysql\n" +
                    "test.url=jdbc:mysql://localhost:3306/\n" +
                    "test.driver=com.mysql.jdbc.Driver\n" +
                    "test.name=jails_test\n" +
                    "test.user=root\n" +
                    "test.password=password\n\n" +
                    "production.adapter=mysql\n" +
                    "production.url=jdbc:mysql://localhost:3306/\n" +
                    "production.driver=com.mysql.jdbc.Driver\n" +
                    "production.name=jails_production\n" +
                    "production.user=root\n" +
                    "production.password=password\n\n");
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void updateCurrentDbVersion() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(migrationsPropertiesFile));
            properties.setProperty(environment, currentDbVersion.toString());
            properties.store(new FileOutputStream(migrationsPropertiesFile), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCurrentDbVersion() {
        try {
            if (currentDbVersion == null) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(migrationsPropertiesFile));
                currentDbVersion = new Long(properties.getProperty(environment));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
