package com.flagstone.transform.as3.abcfile.instruction.debugging;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionDebugline
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionDebugline.class);
    public static final Opcode opcode = Opcode.OP_debugline;

    protected int linenum;

    public InstructionDebugline(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionDebugline(int linenum, int position) {
        super(opcode, position);
        this.linenum = linenum;
    }

    public InstructionDebugline() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getUI32B(this.linenum));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        this.linenum = date.unsigned30int();
    }

    public int getLinenum() {
        return this.linenum;
    }
}