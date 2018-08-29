package com.flagstone.transform.as3.abcfile.instruction.object_creation;
import com.flagstone.transform.as3.abcfile.cpool.StringInfo;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionDxnsExtd
extends InstructionDxns {
    private StringInfo string_info;

    public InstructionDxnsExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        if ((this.index >= 0) && (this.index < cpool.getStrings().size())) {
            this.string_info = ((StringInfo) cpool.getStrings().get(this.index));
        } else {
            LOGGER.error(String.format("Wrong index %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }

    public InstructionDxnsExtd(StringInfo string_info, int position) {
        super(string_info.getId(), position);
        this.string_info = string_info;
    }

    public StringInfo getString_info() {
        return this.string_info;
    }

    public void setString_info(StringInfo stringInfo) {
        this.string_info = stringInfo;
        getIndex();
    }

    public int getIndex() {
        if (this.string_info != null) {
            this.index = this.string_info.getId();
        }
        return this.index;
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