package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 2:29:35 AM
 */
public interface IMigration extends Comparable {

    void up();

    void down();

    Long getVersion();
}
