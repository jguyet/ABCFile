package com.flagstone.transform.as3.abcfile.instruction.stack_management;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCallsupervoid;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionPushbyte
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallsupervoid.class);
    public static final Opcode opcode = Opcode.OP_pushbyte;
    protected int byte_value;

    public InstructionPushbyte(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionPushbyte(int byte_value, int position) {
        super(opcode, position);
        this.byte_value = byte_value;
    }

    public InstructionPushbyte() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.unsignedByte((byte) this.byte_value));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date)
    throws Exception {
        this.byte_value = date.get();
        if (this.byte_value < 0) {
            System.out.println("It's unsigned but minus value: " + this.byte_value);
        }
    }

    public Object runInstruction()
    throws Exception {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        stack.push(Integer.valueOf(this.byte_value));

        return Boolean.valueOf(true);
    }

    public int getByte_value() {
        return this.byte_value;
    }
}