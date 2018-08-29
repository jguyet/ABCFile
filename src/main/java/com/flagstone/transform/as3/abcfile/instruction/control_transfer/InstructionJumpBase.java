package com.flagstone.transform.as3.abcfile.instruction.control_transfer;

import java.io.ByteArrayOutputStream;

import com.flagstone.transform.as3.abcfile.instruction.IInstruction;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public abstract class InstructionJumpBase
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionJumpBase.class);

    public IInstruction target;
    public int offset;

    public InstructionJumpBase(Opcode opcode, ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionJumpBase(Opcode opcode) {
        super(opcode);
    }

    public InstructionJumpBase(Opcode opcode, int offset, int position) {
        super(opcode);
        this.offset = offset;
    }

    public InstructionJumpBase(Opcode opcode, byte[] date, int position, int jump, int offset) {
        super(opcode, position);
        this.offset = offset;
    }

    public InstructionJumpBase(Opcode opcode, byte[] date, int position, IInstruction target) {
        super(opcode, position);
        this.target = target;
    }

    public InstructionJumpBase(Opcode opcode, int position, IInstruction target) {
        super(opcode);
        this.target = target;
    }

    public InstructionJumpBase(Opcode opcode, IInstruction target) {
        super(opcode);
        this.target = target;
    }

    public int getOffset() {
        return this.offset;
    }

    public IInstruction getTarget() {
        return this.target;
    }

    public void setTarget(IInstruction target) {
        this.target = target;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getS24(this.offset));
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        this.offset = date.readS24();
    }
}