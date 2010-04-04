package org.jailsframework.generators;

import junit.framework.Assert;
import org.jailsframework.util.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:08:43 AM
 */

public class MigrationTest {
    @Before
    public void setup() {
        TestHelper.generateJailsProjectStructure("test", "JailsProjectTest");
        System.setProperty("JAILS_ROOT", "test\\JailsProjectTest");
        System.setProperty("APP_NAME", "JailsProjectTest");
    }

    @After
    public void tearDown() {
        TestHelper.deleteJailsProjectStructure("test", "JailsProjectTest");
        System.setProperty("JAILS_ROOT", "");
        System.setProperty("APP_NAME", "");
    }

    @Test
    public void shouldGenerateNewMigrationWithTheGivenName() {
        Assert.assertTrue(new Migration("CreateTableFoo").generate());
    }
}
