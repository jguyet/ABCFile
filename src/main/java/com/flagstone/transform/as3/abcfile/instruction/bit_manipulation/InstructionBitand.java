package com.flagstone.transform.as3.abcfile.instruction.bit_manipulation;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionBitand
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionBitand.class);
    public static final Opcode opcode = Opcode.OP_bitand;

    public InstructionBitand(int position) {
        super(opcode, position);
    }

    public InstructionBitand() {
        super(opcode);
    }
}