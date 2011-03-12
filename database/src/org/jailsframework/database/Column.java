package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 30, 2010
 *          Time: 8:35:40 AM
 */
public class Column {
    private String name;
    private IDataType type;

    public Column(String name, IDataType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " " + type.toString();
    }
}
