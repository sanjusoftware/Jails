package org.jailsframework.database;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 11, 2010
 *          Time: 12:10:35 AM
 */
public abstract class Database implements IDatabase {
    private String name;
    private String user;
    private String password;
    private String driver;
    private String url;
    Statement statement = null;
    Connection connection = null;

    public Database(String url, String driver, String name, String user, String password) {
        this.driver = driver;
        this.user = user;
        this.name = name;
        this.password = password;
        this.url = url;
    }

    public boolean execute(String query) {
        loadDriver();
        try {
            connection = DriverManager.getConnection(url + name, user, password);
            statement = connection.createStatement();
            return statement.execute(query);
        }
        catch (Exception e) {
            throw new DBException("Error executing query: " + e);
        } finally {
            closeConnection();
        }
    }

    public ResultSet executeQuery(String query) {
        loadDriver();
        try {
            connection = DriverManager.getConnection(url + name, user, password);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            CachedRowSetImpl cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
            return cachedRowSet;
        }
        catch (Exception e) {
            throw new DBException("Error executing query: " + e);
        } finally {
            closeConnection();
        }
    }

    public int executeUpdate(String query) {
        loadDriver();
        try {
            connection = DriverManager.getConnection(url + name, user, password);
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        }
        catch (Exception e) {
            throw new DBException("Error executing query: " + e);
        } finally {
            closeConnection();
        }
    }

    private boolean loadDriver() {
        try {
            Class.forName(driver);
        }
        catch (Exception e) {
            throw new DBException("Unable to load the driver class!" + e);
        }
        return true;
    }

    private void closeConnection() {
        if (connection != null) try {
            connection.close();
        } catch (SQLException e) {
            throw new DBException("Could not close connection!" + e);
        }
    }
}
