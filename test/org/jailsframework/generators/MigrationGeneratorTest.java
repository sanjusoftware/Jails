package org.jailsframework.generators;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:08:43 AM
 */

public class MigrationGeneratorTest {
    JailsProject project;

    @Before
    public void setUp() {
        project = new JailsProject("test", "jailsproject");
        project.create();
    }

    @After
    public void tearDown() {
        project.destroy();
    }

    @Test
    public void shouldGenerateNewMigrationWithTheGivenName() {
        Assert.assertTrue(new MigrationGenerator(project).generate("migrationFileName"));
        Assert.assertTrue(new File(project.getMigrationsPath()).listFiles()[0].getName().contains("migrationFileName"));
    }
}
