package org.jailsframework.database;

import org.jailsframework.exceptions.UnsupportedDatabaseException;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 3, 2010
 *          Time: 6:38:43 PM
 */
public class DatabaseFactoryTest {

    @Test(expected = UnsupportedDatabaseException.class)
    public void shouldThroughUnsupportedDatabaseExceptionIfAdapterNotSupported() {
        new DatabaseFactory(this.toString()).getDatabase(null, null, null);
    }
}
