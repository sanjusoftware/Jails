package org.jailsframework.querybuilder;

import junit.framework.Assert;
import org.jailsframework.database.*;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 1, 2010
 *          Time: 9:30:49 PM
 */
public class CreateTest {

    @Test
    public void shouldBuildTheCreateNewTableQueryGivenNameAndColumns() {
        Assert.assertEquals("CREATE TABLE employee (name VARCHAR(225), age INT)",
                new Create(new Table("employee").
                        addColumns(new Column("name", new VarChar()),
                                new Column("age", new Int()))).query());
    }

    @Test
    public void shouldBuildTheCreateNewTableQueryGivenNameAndColumnsWithSize() {
        Assert.assertEquals("CREATE TABLE employee (name VARCHAR(1024), age INT)",
                new Create(new Table("employee").
                        addColumns(new Column("name", new VarChar(1024)),
                        new Column("age", new Int()))).query());
    }

}