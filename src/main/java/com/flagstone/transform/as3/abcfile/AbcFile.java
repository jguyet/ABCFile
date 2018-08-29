package com.flagstone.transform.as3.abcfile;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import com.flagstone.transform.as3.abcfile.structures.ClassInfo;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.structures.ExceptionInfo;
import com.flagstone.transform.as3.abcfile.structures.InstanceInfo;
import com.flagstone.transform.as3.abcfile.structures.MetadataInfo;
import com.flagstone.transform.as3.abcfile.structures.MethodBodyInfo;
import com.flagstone.transform.as3.abcfile.structures.MethodInfo;
import com.flagstone.transform.as3.abcfile.structures.OptionInfo;
import com.flagstone.transform.as3.abcfile.structures.ScriptInfo;
import com.flagstone.transform.as3.abcfile.structures.TraitInfo;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class AbcFile {
    public ArrayList < OptionInfo > ENoption_info = new ArrayList();
    public ArrayList < TraitInfo > ENtrait_info = new ArrayList();
    public ArrayList < ExceptionInfo > ENexception_info = new ArrayList();

    public LinkedHashMap < Integer, String > methodsNames = new LinkedHashMap();
    public LinkedHashMap < String, Integer > namesMethods = new LinkedHashMap();
    public LinkedHashMap < String, Integer > methodsNamesIdName = new LinkedHashMap();

    public Vector < String > constNames = new Vector();

    public HashMap < Integer, String > ClassNames = new HashMap();

    private int minor_version;

    private int major_version;
    private CpoolInfo constant_pool;
    public ArrayList < MethodInfo > ENmethod_info = new ArrayList();
    public ArrayList < MetadataInfo > ENmetadata_info = new ArrayList();
    public ArrayList < InstanceInfo > ENinstance_info = new ArrayList();
    public ArrayList < ClassInfo > ENclass_info = new ArrayList();
    public ArrayList < ScriptInfo > ENscript_info = new ArrayList();
    public ArrayList < MethodBodyInfo > ENmethod_body_info = new ArrayList();

    public AbcFile(int pos, ByteBufferFlash bbuf)
    throws Exception {

        this.minor_version = bbuf.unsignedShort();

        this.major_version = bbuf.unsignedShort();

        this.constant_pool = new CpoolInfo(this);

        this.constant_pool.cpool_info(bbuf, bbuf.position());

        MethodInfo.method_info(bbuf, bbuf.position(), this.constant_pool, this);
        MetadataInfo.metadata_info(bbuf, bbuf.position(), this.constant_pool, this);
        InstanceInfo.instance_info(bbuf, bbuf.position(), this.constant_pool, this);
        ClassInfo.class_info(bbuf, bbuf.position(), this.constant_pool, this);
        ScriptInfo.script_info(bbuf, bbuf.position(), this.constant_pool, this);
        MethodBodyInfo.method_body_info(bbuf, bbuf.position(), this.constant_pool, this);
    }

    public byte[] generateDoABC()
    throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        out.write(getByteMinor_version().array());

        out.write(getByteMajor_version().array());

        out.write(this.constant_pool.toByteCode());

        out.write(toByteCodeENmethod_info());

        out.write(toByteCodeENmetadata_info());

        out.write(toByteCodeENinstance_info());

        out.write(toByteCodeENclass_info());

        out.write(toByteCodeENscript_info());

        out.write(toByteCodeENmethod_body_info());

        return out.toByteArray();
    }

    public int getMinor_version() {
        return this.minor_version;
    }

    public ByteBuffer getByteMinor_version() {
        ByteBuffer bbuf = ByteBuffer.allocate(2);
        bbuf.putShort(Short.reverseBytes((short) this.minor_version));
        bbuf.rewind();
        return bbuf;
    }

    public void setMinor_version(int minor_version) {
        this.minor_version = minor_version;
    }

    public int getMajor_version() {
        return this.major_version;
    }

    public ByteBuffer getByteMajor_version() {
        ByteBuffer bbuf = ByteBuffer.allocate(2);
        bbuf.putShort(Short.reverseBytes((short) this.major_version));
        bbuf.rewind();
        return bbuf;
    }

    public void setMajor_version(int major_version) {
        this.major_version = major_version;
    }

    public CpoolInfo getConstant_pool() {
        return this.constant_pool;
    }

    public void setConstant_pool(CpoolInfo constant_pool) {
        this.constant_pool = constant_pool;
    }

    public byte[] toByteCodeENmethod_info() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.ENmethod_info.size()));

        for (MethodInfo method: this.ENmethod_info) {
            byteDate.write(method.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public byte[] toByteCodeENmetadata_info() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.ENmetadata_info.size()));

        for (MetadataInfo metadata: this.ENmetadata_info) {
            byteDate.write(metadata.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public byte[] toByteCodeENinstance_info() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.ENinstance_info.size()));

        for (InstanceInfo instance: this.ENinstance_info) {
            byteDate.write(instance.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public byte[] toByteCodeENclass_info() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        for (ClassInfo class_info: this.ENclass_info) {
            byteDate.write(class_info.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public byte[] toByteCodeENscript_info() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getUI32B(this.ENscript_info.size()));

        for (ScriptInfo script: this.ENscript_info) {
            byteDate.write(script.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public byte[] toByteCodeENmethod_body_info()
    throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();
        byteDate.write(ByteBufferFlash.getUI32B(this.ENmethod_body_info.size()));

        for (MethodBodyInfo method_body: this.ENmethod_body_info) {
            byteDate.write(method_body.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public String toString() {
        return "AbcFile";
    }
}