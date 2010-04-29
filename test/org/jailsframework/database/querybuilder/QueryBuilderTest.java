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
    public void shouldBuildTheSelectQueryGivenSingleExternalWhereClauses() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE id = 10;",
                new Select().from("EMPLOYEE").columns("name, age").where(new Where("id", "10", Integer.class)).build());

        assertEquals("SELECT name, age FROM EMPLOYEE WHERE id = 10;",
                new Select().columns("name, age").where(new Where("id", "10", Integer.class)).from("EMPLOYEE").build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenSingleExternalWhereClausesWithInequalityOperators() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE id > 10;",
                new Select().from("EMPLOYEE").columns("name, age").where(new Where("id", "10", Operator.GREATER_THAN, Integer.class)).build());

        assertEquals("SELECT name, age FROM EMPLOYEE WHERE id < 10;",
                new Select().columns("name, age").where(new Where("id", "10", Operator.LESS_THAN, Integer.class)).from("EMPLOYEE").build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleExternalWhereClausesAddedWithANDOperator() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE age = 25 AND name = 'Sanjeev';",
                new Select().columns("name, age").where(new Where("age", "25", Integer.class))
                        .and(new Where("name", "Sanjeev", String.class)).from("EMPLOYEE").build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleExternalWhereClausesAddedWithOROperator() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE age = 25 OR name = 'Sanjeev';",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class))
                        .or(new Where("name", "Sanjeev", String.class))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleExternalWhereClausesAddedWithORAndANDOperator() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE age = 25 OR name = 'Sanjeev' AND sal = 25000;",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class))
                        .or(new Where("name", "Sanjeev", String.class))
                        .and(new Where("sal", "25000", Integer.class))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenTheTableNameColumnsAndMultipleInternalWhereClausesAddedWithANDOperator() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age = 25 AND name = 'Sanjeev');",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class)
                                .and(new Where("name", "Sanjeev", String.class)))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleInternalWhereClausesAddedWithOROperator() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age = 25 OR name = 'Sanjeev');",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class)
                                .or(new Where("name", "Sanjeev", String.class)))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleInternalWhereClausesAddedWithANDAndOROperator() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age = 25 OR name = 'Sanjeev' AND sal = '25000');",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class)
                                .or(new Where("name", "Sanjeev", String.class))
                                .and(new Where("sal", "25000", String.class)))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleInternalWhereClausesAddedWithANDAndOROperatorWithTwoLevelDeepHierarchy() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age = 25 OR (name = 'Sanjeev' AND sal = 25000));",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class)
                                .or(new Where("name", "Sanjeev", String.class)
                                .and(new Where("sal", "25000", Integer.class))))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleInternalAndExternalWhereClauses() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age = 25 OR name = 'Sanjeev' AND sal = 25000) AND color = 'Brown';",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Integer.class)
                                .or(new Where("name", "Sanjeev", String.class))
                                .and(new Where("sal", "25000", Integer.class)))
                        .and(new Where("color", "Brown", String.class))
                        .build());
    }

    @Test
    public void shouldBuildTheSelectQueryGivenMultipleInternalAndExternalWhereClausesAndMultipleOperators() {
        assertEquals("SELECT name, age FROM EMPLOYEE WHERE (age >= 25 OR name = 'Sanjeev' AND sal <= 25000) AND color = 'Brown';",
                new Select().columns("name, age")
                        .from("EMPLOYEE")
                        .where(new Where("age", "25", Operator.GREATER_THAN_EQUALS_TO, Integer.class)
                                .or(new Where("name", "Sanjeev", String.class))
                                .and(new Where("sal", "25000", Operator.LESS_THAN_EQUALS_TO, Integer.class)))
                        .and(new Where("color", "Brown", String.class))
                        .build());
    }
}
