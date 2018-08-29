package com.flagstone.transform.as3.abcfile.cpool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class NsSetInfo {
    private int id;
    private CpoolInfo cpool;
    private int start;
    private int end;
    private int count = -1;
    private ArrayList < Integer > ns = new ArrayList();

    public NsSetInfo(CpoolInfo cpool, ArrayList < Integer > ns, int id) {
        this.cpool = cpool;
        this.ns = ns;
        this.count = ns.size();
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public NsSetInfo(ByteBufferFlash bbuf, CpoolInfo cpool, int id) {
        this.cpool = cpool;
        ns_set_info(bbuf);
        this.id = id;
    }

    private void ns_set_info(ByteBufferFlash bbuf) {
        this.start = bbuf.position();
        this.count = bbuf.unsigned30int();
        for (int i2 = 0; i2 < this.count; i2++) {
            this.ns.add(Integer.valueOf(bbuf.unsigned30int()));
        }
        this.end = (bbuf.position() - 1);
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getCount() {
        return this.count;
    }

    public ArrayList < Integer > getNs() {
        return this.ns;
    }

    public List < NamespaceInfo > getNsInfo() {
        List < NamespaceInfo > result = new ArrayList();
        for (int i = 0; i < this.ns.size(); i++)
            result.add((NamespaceInfo) this.cpool.getNamespaces().get(((Integer) this.ns.get(i)).intValue()));
        return result;
    }

    public ArrayList < String > getNs_string() {
        ArrayList < String > result = new ArrayList();
        for (int i = 0; i < this.ns.size(); i++)
            result.add(((StringInfo) this.cpool.getStrings().get(((Integer) this.ns.get(i)).intValue())).getUtf8_string());
        return result;
    }

    public ArrayList < StringInfo > getNs_stringInfo() {
        ArrayList < StringInfo > result = new ArrayList();
        for (int i = 0; i < this.ns.size(); i++)
            result.add((StringInfo) this.cpool.getStrings().get(((Integer) this.ns.get(i)).intValue()));
        return result;
    }

    public String String() {
        String wynik = "";
        wynik = wynik + "\n\t\t\t---#--- ns_set_info " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n";

        wynik = wynik + "\t\t\tcount: " + this.count + "\n";

        wynik = wynik + "\t\t\tns: " + getNs_string() + "\n";

        wynik = wynik + "\n\t\t\t---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end;

        return wynik;
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + getNs_string();
        return wynik;
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        if (this.count != -1) {
            byteDate.write(ByteBufferFlash.getUI32B(this.count));
        }

        if (this.ns.size() > 0) {
            for (int i = 0; i < this.ns.size(); i++) {
                byteDate.write(ByteBufferFlash.getUI32B(((Integer) this.ns.get(i)).intValue()));
            }
        }

        return byteDate.toByteArray();
    }
}