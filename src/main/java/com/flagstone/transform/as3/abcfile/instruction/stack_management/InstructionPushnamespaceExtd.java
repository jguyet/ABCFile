package com.flagstone.transform.as3.abcfile.instruction.stack_management;

import java.util.ArrayList;

import com.flagstone.transform.as3.abcfile.cpool.NamespaceInfo;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.stack.TypeError;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionPushnamespaceExtd
extends InstructionPushnamespace {
    private NamespaceInfo namespace_info;
    protected CpoolInfo cpool;

    public InstructionPushnamespaceExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.cpool = cpool;
        this.namespace_info = ((NamespaceInfo) cpool.getNamespaces().get(this.index));
    }

    public InstructionPushnamespaceExtd(NamespaceInfo namespace_info, int position) {
        super(namespace_info.getId(), position);
        this.namespace_info = namespace_info;
    }

    public NamespaceInfo getNamespace_info() {
        return this.namespace_info;
    }

    public void setNamespace_info(NamespaceInfo namespaceInfo) {
        this.namespace_info = namespaceInfo;
        getIndex();
    }

    public int getIndex() {
        this.index = this.namespace_info.getId();
        return this.index;
    }

    public Object runInstruction()
    throws Exception, TypeError {
        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        stack.push(this.namespace_info);

        return Boolean.valueOf(true);
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        this.namespace_info = ((NamespaceInfo) this.cpool.getNamespaces().get(this.index));
    }
}