package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionEsc_esc_xattr
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionEsc_esc_xattr.class);
    public static final Opcode opcode = Opcode.OP_esc_xattr;

    public InstructionEsc_esc_xattr() {
        super(opcode);
    }

    public InstructionEsc_esc_xattr(int position) {
        super(opcode, position);
    }
}