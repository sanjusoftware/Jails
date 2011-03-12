package org.jailsframework.database;


public class VarChar extends AbstractDataType{

    public VarChar(int size) {
        this.size = size;
    }

    public VarChar() {
        this(225);
    }

    @Override
    public String toString() {
        return "VARCHAR("+size+")";
    }
}
