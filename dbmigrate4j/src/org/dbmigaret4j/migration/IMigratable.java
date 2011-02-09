package org.dbmigaret4j.migration;

import org.jailsframework.database.IDatabase;

public interface IMigratable {

    String getMigrationPackage();

    String getMigrationPath();

    String getEnvironment();

    IDatabase getDatabase();

    String getMigrationsPropertiesFilePath();

    String migrate();

    String migrate(Long toVersion);

    boolean addMigration(String name);
}
