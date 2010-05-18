package org.jailsframework.querybuilder;

import org.jailsframework.database.DBComponent;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 8, 2010
 *          Time: 11:16:29 PM
 */
public class Rename implements IQueryBuilder {
    private DBComponent dbObj;
    private String newName;

    public Rename(DBComponent dbObj) {
        this.dbObj = dbObj;
    }

    public String build() {
        return dbObj.renameQuery(newName);
    }

    public IQueryBuilder to(String newName) {
        this.newName = newName;
        return this;
    }
}
