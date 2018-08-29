package com.flagstone.transform.as3.abcfile.instruction.load;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionSetlocal_0
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionSetlocal_0.class);
    public static final Opcode opcode = Opcode.OP_setlocal0;

    public InstructionSetlocal_0() {
        super(opcode);
    }

    public InstructionSetlocal_0(int position) {
        super(opcode, position);
    }
}