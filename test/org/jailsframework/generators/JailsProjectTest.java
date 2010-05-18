package org.jailsframework.generators;

import junit.framework.Assert;
import org.jailsframework.JailsProjectTestBase;
import org.jailsframework.database.IDatabase;
import org.jailsframework.database.MysqlDatabase;
import org.jailsframework.database.migration.IMigration;
import org.jailsframework.database.migration.Migration;
import org.jailsframework.exceptions.JailsException;
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
                IDatabase mysqlDatabase = new MysqlDatabase("jdbc:mysql://localhost:3306/", null, null, null, null);
                List<IMigration> migrations = new ArrayList<IMigration>();
                migrations.add(getTestMigration(1232L, mysqlDatabase));
                migrations.add(getTestMigration(1234L, mysqlDatabase));
                migrations.add(getTestMigration(1236L, mysqlDatabase));
                migrations.add(getTestMigration(1235L, mysqlDatabase));
                migrations.add(getTestMigration(1237L, mysqlDatabase));
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

    @Test(expected = JailsException.class)
    public void shouldRaiseInvalidPathExceptionForWrongProjectPath() {
        new JailsProject("InvalidPath", "").create();
    }

    @Test
    public void shouldMigrateUpDBToTheLatestMigrationWhenMigrated() {
        Assert.assertEquals("1237", project.migrate());
    }

    @Test
    public void shouldMigrateTheDBToGivenMigrationWhenMigrated() {
        Assert.assertEquals("1236", project.migrate(1236L));
    }

    @Test
    public void shouldMigrateDownTheDBToGivenMigrationWhenMigrated() {
        Assert.assertEquals("1237", project.migrate());
        Assert.assertEquals("1232", project.migrate(1232L));
        Assert.assertEquals("1236", project.migrate(1236L));
    }

    private Migration getTestMigration(final Long version, final IDatabase database) {
        return new Migration() {

            {
                this.setDatabase(database);
            }

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
