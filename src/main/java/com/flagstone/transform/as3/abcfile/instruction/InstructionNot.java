package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionNot
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNot.class);
    public static final Opcode opcode = Opcode.OP_not;

    public InstructionNot() {
        super(opcode);
    }

    public InstructionNot(int position) {
        super(opcode, position);
    }

    public Object runInstruction()
    throws Exception {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        Object obj = stack.pop();

        if ((obj instanceof Boolean)) {
            boolean bObj = ((Boolean) obj).booleanValue();
            stack.push(Boolean.valueOf(!bObj));
        } else {
            throw new Exception("Zły typ danych InstructionNot");
        }

        return Boolean.valueOf(true);
    }
}