package com.flagstone.transform.as3.abcfile.instruction.object_creation;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionNewclass
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNewclass.class);
    public static final Opcode opcode = Opcode.OP_newclass;
    protected int index = -1;

    public InstructionNewclass(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parse(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionNewclass(int index, int position) {
        super(opcode, position);
        this.index = index;
    }

    public InstructionNewclass() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        if (getIndex() >= 0) {
            ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
            byteDate.write(ByteBufferFlash.getUI32B(getIndex()));
            return byteDate.toByteArray();
        }
        LOGGER.info(String.format("Return raw date, can't parse instruction InstructionNewclass with index %d", new Object[] {
            Integer.valueOf(getIndex())
        }));
        return this.date;
    }

    public void parseDate(ByteBufferFlash date)
    throws Exception {
        parse(date);
    }

    private void parse(ByteBufferFlash date) throws Exception {
        this.index = date.unsigned30int();
        if (this.index == -1) {
            LOGGER.error(String.format("Wrong InstructionNewclass index %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }

    public int getIndex() {
        return this.index;
    }
}