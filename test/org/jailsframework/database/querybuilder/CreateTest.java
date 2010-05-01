package org.jailsframework.database.querybuilder;

import org.jailsframework.database.Column;
import org.jailsframework.database.DataType;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 1, 2010
 *          Time: 9:30:49 PM
 */
public class CreateTest {
    @Test
    public void shouldCreateNewTableGivenTableNameAndColumns() {
        assertEquals("CREATE TABLE employee (name VARCHAR, age INT)",
                new Create().table("employee").
                        withColumns(new Column("name", DataType.VARCHAR),
                                new Column("age", DataType.INT)).build());
    }
}
