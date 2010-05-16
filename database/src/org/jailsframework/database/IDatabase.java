package org.jailsframework.database;

import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 3, 2010
 *          Time: 9:50:57 AM
 */
public interface IDatabase {
    String getAdapter();

    String getName();

    String getUser();

    String getPassword();

    boolean execute(String query);

    List<Record> executeQuery(String query);
}
