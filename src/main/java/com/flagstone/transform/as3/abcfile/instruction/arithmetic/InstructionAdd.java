package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionAdd
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionAdd.class);
    public static final Opcode opcode = Opcode.OP_add;

    public InstructionAdd() {
        super(opcode);
    }

    public InstructionAdd(int position) {
        super(opcode, position);
    }
}