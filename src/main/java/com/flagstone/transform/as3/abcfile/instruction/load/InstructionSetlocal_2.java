package com.flagstone.transform.as3.abcfile.instruction.load;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionSetlocal_2
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionSetlocal_2.class);
    public static final Opcode opcode = Opcode.OP_setlocal2;

    public InstructionSetlocal_2() {
        super(opcode);
    }

    public InstructionSetlocal_2(int position) {
        super(opcode, position);
    }
}