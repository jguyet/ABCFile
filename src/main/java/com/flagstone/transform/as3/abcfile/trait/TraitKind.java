package com.flagstone.transform.as3.abcfile.trait;

import java.util.HashMap;
import java.util.Map;

public enum TraitKind {
    Trait_Slot(0),
        Trait_Method(1),
        Trait_Getter(2),
        Trait_Setter(3),
        Trait_Class(4),
        Trait_Function(5),
        Trait_Const(6);
    private static Map < Integer, TraitKind > kindMap;
    private final int kind;

    static {
        kindMap = new HashMap();

        TraitKind[] arrayOfTraitKind;
        int j = (arrayOfTraitKind = values()).length;
        for (int i = 0; i < j; i++) {
            TraitKind kind = arrayOfTraitKind[i];
            kindMap.put(Integer.valueOf(kind.kind), kind);
        }
    }

    private TraitKind(int kind) {
        this.kind = kind;
    }

    public int getKind() {
        return this.kind;
    }

    public static TraitKind getTrait(int kind) {
        return (TraitKind) kindMap.get(Integer.valueOf(kind));
    }
}