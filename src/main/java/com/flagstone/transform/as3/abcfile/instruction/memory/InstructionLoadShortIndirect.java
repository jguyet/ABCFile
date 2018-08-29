package com.flagstone.transform.as3.abcfile.instruction.memory;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionLoadShortIndirect
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionLoadShortIndirect.class);
    public static final Opcode opcode = Opcode.OP_li16;
    protected int value;

    public InstructionLoadShortIndirect() {
        super(opcode);
    }

    public InstructionLoadShortIndirect(int position) {
        super(opcode, position);
    }
}