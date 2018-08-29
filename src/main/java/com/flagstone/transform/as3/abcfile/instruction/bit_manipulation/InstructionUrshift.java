package com.flagstone.transform.as3.abcfile.instruction.bit_manipulation;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionUrshift
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionUrshift.class);
    public static final Opcode opcode = Opcode.OP_urshift;

    public InstructionUrshift(int position) {
        super(opcode, position);
    }

    public InstructionUrshift() {
        super(opcode);
    }
}