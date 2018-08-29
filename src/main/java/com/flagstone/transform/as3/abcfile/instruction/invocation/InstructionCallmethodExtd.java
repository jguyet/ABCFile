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

public class InstructionCallmethodExtd
extends InstructionCallmethod {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallmethodExtd.class);
    private MethodInfo method_info;
    protected AbcFile abc;

    public InstructionCallmethodExtd(ByteBufferFlash date, int position, AbcFile abc) {
        super(date, position);
        this.abc = abc;
        if ((this.index >= 0) && (this.index < abc.ENmethod_info.size())) {
            this.method_info = ((MethodInfo) abc.ENmethod_info.get(this.index));
        } else {
            LOGGER.error(String.format("Wrong index %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }

    public InstructionCallmethodExtd(MethodInfo method_info, int arg_count, int position) {
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
        if (this.method_info != null) {
            return this.method_info.getId();
        }
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
        if ((this.index >= 0) && (this.index < this.abc.ENmethod_info.size())) {
            this.method_info = ((MethodInfo) this.abc.ENmethod_info.get(this.index));
        } else {
            LOGGER.error(String.format("Wrong index %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }
}