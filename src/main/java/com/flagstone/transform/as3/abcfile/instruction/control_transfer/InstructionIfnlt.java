package com.flagstone.transform.as3.abcfile.instruction.control_transfer;
import com.flagstone.transform.as3.abcfile.instruction.IInstructionBaseJump;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionIfnlt
extends InstructionJumpBase
implements IInstructionBaseJump {
    public static final Opcode opcode = Opcode.OP_ifnlt;

    public InstructionIfnlt(ByteBufferFlash date, int position) {
        super(opcode, date, position);
    }

    public InstructionIfnlt() {
        super(opcode);
    }

    public InstructionIfnlt(int offset, int position) {
        super(opcode, offset, position);
    }

    @Deprecated
    public InstructionIfnlt(Opcode opcode, byte[] date, int position, int jump, int offset) {
        super(opcode, date, position, jump, offset);
    }

    @Deprecated
    public InstructionIfnlt(Opcode opcode, byte[] date, int position, Instruction target) {
        super(opcode, date, position, target);
    }

    public Object runInstruction() throws Exception, VerifyError {
        IStackInt stack = StackFactory.getStack();
        IStackInt scopeStack = StackFactory.getScopeStack();

        Object value2 = stack.pop();
        Object value1 = stack.pop();

        if ((value2 instanceof Integer)) {
            if (((Integer) value1).intValue() < ((Integer) value2).intValue()) {
                return Boolean.valueOf(false);
            }
            return Boolean.valueOf(true);
        }

        throw new Exception("Not implemented yet InstructionIfnlt");
    }
}