package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionInstanceof
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionInstanceof.class);
    public static final Opcode opcode = Opcode.OP_instanceof;

    public InstructionInstanceof() {
        super(opcode);
    }

    public InstructionInstanceof(int position) {
        super(opcode, position);
    }
}