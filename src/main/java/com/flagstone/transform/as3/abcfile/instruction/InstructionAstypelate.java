package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionAstypelate
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionAstypelate.class);
    public static final Opcode opcode = Opcode.OP_astypelate;

    public InstructionAstypelate(int position) {
        super(opcode, position);
    }

    public InstructionAstypelate() {
        super(opcode);
    }
}