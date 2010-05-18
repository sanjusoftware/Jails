package org.jailsframework.querybuilder;

import org.jailsframework.database.DBComponent;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 8, 2010
 *          Time: 9:54:14 PM
 */
public class Create extends AbstractQueryBuilder {
    private DBComponent dbComponent;

    public Create(DBComponent dbObj) {
        this.dbComponent = dbObj;
    }

    public String build() {
        return dbComponent.createQuery();
    }
}
