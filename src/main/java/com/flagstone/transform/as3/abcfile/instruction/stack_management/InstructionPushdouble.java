package com.flagstone.transform.as3.abcfile.instruction.stack_management;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionPushdouble
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionPushdouble.class);
    public static final Opcode opcode = Opcode.OP_pushdouble;
    protected int index;

    public InstructionPushdouble(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parse(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionPushdouble(int index, int position) {
        super(opcode, position);
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public InstructionPushdouble() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getUI32B(this.index));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        parse(date);
    }

    private void parse(ByteBufferFlash date) throws Exception {
        this.index = date.unsigned30int();
    }
}