package com.flagstone.transform.as3.abcfile.instructions.container;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.flagstone.transform.as3.abcfile.instruction.IInstruction;
import com.flagstone.transform.as3.abcfile.instruction.IInstructionBaseJump;
import com.flagstone.transform.as3.abcfile.structures.MethodBodyInfo;

public abstract interface IContainerInstruction {
    public abstract void setJumpTarget(IInstruction paramIInstruction1, IInstruction paramIInstruction2);

    public abstract int moveInstructionTo(IInstruction paramIInstruction, int paramInt);

    public abstract void addInstruction(IInstruction paramIInstruction);

    public abstract void addInstruction(IInstruction paramIInstruction, int paramInt);

    public abstract void addInstructions(List < IInstruction > paramList);

    public abstract void addInstructions(List < IInstruction > paramList, int paramInt);

    public abstract int removeInstruction(IInstruction paramIInstruction, boolean paramBoolean);

    public abstract int removeInstruction(int paramInt, boolean paramBoolean);

    public abstract List < IInstruction > removeInstructions(int[] paramArrayOfInt, boolean paramBoolean);

    public abstract List < IInstruction > removeInstructions(List < IInstruction > paramList, boolean paramBoolean);

    public abstract int setInstruction(IInstruction paramIInstruction, int paramInt);

    public abstract byte[] getBytes()
    throws Exception;

    public abstract List < IInstruction > getInstructions();

    public abstract void setInstructionDate(int paramInt, byte[] paramArrayOfByte);

    public abstract MethodBodyInfo getMethodBodyInfo();

    public abstract void setMethodBodyInfo(MethodBodyInfo paramMethodBodyInfo);

    public abstract Set < IInstruction > checkTargets(IInstruction paramIInstruction1, IInstruction paramIInstruction2);

    public abstract Set < IInstruction > checkTargets(int paramInt1, int paramInt2);

    public abstract boolean checkTargets(int[] paramArrayOfInt);

    public abstract Set < IInstruction > checkTargets(Collection < IInstruction > paramCollection);

    public abstract Set < IInstruction > checkExceptionTargets(Collection < IInstruction > paramCollection);

    public abstract Set < IInstruction > checkExceptionTargets(int paramInt1, int paramInt2);

    public abstract Set < IInstruction > checkJumpTargets(Collection < IInstruction > paramCollection);

    public abstract Set < IInstruction > checkJumpTargets(int[] paramArrayOfInt);

    public abstract Set < IInstruction > checkJumpTargets(IInstruction paramIInstruction1, IInstruction paramIInstruction2);

    public abstract void moveInstructionsTo(int paramInt1, int paramInt2, int paramInt3);

    public abstract int getPosId(IInstruction paramIInstruction);

    public abstract Integer getPosId(int paramInt);

    public abstract IInstruction getInstructionByOffset(int paramInt);

    public abstract void glueJump(IInstructionBaseJump paramIInstructionBaseJump1, IInstructionBaseJump paramIInstructionBaseJump2);

    public abstract void glueJump(IInstructionBaseJump paramIInstructionBaseJump);
}