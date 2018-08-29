package com.flagstone.transform.as3.abcfile.instruction.conversion;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionConvert_o
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionConvert_o.class);
    public static final Opcode opcode = Opcode.OP_convert_o;

    public InstructionConvert_o() {
        super(opcode);
    }

    public InstructionConvert_o(int position) {
        super(opcode, position);
    }
}