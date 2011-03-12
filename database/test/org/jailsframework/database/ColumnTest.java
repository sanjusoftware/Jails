package org.jailsframework.database;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 1, 2010
 *          Time: 10:17:26 PM
 */
public class ColumnTest {
    @Test
    public void toStringShouldGiveTheColumnNameAndTheDataType() {
        Assert.assertEquals("name VARCHAR(225)", new Column("name", new VarChar()).toString());
    }

    @Test
    public void toStringShouldGiveNameDataTypeAndSize() {
        Assert.assertEquals("name VARCHAR(25)", new Column("name", new VarChar(25)).toString());
    }
}
