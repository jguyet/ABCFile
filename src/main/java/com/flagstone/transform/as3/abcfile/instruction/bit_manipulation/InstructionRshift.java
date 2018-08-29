package com.flagstone.transform.as3.abcfile.instruction.bit_manipulation;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionRshift
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionRshift.class);
    public static final Opcode opcode = Opcode.OP_rshift;

    public InstructionRshift(int position) {
        super(opcode, position);
    }

    public InstructionRshift() {
        super(opcode);
    }
}