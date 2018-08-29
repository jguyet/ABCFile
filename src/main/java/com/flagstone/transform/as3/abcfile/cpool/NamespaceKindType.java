package com.flagstone.transform.as3.abcfile.cpool;

import java.util.HashMap;
import java.util.Map;

public enum NamespaceKindType {
    CONSTANT_FIRST(0),
        CONSTANT_Namespace(8),
        CONSTANT_PackageNamespace(22),
        CONSTANT_PackageInternalNs(23),
        CONSTANT_ProtectedNamespace(24),
        CONSTANT_ExplicitNamespace(25),
        CONSTANT_StaticProtectedNs(26),
        CONSTANT_PrivateNs(5);

    public static Map < Integer, NamespaceKindType > kindMap;

    private NamespaceKindType(int value) {
        this.value = value;
    }

    static {
        kindMap = new HashMap();

        NamespaceKindType[] arrayOfNamespaceKindType;
        int j = (arrayOfNamespaceKindType = values()).length;
        for (int i = 0; i < j; i++) {
            NamespaceKindType kind = arrayOfNamespaceKindType[i];
            kindMap.put(Integer.valueOf(kind.value), kind);
        }
    }

    public int value;
    public static NamespaceKindType getNamespaceKind(int value) {
        return (NamespaceKindType) kindMap.get(Integer.valueOf(value));
    }
}