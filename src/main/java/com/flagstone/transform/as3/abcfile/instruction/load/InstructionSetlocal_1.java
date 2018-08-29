package com.flagstone.transform.as3.abcfile.instruction.load;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionSetlocal_1
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionSetlocal_1.class);
    public static final Opcode opcode = Opcode.OP_setlocal1;

    public InstructionSetlocal_1() {
        super(opcode);
    }

    public InstructionSetlocal_1(int position) {
        super(opcode, position);
    }
}