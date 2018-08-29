package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class MethodInfo {
    private int id;
    public static int method_count;
    private HashMap < Integer, String > flagsKind = new HashMap();

    private AbcFile abc;
    private CpoolInfo cpool;
    private int start = 0;
    private int end = 0;

    private int param_count;

    private int return_type;
    private ArrayList < Integer > param_type = new ArrayList();
    private int name;
    private int flags;
    private OptionInfo oinfo = null;
    private ArrayList < Integer > param_info = new ArrayList();

    public MethodInfo(AbcFile abc, int param_count, int return_type, ArrayList < Integer > param_type, int name, String flags, OptionInfo oinfo, ArrayList < Integer > param_info) {
        this.abc = abc;
        this.cpool = abc.getConstant_pool();
        abc.ENmethod_info.add(this);
        initFlags();
        this.id = (abc.ENmethod_info.size() - 1);
        this.param_count = param_count;
        this.return_type = return_type;
        if (param_type != null) {
            for (Integer param: param_type)
                this.param_type.add(param);
        }
        this.name = name;

        setFlags(flags);

        if (oinfo != null)
            this.oinfo = oinfo;
        if (param_info != null) {
            for (Integer param: param_info)
                this.param_info.add(param);
        }
        method_count += 1;
    }

    private MethodInfo(AbcFile abc) {
        this.abc = abc;
        this.cpool = abc.getConstant_pool();
        abc.ENmethod_info.add(this);
        initFlags();
        this.id = (abc.ENmethod_info.size() - 1);
    }

    private void initFlags() {
        this.flagsKind.put(Integer.valueOf(0), "NO_FLAGS");
        this.flagsKind.put(Integer.valueOf(1), "NEED_ARGUMENTS");
        this.flagsKind.put(Integer.valueOf(2), "NEED_ACTIVATION");
        this.flagsKind.put(Integer.valueOf(4), "NEED_REST");
        this.flagsKind.put(Integer.valueOf(8), "HAS_OPTIONAL");
        this.flagsKind.put(Integer.valueOf(64), "SET_DXNS");
        this.flagsKind.put(Integer.valueOf(128), "HAS_PARAM_NAMES");
    }

    public static void method_info(ByteBufferFlash bbuf, int position, CpoolInfo cpool, AbcFile abc) {
        bbuf.position(position);

        method_count = bbuf.unsigned30int();

        if (method_count > 0) {
            for (int i = 0; i < method_count; i++) {
                MethodInfo method_info = new MethodInfo(abc);

                method_info.start = bbuf.position();

                method_info.param_count = bbuf.unsigned30int();

                method_info.return_type = bbuf.unsigned30int();

                for (int ii = 0; ii < method_info.param_count; ii++) {
                    method_info.param_type.add(Integer.valueOf(bbuf.unsigned30int()));
                }
                method_info.name = bbuf.unsigned30int();
                method_info.flags = bbuf.unsignedByte();

                if ((method_info.flags & 0x8) != 0) {
                    method_info.oinfo = new OptionInfo(bbuf, method_info.param_count, abc);
                }
                if ((method_info.flags & 0x80) != 0) {
                    for (int i2 = 0; i2 < method_info.param_count; i2++) {
                        method_info.param_info.add(Integer.valueOf(bbuf.unsigned30int()));
                    }
                }
                method_info.end = (bbuf.position() - 1);
            }
        }
    }

    public byte[] toByteCode()
    throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.param_count));

        byteDate.write(ByteBufferFlash.getUI32B(this.return_type));

        for (Iterator localIterator = this.param_type.iterator(); localIterator.hasNext();) {
            int param = ((Integer) localIterator.next()).intValue();
            byteDate.write(ByteBufferFlash.getUI32B(param));
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.name));

        byteDate.write(this.flags);

        if (this.oinfo != null) {
            byteDate.write(this.oinfo.toByteCode());
        }

        for (Iterator < Integer > localIterator = this.param_info.iterator(); localIterator.hasNext();) {
            int param = ((Integer) localIterator.next()).intValue();
            byteDate.write(ByteBufferFlash.getUI32B(param));
        }

        return byteDate.toByteArray();
    }

    public int getId() {
        return this.abc.ENmethod_info.indexOf(this);
    }

    public OptionInfo getOinfo() {
        return this.oinfo;
    }

    public int getReturn_type() {
        this.cpool.setZeroString("*");
        return this.return_type;
    }

    public String getReturn_type_string() {
        return this.cpool.getMultiname(this.return_type);
    }

    public MultinameInfo getReturnTypeMultinameInfo() {
        return this.cpool.getMultinameInfo(this.return_type);
    }

    public ArrayList < Integer > getParam_type() {
        this.cpool.setZeroString("*");
        return this.param_type;
    }

    public List < MultinameInfo > getParamTypeStringInfo() {
        List < MultinameInfo > result = new ArrayList();
        for (int i = 0; i < this.param_count; i++) result.add(this.cpool.getMultinameInfo(((Integer) this.param_type.get(i)).intValue()));
        return result;
    }

    public ArrayList < String > getParam_type_string() {
        ArrayList < String > result = new ArrayList();
        for (int i = 0; i < this.param_count; i++) result.add(this.cpool.getMultiname(((Integer) this.param_type.get(i)).intValue()));
        return result;
    }

    public int getName() {
        this.cpool.setZeroString("NO_NAME");
        return this.name;
    }

    public String getName_string() {
        return this.cpool.getString(this.name);
    }

    public String getFlags() {
        return (String) this.flagsKind.get(Integer.valueOf(this.flags));
    }

    public int getFlagsInt() {
        return this.flags;
    }

    public ArrayList < Integer > getParam_info() {
        this.cpool.setZeroString("NO_NAME");
        return this.param_info;
    }

    public ArrayList < String > getParam_info_string() {
        if ((this.flags & 0x80) != 0) {
            ArrayList < String > result = new ArrayList();
            for (int i = 0; i < this.param_info.size(); i++)
                result.add((String) this.cpool.getStrings_string().get(((Integer) this.param_info.get(i)).intValue()));
            return result;
        }
        return new ArrayList();
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getParam_count() {
        return this.param_count;
    }

    public int getFlags(String flags) {
        return ((Integer) ByteBufferFlash.getKeyFromValue(this.flagsKind, flags)).intValue();
    }

    public HashMap < Integer, String > getFlagsKind() {
        return this.flagsKind;
    }

    public void removeMethod(int id) {
        this.abc.ENmethod_info.remove(id);
        method_count -= 1;
    }

    public void changeMethod(int id, MethodInfo method_info) {
        this.abc.ENmethod_info.set(id, method_info);
    }

    public void setParam_count(int param_count) {
        this.param_count = param_count;
        ByteBufferFlash.out("ok");
    }

    public void setReturn_type(int return_type) {
        this.return_type = return_type;
        ByteBufferFlash.out("ok");
    }

    public void setName(int name) {
        this.name = name;
        ByteBufferFlash.out("ok");
    }

    public void setFlags(int flags) {
        this.flags = flags;
        ByteBufferFlash.out("ok");
    }

    public void setFlags(String flags) {
        if (flags != null) {
            this.flags = ((Integer) ByteBufferFlash.getKeyFromValue(this.flagsKind, flags)).intValue();
            ByteBufferFlash.out("ok");
        } else {
            this.flags = 0;
        }
    }

    public void setParam_info(ArrayList < Integer > param_info) throws Exception {
        if ((this.flags & 0x8) != 0) {
            this.param_info = param_info;
            ByteBufferFlash.out("ok");
        } else {
            throw new Exception("Flaga musi by� ustawiona na 0x08");
        }
    }

    public void setOinfo(OptionInfo oinfo) throws Exception {
        if ((this.flags & 0x8) != 0) {
            this.oinfo = oinfo;
            ByteBufferFlash.out("ok");
        } else {
            throw new Exception("Flaga musi by� ustawiona na 0x08");
        }
    }

    public void addParamInfo(Integer value) throws Exception {
        if ((this.flags & 0x80) != 0) {
            this.param_info.add(value);
            ByteBufferFlash.out("ok");
        } else {
            throw new Exception("Flaga musi by� ustawiona na 0x08");
        }
    }

    public void removeParamInfo(int id) throws Exception {
        this.param_info.remove(id);
        ByteBufferFlash.out("ok");
    }

    public void changeParamInfo(int id, Integer value) throws Exception {
        if ((this.flags & 0x80) != 0) {
            this.param_info.set(id, value);
            ByteBufferFlash.out("ok");
        } else {
            throw new Exception("Flaga musi by� ustawiona na 0x08");
        }
    }

    public void addParamType(Integer value) throws Exception {
        this.param_type.add(value);
        this.param_count += 1;
        ByteBufferFlash.out("ok");
    }

    public void removeParamType(int id) throws Exception {
        this.param_type.remove(id);
        this.param_count -= 1;
        ByteBufferFlash.out("ok");
    }

    public void changeParamType(int id, Integer value) throws Exception {
        this.param_type.set(id, value);
        ByteBufferFlash.out("ok");
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();

        wynik.append(">" + getStart() + "\n");

        wynik.append("param_count: " + getParam_count() + "\n");

        wynik.append("return_type: " + getReturn_type_string() + "\n");

        wynik.append("param_type: " + getParam_type_string() + "\n");

        wynik.append("name: " + getName_string() + "\n");

        wynik.append("flags: " + getFlags() + "\n");

        wynik.append("param_info: " + getParam_info_string() + "\n");

        wynik.append("<" + getEnd() + "\n");

        return wynik.toString();
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "method_info id " + this.id + " " + this.start + " - " + this.end;
        return wynik;
    }

    public String toString2() {
        String wynik = "";
        wynik = wynik + "method_info";
        return wynik;
    }
}