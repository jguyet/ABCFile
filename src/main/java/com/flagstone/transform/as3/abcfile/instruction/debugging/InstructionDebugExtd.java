package com.flagstone.transform.as3.abcfile.instruction.debugging;

import java.util.ArrayList;

import com.flagstone.transform.as3.abcfile.cpool.StringInfo;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionDebugExtd
extends InstructionDebug {
    private StringInfo string_info;

    public InstructionDebugExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.string_info = ((StringInfo) cpool.getStrings().get(this.index));
    }

    public InstructionDebugExtd(StringInfo string_info, int debug_type, int index, int reg, int extra, int position) {
        super(debug_type, string_info.getId(), reg, extra, position);
        this.string_info = string_info;
    }

    public StringInfo getString_info() {
        return this.string_info;
    }

    public void setString_info(StringInfo stringInfo) {
        this.string_info = stringInfo;
        getIndex();
    }

    public int getIndex() {
        this.index = this.string_info.getId();
        return this.index;
    }
}