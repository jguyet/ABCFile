package com.flagstone.transform.as3.abcfile.cpool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class NamespaceInfo {
    private CpoolInfo cpool;
    private int start = -1;
    private int end = -1;

    private int kind;

    private int name;

    public NamespaceInfo(NamespaceInfo namespaceInfo) {
        this.cpool = namespaceInfo.cpool;
        this.kind = namespaceInfo.getKind();
        this.name = namespaceInfo.getName();
        this.cpool.addNamespace(this);
    }

    public NamespaceInfo(CpoolInfo cpool, NamespaceKindType namespaceKind, int name) {
        this.cpool = cpool;
        this.kind = namespaceKind.value;
        this.name = name;
    }

    public int getId() {
        return this.cpool.getNamespaces().indexOf(this);
    }

    public void setKind(NamespaceKindType kind) {
        this.kind = kind.value;
    }

    public void setName(int name) {
        this.name = name;
    }

    public NamespaceInfo(ByteBufferFlash bbuf, CpoolInfo cpool) {
        this.cpool = cpool;
        namespace_info(bbuf);
    }

    private void namespace_info(ByteBufferFlash bbuf) {
        this.start = bbuf.position();

        this.kind = bbuf.unsignedByte();
        this.name = bbuf.unsigned30int();

        this.end = (bbuf.position() - 1);
    }

    public String getName_string() {
        return ((StringInfo) this.cpool.getStrings().get(this.name)).getUtf8_string();
    }

    public StringInfo getName_stringInfo() {
        return (StringInfo) this.cpool.getStrings().get(this.name);
    }

    public int getKind() {
        return this.kind;
    }

    public int getName() {
        return this.name;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "\n\t\t\t---#--- namespace_info " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n";

        wynik = wynik + "\t\t\tkind: " + NamespaceKindType.getNamespaceKind(this.kind) + "\n";

        wynik = wynik + "\t\t\tname: " + getName_string() + "\n";

        wynik = wynik + "\t\t\tnameid: " + this.name + "\n";

        wynik = wynik + "\n\t\t\t---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end;

        return wynik;
    }

    public String String() {
        String wynik = "";
        wynik = wynik + getName_string();
        return wynik;
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write((byte) this.kind);

        byteDate.write(ByteBufferFlash.getUI32B(this.name));

        return byteDate.toByteArray();
    }
}