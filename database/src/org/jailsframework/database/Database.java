package org.jailsframework.database;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 11, 2010
 *          Time: 12:10:35 AM
 */
public abstract class Database implements IDatabase {
    protected String name;
    protected String user;
    protected String password;

    public Database(String user, String name, String password) {
        this.user = user;
        this.name = name;
        this.password = password;
    }

    public abstract String getAdapter();

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public boolean execute(String query) {
        return false;
    }

    public List<Record> executeQuery(String query) {
        return new ArrayList<Record>();
    }
}
