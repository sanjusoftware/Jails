package org.dbmigaret4j.migration;

import junit.framework.Assert;
import org.jailsframework.util.FileUtil;
import org.junit.After;
import org.junit.Test;

import java.io.File;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:08:43 AM
 */

public class MigrationGeneratorTest {

    private String migrationsPath = "dbmigrate4j/test/migrations";

    @After
    public void setUp() {
        FileUtil.emptyDirRecursively(new File(migrationsPath), false);
    }

    @Test
    public void shouldGenerateNewMigrationWithTheGivenName() {
        Assert.assertNotNull(new MigrationGenerator(migrationsPath, "migrations").generate("migrationFileName"));
        Assert.assertTrue(new File(migrationsPath).listFiles()[0].getName().endsWith("MigrationFileName.java"));
    }
}
