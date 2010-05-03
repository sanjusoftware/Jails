package org.jailsframework.database;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 1, 2010
 *          Time: 10:17:26 PM
 */
public class ColumnTest {
    @Test
    public void toStringShouldGiveTheColumnNameAndTheDataType() {
        assertEquals("name VARCHAR", new Column("name", DataType.VARCHAR).toString());
    }

    @Test
    public void toStringShouldGiveNameDataTypeAndSize() {
        assertEquals("name VARCHAR(25)", new Column("name", DataType.VARCHAR, 25).toString());
    }
}
