package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 3, 2010
 *          Time: 8:13:53 AM
 */
public class Table implements DBObject {
    private String name;
    private Column[] columns;

    public Table(String name) {
        this.name = name;
    }

    public Table addColumns(Column... columns) {
        this.columns = columns;
        return this;
    }

    public String createQuery() {
        return "CREATE TABLE " + name + " (" + getColumnsDefinition() + ")";
    }

    public String renameQuery(String newName) {
        return "RENAME TABLE " + name + " TO " + newName;
    }

    private String getColumnsDefinition() {
        if (columns == null) return "";
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
