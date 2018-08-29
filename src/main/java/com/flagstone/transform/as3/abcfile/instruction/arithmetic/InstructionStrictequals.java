package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionStrictequals
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionStrictequals.class);
    public static final Opcode opcode = Opcode.OP_strictequals;

    public InstructionStrictequals() {
        super(opcode);
    }

    public InstructionStrictequals(int position) {
        super(opcode, position);
    }
}