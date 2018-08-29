package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionIn
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionIn.class);
    public static final Opcode opcode = Opcode.OP_in;

    public InstructionIn() {
        super(opcode);
    }

    public InstructionIn(int position) {
        super(opcode, position);
    }
}