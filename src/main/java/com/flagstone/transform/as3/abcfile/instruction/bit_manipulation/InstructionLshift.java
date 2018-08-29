package com.flagstone.transform.as3.abcfile.instruction.bit_manipulation;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionLshift
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionLshift.class);
    public static final Opcode opcode = Opcode.OP_lshift;

    public InstructionLshift(int position) {
        super(opcode, position);
    }

    public InstructionLshift() {
        super(opcode);
    }
}