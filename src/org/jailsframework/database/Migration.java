package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 5:09:30 PM
 */
public abstract class Migration implements IMigration {

    public int compareTo(Object obj) {
        if (obj instanceof IMigration) {
            IMigration migration = (IMigration) obj;
            Long thisVersion = getVersion();
            Long otherVersion = migration.getVersion();
            if (thisVersion < otherVersion)
                return -1;
            else if (thisVersion > otherVersion)
                return 1;
            else
                return 0;
        } else {
            throw new IllegalArgumentException("obj must be an " +
                    " instance of a IMigration object.");
        }
    }

    @Override
    public String toString() {
        return "version = " + getVersion().toString();
    }
}
