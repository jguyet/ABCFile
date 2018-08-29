package com.flagstone.transform.as3.abcfile.instruction.load;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionSetlocal_3
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionSetlocal_3.class);
    public static final Opcode opcode = Opcode.OP_setlocal3;

    public InstructionSetlocal_3() {
        super(opcode);
    }

    public InstructionSetlocal_3(int position) {
        super(opcode, position);
    }
}