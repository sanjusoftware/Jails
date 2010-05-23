package org.jailsframework.database;

import junit.framework.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 17, 2010
 *          Time: 12:14:09 AM
 */
public class DatabaseTest {
    @Test
    public void shouldExecuteAGivenSelectQuery() {
        ResultSet resultSet = getDatabase().executeQuery("Select * from users");
        try {
            resultSet.next();
            String name = resultSet.getString("name");
            Assert.assertEquals("sanjeev", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldExecuteAGivenUpdateQuery() {
        Assert.assertTrue(getDatabase().execute("Select * from users"));
    }

    private IDatabase getDatabase() {
        return new MysqlDatabase("jdbc:mysql://localhost:3306/", "com.mysql.jdbc.Driver", "test", "root", "");
    }
}
