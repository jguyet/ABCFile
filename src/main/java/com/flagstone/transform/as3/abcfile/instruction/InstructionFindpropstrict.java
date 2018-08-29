package com.flagstone.transform.as3.abcfile.instruction;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionFindpropstrict
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionFindpropstrict.class);
    public static final Opcode opcode = Opcode.OP_findpropstrict;
    protected int index = -1;

    public InstructionFindpropstrict(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parse(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionFindpropstrict(int index, int position) {
        super(opcode, position);
        this.index = index;
    }

    public InstructionFindpropstrict() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        if (this.index >= 0) {
            ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
            byteDate.write(ByteBufferFlash.getUI32B(getIndex()));
            return byteDate.toByteArray();
        }
        LOGGER.info("Return raw date");
        return super.getDate();
    }

    public void parseDate(ByteBufferFlash date)
    throws Exception {
        parse(date);
    }

    private void parse(ByteBufferFlash date) throws Exception {
        this.index = date.unsigned30int();
    }

    public int getIndex() {
        return this.index;
    }
}