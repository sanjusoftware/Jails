package org.jailsframework.database;

import junit.framework.Assert;
import org.jailsframework.JailsProjectTestBase;
import org.jailsframework.generators.JailsProject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 3:15:13 PM
 */
public class MigratorTest extends JailsProjectTestBase {

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
        project.create();
    }

    @Test
    public void shouldPickUpTheNextMigrationFileToRunGivenDBVersionToMigrateTo() {
        String currentDbVersion = new Migrator(project).migrate();
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
