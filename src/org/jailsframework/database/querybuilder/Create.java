package org.jailsframework.database.querybuilder;

import org.jailsframework.database.DBObject;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 8, 2010
 *          Time: 9:54:14 PM
 */
public class Create implements IQueryBuilder {
    private DBObject dbObj;

    public Create(DBObject dbObj) {
        this.dbObj = dbObj;
    }

    public String build() {
        return dbObj.createQuery();
    }
}
