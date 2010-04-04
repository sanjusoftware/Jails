package org.jailsframework.loaders;

import junit.framework.Assert;
import org.jailsframework.database.Database;
import org.jailsframework.generators.JailsProject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 2:50:13 PM
 */
public class DatabaseConfigurationTest {
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
    public void shouldReadTheDatabasePropertiesFileAndLoadTheDatabaseConfiguration() {
        Database database = DatabaseConfiguration.getInstance(project).getDatabase();
        Assert.assertEquals("mysql", database.getAdapter());
        Assert.assertEquals("root", database.getUsername());
        Assert.assertEquals("password", database.getPassword());
        Assert.assertEquals("jails_development", database.getDatabase());
    }
}
