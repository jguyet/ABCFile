package com.flagstone.transform.as3.abcfile.instruction;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionSetslot
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionSetslot.class);
    public static final Opcode opcode = Opcode.OP_setslot;

    protected int index;

    public InstructionSetslot(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionSetslot(int index, int position) {
        super(opcode, position);
        this.index = index;
    }

    public InstructionSetslot() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getUI32B(this.index));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        this.index = date.unsigned30int();
    }

    public int getIndex() {
        return this.index;
    }
}