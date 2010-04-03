package org.jailsframework.exceptions;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 3, 2010
 *          Time: 6:30:51 PM
 */
public class UnsupportedDatabaseException extends RuntimeException {
    public UnsupportedDatabaseException(String message) {
        super(message);
    }
}
