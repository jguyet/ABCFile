package com.flagstone.transform.as3.abcfile.cpool;

public enum TypeName {
    INTEGER("int"),
        UINTEGER("uint"),
        DOUBLE("Number"),
        STRING("String"),
        BOOLEAN("Boolean");

    public final String typeName;

    private TypeName(String typeName) {
        this.typeName = typeName;
    }
}