package com.flagstone.transform.as3.abcfile.instruction;

import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Set;

import com.flagstone.transform.as3.abcfile.stack.IStackItem;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class Instruction
implements IInstruction {
    public static final ABCLogger LOGGER = new ABCLogger(Instruction.class);
    protected Set < IInstruction > incomingInstructions = new HashSet();

    protected Opcode opcode;
    protected byte[] date = new byte[0];

    public int position;

    public Instruction(Opcode opcode, byte[] date, int position) {
        if (date != null) {
            this.date = date;
        }
        this.opcode = opcode;
        this.position = position;
    }

    public Instruction(Opcode opcode) {
        this.opcode = opcode;
    }

    public Instruction(Opcode opcode, int position) {
        this.opcode = opcode;
        this.position = position;
    }

    public int getPosition() {
        return this.position;
    }

    public Opcode getOpcode() {
        return this.opcode;
    }

    public int getSize() throws Exception {
        return getDate().length + 1;
    }

    public byte[] getDate() throws Exception {
        return this.date;
    }

    public byte[] getBytes() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write((byte) this.opcode.getOpcode());
        try {
            byteDate.write(getDate());
        } catch (Exception e) {
            LOGGER.error("Can't parse instruction get raw date", e);
            byteDate.write(this.date);
        }
        return byteDate.toByteArray();
    }

    public void setOpcode(Opcode opcode) {
        this.opcode = opcode;
    }

    public void setDate(byte[] date) {
        this.date = date;
    }

    public void addIncomingInstruction(IInstruction ins) {
        this.incomingInstructions.add(ins);
    }

    public boolean removeIncomingInstruction(IInstruction ins) {
        return this.incomingInstructions.remove(ins);
    }

    public Set < IInstruction > getIncomingInstruction() {
        return this.incomingInstructions;
    }

    public void parseDate(byte[] date) throws Exception {
        throw new Exception("Brak implementacji " + Instruction.class);
    }

    public void parseDate(ByteBufferFlash bbuf) throws Exception {
        throw new Exception("Brak implementacji " + Instruction.class);
    }

    public Object runInstruction() throws Exception {
        throw new Exception("Brak implementacji " + Instruction.class);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public IStackItem makeInstruction() throws Exception {
        System.out.println("not implemented");
        return null;
    }

    public String toString() {
        return String.format("Instruction %s incoming ins %s \n", new Object[] {
            this.opcode, this.incomingInstructions
        });
    }
}