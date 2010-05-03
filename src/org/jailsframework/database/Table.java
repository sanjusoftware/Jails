package org.jailsframework.database;

import org.jailsframework.database.querybuilder.IQueryBuilder;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 3, 2010
 *          Time: 8:13:53 AM
 */
public class Table implements IQueryBuilder {
    private String name;
    private Column[] columns;
    private String command;

    public Table(String name) {
        this.name = name;
    }

    public Table addColumns(Column... columns) {
        this.columns = columns;
        return this;
    }

    public String build() {
        return command + " TABLE " + name + " (" + getColumnsDefinition() + ")";
    }

    public IQueryBuilder create() {
        this.command = "CREATE";
        return this;
    }

    private String getColumnsDefinition() {
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
