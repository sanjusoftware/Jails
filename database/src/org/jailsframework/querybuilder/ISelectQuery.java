package org.jailsframework.querybuilder;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 28, 2010
 *          Time: 12:22:37 PM
 */
public abstract class ISelectQuery extends DMLQuery {

    abstract ISelectQuery columns(String columns);

    abstract ISelectQuery from(String table);

    abstract ISelectQuery where(Where whereClause);

    abstract ISelectQuery and(Where where);

    abstract ISelectQuery or(Where where);
}
