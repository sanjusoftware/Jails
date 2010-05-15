package org.jailsframework.database.querybuilder;

import junit.framework.Assert;
import org.jailsframework.database.Column;
import org.jailsframework.database.DataType;
import org.jailsframework.database.Table;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 1, 2010
 *          Time: 9:30:49 PM
 */
public class CreateTest {

    @Test
    public void shouldTestBuildTheCreateQueryNewTableGivenNameAndColumns() {
        Assert.assertEquals("CREATE TABLE employee (name VARCHAR, age INT)",
                new Create(new Table("employee").
                        addColumns(new Column("name", DataType.VARCHAR),
                        new Column("age", DataType.INT))).build());
    }

    @Test
    public void shouldCreateNewTableGivenTableNameAndColumnsWithSize() {
        Assert.assertEquals("CREATE TABLE employee (name VARCHAR(255), age INT)",
                new Create(new Table("employee").
                        addColumns(new Column("name", DataType.VARCHAR, 255),
                        new Column("age", DataType.INT))).build());
    }
}