package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 9, 2010
 *          Time: 11:04:57 AM
 */
public interface IRecord {
    boolean create();

    boolean update();

    boolean delete();
}
