package com.flagstone.transform.as3.abcfile.instruction;

import java.util.ArrayList;

import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionSetsuperExtd
extends InstructionSetsuper {
    private MultinameInfo multiname_info;

    public InstructionSetsuperExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.multiname_info = ((MultinameInfo) cpool.getMultinames().get(this.index));
    }

    public InstructionSetsuperExtd(MultinameInfo multiname_info, int position) {
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

    public IStackInt < Object > [] runInstruction()
    throws Exception, TypeError {
        IStackInt stack = StackFactory.getStack();
        IStackInt scopeStack = StackFactory.getScopeStack();

        IStackInt[] result = {
            StackFactory.getStack(),
            StackFactory.getScopeStack()
        };

        throw new Exception("Not implemented yet in InstructionGetsuper");
    }
}