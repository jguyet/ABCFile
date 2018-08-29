package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;
import com.flagstone.transform.as3.abcfile.cpool.StringInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class InstanceInfo {
    private int id;
    public static int class_count;

    public static enum INSTANCE_KIND_FLAG {
        NO_FLAGS(Integer.valueOf(0), "NO_FLAGS"),
            CONSTANT_ClassSealed(Integer.valueOf(1), "CONSTANT_ClassSealed"),
            CONSTANT_ClassFinal(Integer.valueOf(2), "CONSTANT_ClassFinal"),
            CONSTANT_ClassInterface(Integer.valueOf(4), "CONSTANT_ClassInterface"),
            CONSTANT_ClassInternal(Integer.valueOf(5), "CONSTANT_ClassInternal"),
            CONSTANT_ClassProtectedNs(Integer.valueOf(8), "CONSTANT_ClassProtectedNs"),
            CONSTANT_ClassProtected(Integer.valueOf(24), "CONSTANT_ClassProtected");
        private static final Map < Integer, INSTANCE_KIND_FLAG > map;

        static {
            map = new HashMap();
            INSTANCE_KIND_FLAG[] arrayOfINSTANCE_KIND_FLAG;
            int j = (arrayOfINSTANCE_KIND_FLAG = values()).length;
            for (int i = 0; i < j; i++) {
                INSTANCE_KIND_FLAG kind = arrayOfINSTANCE_KIND_FLAG[i];
                map.put(kind.value, kind);
            }
        }

        public static INSTANCE_KIND_FLAG getKind(Integer value) {
            return map.get(value) == null ? NO_FLAGS : (INSTANCE_KIND_FLAG) map.get(value);
        }

        private INSTANCE_KIND_FLAG(Integer value, String name) {
            this.name = name;
            this.value = value;
        }

        public final String name;
        public final Integer value;
    }

    private HashMap < Integer, String > flagsKind = new HashMap();

    private INSTANCE_KIND_FLAG flagKind;

    private CpoolInfo cpool;
    private AbcFile abc;
    private int start = 0;
    private int end = 0;

    private int name;

    private int super_name;
    private int flags;
    private int protectedNs = -1;
    private int intrf_count;
    private ArrayList < Integer > al_interface = new ArrayList();
    private int iinit;
    private int trait_count;
    private ArrayList < TraitInfo > tinfo = new ArrayList();

    public InstanceInfo(AbcFile abc, int name, int super_name, String flags, Integer protectedNs, int intrf_count, ArrayList < Integer > al_interface, int iinit, int trait_count, ArrayList < TraitInfo > tinfo) {
        this.abc = abc;
        this.cpool = abc.getConstant_pool();
        initFlags();
        abc.ENinstance_info.add(this);
        this.id = (abc.ENinstance_info.size() - 1);
        this.name = name;
        this.super_name = super_name;
        this.flags = getFlags(flags);

        if (protectedNs != null) {
            this.protectedNs = protectedNs.intValue();
        }
        this.intrf_count = intrf_count;

        if (al_interface != null) {
            this.al_interface = al_interface;
        }
        this.iinit = iinit;
        this.trait_count = trait_count;

        if (tinfo != null)
            this.tinfo = tinfo;
        class_count += 1;
    }

    private InstanceInfo(AbcFile abc, CpoolInfo cpool) {
        this.cpool = cpool;
        this.abc = abc;
        initFlags();
        abc.ENinstance_info.add(this);
        this.id = (abc.ENinstance_info.size() - 1);
    }

    private void initFlags() {
        this.flagsKind.put(Integer.valueOf(0), "NO_FLAGS");
        this.flagsKind.put(Integer.valueOf(1), "CONSTANT_ClassSealed");
        this.flagsKind.put(Integer.valueOf(2), "CONSTANT_ClassFinal");
        this.flagsKind.put(Integer.valueOf(4), "CONSTANT_ClassInterface");
        this.flagsKind.put(Integer.valueOf(8), "CONSTANT_ClassProtectedNs");
    }

    public static void instance_info(ByteBufferFlash bbuf, int position, CpoolInfo cpool, AbcFile abc) throws Exception {
        bbuf.position(position);

        class_count = bbuf.unsigned30int();
        for (int i3 = 0; i3 < class_count; i3++) {
            InstanceInfo instance_info = new InstanceInfo(abc, cpool);
            instance_info.start = bbuf.position();
            instance_info.name = bbuf.unsigned30int();
            instance_info.super_name = bbuf.unsigned30int();

            instance_info.setFlags(bbuf.unsignedByte());
            if ((instance_info.flags & 0x8) != 0)
                instance_info.protectedNs = bbuf.unsigned30int();
            instance_info.intrf_count = bbuf.unsigned30int();
            for (int ii = 0; ii < instance_info.intrf_count; ii++)
                instance_info.al_interface.add(Integer.valueOf(bbuf.unsigned30int()));
            instance_info.iinit = bbuf.unsigned30int();
            abc.methodsNames.put(Integer.valueOf(instance_info.iinit), instance_info.getName_string());
            instance_info.trait_count = bbuf.unsigned30int();

            for (int i2 = 0; i2 < instance_info.trait_count; i2++) {
                TraitInfo trInf = null;
                try {
                    trInf = new TraitInfo(abc, bbuf, TraitInfo.Type.INSTANCE);
                } catch (Exception localException) {}

                instance_info.tinfo.add(trInf);
            }

            instance_info.end = (bbuf.position() - 1);
        }
    }

    public byte[] toByteCode()
    throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.name));

        byteDate.write(ByteBufferFlash.getUI32B(this.super_name));

        byteDate.write(ByteBufferFlash.getUI32B(this.flags));

        if (this.protectedNs != -1) {
            byteDate.write(ByteBufferFlash.getUI32B(this.protectedNs));
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.intrf_count));

        for (Iterator localIterator = this.al_interface.iterator(); localIterator.hasNext();) {
            int inter = ((Integer) localIterator.next()).intValue();
            byteDate.write(ByteBufferFlash.getUI32B(inter));
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.iinit));

        byteDate.write(ByteBufferFlash.getUI32B(this.trait_count));

        for (TraitInfo trait: this.tinfo) {
            byteDate.write(trait.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public HashMap < Integer, String > getFlagsKind() {
        return this.flagsKind;
    }

    public int getName() {
        return this.name;
    }

    public String getName_string() {
        this.cpool.setZeroString("*");
        return this.cpool.getMultiname(this.name);
    }

    public String getPackage_string() {
        this.cpool.setZeroString("*");
        return ((MultinameInfo) this.cpool.getMultinames().get(this.name)).getNameString(true);
    }

    public ArrayList < StringInfo > getPackage_stringInfo() {
        return ((MultinameInfo) this.cpool.getMultinames().get(this.name)).getNameStringInfo(true);
    }

    public String getNameWithoutPackage_string() {
        this.cpool.setZeroString("*");
        return ((MultinameInfo) this.cpool.getMultinames().get(this.name)).getNameString(false);
    }

    public ArrayList < StringInfo > getNameWithoutPackage_stringInfo() {
        return ((MultinameInfo) this.cpool.getMultinames().get(this.name)).getNameStringInfo(false);
    }

    public int getSuper_name() {
        this.cpool.setZeroString("NO_BASE_CLASS_NAME");
        return this.super_name;
    }

    public String getSuper_name_string() {
        return this.cpool.getMultiname(this.super_name);
    }

    public MultinameInfo getSuperNameMultinameInfo() {
        return this.cpool.getMultinameInfo(this.super_name);
    }

    public int getFlags() {
        return this.flags;
    }

    public String getFlags_string() {
        if ((this.flags & 0x8) != 0)
            return (String) this.flagsKind.get(Integer.valueOf(8));
        if ((this.flags & 0x4) != 0)
            return (String) this.flagsKind.get(Integer.valueOf(4));
        if ((this.flags & 0x2) != 0)
            return (String) this.flagsKind.get(Integer.valueOf(2));
        if ((this.flags & 0x1) != 0) {
            return (String) this.flagsKind.get(Integer.valueOf(1));
        }
        return "NO_FLAGS";
    }

    public int getProtectedNs() {
        return this.protectedNs;
    }

    public String getProtectedNs_string() {
        this.cpool.setZeroString("*");
        if ((this.flags & 0x8) != 0)
            return this.cpool.getMultiname(this.protectedNs);
        return null;
    }

    public ArrayList < Integer > getAl_interface() {
        this.cpool.setZeroString("*");
        return this.al_interface;
    }

    public ArrayList < String > getAl_interface_string() {
        this.cpool.setZeroString("*");
        ArrayList < String > result = new ArrayList();
        for (int i = 0; i < this.al_interface.size(); i++)
            result.add(this.cpool.getMultiname(((Integer) this.al_interface.get(i)).intValue()));
        return result;
    }

    public int getIinit() {
        return this.iinit;
    }

    public ArrayList < TraitInfo > getTinfo() {
        return this.tinfo;
    }

    public int getIntrf_count() {
        return this.intrf_count;
    }

    public int getFlags(String flags) {
        return ((Integer) ByteBufferFlash.getKeyFromValue(this.flagsKind, flags)).intValue();
    }

    public void removeInstance(int id) {
        this.abc.ENinstance_info.remove(id);
        class_count -= 1;
    }

    public void setFlags(String flags) {
        if (flags != null) {
            this.flags = ((Integer) ByteBufferFlash.getKeyFromValue(this.flagsKind, flags)).intValue();
            ByteBufferFlash.out("ok");
        } else {
            this.flags = 0;
        }
    }

    public void setIntrf_count(int intrf_count) {
        this.intrf_count = intrf_count;
        ByteBufferFlash.out("ok");
    }

    public void setName(int name) {
        this.name = name;
        ByteBufferFlash.out("ok");
    }

    public void setSuper_name(int super_name) {
        this.super_name = super_name;
        ByteBufferFlash.out("ok");
    }

    public void setFlags(int flags) {
        this.flags = flags;
        this.flagKind = INSTANCE_KIND_FLAG.getKind(Integer.valueOf(flags));
        ByteBufferFlash.out("ok");
    }

    public void setProtectedNs(int protectedNs) {
        this.protectedNs = protectedNs;
        ByteBufferFlash.out("ok");
    }

    public void setIinit(int iinit) {
        this.iinit = iinit;
        ByteBufferFlash.out("ok");
    }

    public void addAl_interface(Integer interfaces) {
        this.al_interface.add(interfaces);
        this.intrf_count += 1;
        ByteBufferFlash.out("ok");
    }

    public void removeAl_interface(int id) {
        this.al_interface.remove(id);
        this.intrf_count -= 1;
        ByteBufferFlash.out("ok");
    }

    public void changeAl_interface(int id, Integer interfaces) {
        this.al_interface.set(id, interfaces);

        ByteBufferFlash.out("ok");
    }

    public void addTinfo(TraitInfo tinfo) {
        this.tinfo.add(tinfo);
        this.trait_count += 1;
        ByteBufferFlash.out("ok");
    }

    public void removeTinfo(int id) {
        this.tinfo.remove(id);
        this.trait_count -= 1;
        ByteBufferFlash.out("ok");
    }

    public void changeTinfo(int id, TraitInfo tinfo) {
        this.tinfo.set(id, tinfo);

        ByteBufferFlash.out("ok");
    }

    public INSTANCE_KIND_FLAG getFlagKind() {
        return this.flagKind;
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();

        wynik.append(">" + this.start + "\n");

        wynik.append("name: " + getName_string() + "\n");

        wynik.append("super_name: " + getSuper_name_string() + "\n");

        wynik.append("flags: " + getFlags_string() + "\n");

        wynik.append("protectedNs: " + getProtectedNs_string() + "\n");

        wynik.append("al_interface: " + getAl_interface_string() + "\n");

        wynik.append("iinit: " + getIinit() + "\n");

        wynik.append("trait_count: " + getTrait_count() + "\n");

        wynik.append("<" + this.end + "\n");

        return wynik.toString();
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "instance_info id " + this.id + " " + this.start + " - " + this.end;
        return wynik;
    }

    public String toString2() {
        String wynik = "";
        wynik = wynik + "instance_info";
        return wynik;
    }

    public int getTrait_count() {
        return this.trait_count;
    }
}