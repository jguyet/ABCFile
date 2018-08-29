package com.flagstone.transform.as3.abcfile.instruction.arithmetic;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public class InstructionIstypelate
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionIstypelate.class);
    public static final Opcode opcode = Opcode.OP_istypelate;

    public InstructionIstypelate() {
        super(opcode);
    }

    public InstructionIstypelate(int position) {
        super(opcode, position);
    }
}