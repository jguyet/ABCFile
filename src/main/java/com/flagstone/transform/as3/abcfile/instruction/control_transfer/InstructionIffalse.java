package com.flagstone.transform.as3.abcfile.instruction.control_transfer;
import com.flagstone.transform.as3.abcfile.instruction.IInstructionBaseJump;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionIffalse
extends InstructionJumpBase
implements IInstructionBaseJump {
    public static final Opcode opcode = Opcode.OP_iffalse;

    public InstructionIffalse(ByteBufferFlash date, int position) {
        super(opcode, date, position);
    }

    public InstructionIffalse() {
        super(opcode);
    }

    public InstructionIffalse(int offset, int position) {
        super(opcode, offset, position);
    }

    @Deprecated
    public InstructionIffalse(Opcode opcode, byte[] date, int position, int jump, int offset) {
        super(opcode, date, position, jump, offset);
    }

    @Deprecated
    public InstructionIffalse(Opcode opcode, byte[] date, int position, Instruction target) {
        super(opcode, date, position, target);
    }

    public Object runInstruction() throws Exception, VerifyError {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        Boolean value = (Boolean) stack.pop();
        if (!value.booleanValue()) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }
}