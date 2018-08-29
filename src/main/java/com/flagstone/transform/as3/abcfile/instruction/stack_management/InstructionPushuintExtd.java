package com.flagstone.transform.as3.abcfile.instruction.stack_management;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionPushuintExtd
extends InstructionPushuint {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionPushuintExtd.class);

    private Integer uintValue = Integer.valueOf(-1);
    protected CpoolInfo cpool;

    public InstructionPushuintExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.cpool = cpool;
        if ((this.index >= 0) && (this.index < cpool.getUints().size())) {
            this.uintValue = ((Integer) cpool.getUints().get(this.index));
        } else {
            LOGGER.error(String.format("Can't get uin from index %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }

    public Integer getuintValue() {
        return this.uintValue;
    }

    public Object runInstruction()
    throws Exception {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        stack.push(this.uintValue);
        return Boolean.valueOf(true);
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        if ((this.index >= 0) && (this.index < this.cpool.getUints().size())) {
            this.uintValue = ((Integer) this.cpool.getUints().get(this.index));
        } else {
            LOGGER.error(String.format("Can't get uin from index %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }
}