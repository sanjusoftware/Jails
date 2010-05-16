package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 8, 2010
 *          Time: 9:57:37 PM
 */
public interface DBComponent {
    String createQuery();

    String renameQuery(String newName);
}
