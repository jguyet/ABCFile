package com.flagstone.transform.as3.abcfile.instruction.invocation;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionCallmethod
extends InstructionCallBase {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallmethod.class);
    public static final Opcode opcode = Opcode.OP_callmethod;

    public InstructionCallmethod(ByteBufferFlash date, int position) {
        super(opcode, date, position);
    }

    public InstructionCallmethod(int index, int arg_count, int position) {
        super(opcode, index, arg_count, position);
    }

    public InstructionCallmethod() {
        super(opcode);
    }
}