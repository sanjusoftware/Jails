package org.jailsframework.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public Database(String driver, String name, String user, String password) {
        this.driver = driver;
        this.user = user;
        this.name = name;
        this.password = password;
    }

    public boolean execute(String query) {
        if (!loadDriver()) return false;
        if (!getConnection(query)) return false;
        return false;
    }

    public List<Record> executeQuery(String query) {
        return new ArrayList<Record>();
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

    private boolean getConnection(String query) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + name, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        }
        catch (SQLException e) {
            System.out.println("Could not get connection!");
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Could not close connection!");
                e.printStackTrace();
            }

        }
        return true;
    }
}
