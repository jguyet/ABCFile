package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionCheckfilter
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCheckfilter.class);
    public static final Opcode opcode = Opcode.OP_checkfilter;

    public InstructionCheckfilter() {
        super(opcode);
    }

    public InstructionCheckfilter(int position) {
        super(opcode, position);
    }
}