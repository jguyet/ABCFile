package com.flagstone.transform.as3.abcfile.instruction.load;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionGetlocal_2
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionGetlocal_2.class);
    public static final Opcode opcode = Opcode.OP_getlocal2;

    public InstructionGetlocal_2() {
        super(opcode);
    }

    public InstructionGetlocal_2(int position) {
        super(opcode, position);
    }
}