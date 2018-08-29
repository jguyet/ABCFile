package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionMultiply_i
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionMultiply_i.class);
    public static final Opcode opcode = Opcode.OP_multiply_i;

    public InstructionMultiply_i() {
        super(opcode);
    }

    public InstructionMultiply_i(int position) {
        super(opcode, position);
    }
}