package com.flagstone.transform.as3.abcfile.instruction;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionReturnvalue
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionReturnvalue.class);
    public static final Opcode opcode = Opcode.OP_returnvalue;

    public InstructionReturnvalue() {
        super(opcode);
    }

    public InstructionReturnvalue(int position) {
        super(opcode, position);
    }
}