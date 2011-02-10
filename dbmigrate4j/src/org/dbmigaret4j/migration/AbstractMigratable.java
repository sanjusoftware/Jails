package org.dbmigaret4j.migration;

import org.jailsframework.database.IDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public abstract class AbstractMigratable implements IMigratable {
    private Long currentDbVersion;

    public abstract String getMigrationPackage();

    public abstract String getMigrationPath();

    public Long migrate() {
        return migrate(null);
    }

    public Long migrate(Long toVersion) {
        loadCurrentDbVersion();
        List<IMigration> migrations = getMigrations();
        if (toVersion == null) {
            toVersion = getLatestMigrationVersion(migrations);
        }
        validateVersion(migrations, toVersion);
        if (currentDbVersion.equals(toVersion)) {
            throw new RuntimeException("Database is already up to date");
        }
        if (currentDbVersion < toVersion) {
            migrateUp(toVersion, migrations);
        } else {
            migrateDown(toVersion, migrations);
        }
        updateCurrentDbVersion();
        return currentDbVersion;
    }

    public Long addMigration(String name) {
        return new MigrationGenerator(getMigrationPath(), getMigrationPackage()).generate(name);
    }

    public abstract IDatabase getDatabase();

    public abstract String getEnvironment();

    public abstract String getMigrationsPropertiesFilePath();

    private void validateVersion(List<IMigration> migrations, Long toVersion) {
        boolean valid = false;
        for (IMigration migration : migrations) {
            if(migration.getVersion().equals(toVersion)) {
                valid = true;
            }
        }
        if (!valid){
            throw new RuntimeException("The version provided is invalid");
        }
    }

    private Long getLatestMigrationVersion(List<IMigration> migrations) {
        if (migrations.size() == 0) {
            throw new RuntimeException("No migrations found");
        }
        return migrations.get(migrations.size() - 1).getVersion();
    }

    private void migrateDown(Long toVersion, Iterable<IMigration> migrations) {
        for (IMigration migration : migrations) {
            Long version = migration.getVersion();
            if (currentDbVersion > version && version >= toVersion) {
                migration.executeDown();
                currentDbVersion = version;
            }
        }
    }

    private void migrateUp(Long toVersion, Iterable<IMigration> migrations) {
        for (IMigration migration : migrations) {
            Long version = migration.getVersion();
            if (currentDbVersion < version && version <= toVersion) {
                migration.executeUp();
                currentDbVersion = version;
            }
        }
    }

    private IMigration instantiate(File migrationFile) {
        String migrationsClassPath = getMigrationsClassPath();
        String fileName = migrationFile.getName();
        try {
            String className = getMigrationPackage().concat(".").concat(fileName.substring(0, fileName.lastIndexOf('.')));
            if (migrationsClassPath != null) {
                return (IMigration) Class.forName(className, true, getURLClassLoaderForMigrationsClassPath(migrationsClassPath)).newInstance();
            } else {
                return (IMigration) Class.forName(className).newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load the migration class : " + fileName + ". " +
                    "\nPlease make sure that all migrations are either compiled and present in the classpath or " +
                    "override the getMigrationsClassPath() method to provide the classpath explicitly." + e);
        }
    }

    private URLClassLoader getURLClassLoaderForMigrationsClassPath(String migrationsClassPath) {
        URL[] urls = null;
        try {
            urls = new URL[]{new File(migrationsClassPath).toURI().toURL()};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new URLClassLoader(urls);
    }

    public String getMigrationsClassPath() {
        return null;
    }

    private void updateCurrentDbVersion() {
        try {
            Properties properties = new Properties();
            File migrationPropertiesFile = new File(getMigrationsPropertiesFilePath());
            properties.load(new FileInputStream(migrationPropertiesFile));
            properties.setProperty(getEnvironment(), currentDbVersion.toString());
            properties.store(new FileOutputStream(migrationPropertiesFile), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCurrentDbVersion() {
        try {
            if (currentDbVersion == null) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(new File(getMigrationsPropertiesFilePath())));
                currentDbVersion = new Long(properties.getProperty(getEnvironment()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<IMigration> getMigrations() {
        List<IMigration> migrations = new ArrayList<IMigration>();
        File migrationsDir = new File(getMigrationPath());
        File[] migrationClassFiles = migrationsDir.listFiles();

        for (File migrationFile : migrationClassFiles) {
            if (migrationFile.getName().startsWith("Migration") && migrationFile.getName().endsWith(".java")) {
                IMigration migration = instantiate(migrationFile);
                migration.setDatabase(getDatabase());
                migrations.add(migration);
            }
        }
        return migrations;
    }
}
