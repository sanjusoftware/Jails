package org.jailsframework.database.querybuilder;

import java.util.ArrayList;
import java.util.List;

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
    private List<Where> whereClauses;
    private String valueOperator;

    public Where(String columnName, String value) {
        this(columnName, value, String.class);
    }

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
        return getOperator() + getOpenParenthesis() + columnName + valueOperator
                + getValue() + getWhereClauses() + getClosingParenthesis();
    }

    protected void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Where and(Where whereClause) {
        addWhereClause(whereClause, Operator.AND);
        return this;
    }

    public Where or(Where whereClause) {
        addWhereClause(whereClause, Operator.OR);
        return this;
    }

    private String getValue() {
        return type.equals(String.class) ? "'" + value + "'" : value;
    }

    private String getClosingParenthesis() {
        return whereClauses == null ? "" : ")";
    }

    private String getOpenParenthesis() {
        return whereClauses == null ? " " : " (";
    }

    private String getOperator() {
        return operator == null ? "" : " " + operator.toString();
    }

    private void addWhereClause(Where whereClause, final Operator operator) {
        whereClause.setOperator(operator);
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
