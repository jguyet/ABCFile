package com.flagstone.transform.as3.abcfile.instruction.invocation;

import java.util.ArrayList;
import java.util.List;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.Null;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.stack.Undefined;
import com.flagstone.transform.as3.abcfile.structures.MethodInfo;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionCallstaticExtd
extends InstructionCallstatic {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallstaticExtd.class);
    private MethodInfo method_info;
    protected AbcFile abc;

    public InstructionCallstaticExtd(ByteBufferFlash date, int position, AbcFile abc) {
        super(date, position);
        this.abc = abc;
        try {
            this.method_info = ((MethodInfo) abc.ENmethod_info.get(this.index));
        } catch (Exception e) {
            LOGGER.error("can't get method with id: " + this.index, e);
        }
    }

    public InstructionCallstaticExtd(MethodInfo method_info, int arg_count, int position) {
        super(method_info.getId(), arg_count, position);
        this.method_info = method_info;
    }

    public MethodInfo getMethod_info() {
        return this.method_info;
    }

    public void setMethod_info(MethodInfo methodInfo) {
        this.method_info = methodInfo;
        getIndex();
    }

    public int getIndex() {
        if (this.method_info == null) {
            return this.index;
        }
        this.index = this.method_info.getId();
        return this.index;
    }

    public Object runInstruction()
    throws Exception, TypeError {
        LOGGER.info("not implemented yet InstructionCallmethodExtd");
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        List < Object > args = new ArrayList();
        for (int i = 0; i < this.arg_count; i++) {
            Object arg = stack.pop();
            args.add(arg);
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
        this.method_info = ((MethodInfo) this.abc.ENmethod_info.get(this.index));
    }
}