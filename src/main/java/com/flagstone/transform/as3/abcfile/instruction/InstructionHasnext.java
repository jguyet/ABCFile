package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionHasnext
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionHasnext.class);
    public static final Opcode opcode = Opcode.OP_hasnext;

    public InstructionHasnext() {
        super(opcode);
    }

    public InstructionHasnext(int position) {
        super(opcode, position);
    }

    public Object runInstruction()
    throws Exception {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        Object cur_index = stack.pop();
        Object obj = stack.pop();

        if ((cur_index instanceof Integer)) {
            stack.push("Get the index of the next property after the property at cur_index " + (Integer) cur_index + " for object " + obj);
        } else {
            throw new Exception("Zły typ danych InstructionNextname");
        }

        return Boolean.valueOf(true);
    }
}