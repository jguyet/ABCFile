package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionReturnvoid
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionReturnvoid.class);
    public static final Opcode opcode = Opcode.OP_returnvoid;

    public InstructionReturnvoid() {
        super(opcode);
    }

    public InstructionReturnvoid(int position) {
        super(opcode, position);
    }
}