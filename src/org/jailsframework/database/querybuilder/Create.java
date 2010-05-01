package org.jailsframework.database.querybuilder;

import org.jailsframework.database.Column;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 1, 2010
 *          Time: 9:43:16 PM
 */
public class Create implements IQueryBuilder {
    private String tableName;
    private Column[] columns;

    public Create table(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public IQueryBuilder withColumns(Column... columns) {
        this.columns = columns;
        return this;
    }

    public String build() {
        return "CREATE TABLE " + tableName + " (" + getColumnsDefinition() + ")";
    }

    public String getColumnsDefinition() {
        String columnsDefinition = "";
        for (Column column : columns) {
            columnsDefinition += column.toString();
            columnsDefinition += ", ";
        }
        return removeLastComma(columnsDefinition);
    }

    private String removeLastComma(String commaSeparatedValue) {
        return commaSeparatedValue.substring(0, commaSeparatedValue.lastIndexOf(','));
    }
}
