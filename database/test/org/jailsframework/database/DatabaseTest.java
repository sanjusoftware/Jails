package org.jailsframework.database;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.Assert.fail;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 17, 2010
 *          Time: 12:14:09 AM
 */
public class DatabaseTest {

    @Before
    public void setUp() {
        getDatabase().execute("CREATE TABLE IF NOT EXISTS USERS (name VARCHAR(25))");
        getDatabase().executeUpdate("INSERT INTO USERS VALUES ('test')");
    }

    @After
    public void tearDown() {
        getDatabase().executeUpdate("TRUNCATE TABLE USERS");
    }

    @Test
    public void shouldExecuteAGivenInsertQuery() {
        getDatabase().executeUpdate("TRUNCATE TABLE USERS");
        int result = getDatabase().executeUpdate("INSERT INTO USERS VALUES ('sanjeev')");
        Assert.assertEquals(1, result);
        assertSelectReturns("sanjeev");
    }

    @Test
    public void shouldExecuteAGivenUpdateQuery() {
        int result = getDatabase().executeUpdate("UPDATE USERS SET name = 'new name' WHERE name = 'test'");
        Assert.assertEquals(1, result);
        assertSelectReturns("new name");
    }

    @Test
    public void shouldExecuteADeleteQuery() {
        assertSelectReturns("test");
        int result = getDatabase().executeUpdate("DELETE FROM USERS WHERE name = 'test'");
        Assert.assertEquals(1, result);
        assertSelectThrowsException();
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

    private void assertSelectThrowsException() {
        ResultSet resultSet = getDatabase().executeQuery("Select * from users");
        try {
            resultSet.next();
            resultSet.getString("name");
            fail("Should have thrown exception");
        } catch (Exception e) {

        }
    }

    private IDatabase getDatabase() {
        return new MysqlDatabase("jdbc:mysql://localhost:3306/", "com.mysql.jdbc.Driver", "test", "root", "secret");
    }
}
