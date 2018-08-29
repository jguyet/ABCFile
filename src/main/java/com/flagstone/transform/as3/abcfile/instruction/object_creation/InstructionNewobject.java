package com.flagstone.transform.as3.abcfile.instruction.object_creation;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionNewobject
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNewobject.class);
    public static final Opcode opcode = Opcode.OP_newobject;
    protected int arg_count;

    public InstructionNewobject(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionNewobject(int arg_count, int position) {
        super(opcode, position);
        this.arg_count = arg_count;
    }

    public InstructionNewobject() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getUI32B(this.arg_count));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        this.arg_count = date.unsigned30int();
    }

    public int getArg_count() {
        return this.arg_count;
    }
}