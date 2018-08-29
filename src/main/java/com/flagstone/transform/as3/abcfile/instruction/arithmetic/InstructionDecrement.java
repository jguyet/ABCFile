package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionDecrement
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionDecrement.class);
    public static final Opcode opcode = Opcode.OP_decrement;

    public InstructionDecrement() {
        super(opcode);
    }

    public InstructionDecrement(int position) {
        super(opcode, position);
    }
}