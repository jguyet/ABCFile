package com.flagstone.transform.as3.abcfile.cpool;

import java.util.HashMap;
import java.util.Map;

public enum MultinameKindType {
    CONSTANT_FIRST(0),
        CONSTANT_QName(7),
        CONSTANT_QNameA(13),
        CONSTANT_RTQName(15),
        CONSTANT_RTQNameA(16),
        CONSTANT_RTQNameL(17),
        CONSTANT_RTQNameLA(18),
        CONSTANT_Multiname(9),
        CONSTANT_MultinameA(14),
        CONSTANT_MultinameL(27),
        CONSTANT_MultinameLA(28),
        CONSTANT_TypeName(29);

    public static Map < Integer, MultinameKindType > kindMap;
    public int value;

    static {
        kindMap = new HashMap();

        MultinameKindType[] arrayOfMultinameKindType;
        int j = (arrayOfMultinameKindType = values()).length;
        for (int i = 0; i < j; i++) {
            MultinameKindType kind = arrayOfMultinameKindType[i];
            kindMap.put(Integer.valueOf(kind.value), kind);
        }
    }

    private MultinameKindType(int value) {
        this.value = value;
    }

    public static MultinameKindType getMultinameKind(int value) {
        return (MultinameKindType) kindMap.get(Integer.valueOf(value));
    }
}