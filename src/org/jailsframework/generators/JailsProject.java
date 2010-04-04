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

    public boolean generateStructure() {
        if (new File(path).exists()) {
            return makeProjectRootFolder() &&
                    makeAppFolder() &&
                    makeModelsFolder() &&
                    makeControllersFolder() &&
                    makeViewsFolder() &&
                    makeHelpersFolder() &&
                    makeConfigFolder() &&
                    makeDbFolder() &&
                    makeMigrationsFolder() &&
                    createDatabasePropertiesFile();
        } else {
            throw new InvalidPathException("The project path : " + path + " is does not exists");
        }
    }

    private boolean createDatabasePropertiesFile() {
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
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean makeMigrationsFolder() {
        return makeDirectory(getProjectRoot() + "\\db\\migrate");
    }

    private boolean makeDbFolder() {
        return makeDirectory(getProjectRoot() + "\\db");
    }

    private boolean makeConfigFolder() {
        return makeDirectory(getProjectRoot() + "\\config");
    }

    private boolean makeHelpersFolder() {
        return makeDirectory(getProjectRoot() + "\\app\\helpers");
    }

    private boolean makeViewsFolder() {
        return makeDirectory(getProjectRoot() + "\\app\\views");
    }

    private boolean makeControllersFolder() {
        return makeDirectory(getProjectRoot() + "\\app\\controllers");
    }

    private boolean makeModelsFolder() {
        return makeDirectory(getProjectRoot() + "\\app\\models");
    }

    private boolean makeAppFolder() {
        return makeDirectory(getProjectRoot() + "\\app");
    }

    private String getProjectRoot() {
        return path + "\\" + projectName;
    }

    private boolean makeProjectRootFolder() {
        return makeDirectory(getProjectRoot());
    }

    private boolean makeDirectory(String dirPath) {
        return new File(dirPath).mkdir();
    }
}
