package com.flagstone.transform.as3.abcfile.instruction.arithmetic;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionDeclocal_i
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionDeclocal_i.class);
    public static final Opcode opcode = Opcode.OP_declocal_i;
    protected int index;

    public InstructionDeclocal_i(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionDeclocal_i(int index, int position) {
        super(opcode);
        this.index = index;
    }

    public InstructionDeclocal_i() {
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