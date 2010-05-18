package org.jailsframework.querybuilder;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 28, 2010
 *          Time: 12:06:19 PM
 */
public class SelectTest {
    @Test
    public void shouldBuildTheSelectQueryGivenTheTableName() {
        Assert.assertEquals("SELECT * FROM EMPLOYEE;", new Select().from("EMPLOYEE").build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenTheTableNameAndColumns() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE;", new Select().from("EMPLOYEE").columns("name, age").build());
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE;", new Select().columns("name, age").from("EMPLOYEE").build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenSingleExternalWhereClauses() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE id = 10;",
                new Select().from("EMPLOYEE").columns("name, age").where(new Where("id", "10", Integer.class)).build());

        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE id = 10;",
                new Select().columns("name, age").where(new Where("id", "10", Integer.class)).from("EMPLOYEE").build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenSingleExternalWhereClausesWithInequalityOperators() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE id > 10;",
                new Select().from("EMPLOYEE").columns("name, age").where(new Where("id", "10", Operator.GREATER_THAN, Integer.class)).build());

        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE id < 10;",
                new Select().columns("name, age").where(new Where("id", "10", Operator.LESS_THAN, Integer.class)).from("EMPLOYEE").build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleExternalWhereClausesAddedWithANDOperator() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE age = 25 AND name = 'Sanjeev';",
                new Select().columns("name, age").where(new Where("age", "25", Integer.class))
                        .and(new Where("name", "Sanjeev")).from("EMPLOYEE").build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleExternalWhereClausesAddedWithOROperator() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE age = 25 OR name = 'Sanjeev';",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class))
                        .or(new Where("name", "Sanjeev"))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleExternalWhereClausesAddedWithORAndANDOperator() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE age = 25 OR name = 'Sanjeev' AND sal = 25000;",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class))
                        .or(new Where("name", "Sanjeev"))
                        .and(new Where("sal", "25000", Integer.class))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenTheTableNameColumnsAndMultipleInternalWhereClausesAddedWithANDOperator() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age = 25 AND name = 'Sanjeev');",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class)
                                .and(new Where("name", "Sanjeev")))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleInternalWhereClausesAddedWithOROperator() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age = 25 OR name = 'Sanjeev');",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class)
                                .or(new Where("name", "Sanjeev")))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleInternalWhereClausesAddedWithANDAndOROperator() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age = 25 OR name = 'Sanjeev' AND sal = '25000');",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class)
                                .or(new Where("name", "Sanjeev"))
                                .and(new Where("sal", "25000")))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleInternalWhereClausesAddedWithANDAndOROperatorWithTwoLevelDeepHierarchy() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age = 25 OR (name = 'Sanjeev' AND sal = 25000));",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class)
                                .or(new Where("name", "Sanjeev")
                                .and(new Where("sal", "25000", Integer.class))))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleInternalAndExternalWhereClauses() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age = 25 OR name = 'Sanjeev' AND sal = 25000) AND color = 'Brown';",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class)
                                .or(new Where("name", "Sanjeev"))
                                .and(new Where("sal", "25000", Integer.class)))
                        .and(new Where("color", "Brown"))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleInternalAndExternalWhereClausesAndMultipleOperators() {
        Assert.assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age >= 25 OR name = 'Sanjeev' AND sal <= 25000) AND color = 'Brown';",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Operator.GREATER_THAN_EQUALS_TO, Integer.class)
                                .or(new Where("name", "Sanjeev"))
                                .and(new Where("sal", "25000", Operator.LESS_THAN_EQUALS_TO, Integer.class)))
                        .and(new Where("color", "Brown"))
                        .build());
    }
}
