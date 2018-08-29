package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionLessthan
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionLessthan.class);
    public static final Opcode opcode = Opcode.OP_lessthan;

    public InstructionLessthan() {
        super(opcode);
    }

    public InstructionLessthan(int position) {
        super(opcode, position);
    }
}