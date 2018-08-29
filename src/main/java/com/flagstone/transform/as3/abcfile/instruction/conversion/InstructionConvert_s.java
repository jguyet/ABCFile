package com.flagstone.transform.as3.abcfile.instruction.conversion;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionConvert_s
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionConvert_s.class);
    public static final Opcode opcode = Opcode.OP_convert_s;

    public InstructionConvert_s() {
        super(opcode);
    }

    public InstructionConvert_s(int position) {
        super(opcode, position);
    }
}