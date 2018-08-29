package com.flagstone.transform.as3.abcfile.instruction.control_transfer;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionLabel
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionLabel.class);
    public static final Opcode opcode = Opcode.OP_label;

    public InstructionLabel(int position) {
        super(opcode, position);
    }

    public InstructionLabel() {
        super(opcode);
    }

    public IStackInt < Object > [] runInstruction()
    throws Exception, VerifyError {
        IStackInt stack = StackFactory.getStack();
        IStackInt scopeStack = StackFactory.getScopeStack();

        IStackInt[] result = {
            StackFactory.getStack(),
            StackFactory.getScopeStack()
        };
        return result;
    }
}