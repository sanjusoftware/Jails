package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 2, 2010
 *          Time: 9:34:18 PM
 */
public class MysqlDatabase implements Database {
    private String database;
    private String username;
    private String password;

    public MysqlDatabase(String database, String username, String password) {
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public String getAdapter() {
        return "mysql";
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
