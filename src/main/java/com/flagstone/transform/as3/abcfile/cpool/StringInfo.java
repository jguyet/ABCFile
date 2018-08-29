package com.flagstone.transform.as3.abcfile.cpool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class StringInfo {
    private int id;
    private int start = -1;
    private int end = -1;

    private int size = -1;
    private ArrayList < Byte > utf8 = new ArrayList();

    public StringInfo(String value, int id) {
        setString(value);
        this.id = id;
    }

    public StringInfo(ByteBufferFlash bbuf, int id) {
        string_info(bbuf);
        this.id = id;
    }

    private void string_info(ByteBufferFlash bbuf) {
        this.start = bbuf.position();

        this.size = bbuf.unsigned30int();

        for (int ii = 0; ii < this.size; ii++) {
            this.utf8.add(Byte.valueOf(bbuf.get()));
        }
        this.end = (bbuf.position() - 1);
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public String getUtf8_string() {
        String result = "";
        for (int i = 0; i < this.utf8.size(); i++)
            result = result + (char)((Byte) this.utf8.get(i)).byteValue();
        return result;
    }

    public void setString(String s) {
        byte[] b = s.getBytes();
        this.utf8.clear();
        for (int i = 0; i < b.length; i++)
            this.utf8.add(Byte.valueOf(b[i]));
        this.size = b.length;
    }

    public String String() {
        String wynik = "";
        wynik = wynik + "\n---#--- ns_set_info " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n";

        wynik = wynik + "size: " + this.size + "\n";

        wynik = wynik + "ns: " + getUtf8_string() + "\n";

        wynik = wynik + "\n---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end;
        return wynik;
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + getUtf8_string();
        return wynik;
    }

    public int getSize() {
        return this.size;
    }

    public ArrayList < Byte > getUtf8() {
        return this.utf8;
    }

    public void setUtf8(ArrayList < Byte > utf8) {
        this.utf8 = utf8;
    }

    public int getId() {
        return this.id;
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        if (this.size != -1) {
            byteDate.write(ByteBufferFlash.getUI32B(this.size));
        }

        if (this.utf8.size() > 0) {
            for (Iterator localIterator = this.utf8.iterator(); localIterator.hasNext();) {
                byte b = ((Byte) localIterator.next()).byteValue();
                byteDate.write(b);
            }
        }
        return byteDate.toByteArray();
    }
}