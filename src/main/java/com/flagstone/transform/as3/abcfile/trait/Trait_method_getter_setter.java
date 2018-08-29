package com.flagstone.transform.as3.abcfile.trait;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class Trait_method_getter_setter
implements ITraitKindData {
    private int start = 0;
    private int end = 0;
    private int disp_id;
    private int method;

    public Trait_method_getter_setter(ByteBufferFlash bbuf)
    throws Exception {
        trait_method_getter_setter(bbuf);
    }

    public void trait_method_getter_setter(ByteBufferFlash bbuf) throws Exception {
        this.start = bbuf.position();
        this.disp_id = bbuf.unsigned30int();

        this.method = bbuf.unsigned30int();
        this.end = (bbuf.position() - 1);
    }

    public int getDisp_id() {
        return this.disp_id;
    }

    public int getMethod() {
        return this.method;
    }

    public void setDisp_id(int disp_id) {
        this.disp_id = disp_id;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "\n---#--- Trait_method_getter_setter \n" + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n";

        wynik = wynik + "disp_id: " + this.disp_id + "\n";
        wynik = wynik + "method: " + this.method + "\n";

        wynik = wynik + Internatiolizer.msgs.getString("Position") + ":  " + this.end + "\n---!!--- " + Internatiolizer.msgs.getString("end") + "\n";
        return wynik;
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.disp_id));

        byteDate.write(ByteBufferFlash.getUI32B(this.method));

        return byteDate.toByteArray();
    }
}