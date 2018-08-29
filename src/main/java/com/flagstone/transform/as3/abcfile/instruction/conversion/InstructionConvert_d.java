package com.flagstone.transform.as3.abcfile.instruction.conversion;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionConvert_d
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionConvert_d.class);
    public static final Opcode opcode = Opcode.OP_convert_d;

    public InstructionConvert_d() {
        super(opcode);
    }

    public InstructionConvert_d(int position) {
        super(opcode, position);
    }
}