package org.jailsframework.generators;

import org.jailsframework.exceptions.InvalidPathException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Sanjeev Mishra
 * Date: Mar 30, 2010
 * Time: 11:26:41 PM
 */
public class JailsProject {
    private String path;
    private String projectName;

    public JailsProject(String path, String projectName) {
        this.path = path;
        this.projectName = projectName;
    }

    public void generateStructure() {
        if (new File(path).exists()) {
            makeProjectRootFolder();
            makeAppFolder();
            makeModelsFolder();
            makeControllersFolder();
            makeViewsFolder();
            makeHelpersFolder();
            makeConfigFolder();
            makeDbFolder();
            makeMigrationsFolder();
            createDatabasePropertiesFile();
        } else {
            throw new InvalidPathException("The project path : " + path + " is does not exists");
        }
    }

    private void createDatabasePropertiesFile() {
        File databasePropertiesFile = new File(getProjectRoot() + "\\config", "database.properties");
        try {
            databasePropertiesFile.createNewFile();
            FileWriter fileWriter = new FileWriter(databasePropertiesFile);
            fileWriter.write("development.adapter=mysql\n" +
                    "development.database=jails_development\n" +
                    "development.username=root\n" +
                    "development.password=password");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeMigrationsFolder() {
        makeDirectory(getProjectRoot() + "\\db\\migrate");
    }

    private void makeDbFolder() {
        makeDirectory(getProjectRoot() + "\\db");
    }

    private void makeConfigFolder() {
        makeDirectory(getProjectRoot() + "\\config");
    }

    private void makeHelpersFolder() {
        makeDirectory(getProjectRoot() + "\\app\\helpers");
    }

    private void makeViewsFolder() {
        makeDirectory(getProjectRoot() + "\\app\\views");
    }

    private void makeControllersFolder() {
        makeDirectory(getProjectRoot() + "\\app\\controllers");
    }

    private void makeModelsFolder() {
        makeDirectory(getProjectRoot() + "\\app\\models");
    }

    private void makeAppFolder() {
        makeDirectory(getProjectRoot() + "\\app");
    }

    private String getProjectRoot() {
        return path + "\\" + projectName;
    }

    private void makeProjectRootFolder() {
        makeDirectory(getProjectRoot());
    }

    private void makeDirectory(String dirPath) {
        new File(dirPath).mkdir();
    }
}
