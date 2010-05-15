package org.jailsframework.database;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 1, 2010
 *          Time: 9:30:49 PM
 */
public class TableTest {

    @Test
    public void shouldCreateNewTableGivenNameAndColumns() {
        Assert.assertEquals("CREATE TABLE employee (name VARCHAR, age INT)",
                new Table("employee").
                        addColumns(new Column("name", DataType.VARCHAR),
                                new Column("age", DataType.INT)).createQuery());
    }

    @Test
    public void shouldCreateNewTableGivenTableNameAndColumnsWithSize() {
        Assert.assertEquals("CREATE TABLE employee (name VARCHAR(255), age INT)",
                new Table("employee").
                        addColumns(new Column("name", DataType.VARCHAR, 255),
                                new Column("age", DataType.INT)).createQuery());
    }
}
