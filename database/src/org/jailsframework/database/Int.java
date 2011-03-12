package org.jailsframework.database;

public class Int extends AbstractDataType {

    public Int(int size) {
        this.size = size;
    }

    public Int() {
        this(0);
    }

    @Override
    public String toString() {
        return size > 0 ? "INT("+size+")" : "INT";
    }
}
