package org.jailsframework.database.querybuilder;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 28, 2010
 *          Time: 12:22:37 PM
 */
public interface ISelectQueryBuilder extends IQueryBuilder {
    ISelectQueryBuilder columns(String columns);

    ISelectQueryBuilder from(String table);

    ISelectQueryBuilder where(Where whereClause);

    ISelectQueryBuilder and(Where where);

    ISelectQueryBuilder or(Where where);
}
