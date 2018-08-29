package com.flagstone.transform.as3.abcfile.multiname;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.cpool.StringInfo;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class MultinameKindRTQName
extends MultinameKind {
    private CpoolInfo cpool;
    private int start;
    private int end;
    private int name = -1;

    public MultinameKindRTQName(CpoolInfo cpool, int name) {
        this.cpool = cpool;
        this.name = name;
    }

    public MultinameKindRTQName(ByteBufferFlash bbuf, CpoolInfo cpool) {
        this.cpool = cpool;
        multiname_kind_RTQName(bbuf);
    }

    private void multiname_kind_RTQName(ByteBufferFlash bbuf) {
        this.start = bbuf.position();

        this.name = bbuf.unsigned30int();

        this.end = (bbuf.position() - 1);
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getName() {
        return this.name;
    }

    public String getName_string() {
        return ((StringInfo) this.cpool.getStrings().get(this.name)).getUtf8_string();
    }

    public StringInfo getName_stringInfo() {
        return (StringInfo) this.cpool.getStrings().get(this.name);
    }

    public void setName(int name) {
        this.name = name;
    }

    public String String() {
        String wynik = "";
        wynik = wynik + "\n\t\t---#--- multiname_kind_RTQName " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n";

        wynik = wynik + "\t\tname: " + getName_string() + "\n";

        wynik = wynik + "\n\t\t---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end;

        return wynik;
    }

    public String toString() {
        String wynik = "";

        wynik = wynik + getName_string();

        return wynik;
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        if (this.name != -1) {
            byteDate.write(ByteBufferFlash.getUI32B(this.name));
        }

        return byteDate.toByteArray();
    }
}