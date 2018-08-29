package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionNextvalue
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNextvalue.class);
    public static final Opcode opcode = Opcode.OP_nextvalue;

    public InstructionNextvalue() {
        super(opcode);
    }

    public InstructionNextvalue(int position) {
        super(opcode, position);
    }

    public Object runInstruction()
    throws Exception {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        Object index = stack.pop();
        Object obj = stack.pop();

        if ((index instanceof Integer)) {
            stack.push("value of the property at " + (((Integer) index).intValue() + 1) + " for object " + obj);
        } else {
            throw new Exception("Zły typ danych InstructionNextname");
        }

        return Boolean.valueOf(true);
    }
}