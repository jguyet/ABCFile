package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionSubtract
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionSubtract.class);
    public static final Opcode opcode = Opcode.OP_subtract;

    public InstructionSubtract() {
        super(opcode);
    }

    public InstructionSubtract(int position) {
        super(opcode, position);
    }
}