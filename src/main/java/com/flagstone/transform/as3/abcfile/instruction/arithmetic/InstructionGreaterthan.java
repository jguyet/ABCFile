package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionGreaterthan
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionGreaterthan.class);
    public static final Opcode opcode = Opcode.OP_greaterthan;

    public InstructionGreaterthan() {
        super(opcode);
    }

    public InstructionGreaterthan(int position) {
        super(opcode, position);
    }
}