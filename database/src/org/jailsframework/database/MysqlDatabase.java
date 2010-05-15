package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 2, 2010
 *          Time: 9:34:18 PM
 */
public class MysqlDatabase extends Database {

    public MysqlDatabase(String database, String username, String password) {
        super(username, database, password);
    }

    public String getAdapter() {
        return "mysql";
    }

}