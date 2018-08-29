package com.flagstone.transform.as3.abcfile.instruction;

public abstract interface IInstructionBaseJump {
    public abstract int getOffset();

    public abstract IInstruction getTarget();

    public abstract void setOffset(int paramInt);

    public abstract void setTarget(IInstruction paramIInstruction);

    public abstract Opcode getOpcode();
}