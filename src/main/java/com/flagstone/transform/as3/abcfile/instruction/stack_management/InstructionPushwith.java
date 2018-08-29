package com.flagstone.transform.as3.abcfile.instruction.stack_management;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionPushwith
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionPushwith.class);
    public static final Opcode opcode = Opcode.OP_pushwith;

    public InstructionPushwith(int position) {
        super(opcode, position);
    }

    public InstructionPushwith() {
        super(opcode);
    }

    public Object runInstruction()
    throws Exception, TypeError {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        Object scope_obj = stack.pop();
        if ((scope_obj == null) || (scope_obj.equals("undefined"))) {
            throw new TypeError();
        }
        scopeStack.push(scope_obj);

        return Boolean.valueOf(true);
    }
}