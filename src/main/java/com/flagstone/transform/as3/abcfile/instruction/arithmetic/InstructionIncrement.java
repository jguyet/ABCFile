package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionIncrement
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionIncrement.class);
    public static final Opcode opcode = Opcode.OP_increment;

    public InstructionIncrement() {
        super(opcode);
    }

    public InstructionIncrement(int position) {
        super(opcode, position);
    }
}