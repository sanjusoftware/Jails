package org.jailsframework.database;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 17, 2010
 *          Time: 12:14:09 AM
 */
public class DatabaseTest {
    @Test
    public void shouldExecuteAGivenQuery() {
        Assert.assertTrue(getDatabase().execute("Select * from users"));
    }

    private IDatabase getDatabase() {
        return new MysqlDatabase("jdbc:mysql://localhost:3306/", "com.mysql.jdbc.Driver", "test", "root", "");
    }
}
