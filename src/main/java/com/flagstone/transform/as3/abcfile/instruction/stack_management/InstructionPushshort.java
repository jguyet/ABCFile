package com.flagstone.transform.as3.abcfile.instruction.stack_management;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionPushshort
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionPushshort.class);
    public static final Opcode opcode = Opcode.OP_pushshort;
    protected int value;

    public InstructionPushshort(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public int getValue() {
        return this.value;
    }

    public InstructionPushshort(int value, int position) {
        super(opcode, position);
        this.value = value;
    }

    public InstructionPushshort() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getUI32B(this.value));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        this.value = date.unsigned32int();
    }

    public Object runInstruction()
    throws Exception {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        stack.push(Integer.valueOf(this.value));

        return Boolean.valueOf(true);
    }
}