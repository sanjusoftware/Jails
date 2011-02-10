package org.dbmigaret4j.migration;

import org.jailsframework.database.IDatabase;

public interface IMigratable {

    String getMigrationPackage();

    String getMigrationPath();

    String getEnvironment();

    IDatabase getDatabase();

    String getMigrationsPropertiesFilePath();

    Long migrate();

    Long migrate(Long toVersion);

    Long addMigration(String name);

    String getMigrationsClassPath();
}
