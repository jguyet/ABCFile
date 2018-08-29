package com.flagstone.transform.as3.abcfile.multiname;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.cpool.NsSetInfo;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class MultinameKindMultinameL
extends MultinameKind {
    private CpoolInfo cpool;
    private int start;
    private int end;
    private int ns_set = -1;

    public MultinameKindMultinameL(CpoolInfo cpool, int ns_set) {
        this.cpool = cpool;
        this.ns_set = ns_set;
    }

    public MultinameKindMultinameL(ByteBufferFlash bbuf, CpoolInfo cpool) {
        this.cpool = cpool;
        multiname_kind_MultinameL(bbuf);
    }

    private void multiname_kind_MultinameL(ByteBufferFlash bbuf) {
        this.start = bbuf.position();

        this.ns_set = bbuf.unsigned30int();

        this.end = (bbuf.position() - 1);
    }

    public NsSetInfo getNs_setInfo() {
        return (NsSetInfo) this.cpool.getNsset().get(this.ns_set);
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getNs_set() {
        return this.ns_set;
    }

    public ArrayList < String > getNs_set_string() {
        if (this.ns_set == 0)
            return null;
        return ((NsSetInfo) this.cpool.getNsset().get(this.ns_set)).getNs_string();
    }

    public String String() {
        String wynik = "";
        wynik = wynik + "\n\t\t---#--- multiname_kind_MultinameL " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n";

        wynik = wynik + "\t\tns_set: " + this.cpool.getNsset().get(this.ns_set) + "\n";

        wynik = wynik + "\n\t\t---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end;

        return wynik;
    }

    public String toString() {
        String wynik = "";

        wynik = wynik + getNs_set_string();

        return wynik;
    }

    public byte[] toByteCode()
    throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        if (this.ns_set != -1) {
            byteDate.write(ByteBufferFlash.getUI32B(this.ns_set));
        }

        return byteDate.toByteArray();
    }
}