package com.flagstone.transform.as3.abcfile.instruction.load;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionGetlocal_0
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionGetlocal_0.class);
    public static final Opcode opcode = Opcode.OP_getlocal0;

    public InstructionGetlocal_0() {
        super(opcode);
    }

    public InstructionGetlocal_0(int position) {
        super(opcode, position);
    }
}