package org.jailsframework.querybuilder;

import org.jailsframework.database.Table;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 8, 2010
 *          Time: 9:54:14 PM
 */
public class Create extends DDLQuery {

    private Table table;

    public Create(Table table) {
        this.table = table;
    }

    @Override
    public String query() {
        return "CREATE TABLE " + table.getName() + " (" + table.getColumnsDefinition() + ")";
    }
}
