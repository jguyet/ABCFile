package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionNegate
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNegate.class);
    public static final Opcode opcode = Opcode.OP_negate;

    public InstructionNegate() {
        super(opcode);
    }

    public InstructionNegate(int position) {
        super(opcode, position);
    }
}