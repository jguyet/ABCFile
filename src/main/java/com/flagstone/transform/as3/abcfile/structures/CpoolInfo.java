package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;
import com.flagstone.transform.as3.abcfile.cpool.MultinameKindType;
import com.flagstone.transform.as3.abcfile.cpool.NamespaceInfo;
import com.flagstone.transform.as3.abcfile.cpool.NamespaceKindType;
import com.flagstone.transform.as3.abcfile.cpool.NsSetInfo;
import com.flagstone.transform.as3.abcfile.cpool.StringInfo;
import com.flagstone.transform.as3.abcfile.multiname.MultinameKindQName;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class CpoolInfo {
    public static final ABCLogger LOGGER = new ABCLogger(CpoolInfo.class);

    private AbcFile abc;
    public int start = 0;
    public int end = 0;

    private int int_count;

    private ArrayList < Integer > ints = new ArrayList();
    private int uint_count;
    public ArrayList < Integer > uints = new ArrayList();
    private int double_count;
    public ArrayList < Double > doubles = new ArrayList();
    private int string_count;
    private ArrayList < StringInfo > strings = new ArrayList();
    private int namespace_count;
    private ArrayList < NamespaceInfo > namespaces = new ArrayList();
    private int ns_set_count;
    private ArrayList < NsSetInfo > nsset = new ArrayList();

    private int multiname_count;

    private ArrayList < MultinameInfo > multinames = new ArrayList();

    public int undefinedMultinameIndex;
    public int nullMultinameIndex;
    public int booleanMultinameIndex;
    public int numberMultinameIndex;
    public int intMultinameIndex;
    public int uintMultinameIndex;
    public int stringMultinameIndex;
    public int functionMultinameIndex;
    public int xmlMultinameIndex;
    public int xmllistMultinameIndex;
    public int objectMultinameIndex;

    public CpoolInfo(AbcFile abc) {
        this.abc = abc;
    }

    public void cpool_info(ByteBufferFlash bbuf, int position) {
        try {
            bbuf.position(position);
            this.start = bbuf.position();

            this.int_count = bbuf.unsigned30int();
            this.ints.add(Integer.valueOf(0));
            if (this.int_count > 0) {
                for (int i = 0; i < this.int_count - 1; i++) {
                    int ni = bbuf.signed32int();
                    this.ints.add(Integer.valueOf(ni));
                }
            }

            this.uint_count = bbuf.unsigned30int();
            this.uints.add(Integer.valueOf(0));
            if (this.uint_count > 0) {
                for (int i = 0; i < this.uint_count - 1; i++) {
                    this.uints.add(Integer.valueOf(bbuf.unsigned32int()));
                }
            }
            this.double_count = bbuf.unsigned30int();
            this.doubles.add(Double.valueOf(0.0D));
            if (this.double_count > 0) {
                for (int i = 0; i < this.double_count - 1; i++) {
                    this.doubles.add(Double.valueOf(bbuf.getDouble()));
                }
            }
            this.string_count = bbuf.unsigned30int();

            this.strings.add(new StringInfo("*", 0));
            if (this.string_count > 0) {
                for (int i = 0; i < this.string_count - 1; i++) {
                    this.strings.add(new StringInfo(bbuf, this.strings.size()));
                }
            }
            this.namespace_count = bbuf.unsigned30int();
            this.namespaces.add(new NamespaceInfo(this.abc.getConstant_pool(), NamespaceKindType.CONSTANT_FIRST, 0));
            if (this.namespace_count > 0) {
                for (int i = 0; i < this.namespace_count - 1; i++) {
                    this.namespaces.add(new NamespaceInfo(bbuf, this));
                }
            }
            this.ns_set_count = bbuf.unsigned30int();
            ArrayList < Integer > al = new ArrayList();
            al.add(Integer.valueOf(0));
            this.nsset.add(new NsSetInfo(this.abc.getConstant_pool(), al, 0));
            if (this.ns_set_count > 0) {
                for (int i = 0; i < this.ns_set_count - 1; i++) {
                    this.nsset.add(new NsSetInfo(bbuf, this, this.nsset.size()));
                }
            }

            this.multiname_count = bbuf.unsigned30int();
            this.multinames.add(new MultinameInfo(this.abc.getConstant_pool(), MultinameKindType.CONSTANT_FIRST, 0));
            if (this.multiname_count > 0) {
                for (int i = 0; i < this.multiname_count - 1; i++) {
                    this.multinames.add(new MultinameInfo(bbuf, this));
                }
            }

            this.end = (bbuf.position() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.undefinedMultinameIndex = findMultiName("", "undefined");
        this.nullMultinameIndex = findMultiName("", "null");
        this.booleanMultinameIndex = findMultiName("", "Boolean");
        this.numberMultinameIndex = findMultiName("", "Number");
        this.intMultinameIndex = findMultiName("", "int");
        this.uintMultinameIndex = findMultiName("", "uint");
        this.stringMultinameIndex = findMultiName("", "String");
        this.functionMultinameIndex = findMultiName("", "Function");
        this.xmlMultinameIndex = findMultiName("", "XML");
        this.xmllistMultinameIndex = findMultiName("", "XMLList");
        this.objectMultinameIndex = findMultiName("", "Object");
    }

    public byte[] toByteCode() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.int_count));

        for (int i = 1; i < this.ints.size(); i++) {
            byteDate.write(ByteBufferFlash.getSI32B2(((Integer) this.ints.get(i)).intValue()));
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.uint_count));

        for (int i = 1; i < this.uints.size(); i++) {
            byteDate.write(ByteBufferFlash.getUI32B(((Integer) this.uints.get(i)).intValue()));
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.double_count));

        for (int i = 1; i < this.doubles.size(); i++) {
            byteDate.write(ByteBufferFlash.toBytaJava(((Double) this.doubles.get(i)).doubleValue()));
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.string_count));

        for (int i = 1; i < this.strings.size(); i++) {
            byteDate.write(((StringInfo) this.strings.get(i)).toByteCode());
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.namespace_count));

        for (int i = 1; i < this.namespaces.size(); i++) {
            byteDate.write(((NamespaceInfo) this.namespaces.get(i)).toByteCode());
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.ns_set_count));

        for (int i = 1; i < this.nsset.size(); i++) {
            byteDate.write(((NsSetInfo) this.nsset.get(i)).toByteCode());
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.multiname_count));

        for (int i = 1; i < this.multinames.size(); i++) {
            byteDate.write(((MultinameInfo) this.multinames.get(i)).toByteCode());
        }

        return byteDate.toByteArray();
    }

    public String getString(int id) {
        return (String) this.strings.get(id).toString();
    }

    public String getNamespace(int id) {
        return (String) this.namespaces.get(id).toString();
    }

    public String getNsset(int id) {
        return (String) this.nsset.get(id).toString();
    }

    public String getMultiname(int id) {
        if (id >= this.multinames.size()) {
            return ">za wczesnie - CpoolInfo<";
        }
        return (String) this.multinames.get(id).toString();
    }

    public MultinameInfo getMultinameInfo(int id) {
        if (id >= this.multinames.size()) {
            LOGGER.error(">za wczesnie - CpoolInfo<");
            return null;
        }

        return (MultinameInfo) this.multinames.get(id);
    }

    public ArrayList < StringInfo > getStrings() {
        return this.strings;
    }

    public ArrayList < String > getStrings_string() {
        ArrayList < String > result = new ArrayList();
        for (StringInfo str: this.strings)
            result.add(str.getUtf8_string());
        return result;
    }

    public ArrayList < NamespaceInfo > getNamespaces() {
        return this.namespaces;
    }

    public ArrayList < NsSetInfo > getNsset() {
        return this.nsset;
    }

    public ArrayList < MultinameInfo > getMultinames() {
        return this.multinames;
    }

    public int getInts(int id) {
        return ((Integer) this.ints.get(id)).intValue();
    }

    public double getDoubles(int id) {
        return ((Double) this.doubles.get(id)).doubleValue();
    }

    public int getInt_count() {
        return this.int_count;
    }

    public ArrayList < Integer > getInts() {
        return this.ints;
    }

    public int getUint_count() {
        return this.uint_count;
    }

    public ArrayList < Integer > getUints() {
        return this.uints;
    }

    public int getDouble_count() {
        return this.double_count;
    }

    public ArrayList < Double > getDoubles() {
        return this.doubles;
    }

    public int getString_count() {
        return this.string_count;
    }

    public int getNamespace_count() {
        return this.namespace_count;
    }

    public int getNs_set_count() {
        return this.ns_set_count;
    }

    public int getMultiname_count() {
        return this.multiname_count;
    }

    public void setMultiname_count(int multiname_count) {
        this.multiname_count = multiname_count;
    }

    public void setZeroString(String s) {
        ((StringInfo) this.strings.get(0)).setString(s);
    }

    public void setInt_count(int int_count) {
        this.int_count = int_count;
    }

    public void setInts(ArrayList < Integer > ints) {
        this.ints = ints;
    }

    public void setUint_count(int uint_count) {
        this.uint_count = uint_count;
    }

    public void setUints(ArrayList < Integer > uints) {
        this.uints = uints;
    }

    public void setDouble_count(int double_count) {
        this.double_count = double_count;
    }

    public void setDoubles(ArrayList < Double > doubles) {
        this.doubles = doubles;
    }

    public int addInt(Integer i) {
        this.ints.add(i);
        if (this.int_count == 0) {
            this.int_count += 1;
        }
        this.int_count += 1;
        ByteBufferFlash.out("ok");
        return this.ints.size() - 1;
    }

    public void removeInt(int i) {
        this.ints.remove(i);
        this.int_count -= 1;
        ByteBufferFlash.out("ok");
    }

    public void changeInt(int i, int value) {
        this.ints.set(i, Integer.valueOf(value));
        ByteBufferFlash.out("ok");
    }

    public void addUint(Integer i) {
        this.uints.add(i);
        if (this.uint_count == 0) {
            this.uint_count += 1;
        }
        this.uint_count += 1;
        ByteBufferFlash.out("ok");
    }

    public void removeUint(int i) {
        this.uints.remove(i);
        this.uint_count -= 1;
        ByteBufferFlash.out("ok");
    }

    public void changeUint(int i, Integer value) {
        this.uints.set(i, value);
        ByteBufferFlash.out("ok");
    }

    public int addDouble(Double d) {
        this.doubles.add(d);
        if (this.double_count == 0) {
            this.double_count += 1;
        }
        this.double_count += 1;
        ByteBufferFlash.out("ok");
        return this.doubles.size() - 1;
    }

    public void removeDouble(int i) {
        this.doubles.remove(i);
        this.double_count -= 1;
        ByteBufferFlash.out("ok");
    }

    public void changeDouble(int i, Double value) {
        this.doubles.set(i, value);
        ByteBufferFlash.out("ok");
    }

    public void removeString(int id) {
        this.strings.remove(id);
        this.string_count -= 1;
        ByteBufferFlash.out("ok");
    }

    public StringInfo addString(String value) {
        this.strings.add(new StringInfo(value, this.strings.size()));
        if (this.string_count == 0) {
            this.string_count += 1;
        }
        this.string_count += 1;
        ByteBufferFlash.out("ok");
        return (StringInfo) this.strings.get(this.strings.size() - 1);
    }

    public StringInfo addStringRet(String value) {
        StringInfo sinf = new StringInfo(value, this.strings.size());
        this.strings.add(sinf);
        if (this.string_count == 0) {
            this.string_count += 1;
        }
        this.string_count += 1;
        ByteBufferFlash.out("ok");
        return sinf;
    }

    public void addString(StringInfo strin) {
        this.strings.add(strin);
        if (this.string_count == 0) {
            this.string_count += 1;
        }
        this.string_count += 1;
        ByteBufferFlash.out("ok");
    }

    public void changeString(int i, String value) {
        ((StringInfo) this.strings.get(i)).setString(value);
        ByteBufferFlash.out("ok");
    }

    public void changeString(int i, StringInfo string) {
        ((StringInfo) this.strings.get(i)).setString(string.getUtf8_string());
        ByteBufferFlash.out("ok");
    }

    public void addNamespace(NamespaceInfo ns) {
        this.namespaces.add(ns);
        this.namespace_count += 1;
        ByteBufferFlash.out("ok");
    }

    public void removeNamespace(int i) {
        this.namespaces.remove(i);
        this.namespace_count -= 1;
        ByteBufferFlash.out("ok");
    }

    public void changeNamespace(int i, NamespaceInfo ns) {
        this.namespaces.set(i, ns);
        ByteBufferFlash.out("ok");
    }

    public void addNsSetInfo(NsSetInfo nsSet) {
        this.nsset.add(nsSet);
        this.ns_set_count += 1;
        ByteBufferFlash.out("ok");
    }

    public void removeNsSetInfo(int i) {
        this.nsset.remove(i);
        this.ns_set_count -= 1;
        ByteBufferFlash.out("ok");
    }

    public void changeNsSetInfo(int i, NsSetInfo nsSet) {
        this.nsset.set(i, nsSet);
        ByteBufferFlash.out("ok");
    }

    public void addMultiname(MultinameInfo multiname) {
        this.multinames.add(multiname);
        this.multiname_count += 1;
        ByteBufferFlash.out("ok");
    }

    public void removeMultiname(int i) {
        this.multinames.remove(i);
        this.multiname_count -= 1;
        ByteBufferFlash.out("ok");
    }

    public void changeMultiname(int i, MultinameInfo multiname) {
        this.multinames.set(i, multiname);
        ByteBufferFlash.out("ok");
    }

    public List < Integer > findMultiname(boolean isPackage, String name) {
        List < Integer > foundedMultinames = new ArrayList();
        for (MultinameInfo multiname: this.multinames) {
            if (multiname.getNameString(isPackage).equals(name)) {
                foundedMultinames.add(Integer.valueOf(this.multinames.indexOf(multiname)));
            }
        }
        return foundedMultinames;
    }

    public int findMultiName(String nsName, String typeName) {
        for (MultinameInfo multiname: this.multinames) {
            if ((multiname.getData() instanceof MultinameKindQName)) {
                MultinameKindQName mkind = (MultinameKindQName) multiname.getData();
                if ((mkind.getNameSpac().getName_string().equals("")) && (mkind.getName_string().equals(typeName))) {
                    return this.multinames.indexOf(multiname);
                }
            }
        }
        return 0;
    }

    public AbcFile getAbc() {
        return this.abc;
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();
        try {
            wynik.append("int_count: " + this.int_count + "\n");
            wynik.append("ints: " + this.ints + "\n");
            wynik.append("uint_count: " + this.uint_count + "\n");
            wynik.append("uints: " + this.uints + "\n");
            wynik.append("double_count: " + this.double_count + "\n");
            wynik.append("doubles: " + this.doubles + "\n");

            wynik.append("string_count: " + this.string_count + "\n");
            wynik.append("namespace_count: " + this.namespace_count + "\n");
            wynik.append("ns_set_count: " + this.ns_set_count + "\n");
            wynik.append("multiname_count: " + this.multiname_count + "\n\n");

            wynik.append("strings: ");
            for (int i = 1; i < this.strings.size(); i++)
                wynik.append(((StringInfo) this.strings.get(i)).getUtf8_string() + ",");
            wynik.append("\n\n");
            wynik.append("namespaces: ");
            for (int i = 1; i < this.namespaces.size(); i++)
                wynik.append(((NamespaceInfo) this.namespaces.get(i)).getName_string() + ",");
            wynik.append("\n\n");
            wynik.append("nsset: ");
            for (int i = 1; i < this.nsset.size(); i++)
                wynik.append(((NsSetInfo) this.nsset.get(i)).getNs_string() + ",");
            wynik.append("\n\n");
            wynik.append("multinames: ");
            for (int i = 1; i < this.multinames.size(); i++)
                wynik.append(((MultinameInfo) this.multinames.get(i)).getData() + ",");
            wynik.append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wynik.toString();
    }

    public String toString2() {
        String wynik = "";
        wynik = wynik + "cpool_info";
        return wynik;
    }

    public String toString() {
        return "cpool_info " + this.start + " - " + this.end;
    }
}