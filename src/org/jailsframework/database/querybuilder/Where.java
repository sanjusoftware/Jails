package org.jailsframework.database.querybuilder;

import java.util.ArrayList;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 28, 2010
 *          Time: 1:46:40 PM
 */
public class Where {
    private String columnName;
    private String value;
    private Object type;
    private Operator operator;
    private ArrayList<Where> whereClauses;

    public Where(String columnName, String value, Object type) {
        this.columnName = columnName;
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return getOperator() + " ( " + columnName + " = " + getValue() + getWhereClauses() + " )";
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    private String getOperator() {
        return operator == null ? "" : " " + operator.toString();
    }

    public String getValue() {
        return type.equals(String.class) ? "'" + value + "'" : value;
    }

    public Where and(Where whereClause) {
        whereClause.setOperator(Operator.AND);
        addWhereClause(whereClause);
        return this;
    }

    private void addWhereClause(Where whereClause) {
        if (whereClauses == null) {
            whereClauses = new ArrayList<Where>();
        }
        whereClauses.add(whereClause);
    }

    private String getWhereClauses() {
        String internalWhereClause = "";
        if (whereClauses == null) return internalWhereClause;
        for (Where whereClause : whereClauses) {
            internalWhereClause += whereClause.toString();
        }
        return internalWhereClause;
    }

}
