package com.flagstone.transform.as3.abcfile.instruction;

import java.util.ArrayList;

import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionGetsuperExtd
extends InstructionGetsuper {
    private MultinameInfo multiname_info;

    public InstructionGetsuperExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.multiname_info = ((MultinameInfo) cpool.getMultinames().get(this.index));
    }

    public InstructionGetsuperExtd(MultinameInfo multiname_info, int position) {
        super(multiname_info.getId(), position);
        this.multiname_info = multiname_info;
    }

    public MultinameInfo getMultiname_info() {
        return this.multiname_info;
    }

    public void setMultiname_info(MultinameInfo multinameInfo) {
        this.multiname_info = multinameInfo;
        getIndex();
    }

    public int getIndex() {
        this.index = this.multiname_info.getId();
        return this.index;
    }
}