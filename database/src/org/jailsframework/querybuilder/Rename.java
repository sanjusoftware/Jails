package org.jailsframework.querybuilder;

import org.jailsframework.database.Table;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 8, 2010
 *          Time: 11:16:29 PM
 */
public class Rename extends DDLQuery {

    private Table table;
    private String newName;

    public Rename(Table table) {
        this.table = table;
    }

    public DDLQuery to(String newName) {
        this.newName = newName;
        return this;
    }

    @Override
    public String query() {
        return "RENAME TABLE " + table.getName() + " TO " + newName;
    }
}
