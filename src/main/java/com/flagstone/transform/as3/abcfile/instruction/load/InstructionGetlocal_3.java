package com.flagstone.transform.as3.abcfile.instruction.load;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionGetlocal_3
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionGetlocal_3.class);
    public static final Opcode opcode = Opcode.OP_getlocal3;

    public InstructionGetlocal_3() {
        super(opcode);
    }

    public InstructionGetlocal_3(int position) {
        super(opcode, position);
    }
}