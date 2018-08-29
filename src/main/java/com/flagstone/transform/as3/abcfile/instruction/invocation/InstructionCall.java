package com.flagstone.transform.as3.abcfile.instruction.invocation;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.structures.MethodInfo;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionCall
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionCall.class);
    public static final Opcode opcode = Opcode.OP_call;
    protected int arg_count;

    public InstructionCall(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionCall(int arg_count, int position) {
        super(opcode);
        this.arg_count = arg_count;
    }

    public InstructionCall() {
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

    public Object runInstruction() throws Exception, TypeError {
        LOGGER.info("not implemented yet InstructionCall");
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        List < Object > args = new ArrayList();
        for (int i = 0; i < this.arg_count; i++) {
            Object arg = stack.pop();
            args.add(arg);
        }
        Object receiver = stack.pop();
        Object
        function = stack.pop();
        if ((function instanceof MethodInfo)) {
            throw new TypeError();
        }

        stack.push(Integer.valueOf(0));

        return Boolean.valueOf(true);
    }
}