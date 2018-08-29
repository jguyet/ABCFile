package com.flagstone.transform.as3.abcfile.instruction.debugging;
import com.flagstone.transform.as3.abcfile.cpool.StringInfo;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionDebugfileExtd
extends InstructionDebugfile {
    private StringInfo string_info;
    protected CpoolInfo cpool;

    public InstructionDebugfileExtd(ByteBufferFlash date, int position, CpoolInfo cpool) {
        super(date, position);
        this.cpool = cpool;
        if ((this.index >= 0) && (this.index < cpool.getStrings().size())) {
            this.string_info = ((StringInfo) cpool.getStrings().get(this.index));
        } else {
            LOGGER.error(String.format("Wring index %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }

    public InstructionDebugfileExtd(StringInfo string_info, int position) {
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

    public void parseDate(ByteBufferFlash bbuf) throws Exception {
        super.parseDate(this.date);
        if ((this.index >= 0) && (this.index < this.cpool.getStrings().size())) {
            this.string_info = ((StringInfo) this.cpool.getStrings().get(this.index));
        } else {
            LOGGER.error(String.format("Wring index %d", new Object[] {
                Integer.valueOf(this.index)
            }));
        }
    }
}