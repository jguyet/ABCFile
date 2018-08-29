package com.flagstone.transform.as3.abcfile.instruction.exception;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.IStackItem;
import com.flagstone.transform.as3.abcfile.stack.Null;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.stack.Undefined;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionThrow
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionThrow.class);
    public static final Opcode opcode = Opcode.OP_throw;

    public InstructionThrow() {
        super(opcode);
    }

    public InstructionThrow(int position) {
        super(opcode, position);
    }

    public IStackInt < Object > [] runInstruction()
    throws Exception, TypeError {
        IStackInt stack = StackFactory.getStack();
        IStackInt scopeStack = StackFactory.getScopeStack();
        Object value = stack.pop();
        if (((value instanceof Null)) || ((value instanceof Undefined))) {
            throw new TypeError();
        }
        scopeStack.push(value);

        IStackInt[] result = {
            StackFactory.getStack(),
            StackFactory.getScopeStack()
        };
        return result;
    }

    public IStackItem makeInstruction() throws Exception {
        return (IStackItem) StackFactory.getStack().pop();
    }
}