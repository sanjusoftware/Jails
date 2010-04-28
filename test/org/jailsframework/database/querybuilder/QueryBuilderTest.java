package org.jailsframework.database.querybuilder;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 28, 2010
 *          Time: 12:06:19 PM
 */
public class QueryBuilderTest {
    @Test
    public void shouldBuildTheSelectQueryGivenTheTableName() {
        assertEquals("SELECT * FROM EMPLOYEE;", new Select().from("EMPLOYEE").build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenTheTableNameAndColumns() {
        assertEquals("SELECT name, age FROM EMPLOYEE;", new Select().from("EMPLOYEE").columns("name, age").build());
        assertEquals("SELECT name, age FROM EMPLOYEE;", new Select().columns("name, age").from("EMPLOYEE").build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenTheTableNameColumnsAndWhereClauses() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE ( id = 10 ) ;", new Select().from("EMPLOYEE").columns("name, age").where(new Where("id", "10", Integer.class)).build());
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE ( id = 10 ) ;", new Select().columns("name, age").where(new Where("id", "10", Integer.class)).from("EMPLOYEE").build());
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE ( age = 25 ) AND ( name = 'Sanjeev' ) ;",
                new Select().columns("name, age").where(new Where("age", "25", Integer.class))
                        .and(new Where("name", "Sanjeev", String.class)).from("EMPLOYEE").build());
    }
}
