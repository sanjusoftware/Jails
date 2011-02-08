package org.jailsframework.querybuilder;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 28, 2010
 *          Time: 12:11:58 PM
 */
public class Select extends ISelectQuery {
    private String table;
    private String columns;
    private List<Where> whereClauses;

    public ISelectQuery from(String table) {
        this.table = table;
        return this;
    }

    public ISelectQuery where(Where whereClause) {
        whereClauses = new ArrayList<Where>();
        whereClauses.add(whereClause);
        return this;
    }

    public ISelectQuery and(Where where) {
        addWhereClause(where, Operator.AND);
        return this;
    }

    public ISelectQuery or(Where where) {
        addWhereClause(where, Operator.OR);
        return this;
    }

    public ISelectQuery columns(String comaSeparatedColumns) {
        this.columns = comaSeparatedColumns;
        return this;
    }

    private void addWhereClause(Where where, final Operator operator) {
        where.setOperator(operator);
        if (whereClauses == null) {
            whereClauses = new ArrayList<Where>();
        }
        whereClauses.add(where);
    }

    private String getColumns() {
        return columns == null ? "*" : columns;
    }

    private String getWhereClauses() {
        if (whereClauses == null) return "";
        String fullWhereClause = " WHERE";
        for (Where whereClause : whereClauses) {
            fullWhereClause += whereClause.toString();
        }
        return fullWhereClause;
    }

    @Override
    public ResultSet executeQuery() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int executeUpdate() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String query() {
        return "SELECT " + getColumns() + " FROM " + table + getWhereClauses() + ";";
    }
}
