package org.jailsframework.database.querybuilder;

import org.jailsframework.database.Column;

import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 9, 2010
 *          Time: 11:13:52 AM
 */
public class Insert extends AbstractQueryBuilder {
    private String table;
    private List<Column> columns;

    public Insert into(String table) {
        this.table = table;
        return this;
    }

    public String build() {
        return "INSERT INTO " + table + " VALUES " + columns;
    }

    public IQueryBuilder values(List<Column> columns) {
        this.columns = columns;
        return this;
    }
}
