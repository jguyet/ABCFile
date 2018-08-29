package com.flagstone.transform.as3.abcfile.instruction.object_creation;
import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.structures.ClassInfo;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionNewclassExtd
extends InstructionNewclass {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNewclassExtd.class);
    private ClassInfo class_info;
    protected AbcFile abc;

    public InstructionNewclassExtd(ByteBufferFlash date, int position, AbcFile abc) {
        super(date, position);
        this.abc = abc;
        try {
            this.class_info = ((ClassInfo) abc.ENclass_info.get(this.index));
        } catch (Exception e) {
            LOGGER.info("Can't find method_in method_info with id" + this.index, e);
        }
    }

    public InstructionNewclassExtd(ClassInfo class_info, int position) {
        super(class_info.getId(), position);
        this.class_info = class_info;
    }

    public ClassInfo getClass_info() {
        return this.class_info;
    }

    public void setClass_info(ClassInfo classInfo) {
        this.class_info = classInfo;
        this.index = getIndex();
    }

    public int getIndex() {
        if (this.class_info == null) {
            return this.index;
        }
        this.index = this.class_info.getId();

        return this.index;
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        this.class_info = ((ClassInfo) this.abc.ENclass_info.get(this.index));
    }
}