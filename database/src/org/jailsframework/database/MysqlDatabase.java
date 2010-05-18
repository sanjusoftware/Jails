package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 2, 2010
 *          Time: 9:34:18 PM
 */
public class MysqlDatabase extends Database {
    public MysqlDatabase(String url, String driver, String database, String user, String password) {
        super(url, driver, database, user, password);
    }
}
