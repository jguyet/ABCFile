package com.flagstone.transform.as3.abcfile.instruction.stack_management;

import java.util.ArrayList;

import com.flagstone.transform.as3.abcfile.cpool.StringInfo;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionPushstringExtd
extends InstructionPushstring {
    private StringInfo string_info;
    private CpoolInfo cpool;

    public InstructionPushstringExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.cpool = cpool;
        this.string_info = ((StringInfo) cpool.getStrings().get(this.index));
    }

    public InstructionPushstringExtd(StringInfo string_info, int position) {
        super(string_info.getId(), position);
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

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        this.string_info = ((StringInfo) this.cpool.getStrings().get(this.index));
    }

    public Object runInstruction()
    throws Exception {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        stack.push(this.string_info);
        return Boolean.valueOf(true);
    }
}