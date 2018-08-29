package com.flagstone.transform.as3.abcfile.instruction.conversion;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionConvert_u
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionConvert_u.class);
    public static final Opcode opcode = Opcode.OP_convert_u;

    public InstructionConvert_u() {
        super(opcode);
    }

    public InstructionConvert_u(int position) {
        super(opcode, position);
    }
}