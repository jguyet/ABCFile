package com.flagstone.transform.as3.abcfile.instruction.stack_management;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.Null;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.stack.Undefined;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionPushscope
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionPushscope.class);
    public static final Opcode opcode = Opcode.OP_pushscope;

    public InstructionPushscope(int position) {
        super(opcode, position);
    }

    public InstructionPushscope() {
        super(opcode);
    }

    public Object runInstruction()
    throws Exception, TypeError {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        Object oStack = stack.pop();
        if (((oStack instanceof Null)) || ((oStack instanceof Undefined))) {
            throw new TypeError();
        }

        scopeStack.push(oStack);

        return Boolean.valueOf(true);
    }
}