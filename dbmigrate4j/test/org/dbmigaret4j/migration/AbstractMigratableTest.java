package org.dbmigaret4j.migration;

import junit.framework.Assert;
import org.jailsframework.database.IDatabase;
import org.jailsframework.util.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:08:43 AM
 */

public class AbstractMigratableTest {

    private IMigratable migratable;

    @Before
    public void setUp() {
        migratable = new AbstractMigratable() {
            @Override
            public String getMigrationPackage() {
                return "migrations";
            }

            @Override
            public String getMigrationPath() {
                return "dbmigrate4j/test/migrations";
            }

            @Override
            public IDatabase getDatabase() {
                return null;
            }

            @Override
            public String getEnvironment() {
                return "development";
            }

            @Override
            public String getMigrationsPropertiesFilePath() {
                return "dbmigrate4j/test/versions.properties";
            }
        };
    }

    @After
    public void tearDown() {
        FileUtil.emptyDirRecursively(new File(migratable.getMigrationPath()), false);
    }

    @Test
    public void shouldMigrateUpDBToTheLatestMigrationWhenMigrated() {
        migratable.addMigration("migration1");
        migratable.addMigration("migration2");
        compileMigrations();
        loadMigrations();
        migratable.migrate();
//        Assert.assertEquals("1237", migratable.migrate());
    }

    @Test
    public void shouldMigrateTheDBToGivenMigrationWhenMigrated() {
        Assert.assertEquals("1236", migratable.migrate(1236L));
    }

    @Test
    public void shouldMigrateDownTheDBToGivenMigrationWhenMigrated() {
        Assert.assertEquals("1237", migratable.migrate());
        Assert.assertEquals("1232", migratable.migrate(1232L));
        Assert.assertEquals("1236", migratable.migrate(1236L));
    }

    @Test
    public void shouldExecuteTheQueriesInMigrateUpIfMigratedUp() {
        Assert.assertEquals("1237", migratable.migrate());
    }


    private void compileMigrations() {
        File[] migrationClassFiles = new File(migratable.getMigrationPath()).listFiles();

        for (File migrationFile : migrationClassFiles) {
            if (migrationFile.getName().startsWith("Migration") && migrationFile.getName().endsWith(".java")) {
                compile(migrationFile);
            }
        }
    }

    private void compile(File migrationFile) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler.run(null, null, null, migrationFile.getAbsolutePath()) != 0) {
            System.err.println("Could not compile file : " + migrationFile.getAbsolutePath());
            System.exit(0);
        }
    }

    private void loadMigrations() {
        File[] migrationClassFiles = new File(migratable.getMigrationPath()).listFiles();

        for (File migrationFile : migrationClassFiles) {
            if (migrationFile.getName().startsWith("Migration") && migrationFile.getName().endsWith(".java")) {
                load(migrationFile);
            }
        }
    }

    private void load(File migrationFile) {
        URL[] urls = null;
        try {
            urls = new URL[]{new File("dbmigrate4j/test/").toURI().toURL()};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLClassLoader cl = new URLClassLoader(urls);
        try {
            String className = migratable.getMigrationPackage() + "." + migrationFile.getName().substring(0, migrationFile.getName().lastIndexOf('.'));
            Class.forName(className, true, cl).newInstance();
            System.out.println("loaded = " + className);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
