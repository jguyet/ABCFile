package com.flagstone.transform.as3.abcfile.instruction.conversion;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionCoerce_a
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCoerce_a.class);
    public static final Opcode opcode = Opcode.OP_coerce_a;

    public InstructionCoerce_a() {
        super(opcode);
    }

    public InstructionCoerce_a(int position) {
        super(opcode, position);
    }
}