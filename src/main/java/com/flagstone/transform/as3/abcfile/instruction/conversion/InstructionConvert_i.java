package com.flagstone.transform.as3.abcfile.instruction.conversion;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionConvert_i
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionConvert_i.class);
    public static final Opcode opcode = Opcode.OP_convert_i;

    public InstructionConvert_i() {
        super(opcode);
    }

    public InstructionConvert_i(int position) {
        super(opcode, position);
    }
}