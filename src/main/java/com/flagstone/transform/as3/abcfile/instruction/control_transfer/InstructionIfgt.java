package com.flagstone.transform.as3.abcfile.instruction.control_transfer;
import com.flagstone.transform.as3.abcfile.instruction.IInstructionBaseJump;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionIfgt
extends InstructionJumpBase
implements IInstructionBaseJump {
    public static final Opcode opcode = Opcode.OP_ifgt;

    public InstructionIfgt(ByteBufferFlash date, int position) {
        super(opcode, date, position);
    }

    public InstructionIfgt() {
        super(opcode);
    }

    public InstructionIfgt(int offset, int position) {
        super(opcode, offset, position);
    }

    @Deprecated
    public InstructionIfgt(Opcode opcode, byte[] date, int position, int jump, int offset) {
        super(opcode, date, position, jump, offset);
    }

    @Deprecated
    public InstructionIfgt(Opcode opcode, byte[] date, int position, Instruction target) {
        super(opcode, date, position, target);
    }

    public Object runInstruction() throws Exception, VerifyError {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        Object value2 = stack.pop();
        Object value1 = stack.pop();

        if (((value2 instanceof Integer)) && ((value1 instanceof Integer))) {
            if (((Integer) value2).intValue() < ((Integer) value1).intValue()) {
                return Boolean.valueOf(true);
            }
            return Boolean.valueOf(false);
        }

        throw new Exception("not implemented yet InstructionIfeq");
    }
}