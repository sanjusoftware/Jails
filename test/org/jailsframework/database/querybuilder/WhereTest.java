package org.jailsframework.database.querybuilder;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 30, 2010
 *          Time: 7:41:31 AM
 */
public class WhereTest {
    @Test
    public void shouldGiveProperValueWithQuotesIfRequired() {
        assertEquals(" name = 'Sanjeev'", new Where("name", "Sanjeev").toString());
        assertEquals(" name = 'Sanjeev'", new Where("name", "Sanjeev", String.class).toString());
        assertEquals(" id = 10", new Where("id", "10", Integer.class).toString());
    }

    @Test
    public void shouldApplyParenthesisIfHasInternalWhereClause() {
        Where where = new Where("id", "10", Integer.class);
        where.and(new Where("name", "Sanjeev"));
        assertEquals(" (id = 10 AND name = 'Sanjeev')", where.toString());
        where.or(new Where("sal", "10", Integer.class));
        assertEquals(" (id = 10 AND name = 'Sanjeev' OR sal = 10)", where.toString());
    }

    @Test
    public void shouldApplyParenthesisIfHasInternalWhereClauseWithMultipleLevelOfHierarchy() {
        Where outerWhere = new Where("id", "10", Integer.class);
        Where innerWhere = new Where("name", "Sanjeev");
        outerWhere.and(innerWhere);
        assertEquals(" (id = 10 AND name = 'Sanjeev')", outerWhere.toString());
        innerWhere.or(new Where("sal", "10", Integer.class));
        assertEquals(" (id = 10 AND (name = 'Sanjeev' OR sal = 10))", outerWhere.toString());
    }
}
