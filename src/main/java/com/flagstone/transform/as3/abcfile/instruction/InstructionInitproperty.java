package com.flagstone.transform.as3.abcfile.instruction;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionInitproperty
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionInitproperty.class);
    public static final Opcode opcode = Opcode.OP_initproperty;
    protected int index;

    public InstructionInitproperty(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parse(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionInitproperty(int index, int position) {
        super(opcode);
        this.index = index;
    }

    public InstructionInitproperty() {
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
        this.index = date.unsigned30int();
    }

    public int getIndex() {
        return this.index;
    }
}