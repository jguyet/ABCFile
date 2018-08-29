package com.flagstone.transform.as3.abcfile.trait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;

public enum ConstantKind {
    CONSTANT_Int(3),
        CONSTANT_UInt(4),
        CONSTANT_Double(6),
        CONSTANT_Utf8(1),
        CONSTANT_True(11),
        CONSTANT_False(10),
        CONSTANT_Null(12),
        CONSTANT_Undefined(0),
        CONSTANT_Namespace(8),
        CONSTANT_PackageNamespace(22),
        CONSTANT_PackageInternalNs(23),
        CONSTANT_ProtectedNamespace(24),
        CONSTANT_ExplicitNamespace(25),
        CONSTANT_StaticProtectedNs(26),
        CONSTANT_PrivateNs(5);

    private static Map < Integer, ConstantKind > constantKindMap;
    private final int constantKind;
    static {
        constantKindMap = new HashMap();

        ConstantKind[] arrayOfConstantKind;
        int j = (arrayOfConstantKind = values()).length;
        for (int i = 0; i < j; i++) {
            ConstantKind constantKind = arrayOfConstantKind[i];
            constantKindMap.put(Integer.valueOf(constantKind.constantKind), constantKind);
        }
    }

    private ConstantKind(int constantKind) {
        this.constantKind = constantKind;
    }

    public int getConstantKind() {
        return this.constantKind;
    }

    public static ConstantKind getConstant(int constantKind) {
        return (ConstantKind) constantKindMap.get(Integer.valueOf(constantKind));
    }

    public Object getValueReal(int vIndex, CpoolInfo cpool) {
        switch (this) {
            case CONSTANT_Double:
                return cpool.getInts().get(vIndex);
            case CONSTANT_ExplicitNamespace:
                return cpool.getUints().get(vIndex);
            case CONSTANT_False:
                return cpool.getDoubles().get(vIndex);
            case CONSTANT_Int:
                return cpool.getStrings().get(vIndex);
            case CONSTANT_Namespace:
                return Boolean.valueOf(true);
            case CONSTANT_Null:
                return Boolean.valueOf(false);
            case CONSTANT_PackageInternalNs:
                return null;
            case CONSTANT_PackageNamespace:
                return null;
            case CONSTANT_PrivateNs:
                return cpool.getNamespaces().get(vIndex);
            case CONSTANT_ProtectedNamespace:
                return cpool.getNamespaces().get(vIndex);
            case CONSTANT_StaticProtectedNs:
                return cpool.getNamespaces().get(vIndex);
            case CONSTANT_True:
                return cpool.getNamespaces().get(vIndex);
            case CONSTANT_UInt:
                return cpool.getNamespaces().get(vIndex);
            case CONSTANT_Undefined:
                return cpool.getNamespaces().get(vIndex);
            case CONSTANT_Utf8:
                return cpool.getNamespaces().get(vIndex);
        }

        return null;
    }
}