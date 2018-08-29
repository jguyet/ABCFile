package com.flagstone.transform.as3.abcfile.instruction.exception;
import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.structures.ExceptionInfo;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstructionNewcatchExtd
extends InstructionNewcatch {
    public static final ABCLogger LOGGER = new ABCLogger(InstructionNewcatchExtd.class);
    private ExceptionInfo exception_info;
    protected AbcFile abc;

    public InstructionNewcatchExtd(ByteBufferFlash date, int position, AbcFile abc) {
        super(date, position);
        this.abc = abc;
        this.exception_info = ((ExceptionInfo) abc.ENexception_info.get(this.index));
    }

    public InstructionNewcatchExtd(ExceptionInfo exception_info, int position) {
        super(exception_info.getId(), position);
        this.exception_info = exception_info;
    }

    public ExceptionInfo getException_info() {
        return this.exception_info;
    }

    public void setException_info(ExceptionInfo exceptionInfo) {
        this.exception_info = exceptionInfo;
        getIndex();
    }

    public int getIndex() {
        this.index = this.exception_info.getId();
        return this.index;
    }

    public void parseDate(ByteBufferFlash date) throws Exception {
        super.parseDate(date);
        this.exception_info = ((ExceptionInfo) this.abc.ENexception_info.get(this.index));
    }
}