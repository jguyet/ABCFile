package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.flagstone.transform.as3.abcfile.trait.ConstantKind;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class OptionDetail {
    private int start;
    private int end;
    private int val;
    private int kind;

    public OptionDetail(ByteBufferFlash bbuf) {
        option_detail(bbuf);
    }

    private void option_detail(ByteBufferFlash bbuf) {
        this.start = bbuf.position();

        this.val = bbuf.unsigned30int();

        this.kind = bbuf.unsignedByte();

        this.end = (bbuf.position() - 1);
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.val));

        byteDate.write(this.kind);

        return byteDate.toByteArray();
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();

        wynik.append(">" + this.start + "\n");

        wynik.append("val: " + this.val + "\n");

        wynik.append("kind: " + getKind_string() + "\n");

        wynik.append("<" + this.end + "\n");

        return wynik.toString();
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "option_detail";
        return wynik;
    }

    public String getKind_string() {
        ConstantKind cKind = ConstantKind.getConstant(this.kind);
        if (cKind != null) {
            return ConstantKind.getConstant(this.kind).name();
        }
        return "";
    }

    public int getVal() {
        return this.val;
    }

    public int getKind() {
        return this.kind;
    }
}