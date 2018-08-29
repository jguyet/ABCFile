package com.flagstone.transform.as3.abcfile.instruction.stack_management;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionPushintExtd
extends InstructionPushint {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionPushintExtd.class);

    private Integer intValue;
    private CpoolInfo cpool;

    public InstructionPushintExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.cpool = cpool;
        if ((this.index > 0) && (this.index < cpool.getInts().size())) {
            this.intValue = ((Integer) cpool.getInts().get(this.index));
        } else {
            LOGGER.info(String.format("Can't find index in ints array, index: %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }

    public Integer getintValue() {
        return this.intValue;
    }

    public Object runInstruction()
    throws Exception {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        stack.push(this.intValue);
        return Boolean.valueOf(true);
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        if ((this.index > 0) && (this.index < this.cpool.getInts().size())) {
            this.intValue = ((Integer) this.cpool.getInts().get(this.index));
        }
    }
}