package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionIncrement_i
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionIncrement_i.class);
    public static final Opcode opcode = Opcode.OP_increment_i;

    public InstructionIncrement_i() {
        super(opcode);
    }

    public InstructionIncrement_i(int position) {
        super(opcode, position);
    }
}