package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class ItemInfo {
    private CpoolInfo cpool;
    private int start;
    private int end;
    private int key;
    private int value;

    public ItemInfo(ByteBufferFlash bbuf, CpoolInfo cpool) {
        this.cpool = cpool;
        item_info(bbuf);
    }

    private void item_info(ByteBufferFlash bbuf) {
        this.start = bbuf.position();
        this.key = bbuf.unsigned30int();
        this.value = bbuf.unsigned30int();
        this.end = (bbuf.position() - 1);
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.key));

        byteDate.write(ByteBufferFlash.getUI32B(this.value));

        return byteDate.toByteArray();
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getKey() {
        return this.key;
    }

    public int getValue() {
        return this.value;
    }

    public String getKey_string() {
        return this.cpool.getString(this.key);
    }

    public String getValue_string() {
        return this.cpool.getString(this.value);
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();

        wynik.append("\n---#--- option_info " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n");

        wynik.append("key: " + this.key + "\n");

        wynik.append("value: " + this.value + "\n");

        wynik.append("\n---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end);

        return wynik.toString();
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + this.start + " - " + this.end;
        return wynik;
    }
}