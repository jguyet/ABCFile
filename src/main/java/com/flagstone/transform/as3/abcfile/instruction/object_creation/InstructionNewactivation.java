package com.flagstone.transform.as3.abcfile.instruction.object_creation;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionNewactivation
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNewactivation.class);
    public static final Opcode opcode = Opcode.OP_newactivation;

    public InstructionNewactivation() {
        super(opcode);
    }

    public InstructionNewactivation(int position) {
        super(opcode, position);
    }
}