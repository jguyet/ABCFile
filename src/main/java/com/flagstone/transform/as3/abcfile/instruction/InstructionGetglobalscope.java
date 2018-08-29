package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionGetglobalscope
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionGetglobalscope.class);
    public static final Opcode opcode = Opcode.OP_getglobalscope;

    public InstructionGetglobalscope() {
        super(opcode);
    }

    public InstructionGetglobalscope(int position) {
        super(opcode, position);
    }
}