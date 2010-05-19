package org.jailsframework.database;

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

    public Database(String url, String driver, String name, String user, String password) {
        this.driver = driver;
        this.user = user;
        this.name = name;
        this.password = password;
        this.url = url;
        loadDriver();
    }

    public boolean execute(String query) {
        return fire(query);
    }

    public ResultSet executeQuery(String query) {
        return null;
    }

    private boolean loadDriver() {
        try {
            Class.forName(driver);
        }
        catch (Exception x) {
            System.out.println("Unable to load the driver class!");
            return false;
        }
        return true;
    }

    private boolean fire(String query) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(url + name, user, password);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("resultSet = " + resultSet);
        }
        catch (Exception e) {
            System.out.println("Could not get connection!");
            e.printStackTrace();
            return false;
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return true;
    }

    private void closeConnection(Connection connection) {
        if (connection != null) try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Could not close connection!");
            e.printStackTrace();
        }
    }

    private void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Could not close statement!");
            }
        }
    }
}
