package com.flagstone.transform.as3.abcfile.instruction;

import java.util.ArrayList;

import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionGetpropertyExtd
extends InstructionGetproperty {
    private MultinameInfo multiname_info;
    protected CpoolInfo cpool;

    public InstructionGetpropertyExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.cpool = cpool;
        if ((this.index >= 0) && (this.index < cpool.getMultinames().size())) {
            this.multiname_info = ((MultinameInfo) cpool.getMultinames().get(this.index));
        }
    }

    public InstructionGetpropertyExtd(MultinameInfo multiname_info, int position) {
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
        if (this.multiname_info != null) {
            this.index = this.multiname_info.getId();
        }
        return this.index;
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        this.multiname_info = ((MultinameInfo) this.cpool.getMultinames().get(this.index));
    }
}