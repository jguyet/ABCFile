package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionSubtract_i
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionSubtract_i.class);
    public static final Opcode opcode = Opcode.OP_subtract_i;

    public InstructionSubtract_i() {
        super(opcode);
    }

    public InstructionSubtract_i(int position) {
        super(opcode, position);
    }
}