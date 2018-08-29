package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionNop
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNop.class);
    public static final Opcode opcode = Opcode.OP_nop;

    public InstructionNop() {
        super(opcode);
    }

    public InstructionNop(int position) {
        super(opcode, position);
    }

    public IStackInt < Object > [] runInstruction()
    throws Exception {
        IStackInt[] result = {
            StackFactory.getStack(),
            StackFactory.getScopeStack()
        };
        return result;
    }
}