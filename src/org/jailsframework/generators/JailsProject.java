package org.jailsframework.generators;

import org.dbmigaret4j.migration.AbstractMigratable;
import org.jailsframework.database.IDatabase;
import org.jailsframework.exceptions.JailsException;
import org.jailsframework.loaders.DatabaseConfiguration;
import org.jailsframework.util.FileUtil;

import java.io.File;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:08:43 AM
 */

public class JailsProject extends AbstractMigratable {
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
    private String environment;
    private File migrationsPropertiesFile;

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

    public String getModelPackage() {
        return name.toLowerCase().concat(".app.models");
    }

    public String getEnvironment() {
        return environment;
    }

    public void destroy() {
        FileUtil.deleteDirRecursively(new File(root));
    }

    public String getMigrationPackage() {
        return name.toLowerCase().concat(".db.migrate");
    }

    @Override
    public String getMigrationPath() {
        return this.migrationsPath;
    }

    public IDatabase getDatabase() {
        return DatabaseConfiguration.getInstance(this).getDatabase();
    }

    @Override
    public String getMigrationsPropertiesFilePath() {
        return dbPath + "\\versions.properties";
    }

    public boolean addModel(String modelName) {
        return new ModelGenerator(this).generate(modelName);
    }

    private boolean createMigrationPropertiesFile() {
        String content = "# This file is auto generated. Instead of editing this file, please use the\n" +
                "# migrations feature of Jails to incrementally modify your database, and\n" +
                "# then regenerate this version file.\n" +
                "development=0\n" +
                "test=0\n" +
                "production=0";
        return FileUtil.createFileWithContent(migrationsPropertiesFile, content);
    }

    private boolean createDatabasePropertiesFile() {
        String content = "# This file is auto generated. Please provide the database details in here.\n" +
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
                "production.password=password\n\n";
        return FileUtil.createFileWithContent(dbPropertiesFile, content);
    }

}
