package com.flagstone.transform.as3.abcfile.instruction;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionKill
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionKill.class);
    public static final Opcode opcode = Opcode.OP_kill;
    protected int index;

    public InstructionKill(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionKill(int index, int position) {
        super(opcode, position);
        this.index = index;
    }

    public InstructionKill() {
        super(opcode);
    }

    public byte[] getDate()
    throws Exception {
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

    public void setIndex(int index) {
        this.index = index;
    }

    public IStackInt < Object > [] runInstruction()
    throws Exception, VerifyError {
        IStackInt stack = StackFactory.getStack();
        IStackInt scopeStack = StackFactory.getScopeStack();

        IStackInt[] result = {
            StackFactory.getStack(),
            StackFactory.getScopeStack()
        };
        return result;
    }
}