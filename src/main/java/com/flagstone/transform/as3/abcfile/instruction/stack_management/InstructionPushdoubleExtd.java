package com.flagstone.transform.as3.abcfile.instruction.stack_management;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionPushdoubleExtd
extends InstructionPushdouble {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionPushdoubleExtd.class);
    private Double doubleValue;
    protected CpoolInfo cpool;

    public InstructionPushdoubleExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.cpool = cpool;
        this.doubleValue = ((Double) cpool.getDoubles().get(this.index));
    }

    public Double getdoubleValue() {
        return this.doubleValue;
    }

    public Object runInstruction()
    throws Exception {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        stack.push(this.doubleValue);
        return Boolean.valueOf(true);
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        this.doubleValue = ((Double) this.cpool.getDoubles().get(this.index));
    }
}