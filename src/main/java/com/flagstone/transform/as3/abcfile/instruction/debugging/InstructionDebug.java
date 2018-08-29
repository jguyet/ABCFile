package com.flagstone.transform.as3.abcfile.instruction.debugging;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionDebug
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionDebug.class);
    public static final Opcode opcode = Opcode.OP_debug;
    protected int debug_type;
    protected int index;
    protected int reg;
    protected int extra;

    public InstructionDebug(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionDebug(int debug_type, int index, int reg, int extra, int position) {
        super(opcode, position);
        this.debug_type = debug_type;
        this.index = index;
        this.reg = reg;
        this.extra = extra;
    }

    public InstructionDebug() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.unsignedByte((byte) this.debug_type));

        byteDate.write(ByteBufferFlash.getUI32B(getIndex()));

        byteDate.write(ByteBufferFlash.unsignedByte((byte) this.reg));

        byteDate.write(ByteBufferFlash.getUI32B(this.extra));

        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        this.debug_type = date.unsignedByte();
        this.index = date.unsigned30int();
        this.reg = date.unsignedByte();
        this.extra = date.unsigned30int();
    }

    public int getIndex() {
        return this.index;
    }
}