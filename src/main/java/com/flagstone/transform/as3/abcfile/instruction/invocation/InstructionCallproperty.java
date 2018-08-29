package com.flagstone.transform.as3.abcfile.instruction.invocation;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionCallproperty
extends InstructionCallBase {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallproperty.class);
    public static final Opcode opcode = Opcode.OP_callproperty;

    public InstructionCallproperty(ByteBufferFlash date, int position) {
        super(opcode, date, position);
    }

    public InstructionCallproperty(int index, int arg_count, int position) {
        super(opcode, index, arg_count, position);
    }

    public InstructionCallproperty() {
        super(opcode);
    }
}