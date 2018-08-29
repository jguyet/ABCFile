package com.flagstone.transform.as3.abcfile.instruction.object_creation;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCallsupervoid;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionConstruct
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCallsupervoid.class);
    public static final Opcode opcode = Opcode.OP_construct;
    protected int arg_count;

    public InstructionConstruct(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionConstruct(int arg_count, int position) {
        super(opcode, position);
        this.arg_count = arg_count;
    }

    public InstructionConstruct() {
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

    public Object runInstruction()
    throws Exception, TypeError {
        LOGGER.info("not implemented yet InstructionConstruct");
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        List < Object > args = new ArrayList();
        for (int i = 0; i < this.arg_count; i++) {
            Object arg = stack.pop();
            args.add(arg);
        }
        Object object = stack.pop();

        stack.push(Integer.valueOf(0));

        return Boolean.valueOf(true);
    }

    public int getArg_count() {
        return this.arg_count;
    }
}