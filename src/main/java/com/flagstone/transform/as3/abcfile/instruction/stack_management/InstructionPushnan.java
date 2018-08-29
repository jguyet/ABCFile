package com.flagstone.transform.as3.abcfile.instruction.stack_management;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.Nan;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionPushnan
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionPushnan.class);
    public static final Opcode opcode = Opcode.OP_pushnan;

    public InstructionPushnan(int position) {
        super(opcode, position);
    }

    public InstructionPushnan() {
        super(opcode);
    }

    public Object runInstruction()
    throws Exception {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        stack.push(new Nan());

        return Boolean.valueOf(true);
    }
}