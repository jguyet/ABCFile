package com.flagstone.transform.as3.abcfile.instruction;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionHasnext2
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionHasnext2.class);
    public static final Opcode opcode = Opcode.OP_hasnext2;
    protected int object_reg;
    protected int index_reg;

    public InstructionHasnext2(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionHasnext2(int object_reg, int index_reg, int position) {
        super(opcode, position);
        this.object_reg = object_reg;
        this.index_reg = index_reg;
    }

    public InstructionHasnext2() {
        super(opcode);
    }

    public int getObject_reg() {
        return this.object_reg;
    }

    public int getIndex_reg() {
        return this.index_reg;
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getUI32B(this.object_reg));
        byteDate.write(ByteBufferFlash.getUI32B(this.index_reg));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        this.object_reg = date.unsigned32int();
        this.index_reg = date.unsigned32int();
    }

    public Object runInstruction()
    throws Exception, TypeError {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();
        LOGGER.info("Not implemented yet InstructionHasnext2");
        int cur_index = 0;
        if (cur_index == 0) {
            stack.push(new Boolean(false));
        } else {
            stack.push(new Boolean(true));
        }

        return Boolean.valueOf(true);
    }
}