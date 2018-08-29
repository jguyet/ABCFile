package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructioneTypeof
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructioneTypeof.class);
    public static final Opcode opcode = Opcode.OP_typeof;

    public InstructioneTypeof() {
        super(opcode);
    }

    public InstructioneTypeof(int position) {
        super(opcode, position);
    }
}