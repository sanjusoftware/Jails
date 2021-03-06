package org.jailsframework.querybuilder;

import org.jailsframework.database.Column;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 9, 2010
 *          Time: 11:13:52 AM
 */
public class Insert extends DMLQuery {
    private String table;
    private List<Column> columns;

    public Insert into(String table) {
        this.table = table;
        return this;
    }

    public DMLQuery values(List<Column> columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public String query() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ResultSet executeQuery() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int executeUpdate() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
