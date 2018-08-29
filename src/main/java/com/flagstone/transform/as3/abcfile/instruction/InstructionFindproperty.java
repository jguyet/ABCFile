package com.flagstone.transform.as3.abcfile.instruction;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionFindproperty
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionFindproperty.class);
    public static final Opcode opcode = Opcode.OP_findproperty;
    protected int index;

    public InstructionFindproperty(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parse(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionFindproperty(int index, int position) {
        super(opcode);
        this.index = index;
    }

    public InstructionFindproperty() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getUI32B(getIndex()));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        parse(date);
    }

    private void parse(ByteBufferFlash date) throws Exception {
        this.index = date.unsigned32int();
    }

    public int getIndex() {
        return this.index;
    }
}