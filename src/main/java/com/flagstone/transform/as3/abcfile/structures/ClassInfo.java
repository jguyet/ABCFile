package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class ClassInfo {
    private int id;
    private AbcFile abc;
    private int start = 0;
    private int end = 0;

    private int cinit;

    private int trait_count;
    private ArrayList < TraitInfo > tinfo = new ArrayList();

    public ClassInfo(AbcFile abc, int cinit, int trait_count, ArrayList < TraitInfo > tinfo) {
        this.abc = abc;
        abc.ENclass_info.add(this);
        this.id = (abc.ENclass_info.size() - 1);

        this.cinit = cinit;
        this.trait_count = trait_count;
        if (tinfo != null)
            this.tinfo = tinfo;
    }

    private ClassInfo(AbcFile abc) {
        this.abc = abc;
        abc.ENclass_info.add(this);
        this.id = (abc.ENclass_info.size() - 1);
    }

    public static void class_info(ByteBufferFlash bbuf, int position, CpoolInfo cpool, AbcFile abc) {
        try {
            bbuf.position(position);

            for (int i = 0; i < InstanceInfo.class_count; i++) {
                ClassInfo class_info = new ClassInfo(abc);
                class_info.start = bbuf.position();

                class_info.cinit = bbuf.unsigned30int();

                class_info.trait_count = bbuf.unsigned30int();

                for (int i2 = 0; i2 < class_info.trait_count; i2++) {
                    TraitInfo trinfo = new TraitInfo(abc, bbuf, TraitInfo.Type.CLASS);
                    class_info.tinfo.add(trinfo);
                }

                abc.methodsNames.put(Integer.valueOf(class_info.cinit), ((InstanceInfo) abc.ENinstance_info.get(i)).getName_string() + "$cinit");

                class_info.end = (bbuf.position() - 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] toByteCode()
    throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.cinit));

        byteDate.write(ByteBufferFlash.getUI32B(this.trait_count));

        for (TraitInfo trait: this.tinfo) {
            byteDate.write(trait.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public int getId() {
        return this.id;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getCinit() {
        return this.cinit;
    }

    public ArrayList < TraitInfo > getTinfo() {
        return this.tinfo;
    }

    public int getTrait_count() {
        return this.trait_count;
    }

    public void removeClass(int id) {
        this.abc.ENclass_info.remove(id);
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

    public void setCinit(int cinit) {
        this.cinit = cinit;
        ByteBufferFlash.out("ok");
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "class_info id " + this.id + " " + this.start + " - " + this.end;
        return wynik;
    }

    public String toString2() {
        String wynik = "";
        wynik = wynik + "class_info";
        return wynik;
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();

        wynik.append(">" + getStart() + "\n");

        wynik.append("cinit: " + getCinit() + "\n");

        wynik.append("trait_count: " + getTrait_count() + "\n");

        wynik.append("<" + getEnd() + "\n");

        return wynik.toString();
    }
}