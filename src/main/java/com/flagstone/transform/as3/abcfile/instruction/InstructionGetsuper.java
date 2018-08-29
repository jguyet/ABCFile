package com.flagstone.transform.as3.abcfile.instruction;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.IStackItem;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionGetsuper
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionGetsuper.class);
    public static final Opcode opcode = Opcode.OP_getsuper;
    protected int index;

    public InstructionGetsuper(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionGetsuper(int index, int position) {
        super(opcode);
        this.index = index;
    }

    public InstructionGetsuper() {
        super(opcode);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getUI32B(getIndex()));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        this.index = date.unsigned30int();
    }

    public int getIndex() {
        return this.index;
    }

    public IStackInt < Object > [] runInstruction()
    throws Exception, TypeError {
        IStackInt stack = StackFactory.getStack();
        IStackInt scopeStack = StackFactory.getScopeStack();

        IStackInt[] result = {
            StackFactory.getStack(),
            StackFactory.getScopeStack()
        };

        throw new Exception("Not implemented yet in InstructionGetsuper");
    }

    public IStackItem makeInstruction()
    throws Exception {
        return (IStackItem) StackFactory.getStack().pop();
    }
}