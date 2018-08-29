package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionMultiply
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionMultiply.class);
    public static final Opcode opcode = Opcode.OP_multiply;

    public InstructionMultiply() {
        super(opcode);
    }

    public InstructionMultiply(int position) {
        super(opcode, position);
    }
}