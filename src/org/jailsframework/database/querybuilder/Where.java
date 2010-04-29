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
    private String valueOperator;

    public Where(String columnName, String value, Object type) {
        this(columnName, value, Operator.EQUALS, type);
    }

    public Where(String columnName, String value, String valueOperator, Object type) {
        this.columnName = columnName;
        this.value = value;
        this.type = type;
        this.valueOperator = valueOperator;
    }

    @Override
    public String toString() {
        return getOperator() + getOpenParenthesis() + columnName + valueOperator + getValue() + getWhereClauses() + getClosingParenthesis();
    }

    private String getClosingParenthesis() {
        return whereClauses == null ? "" : ")";
    }

    private String getOpenParenthesis() {
        return whereClauses == null ? " " : " (";
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

    public Where or(Where whereClause) {
        whereClause.setOperator(Operator.OR);
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
