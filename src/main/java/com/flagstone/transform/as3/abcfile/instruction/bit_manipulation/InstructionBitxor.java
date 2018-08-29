package com.flagstone.transform.as3.abcfile.instruction.bit_manipulation;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionBitxor
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionBitxor.class);
    public static final Opcode opcode = Opcode.OP_bitxor;

    public InstructionBitxor(int position) {
        super(opcode, position);
    }

    public InstructionBitxor() {
        super(opcode);
    }
}