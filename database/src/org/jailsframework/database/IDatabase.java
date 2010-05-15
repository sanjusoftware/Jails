package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 3, 2010
 *          Time: 9:50:57 AM
 */
public interface IDatabase {
    String getAdapter();

    String getDatabase();

    String getUsername();

    String getPassword();

    void execute(String query);
}
