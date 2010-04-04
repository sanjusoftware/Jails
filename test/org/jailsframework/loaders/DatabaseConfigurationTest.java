package org.jailsframework.loaders;

import junit.framework.Assert;
import org.jailsframework.database.Database;
import org.jailsframework.util.TestHelper;
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

    @Before
    public void setup() {
        TestHelper.generateJailsProjectStructure("test", "JailsProjectTest");
        System.setProperty("JAILS_ROOT", "test\\JailsProjectTest");
        System.setProperty("JAILS_ENV", "development");
    }

    @After
    public void tearDown() {
        TestHelper.deleteJailsProjectStructure("test", "JailsProjectTest");
        System.setProperty("JAILS_ROOT", "");
        System.setProperty("JAILS_ENV", "");
    }

    @Test
    public void shouldReadTheDatabasePropertiesFileAndLoadTheDatabaseConfiguration() {
        Database database = DatabaseConfiguration.getInstance().getDatabase();
        Assert.assertEquals("mysql", database.getAdapter());
        Assert.assertEquals("root", database.getUsername());
        Assert.assertEquals("password", database.getPassword());
        Assert.assertEquals("jails_development", database.getDatabase());
    }
}
