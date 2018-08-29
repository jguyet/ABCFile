package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class OptionInfo {
    private int id;
    private int start;
    private int end;
    private int option_count;
    private ArrayList < OptionDetail > option_detail = new ArrayList();

    public OptionInfo(ByteBufferFlash bbuf, int param_count, AbcFile abc) {
        option_info(bbuf, param_count, abc);
        abc.ENoption_info.add(this);
        this.id = (abc.ENoption_info.size() - 1);
    }

    private void option_info(ByteBufferFlash bbuf, int param_count, AbcFile abc) {
        try {
            this.start = bbuf.position();

            this.option_count = bbuf.unsigned30int();

            for (int i = 0; i < this.option_count; i++)
                this.option_detail.add(new OptionDetail(bbuf));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.end = (bbuf.position() - 1);
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.option_count));

        for (OptionDetail option: this.option_detail) {
            byteDate.write(option.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public ArrayList < OptionDetail > getOption_detail() {
        return this.option_detail;
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();

        wynik.append(">" + this.start + "\n");

        wynik.append("option_count: " + this.option_count + "\n");

        wynik.append("<" + this.end + "\n");

        return wynik.toString();
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "option_info id " + this.id + " " + this.start + " - " + this.end;
        return wynik;
    }

    public String toString2() {
        String wynik = "";
        wynik = wynik + "option_info";
        return wynik;
    }
}