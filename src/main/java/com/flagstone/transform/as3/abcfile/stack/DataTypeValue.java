package com.flagstone.transform.as3.abcfile.stack;
import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;

public class DataTypeValue
implements IStackItem {
    public DataType type;
    public MultinameInfo multiname;
    public Integer multiNameIndex;
    public Integer slotIndex;
    public boolean printPopCommand;
    public String name;
    public boolean knownValue;

    public static class DataValue {
        public boolean boolValue;
        public int functionIndexValue;
        public int exceptionIndexValue;

        public DataValue setBoolValue(boolean boolValue) {
            this.boolValue = boolValue;
            return this;
        }

        public DataValue setFunctionIndexValue(int functionIndexValue) {
            this.functionIndexValue = functionIndexValue;
            return this;
        }

        public DataValue setExceptionIndexValue(int exceptionIndexValue) {
            this.exceptionIndexValue = exceptionIndexValue;
            return this;
        }
    }

    public static enum DataType {
        DataTypeUndefined,
        DataTypeActivation,
        DataTypeExceptionScope;
    }

    public DataValue value = new DataValue();

    public void setName(String name) {
        this.name = name;
        prepareName();
    }

    public DataTypeValue(DataType type, MultinameInfo multiname, Integer multiNameIndex, Integer slotIndex, boolean printPopCommand, boolean knownValue, DataValue value, String name) {
        this.type = type;
        this.multiname = multiname;
        this.multiNameIndex = multiNameIndex;
        this.slotIndex = slotIndex;
        this.printPopCommand = printPopCommand;
        this.knownValue = knownValue;
        this.value = value;
        setName(name);
    }

    public DataTypeValue(Integer multiNameIndex, MultinameInfo multiname, String name) {
        this.multiname = multiname;
        this.multiNameIndex = multiNameIndex;
        setName(name);
    }

    public DataTypeValue() {
        this.type = DataType.DataTypeUndefined;
        this.value.functionIndexValue = 0;
        this.multiNameIndex = Integer.valueOf(0);
        this.slotIndex = Integer.valueOf(0);
        this.knownValue = false;
        this.name = "";
        this.printPopCommand = false;
    }

    private void prepareName() {
        if ((this.name != null) && (this.name.charAt(0) == '"') && (this.name.charAt(this.name.length() - 1) == '"')) {
            this.name = this.name.substring(1, this.name.length() - 1);
        }
    }

    public void RemoveQuotation() {
        if ((this.name.charAt(0) == '"') && (this.name.charAt(this.name.length() - 1) == '"')) {
            this.name = this.name.substring(1, this.name.length() - 1);
        }
    }

    public String toString() {
        return

        "DataTypeValue [type=" + this.type + ", multiname=" + this.multiname + ", multiNameIndex=" + this.multiNameIndex + ", slotIndex=" + this.slotIndex + ", printPopCommand=" + this.printPopCommand + ", name=" + this.name + ", knownValue=" + this.knownValue + ", value=" + this.value + "]";
    }
}