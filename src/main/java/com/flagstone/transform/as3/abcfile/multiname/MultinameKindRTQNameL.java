package com.flagstone.transform.as3.abcfile.multiname;

import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class MultinameKindRTQNameL
extends MultinameKind {
    private int start;
    private int end;

    public MultinameKindRTQNameL() {}

    public MultinameKindRTQNameL(ByteBufferFlash bbuf) {
        multiname_kind_RTQNameL(bbuf);
    }

    private void multiname_kind_RTQNameL(ByteBufferFlash bbuf) {}

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public String String() {
        String wynik = "";
        wynik = wynik + "\n\t\t---#--- multiname_kind_RTQNameL " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n";

        wynik = wynik + "\n\t\t---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end;

        return wynik;
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + this.start + " - " + this.end;
        return wynik;
    }
}