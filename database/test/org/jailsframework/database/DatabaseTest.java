package org.jailsframework.database;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
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

    @Before
    public void setUp() {
        getDatabase().executeUpdate("TRUNCATE users");
        getDatabase().executeUpdate("INSERT INTO USERS VALUES ('test')");
    }

    @After
    public void tearDown() {
        getDatabase().executeUpdate("TRUNCATE users");
    }


    @Test
    public void shouldExecuteAGivenInsertQuery() {
        getDatabase().executeUpdate("TRUNCATE users");
        int result = getDatabase().executeUpdate("INSERT INTO USERS VALUES ('sanjeev')");
        Assert.assertEquals(1, result);
        assertSelectReturns("sanjeev");
    }

    @Test
    public void shouldExecuteAGivenDeleteQuery() {
        int result = getDatabase().executeUpdate("UPDATE USERS SET name = 'new name' WHERE name = 'test'");
        Assert.assertEquals(1, result);
        assertSelectReturns("new name");
    }

    @Test
    public void shouldExecuteAGivenSelectQuery() {
        assertSelectReturns("test");
    }

    @Test
    public void shouldExecuteAGivenQuery() {
        Assert.assertTrue(getDatabase().execute("Select * from users"));
    }

    private void assertSelectReturns(String value) {
        ResultSet resultSet = getDatabase().executeQuery("Select * from users");
        try {
            resultSet.next();
            String name = resultSet.getString("name");
            Assert.assertEquals(value, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private IDatabase getDatabase() {
        return new MysqlDatabase("jdbc:mysql://localhost:3306/", "com.mysql.jdbc.Driver", "test", "root", "");
    }
}
