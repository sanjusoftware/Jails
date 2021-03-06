package org.jailsframework.database;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 3, 2010
 *          Time: 6:38:43 PM
 */
public class DatabaseFactoryTest {

    @Test(expected = DBException.class)
    public void shouldThroughUnsupportedDatabaseExceptionIfAdapterNotSupported() {
        DatabaseFactory.getDatabase(this.toString(), "url", null, null, null, null);
    }

    @Test
    public void shouldReturnMySQLDatabaseInstanceIfGivenMYSQLAdapter() {
        Assert.assertTrue(
                DatabaseFactory.getDatabase("mysql", "url", null, null, null, null) instanceof MysqlDatabase);
    }
}
