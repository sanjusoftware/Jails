package org.jailsframework.generators;

import junit.framework.Assert;
import org.jailsframework.JailsProjectTestBase;
import org.jailsframework.database.IMigration;
import org.jailsframework.database.Migration;
import org.jailsframework.exceptions.InvalidPathException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:51:30 PM
 */

public class JailsProjectTest extends JailsProjectTestBase {

    @Before
    public void setUp() {
        project = new JailsProject("test", "jailsproject") {
            @Override
            public List<IMigration> getMigrations() {
                ArrayList<IMigration> migrations = new ArrayList<IMigration>();
                migrations.add(getTestMigration(1234L));
                migrations.add(getTestMigration(1235L));
                migrations.add(getTestMigration(1236L));
                migrations.add(getTestMigration(1237L));
                return migrations;
            }
        };
    }

    @Test
    public void shouldGenerateAnMVCJavaProjectForValidProjectPath() {
        boolean b = project.create();
        Assert.assertTrue(b);
        Assert.assertTrue("The versions.properties file is not generated",
                new File(project.getDbPath().concat("\\versions.properties")).exists());
        Assert.assertTrue("The database.properties file is not generated",
                new File(project.getConfigPath().concat("\\database.properties")).exists());
    }

    @Test(expected = InvalidPathException.class)
    public void shouldRaiseInvalidPathExceptionForWrongProjectPath() {
        new JailsProject("InvalidPath", "").create();
    }

    @Test
    public void shouldPickUpTheNextMigrationFileToRunGivenDBVersionToMigrateTo() {
        project.create();
        String currentDbVersion = project.migrate();
        Assert.assertEquals("1237", currentDbVersion);
    }

    private Migration getTestMigration(final Long version) {
        return new Migration() {

            public void up() {

            }

            public void down() {

            }

            public Long getVersion() {
                return version;
            }
        };
    }
}
