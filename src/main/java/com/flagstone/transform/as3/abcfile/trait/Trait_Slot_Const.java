package com.flagstone.transform.as3.abcfile.trait;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;
import com.flagstone.transform.as3.abcfile.cpool.NamespaceInfo;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.structures.TraitInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class Trait_Slot_Const
implements ITraitKindData {
    private CpoolInfo cpool;
    private TraitInfo tinfo;
    private int start = 0;
    private int end = 0;

    private int slot_id;
    private int type_name;
    private int vindex;
    private int vkind = -1;

    public boolean alreadySet = false;
    public String defaultValue = "";

    public Trait_Slot_Const(ByteBufferFlash bbuf, CpoolInfo cpool, TraitInfo tinfo)
    throws Exception {
        this.cpool = cpool;
        this.tinfo = tinfo;
        trait_slot_const(bbuf);
    }

    public Trait_Slot_Const(TraitInfo tinfo, MultinameInfo type_name, int vindex, ConstantKind constantKind) throws Exception {
        this.slot_id = 0;
        this.type_name = type_name.getId();
        this.vindex = vindex;
        this.vkind = constantKind.getConstantKind();
    }

    public Trait_Slot_Const(CpoolInfo cpool, TraitInfo tinfo, int type_name, int vindex, ConstantKind constantKind)
    throws Exception {
        this.cpool = cpool;
        this.slot_id = 0;
        this.type_name = type_name;
        this.vindex = vindex;
        this.vkind = constantKind.getConstantKind();
    }

    public void trait_slot_const(ByteBufferFlash bbuf)
    throws Exception {
        this.start = bbuf.position();
        this.slot_id = bbuf.unsigned30int();

        this.type_name = bbuf.unsigned30int();

        this.vindex = bbuf.unsigned30int();

        if (this.vindex > 0) {
            this.vkind = bbuf.unsigned30int();
        }
        this.end = (bbuf.position() - 1);
    }

    public byte[] toByteCode()
    throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.slot_id));

        byteDate.write(ByteBufferFlash.getUI32B(this.type_name));

        byteDate.write(ByteBufferFlash.getUI32B(this.vindex));

        if ((this.vkind != -1) && (this.vindex > 0)) {
            byteDate.write(ByteBufferFlash.getUI32B(this.vkind));
        }

        return byteDate.toByteArray();
    }

    public int getSlot_id() {
        return this.slot_id;
    }

    public int getVindex() {
        return this.vindex;
    }

    public int getVkind() {
        return this.vkind;
    }

    public String getVkind_string() {
        ConstantKind cKind = ConstantKind.getConstant(this.vkind);
        if (cKind != null) {
            return ConstantKind.getConstant(this.vkind).name();
        }
        return "";
    }

    public int getType_name() {
        return this.type_name;
    }

    public String getType_name_string() {
        return this.cpool.getMultiname(this.type_name);
    }

    public MultinameInfo getTypeNameMultinameInfo() {
        return this.cpool.getMultinameInfo(this.type_name);
    }

    public MultinameInfo getType() {
        return (MultinameInfo) this.cpool.getMultinames().get(this.type_name);
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public void setType_name(int type_name) {
        this.type_name = type_name;
    }

    public void setVkind(int vkind) {
        this.vkind = vkind;
    }

    public void setVindex(int vindex) {
        this.vindex = vindex;
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "\n---#--- Trait_Slot_Const \n" + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n";

        wynik = wynik + "slot_id: " + this.slot_id + "\n";
        wynik = wynik + "type_name: " + getType_name() + "\n";
        wynik = wynik + "vindex: " + this.vindex + "\n";
        wynik = wynik + "vkind: " + this.vkind + "\n";

        wynik = wynik + Internatiolizer.msgs.getString("Position") + ":  " + this.end + "\n---!!--- " + Internatiolizer.msgs.getString("end") + "\n";
        return wynik;
    }

    public void getKind() {}

    public ConstantKind getValueType() {
        if (this.vkind != -1) {
            return ConstantKind.getConstant(this.vkind);
        }
        return null;
    }

    public Object getValue() {
        ConstantKind constantKindType = getValueType();

        if (constantKindType != null) {
            return constantKindType.getValueReal(this.vindex, this.cpool);
        }
        return null;
    }

    public void setValue(ConstantKind cKind, Object value) {
        this.vkind = cKind.getConstantKind();
        switch (cKind) {
            case CONSTANT_Double:
                List < Integer > foundedMultinameInt = this.cpool.findMultiname(false, "int");
                if (foundedMultinameInt.size() > 0) {
                    setType_name(((Integer) foundedMultinameInt.get(0)).intValue());

                    Integer valueIn = null;
                    if ((value instanceof String)) {
                        valueIn = Integer.valueOf(Integer.parseInt((String) value));
                    } else {
                        valueIn = (Integer) value;
                    }
                    this.cpool.addInt(valueIn);
                    this.vindex = this.cpool.getInts().indexOf(valueIn);
                } else {
                    System.err.println("Brak multiname dla int");
                }
                break;
            case CONSTANT_ExplicitNamespace:
                List < Integer > foundedMultinameUint = this.cpool.findMultiname(false, "uint");
                if (foundedMultinameUint.size() > 0) {
                    setType_name(((Integer) foundedMultinameUint.get(0)).intValue());
                    Integer valueUin = null;
                    if ((value instanceof String)) {
                        valueUin = Integer.valueOf(Integer.parseInt((String) value));
                    } else {
                        valueUin = (Integer) value;
                    }
                    this.cpool.addUint(valueUin);
                    this.vindex = this.cpool.getUints().indexOf(valueUin);
                } else {
                    System.err.println("Brak multiname dla uint");
                }
                break;
            case CONSTANT_False:
                List < Integer > foundedMultinameDouble = this.cpool.findMultiname(false, "Number");
                if (foundedMultinameDouble.size() > 0) {
                    setType_name(((Integer) foundedMultinameDouble.get(0)).intValue());
                    Double valueDoub = null;
                    if ((value instanceof String)) {
                        valueDoub = Double.valueOf(Double.parseDouble((String) value));
                    } else {
                        valueDoub = (Double) value;
                    }
                    this.cpool.addDouble(valueDoub);
                    this.vindex = this.cpool.getDoubles().indexOf(valueDoub);
                } else {
                    System.err.println("Brak multiname dla Number");
                }
                break;
            case CONSTANT_Int:
                List < Integer > foundedMultinameString = this.cpool.findMultiname(false, "String");
                if (foundedMultinameString.size() > 0) {
                    setType_name(((Integer) foundedMultinameString.get(0)).intValue());
                    this.vindex = this.cpool.getStrings().indexOf(this.cpool.addString((String) value));
                } else {
                    System.err.println("Brak multiname dla String");
                }
                break;
            case CONSTANT_Namespace:
                List < Integer > foundedMultinameBoolean = this.cpool.findMultiname(false, "Boolean");
                if (foundedMultinameBoolean.size() > 0) {
                    this.vindex = 11;
                } else {
                    System.err.println("Brak multiname dla Boolean");
                }
                break;
            case CONSTANT_Null:
                List < Integer > foundedMultinameBoolean2 = this.cpool.findMultiname(false, "Boolean");
                if (foundedMultinameBoolean2.size() > 0) {
                    this.vindex = 11;
                } else {
                    System.err.println("Brak multiname dla Boolean");
                }
                this.vindex = 10;
                break;
            case CONSTANT_PackageInternalNs:
                this.vindex = 12;
                break;
            case CONSTANT_PackageNamespace:
                this.vindex = 0;
                break;
            case CONSTANT_PrivateNs:
            case CONSTANT_ProtectedNamespace:
            case CONSTANT_StaticProtectedNs:
            case CONSTANT_True:
            case CONSTANT_UInt:
            case CONSTANT_Undefined:
            case CONSTANT_Utf8:
                NamespaceInfo valueNamespace = null;
                if ((value instanceof String)) {
                    valueNamespace = (NamespaceInfo) this.cpool.getNamespaces().get(Integer.parseInt((String) value));
                } else if ((value instanceof Integer)) {
                    valueNamespace = (NamespaceInfo) this.cpool.getNamespaces().get(((Integer) value).intValue());
                } else {
                    valueNamespace = (NamespaceInfo) value;
                }
                this.cpool.addNamespace(valueNamespace);
                this.vindex = this.cpool.getNamespaces().indexOf(value);

                break;
        }
    }

    public TraitInfo getTinfo() {
        return this.tinfo;
    }

    public CpoolInfo getCpool() {
        return this.cpool;
    }
}