package com.flagstone.transform.as3.abcfile.instruction;

import java.util.Set;

import com.flagstone.transform.as3.abcfile.stack.IStackItem;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public abstract interface IInstruction {
    public abstract byte[] getBytes()
    throws Exception;

    public abstract void parseDate(ByteBufferFlash paramByteBufferFlash)
    throws Exception;

    public abstract byte[] getDate()
    throws Exception;

    public abstract int getSize()
    throws Exception;

    public abstract Object runInstruction()
    throws Exception;

    public abstract IStackItem makeInstruction()
    throws Exception;

    public abstract Opcode getOpcode();

    public abstract void setOpcode(Opcode paramOpcode);

    public abstract Set < IInstruction > getIncomingInstruction();

    public abstract void addIncomingInstruction(IInstruction paramIInstruction);

    public abstract int getPosition();

    public abstract void setPosition(int paramInt);

    public abstract void setDate(byte[] paramArrayOfByte);
}