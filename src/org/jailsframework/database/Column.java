package org.jailsframework.database;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 30, 2010
 *          Time: 8:35:40 AM
 */
public class Column {
    private String name;
    private DataType type;
    private int size;

    public Column(String name, DataType type) {
        this(name, type, 0);
    }

    public Column(String name, DataType type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    @Override
    public String toString() {
        return name + " " + type + getSizeValue();
    }

    private String getSizeValue() {
        return size > 0 ? "(" + size + ")" : "";
    }
}
