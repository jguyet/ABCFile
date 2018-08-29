package com.flagstone.transform.as3.abcfile.instruction.bit_manipulation;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionBitor
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionBitor.class);
    public static final Opcode opcode = Opcode.OP_bitor;

    public InstructionBitor(int position) {
        super(opcode, position);
    }

    public InstructionBitor() {
        super(opcode);
    }
}