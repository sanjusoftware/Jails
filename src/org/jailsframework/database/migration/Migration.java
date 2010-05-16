package org.jailsframework.database.migration;

import org.jailsframework.database.IDatabase;
import org.jailsframework.exceptions.JailsException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 5:09:30 PM
 */
public abstract class Migration implements IMigration {
    private IDatabase database;
    private List<String> queries = new ArrayList<String>();

    public final int compareTo(Object obj) {
        if (obj instanceof IMigration) {
            IMigration migration = (IMigration) obj;
            Long thisVersion = getVersion();
            Long otherVersion = migration.getVersion();
            if (thisVersion < otherVersion)
                return -1;
            else if (thisVersion > otherVersion)
                return 1;
            else
                return 0;
        } else {
            throw new IllegalArgumentException("The obj to be compared must also be an " +
                    " instance of a IMigration object.");
        }
    }

    public final void executeUp() {
        up();
        executeAll();
    }

    public final void executeDown() {
        down();
        executeAll();
    }

    public void setDatabase(IDatabase database) {
        this.database = database;
    }

    private void executeAll() {
        if (database == null) {
            throw new JailsException("Database is not specified !!");
        }
        for (String query : queries) {
            database.execute(query);
        }
    }

    public final void addAction(String query) {
        this.queries.add(query);
    }
}
