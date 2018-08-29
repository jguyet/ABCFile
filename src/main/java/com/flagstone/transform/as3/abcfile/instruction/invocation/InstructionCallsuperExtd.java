package com.flagstone.transform.as3.abcfile.instruction.invocation;

import java.util.ArrayList;
import java.util.List;

import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;
import com.flagstone.transform.as3.abcfile.multiname.MultinameKind;
import com.flagstone.transform.as3.abcfile.multiname.MultinameKindMultinameL;
import com.flagstone.transform.as3.abcfile.multiname.MultinameKindRTQName;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.Null;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.stack.Undefined;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionCallsuperExtd
extends InstructionCallsuper {
    private MultinameInfo multiname_info;
    protected CpoolInfo cpool;

    public InstructionCallsuperExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.cpool = cpool;
        this.multiname_info = ((MultinameInfo) cpool.getMultinames().get(this.index));
    }

    public InstructionCallsuperExtd(MultinameInfo multiname_info, int arg_count, int position) {
        super(multiname_info.getId(), arg_count, position);
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

    public Object runInstruction() throws Exception, TypeError {
        LOGGER.info("czy dobrze zaimplementowane InstructionCallsuperExtd?");
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        List < Object > args = new ArrayList();
        Object arg;
        for (int i = 0; i < this.arg_count; i++) {
            arg = stack.pop();
            args.add(arg);
        }

        MultinameKind kind = this.multiname_info.getData();

        if ((kind instanceof MultinameKindRTQName)) {
            if (((MultinameKindRTQName) kind).getName() == -1) {
                arg = stack.pop();
            }
            arg = stack.pop();
        }

        if ((kind instanceof MultinameKindMultinameL)) {
            if (((MultinameKindMultinameL) kind).getNs_set() == -1) {
                arg = stack.pop();
            }
            arg = stack.pop();
        }
        Object receiver = stack.pop();
        if (((receiver instanceof Null)) || ((receiver instanceof Undefined))) {
            throw new TypeError();
        }

        stack.push(Integer.valueOf(0));

        return Boolean.valueOf(true);
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        this.multiname_info = ((MultinameInfo) this.cpool.getMultinames().get(this.index));
    }
}