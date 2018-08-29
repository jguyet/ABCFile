package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionAdd_i
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionAdd_i.class);
    public static final Opcode opcode = Opcode.OP_add_i;

    public InstructionAdd_i() {
        super(opcode);
    }

    public InstructionAdd_i(int position) {
        super(opcode, position);
    }
}