package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class ExceptionInfo {
    private int id;
    private AbcFile abc;
    private int start;
    private int end;
    private int from;
    private int to;
    private int target;
    private int exc_type;
    private int var_name;
    public int catchEnd;

    public ExceptionInfo(ByteBufferFlash bbuf, AbcFile abc) {
        this.abc = abc;
        exception_info(bbuf);
        abc.ENexception_info.add(this);
        this.id = (abc.ENexception_info.size() - 1);
    }

    public ExceptionInfo(AbcFile abc, int from, int to, int target, int exc_type, int var_name) {
        this.abc = abc;
        abc.ENexception_info.add(this);
        this.id = (abc.ENexception_info.size() - 1);
        this.from = from;
        this.to = to;
        this.target = target;
        this.exc_type = exc_type;
        this.var_name = var_name;
    }

    public void exception_info(ByteBufferFlash bbuf) {
        try {
            this.start = bbuf.position();

            this.from = bbuf.unsigned30int();

            this.to = bbuf.unsigned30int();

            this.target = bbuf.unsigned30int();

            this.exc_type = bbuf.unsigned30int();

            this.var_name = bbuf.unsigned30int();

            this.end = (bbuf.position() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] toByteCode() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.from));

        byteDate.write(ByteBufferFlash.getUI32B(this.to));

        byteDate.write(ByteBufferFlash.getUI32B(this.target));

        byteDate.write(ByteBufferFlash.getUI32B(this.exc_type));

        byteDate.write(ByteBufferFlash.getUI32B(this.var_name));

        return byteDate.toByteArray();
    }

    public int getId() {
        return this.id;
    }

    public int getFrom() {
        return this.from;
    }

    public int getTo() {
        return this.to;
    }

    public int getTarget() {
        return this.target;
    }

    public int getExc_type() {
        return this.exc_type;
    }

    public String getExc_type_string() {
        this.abc.getConstant_pool().setZeroString("*");
        return (String) this.abc.getConstant_pool().getStrings_string().get(this.exc_type);
    }

    public int getVar_name() {
        return this.var_name;
    }

    public String getVar_name_string() {
        this.abc.getConstant_pool().setZeroString("NO_NAME");
        return (String) this.abc.getConstant_pool().getStrings_string().get(this.var_name);
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVar_name(int var_name) {
        this.var_name = var_name;
    }

    public void setExc_type(int exc_type) {
        this.exc_type = exc_type;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();
        wynik.append("\n---#--- exception_info " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n");

        wynik.append("from: " + this.from + "\n");

        wynik.append("to: " + this.to + "\n");

        wynik.append("target: " + this.target + "\n");

        wynik.append("exc_type: " + this.exc_type + "\n");

        wynik.append("var_name: " + this.var_name + "\n");

        wynik.append("\n---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end);
        return wynik.toString();
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "ExceptionInfo id" + this.id + " " + this.start + " - " + this.end;
        return wynik;
    }
}