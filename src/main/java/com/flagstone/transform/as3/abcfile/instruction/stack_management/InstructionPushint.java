package com.flagstone.transform.as3.abcfile.instruction.stack_management;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCallsupervoid;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionPushint
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallsupervoid.class);
    public static final Opcode opcode = Opcode.OP_pushint;

    protected int index = -1;

    public InstructionPushint(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parse(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionPushint(int index, int position) {
        super(opcode, position);
        this.index = index;
    }

    public InstructionPushint() {
        super(opcode);
    }

    public int getIndex() {
        return this.index;
    }

    public byte[] getDate() throws Exception {
        if (this.index >= 0) {
            ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
            byteDate.write(ByteBufferFlash.getUI32B(this.index));
            return byteDate.toByteArray();
        }
        LOGGER.info("Return row data because of wring index");
        return super.getDate();
    }

    public void parseDate(ByteBufferFlash date)
    throws Exception {
        parse(date);
    }

    private void parse(ByteBufferFlash date) throws Exception {
        this.index = date.unsigned30int();
    }
}