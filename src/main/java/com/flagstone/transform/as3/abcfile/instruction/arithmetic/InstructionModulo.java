package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionModulo
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionModulo.class);
    public static final Opcode opcode = Opcode.OP_modulo;

    public InstructionModulo() {
        super(opcode);
    }

    public InstructionModulo(int position) {
        super(opcode, position);
    }
}