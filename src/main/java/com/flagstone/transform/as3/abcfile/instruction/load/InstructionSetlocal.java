package com.flagstone.transform.as3.abcfile.instruction.load;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionSetlocal
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionSetlocal.class);
    public static final Opcode opcode = Opcode.OP_setlocal;
    protected int index;

    public InstructionSetlocal(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionSetlocal(int index, int position) {
        super(opcode, position);
        this.index = index;
    }

    public InstructionSetlocal() {
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