package com.flagstone.transform.as3.abcfile.instruction.object_creation;
import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionConstructpropExtd
extends InstructionConstructprop {
    private MultinameInfo multiname_info;
    protected CpoolInfo cpool;

    public InstructionConstructpropExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.cpool = cpool;
        if ((this.index >= 0) && (this.index < cpool.getMultinames().size())) {
            this.multiname_info = ((MultinameInfo) cpool.getMultinames().get(this.index));
        } else {
            LOGGER.error(String.format("Wrong index %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }

    public InstructionConstructpropExtd(MultinameInfo multiname_info, int arg_count, int position) {
        super(multiname_info.getId(), arg_count, position);
        this.multiname_info = multiname_info;
    }

    public MultinameInfo getMultiname_info() {
        return this.multiname_info;
    }

    public void setMultiname_info(MultinameInfo multinameInfo) {
        this.multiname_info = multinameInfo;
        getIndex();
    }

    public int getIndex() {
        if (this.multiname_info != null) {
            this.index = this.multiname_info.getId();
        }
        return this.index;
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        if ((this.index >= 0) && (this.index < this.cpool.getMultinames().size())) {
            this.multiname_info = ((MultinameInfo) this.cpool.getMultinames().get(this.index));
        } else {
            LOGGER.error(String.format("Wrong index %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }
}