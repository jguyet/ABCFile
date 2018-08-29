package com.flagstone.transform.as3.abcfile.instruction.load;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionGetlocal_1
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionGetlocal_1.class);
    public static final Opcode opcode = Opcode.OP_getlocal1;

    public InstructionGetlocal_1() {
        super(opcode);
    }

    public InstructionGetlocal_1(int position) {
        super(opcode, position);
    }
}