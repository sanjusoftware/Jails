package org.jailsframework.database.querybuilder;

import org.jailsframework.database.IDatabase;
import org.jailsframework.exceptions.JailsException;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 10, 2010
 *          Time: 11:46:21 PM
 */
public abstract class AbstractQueryBuilder implements IQueryBuilder {
    IDatabase database;

    public abstract String build();

    public boolean execute() {
        if (database == null) {
            throw new JailsException("No name specified !!");
        }
        return database.execute(build());
    }
}
