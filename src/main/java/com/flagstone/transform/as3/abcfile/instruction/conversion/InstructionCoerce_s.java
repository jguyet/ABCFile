package com.flagstone.transform.as3.abcfile.instruction.conversion;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionCoerce_s
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCoerce_s.class);
    public static final Opcode opcode = Opcode.OP_coerce_s;

    public InstructionCoerce_s() {
        super(opcode);
    }

    public InstructionCoerce_s(int position) {
        super(opcode, position);
    }
}