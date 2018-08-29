package com.flagstone.transform.as3.abcfile.instruction.object_creation;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionDxns
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionDxns.class);
    public static final Opcode opcode = Opcode.OP_dxns;

    protected int index = -1;

    public InstructionDxns(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parse(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionDxns(int index, int position) {
        super(opcode, position);
        this.index = index;
    }

    public InstructionDxns() {
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