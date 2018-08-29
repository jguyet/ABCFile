package com.flagstone.transform.as3.abcfile.multiname;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.cpool.NamespaceInfo;
import com.flagstone.transform.as3.abcfile.cpool.StringInfo;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class MultinameKindQName
extends MultinameKind {
    private CpoolInfo cpool;
    private int start;
    private int end;
    private int ns = -1;
    private int name = -1;

    public MultinameKindQName(CpoolInfo cpool, int ns, int name) {
        this.cpool = cpool;
        this.ns = ns;
        this.name = name;
    }

    public MultinameKindQName(ByteBufferFlash bbuf, CpoolInfo cpool) {
        this.cpool = cpool;
        multiname_kind_QName(bbuf);
    }

    private void multiname_kind_QName(ByteBufferFlash bbuf) {
        this.start = bbuf.position();

        this.ns = bbuf.unsigned30int();
        this.name = bbuf.unsigned30int();

        this.end = (bbuf.position() - 1);
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getNs() {
        return this.ns;
    }

    public NamespaceInfo getNameSpac() {
        return (NamespaceInfo) this.cpool.getNamespaces().get(this.ns);
    }

    public int getName() {
        return this.name;
    }

    public String getNs_string() {
        if (this.ns == 0)
            return "*";
        return ((NamespaceInfo) this.cpool.getNamespaces().get(this.ns)).getName_string();
    }

    public StringInfo getNs_stringInfo() {
        if (this.ns == 0)
            return null;
        return ((NamespaceInfo) this.cpool.getNamespaces().get(this.ns)).getName_stringInfo();
    }

    public String getName_string() {
        if (this.name == 0)
            return "*";
        return ((StringInfo) this.cpool.getStrings().get(this.name)).getUtf8_string();
    }

    public StringInfo getName_stringInfo() {
        if (this.name == 0)
            return null;
        return (StringInfo) this.cpool.getStrings().get(this.name);
    }

    public void setNs(int ns) {
        this.ns = ns;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String String() {
        String wynik = "";
        wynik = wynik + "\n\t\t---#--- multiname_kind_QName " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n";

        wynik = wynik + "\t\tns: " + this.cpool.getNamespaces().get(this.ns) + "\n";

        wynik = wynik + "\t\tname: " + getName_string() + "\n";

        wynik = wynik + "\n\t\t---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end;

        return wynik;
    }

    public String toString() {
        String wynik = "";
        if (!getNs_string().equals(""))
            wynik = wynik + getNs_string() + "::" + getName_string();
        else {
            wynik = wynik + getName_string();
        }
        return wynik;
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        if (this.ns != -1) {
            byteDate.write(ByteBufferFlash.getUI32B(this.ns));
        }

        if (this.name != -1) {
            byteDate.write(ByteBufferFlash.getUI32B(this.name));
        }

        return byteDate.toByteArray();
    }
}