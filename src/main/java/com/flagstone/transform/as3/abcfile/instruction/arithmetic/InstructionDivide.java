package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionDivide
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionDivide.class);
    public static final Opcode opcode = Opcode.OP_divide;

    public InstructionDivide() {
        super(opcode);
    }

    public InstructionDivide(int position) {
        super(opcode, position);
    }
}