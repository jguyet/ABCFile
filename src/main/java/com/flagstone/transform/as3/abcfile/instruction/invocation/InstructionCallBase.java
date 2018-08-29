package com.flagstone.transform.as3.abcfile.instruction.invocation;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public abstract class InstructionCallBase
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallBase.class);

    protected int index = -1;
    protected int arg_count;

    public InstructionCallBase(Opcode opcode, ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parse(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionCallBase(Opcode opcode, int index, int arg_count, int position) {
        super(opcode, position);
        this.index = index;
        this.arg_count = arg_count;
    }

    public InstructionCallBase(Opcode opcode) {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        if (this.index >= 0) {
            ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
            byteDate.write(ByteBufferFlash.getUI32B(getIndex()));
            byteDate.write(ByteBufferFlash.getUI32B(this.arg_count));
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
        this.arg_count = date.unsigned30int();
    }

    public int getIndex() {
        return this.index;
    }

    public int getArg_count() {
        return this.arg_count;
    }
}