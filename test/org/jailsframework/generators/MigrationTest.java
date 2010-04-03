package org.jailsframework.generators;

import junit.framework.Assert;
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
        System.setProperty("JAILS_ROOT", "test");
    }

    @After
    public void tearDown() {
        System.setProperty("JAILS_ROOT", "");
    }

    @Test
    public void shouldGenerateNewMigrationWithTheGivenName() {
        Assert.assertTrue(new Migration("CreateTableFoo").generate());
    }
}
