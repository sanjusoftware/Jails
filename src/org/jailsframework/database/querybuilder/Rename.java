package org.jailsframework.database.querybuilder;

import org.jailsframework.database.DBObject;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 8, 2010
 *          Time: 11:16:29 PM
 */
public class Rename implements IQueryBuilder {
    private DBObject dbObj;
    private String newName;

    public Rename(DBObject dbObj) {
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
