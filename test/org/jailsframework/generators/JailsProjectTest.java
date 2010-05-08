package org.jailsframework.generators;

import junit.framework.Assert;
import org.jailsframework.JailsProjectTestBase;
import org.jailsframework.database.IMigration;
import org.jailsframework.database.Migration;
import org.jailsframework.exceptions.InvalidPathException;
import org.junit.Before;
import org.junit.Test;

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
            protected List<IMigration> getMigrations() {
                ArrayList<IMigration> migrations = new ArrayList<IMigration>();
                migrations.add(getTestMigration(1232L));
                migrations.add(getTestMigration(1234L));
                migrations.add(getTestMigration(1236L));
                migrations.add(getTestMigration(1235L));
                migrations.add(getTestMigration(1237L));
                return migrations;
            }
        };
        project.create();
    }

    @Test
    public void shouldGenerateAnMVCJavaProjectForValidProjectPath() {
        Assert.assertTrue(project.create());
    }

    @Test
    public void shouldGenerateAModelGivenAValidName() {
        Assert.assertTrue("Should have created model \"employee\"", project.addModel("employee"));
    }

    @Test(expected = InvalidPathException.class)
    public void shouldRaiseInvalidPathExceptionForWrongProjectPath() {
        new JailsProject("InvalidPath", "").create();
    }

    @Test
    public void shouldMigrateUpDBToTheLatestMigrationWhenMigrated() {
        project.create();
        Assert.assertEquals("1237", project.migrate());
    }

    @Test
    public void shouldMigrateTheDBToGivenMigrationWhenMigrated() {
        project.create();
        Assert.assertEquals("1236", project.migrate(1236L));
    }

    @Test
    public void shouldMigrateDownTheDBToGivenMigrationWhenMigrated() {
        project.create();
        Assert.assertEquals("1237", project.migrate());
        Assert.assertEquals("1232", project.migrate(1232L));
        Assert.assertEquals("1236", project.migrate(1236L));
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
