package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class ScriptInfo {
    private int id;
    public static int script_count;
    private AbcFile abc;
    private int start;
    private int end;
    private int init;
    private int trait_count;
    private ArrayList < TraitInfo > tinfo = new ArrayList();

    private ScriptInfo(AbcFile abc) {
        this.abc = abc;
        abc.ENscript_info.add(this);
        this.id = (abc.ENscript_info.size() - 1);
    }

    public static void script_info(ByteBufferFlash bbuf, int position, CpoolInfo cpool, AbcFile abc) {
        try {
            bbuf.position(position);

            script_count = bbuf.unsigned30int();
            for (int i = 0; i < script_count; i++) {
                ScriptInfo script_info = new ScriptInfo(abc);

                script_info.start = bbuf.position();

                script_info.init = bbuf.unsigned30int();

                script_info.trait_count = bbuf.unsigned30int();

                for (int i2 = 0; i2 < script_info.trait_count; i2++) {
                    script_info.tinfo.add(new TraitInfo(abc, bbuf, TraitInfo.Type.SCRIPT));
                }

                abc.methodsNames.put(Integer.valueOf(script_info.init), "script" + i + "$init");

                script_info.end = (bbuf.position() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] toByteCode() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.init));

        byteDate.write(ByteBufferFlash.getUI32B(this.trait_count));

        for (TraitInfo trait: this.tinfo) {
            byteDate.write(trait.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getInit() {
        return this.init;
    }

    public ArrayList < TraitInfo > getTinfo() {
        return this.tinfo;
    }

    public int getTrait_count() {
        return this.trait_count;
    }

    public void removeScript_info(int id) {
        this.abc.ENscript_info.remove(id);
        script_count -= 1;
    }

    public void setInit(int init) {
        this.init = init;
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

    public String String() {
        StringBuffer wynik = new StringBuffer();

        wynik.append(">" + this.start + "\n");

        wynik.append("init: " + getInit() + "\n");

        wynik.append("trait_count: " + getTrait_count() + "\n");

        wynik.append("<" + this.end + "\n");

        return wynik.toString();
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "script_info id " + this.id + " " + this.start + " - " + this.end;
        return wynik;
    }

    public String toString2() {
        String wynik = "";
        wynik = wynik + "script_info";
        return wynik;
    }
}