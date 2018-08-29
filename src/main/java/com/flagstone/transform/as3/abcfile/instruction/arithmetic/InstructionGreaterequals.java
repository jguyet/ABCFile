package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionGreaterequals
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionGreaterequals.class);
    public static final Opcode opcode = Opcode.OP_greaterequals;

    public InstructionGreaterequals() {
        super(opcode);
    }

    public InstructionGreaterequals(int position) {
        super(opcode, position);
    }
}