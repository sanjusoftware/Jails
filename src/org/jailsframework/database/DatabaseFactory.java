package org.jailsframework.database;

import org.jailsframework.exceptions.UnsupportedDatabaseException;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 3, 2010
 *          Time: 9:42:17 AM
 */
public class DatabaseFactory {
    private String adapter;

    public DatabaseFactory(String adapterName) {
        this.adapter = adapterName;
    }

    public Database getDatabase(String databaseName, String userName, String password) {
        if ("mysql".equals(adapter)) {
            return new MysqlDatabase(databaseName, userName, password);
        } else {
            throw new UnsupportedDatabaseException("The \"" + adapter + "\" database is not yet supported !!");
        }
    }
}
