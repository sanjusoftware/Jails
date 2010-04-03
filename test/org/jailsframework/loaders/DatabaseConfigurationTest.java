package org.jailsframework.loaders;

import junit.framework.Assert;
import org.jailsframework.database.Database;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Sanjeev Mishra
 * Date: Apr 2, 2010
 * Time: 6:34:16 PM
 */
public class DatabaseConfigurationTest {

    @Before
    public void setup() {
        System.setProperty("JAILS_ROOT", "D:\\projects\\java\\Jails");
        System.setProperty("JAILS_ENV", "development");
    }

    @Test
    public void shouldReadTheDatabasePropertiesFileAndLoadTheDatabaseConfiguration() {
        Database database = DatabaseConfiguration.getInstance().getDatabase();
        Assert.assertEquals("mysql", database.getAdapter());
    }
}
