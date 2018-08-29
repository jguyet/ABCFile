package com.flagstone.transform.as3.abcfile.instruction.invocation;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionCallpropvoid
extends InstructionCallBase {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallpropvoid.class);
    public static final Opcode opcode = Opcode.OP_callpropvoid;

    public InstructionCallpropvoid(ByteBufferFlash date, int position) {
        super(opcode, date, position);
    }

    public InstructionCallpropvoid(int index, int arg_count, int position) {
        super(opcode, index, arg_count, position);
    }

    public InstructionCallpropvoid() {
        super(opcode);
    }
}