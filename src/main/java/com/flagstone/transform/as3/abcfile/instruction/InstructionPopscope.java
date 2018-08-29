package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionPopscope
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionPopscope.class);
    public static final Opcode opcode = Opcode.OP_popscope;

    public InstructionPopscope() {
        super(opcode);
    }

    public InstructionPopscope(int position) {
        super(opcode, position);
    }

    public Object runInstruction()
    throws Exception, TypeError {
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        scopeStack.pop();

        return Boolean.valueOf(true);
    }
}