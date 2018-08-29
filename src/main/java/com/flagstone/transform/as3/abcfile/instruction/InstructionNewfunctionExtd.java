package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.structures.MethodInfo;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionNewfunctionExtd
extends InstructionNewfunction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNewfunctionExtd.class);
    private MethodInfo method_info;
    protected AbcFile abc;

    public InstructionNewfunctionExtd(ByteBufferFlash date, int position, AbcFile abc) {
        super(date, position);
        this.abc = abc;
        try {
            this.method_info = ((MethodInfo) abc.ENmethod_info.get(this.index));
        } catch (Exception e) {
            LOGGER.error("There is no such method with id: " + this.index, e);
        }
    }

    public InstructionNewfunctionExtd(MethodInfo method_info, int position) {
        super(method_info.getId(), position);
        this.method_info = method_info;
    }

    public MethodInfo getMethod_info() {
        return this.method_info;
    }

    public void setMethod_info(MethodInfo methodInfo) {
        this.method_info = methodInfo;
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
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        stack.push(this.method_info);

        return Boolean.valueOf(true);
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        this.method_info = ((MethodInfo) this.abc.ENmethod_info.get(this.index));
    }
}