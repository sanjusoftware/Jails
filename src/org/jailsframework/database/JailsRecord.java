package org.jailsframework.database;

import org.jailsframework.database.querybuilder.Insert;
import org.jailsframework.util.StringUtil;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 9, 2010
 *          Time: 11:07:25 AM
 */
public abstract class JailsRecord implements Record {

    public boolean create() {
        new Insert().into(getTable());
        return false;
    }

    protected String getTable() {
        return new StringUtil(this.getClass().getSimpleName()).tabelize();
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }
}
