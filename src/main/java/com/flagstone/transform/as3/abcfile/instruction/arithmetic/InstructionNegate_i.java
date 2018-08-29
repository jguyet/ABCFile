package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionNegate_i
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNegate_i.class);
    public static final Opcode opcode = Opcode.OP_negate_i;

    public InstructionNegate_i() {
        super(opcode);
    }

    public InstructionNegate_i(int position) {
        super(opcode, position);
    }
}