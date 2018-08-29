package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionEsc_xelem
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionEsc_xelem.class);
    public static final Opcode opcode = Opcode.OP_esc_xelem;

    public InstructionEsc_xelem() {
        super(opcode);
    }

    public InstructionEsc_xelem(int position) {
        super(opcode, position);
    }
}