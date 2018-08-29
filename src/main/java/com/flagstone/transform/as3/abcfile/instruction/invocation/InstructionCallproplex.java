package com.flagstone.transform.as3.abcfile.instruction.invocation;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionCallproplex
extends InstructionCallBase {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallproplex.class);
    public static final Opcode opcode = Opcode.OP_callproplex;

    public InstructionCallproplex(ByteBufferFlash date, int position) {
        super(opcode, date, position);
    }

    public InstructionCallproplex(int index, int arg_count, int position) {
        super(opcode, index, arg_count, position);
    }

    public InstructionCallproplex() {
        super(opcode);
    }
}