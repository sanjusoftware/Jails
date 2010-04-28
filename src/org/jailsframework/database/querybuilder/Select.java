package org.jailsframework.database.querybuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 28, 2010
 *          Time: 12:11:58 PM
 */
public class Select implements ISelectQueryBuilder {
    private String table;
    private String columns;
    private List<Where> whereClauses;

    public IQueryBuilder from(String table) {
        this.table = table;
        return this;
    }

    public IQueryBuilder where(Where whereClause) {
        whereClauses = new ArrayList<Where>();
        whereClauses.add(whereClause);
        return this;
    }

    public IQueryBuilder and(Where whereClause) {
        whereClause.setOperator(Operator.AND);
        if (whereClauses == null) {
            whereClauses = new ArrayList<Where>();
        }
        whereClauses.add(whereClause);
        return this;
    }

    public String build() {
        return "SELECT " + getColumns() + " FROM " + table + getWhereClauses() + ";";
    }

    public IQueryBuilder columns(String comaSeparatedColumns) {
        this.columns = comaSeparatedColumns;
        return this;
    }

    private String getColumns() {
        return columns == null ? "*" : columns;
    }

    public String getWhereClauses() {
        if (whereClauses == null) return "";
        String fullWhereClause = " WHERE";
        for (Where whereClause : whereClauses) {
            fullWhereClause += whereClause.toString();
        }
        return fullWhereClause;
    }
}
