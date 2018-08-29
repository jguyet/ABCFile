package com.flagstone.transform.as3.abcfile.instruction.conversion;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionConvert_b
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionConvert_b.class);
    public static final Opcode opcode = Opcode.OP_convert_b;

    public InstructionConvert_b() {
        super(opcode);
    }

    public InstructionConvert_b(int position) {
        super(opcode, position);
    }
}