package com.flagstone.transform.as3.abcfile.instruction.object_creation;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionDxnslate
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionDxnslate.class);
    public static final Opcode opcode = Opcode.OP_dxnslate;

    public InstructionDxnslate() {
        super(opcode);
    }

    public InstructionDxnslate(int position) {
        super(opcode, position);
    }

    public IStackInt < Object > [] runInstruction()
    throws Exception, VerifyError {
        IStackInt stack = StackFactory.getStack();
        IStackInt scopeStack = StackFactory.getScopeStack();

        String xml_namespace = (String) stack.pop();

        IStackInt[] result = {
            StackFactory.getStack(),
            StackFactory.getScopeStack()
        };
        return result;
    }
}