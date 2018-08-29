package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionEquals
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionEquals.class);
    public static final Opcode opcode = Opcode.OP_equals;

    public InstructionEquals() {
        super(opcode);
    }

    public InstructionEquals(int position) {
        super(opcode, position);
    }
}