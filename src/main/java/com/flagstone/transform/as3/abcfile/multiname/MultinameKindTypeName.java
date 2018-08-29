package com.flagstone.transform.as3.abcfile.multiname;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class MultinameKindTypeName
extends MultinameKind {
    private CpoolInfo cpool;
    private int start;
    private int end;
    private int nameIndex = -1;
    private int count = -1;
    private ArrayList < Integer > params;

    public MultinameKindTypeName(CpoolInfo cpool) {
        this.cpool = cpool;
    }

    public MultinameKindTypeName(ByteBufferFlash bbuf, CpoolInfo cpool) {
        this.cpool = cpool;
        multiname_kind_TypeName(bbuf);
    }

    private void multiname_kind_TypeName(ByteBufferFlash bbuf) {
        this.start = bbuf.position();

        this.nameIndex = bbuf.unsigned30int();
        this.count = bbuf.unsigned30int();
        this.params = new ArrayList();
        for (int i = 0; i < this.count; i++) {
            this.params.add(Integer.valueOf(bbuf.unsigned30int()));
        }

        this.end = (bbuf.position() - 1);
    }

    public ArrayList < Integer > getParams() {
        return this.params;
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public String String() {
        String wynik = "";
        wynik = wynik + "\n\t\t---#--- multiname_kind_TypeName " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n";

        wynik = wynik + "\t\tmultiname: " + this.cpool.getMultinames().get(this.nameIndex) + "\n";

        for (Iterator localIterator = this.params.iterator(); localIterator.hasNext();) {
            int i = ((Integer) localIterator.next()).intValue();
            wynik = wynik + "\t\tparam " + i + ": " + this.cpool.getMultinames().get(this.nameIndex) + "\n";
        }

        wynik = wynik + "\n\t\t---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end;

        return wynik;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public String toString() {
        String wynik = "";

        wynik = wynik + this.cpool.getMultinames().get(this.nameIndex);

        return wynik;
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        if (this.nameIndex != -1) {
            byteDate.write(ByteBufferFlash.getUI32B(this.nameIndex));
        }
        if (this.count != -1) {
            byteDate.write(ByteBufferFlash.getUI32B(this.count));
        }

        for (int i = 0; i < this.params.size(); i++) {
            byteDate.write(ByteBufferFlash.getUI32B(((Integer) this.params.get(i)).intValue()));
        }

        return byteDate.toByteArray();
    }
}