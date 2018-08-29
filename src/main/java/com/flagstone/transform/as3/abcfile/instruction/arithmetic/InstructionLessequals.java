package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionLessequals
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionLessequals.class);
    public static final Opcode opcode = Opcode.OP_lessequals;

    public InstructionLessequals() {
        super(opcode);
    }

    public InstructionLessequals(int position) {
        super(opcode, position);
    }
}