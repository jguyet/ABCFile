package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionDecrement_i
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionDecrement_i.class);
    public static final Opcode opcode = Opcode.OP_decrement_i;

    public InstructionDecrement_i() {
        super(opcode);
    }

    public InstructionDecrement_i(int position) {
        super(opcode, position);
    }
}