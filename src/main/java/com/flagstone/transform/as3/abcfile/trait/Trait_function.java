package com.flagstone.transform.as3.abcfile.trait;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class Trait_function
implements ITraitKindData {
    private int start = 0;
    private int end = 0;
    private int slot_id;
    private int
    function;

    public Trait_function(ByteBufferFlash bbuf)
    throws Exception {
        trait_function(bbuf);
    }

    public void trait_function(ByteBufferFlash bbuf) throws Exception {
        this.start = bbuf.position();
        this.slot_id = bbuf.unsigned30int();

        this.function = bbuf.unsigned30int();
        this.end = (bbuf.position() - 1);
    }

    public int getSlot_id() {
        return this.slot_id;
    }

    public int getFunction() {
        return this.function;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public void setFunction(int
        function) {
        this.function = function;
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "\n---#--- Trait_function \n" + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n";

        wynik = wynik + "slot_id: " + this.slot_id + "\n";
        wynik = wynik + "function: " + this.function+"\n";

        wynik = wynik + Internatiolizer.msgs.getString("Position") + ":  " + this.end + "\n---!!--- " + Internatiolizer.msgs.getString("end") + "\n";
        return wynik;
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.slot_id));

        byteDate.write(ByteBufferFlash.getUI32B(this.function));

        return byteDate.toByteArray();
    }
}