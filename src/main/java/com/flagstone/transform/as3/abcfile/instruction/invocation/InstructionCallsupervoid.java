package com.flagstone.transform.as3.abcfile.instruction.invocation;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionCallsupervoid
extends InstructionCallBase {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallsupervoid.class);
    public static final Opcode opcode = Opcode.OP_callsupervoid;

    public InstructionCallsupervoid(ByteBufferFlash date, int position) {
        super(opcode, date, position);
    }

    public InstructionCallsupervoid(int index, int arg_count, int position) {
        super(opcode, index, arg_count, position);
    }

    public InstructionCallsupervoid() {
        super(opcode);
    }
}