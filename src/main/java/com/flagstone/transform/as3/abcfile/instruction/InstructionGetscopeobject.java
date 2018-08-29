package com.flagstone.transform.as3.abcfile.instruction;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionGetscopeobject
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionGetscopeobject.class);
    public static final Opcode opcode = Opcode.OP_getscopeobject;

    protected int index;

    public InstructionGetscopeobject(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionGetscopeobject(int index, int position) {
        super(opcode, position);
        this.index = index;
    }

    public InstructionGetscopeobject() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.unsignedByte((byte) this.index));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        this.index = date.unsignedByte();
    }

    public int getIndex() {
        return this.index;
    }
}