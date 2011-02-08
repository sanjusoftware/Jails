package org.jailsframework.querybuilder;

import java.sql.ResultSet;

public abstract class DMLQuery extends AbstractQuery {

    public abstract ResultSet executeQuery();

    public abstract int executeUpdate();
}
