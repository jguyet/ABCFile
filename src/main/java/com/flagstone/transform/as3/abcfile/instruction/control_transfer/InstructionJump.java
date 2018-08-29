package com.flagstone.transform.as3.abcfile.instruction.control_transfer;
import com.flagstone.transform.as3.abcfile.instruction.IInstruction;
import com.flagstone.transform.as3.abcfile.instruction.IInstructionBaseJump;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionJump
extends InstructionJumpBase
implements IInstructionBaseJump {
    public static final Opcode opcode = Opcode.OP_jump;

    @Deprecated
    public InstructionJump(ByteBufferFlash date, int position) {
        super(opcode, date, position);
    }

    public InstructionJump() {
        super(opcode);
    }

    public InstructionJump(int offset, int position) {
        super(opcode, offset, position);
    }

    @Deprecated
    public InstructionJump(Opcode opcode, byte[] date, int position, int jump, int offset) {
        super(opcode, date, position, jump, offset);
    }

    @Deprecated
    public InstructionJump(Opcode opcode, byte[] date, int position, IInstruction target) {
        super(opcode, date, position, target);
    }

    public InstructionJump(Opcode opcode, IInstruction target) {
        super(opcode, target);
    }

    public Object runInstruction()
    throws Exception, VerifyError {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        return Boolean.valueOf(true);
    }
}