package com.flagstone.transform.as3.abcfile.instruction.control_transfer;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import com.flagstone.transform.as3.abcfile.instruction.IInstruction;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.stack.IStackInt;
import com.flagstone.transform.as3.abcfile.stack.StackFactory;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionLookupswitch
extends Instruction {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionLookupswitch.class);
    public static final Opcode opcode = Opcode.OP_lookupswitch;

    public ArrayList < IInstruction > targets = new ArrayList();
    public ArrayList < Integer > targetsPos = new ArrayList();

    public ArrayList < Integer > case_offsets = new ArrayList();
    public int maxindex;

    public InstructionLookupswitch(ByteBufferFlash date, int position) {
        super(opcode, position);
        try {
            parseDate(date);
        } catch (Exception e) {
            LOGGER.error("Blad parsowania instrukcji", e);
        }
    }

    public InstructionLookupswitch(Opcode opcode, byte[] date, int position, int maxindex, ArrayList < Integer > targetsPos, ArrayList < Integer > case_offsets) {
        super(opcode, date, position);
        this.maxindex = maxindex;
        this.targetsPos.addAll(targetsPos);
        this.case_offsets.addAll(case_offsets);
    }

    public InstructionLookupswitch(int position, int maxindex, ArrayList < Integer > targetsPos, ArrayList < Integer > case_offsets) {
        super(opcode, position);
        this.maxindex = maxindex;
        this.targetsPos.addAll(targetsPos);
        this.case_offsets.addAll(case_offsets);
    }

    public byte[] getDate() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getS24(((Integer) this.case_offsets.get(0)).intValue()));
        byteDate.write(ByteBufferFlash.getUI32B(this.maxindex));
        for (int i = 1; i < this.case_offsets.size(); i++) {
            byteDate.write(ByteBufferFlash.getS24(((Integer) this.case_offsets.get(i)).intValue()));
        }
        return byteDate.toByteArray();
    }

    public void parseDate(ByteBufferFlash date)
    throws Exception {
        int pos = date.position() - 1;
        int of = date.readS24();
        int target = pos + of ;
        this.targetsPos.add(Integer.valueOf(target));
        this.case_offsets.add(Integer.valueOf( of ));
        this.maxindex = date.unsigned32int();

        for (int i1 = 0; i1 <= this.maxindex; i1++) { of = date.readS24();
            target = pos + of ;
            this.targetsPos.add(Integer.valueOf(target));
            this.case_offsets.add(Integer.valueOf( of ));
        }
    }

    public Object runInstruction()
    throws Exception {
        LOGGER.info("Musi być odświeżona pozycja zanim wykona się tą instrukcję InstructionLookupswitch");

        IStackInt < Object > stack = StackFactory.getStack();
        IStackInt < Object > scopeStack = StackFactory.getScopeStack();

        Object index = stack.pop();

        if ((index instanceof Integer)) {
            if ((((Integer) index).intValue() < 0) || (((Integer) index).intValue() > this.case_offsets.size() - 1)) {
                int target = ((Integer) this.case_offsets.get(0)).intValue() + this.position;
                return Integer.valueOf(target);
            }
            int target = ((Integer) this.case_offsets.get(((Integer) index).intValue())).intValue() + this.position;
            return Integer.valueOf(target);
        }

        throw new Exception("Zły typ danych InstructionLookupswitch");
    }
}