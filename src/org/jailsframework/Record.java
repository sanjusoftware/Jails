package org.jailsframework;

import org.jailsframework.database.Column;
import org.jailsframework.database.IDatabase;
import org.jailsframework.querybuilder.Insert;
import org.jailsframework.util.StringUtil;

import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 9, 2010
 *          Time: 11:07:25 AM
 */
public abstract class Record implements IRecord {
    private List<Column> columns;
    private IDatabase database;

    public boolean create() {
        database.executeUpdate(new Insert().into(table()).values(columns).build());
        return false;
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }

    private String table() {
        return new StringUtil(this.getClass().getSimpleName()).tabelize();
    }
}
