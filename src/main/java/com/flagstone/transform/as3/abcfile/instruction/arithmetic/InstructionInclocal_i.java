package com.flagstone.transform.as3.abcfile.instruction.arithmetic;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionInclocal_i
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionInclocal_i.class);
    public static final Opcode opcode = Opcode.OP_inclocal_i;
    protected int index;

    public InstructionInclocal_i(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionInclocal_i(int index, int position) {
        super(opcode);
        this.index = index;
    }

    public InstructionInclocal_i() {
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