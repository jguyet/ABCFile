package com.flagstone.transform.as3.abcfile.instruction.bit_manipulation;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionBitnot
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionBitnot.class);
    public static final Opcode opcode = Opcode.OP_bitnot;

    public InstructionBitnot(int position) {
        super(opcode, position);
    }

    public InstructionBitnot() {
        super(opcode);
    }
}