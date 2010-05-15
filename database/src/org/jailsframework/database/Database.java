package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 11, 2010
 *          Time: 12:10:35 AM
 */
public abstract class Database implements IDatabase {
    protected String database;
    protected String username;
    protected String password;

    public Database(String username, String database, String password) {
        this.username = username;
        this.database = database;
        this.password = password;
    }

    public abstract String getAdapter();

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void execute(String query) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
