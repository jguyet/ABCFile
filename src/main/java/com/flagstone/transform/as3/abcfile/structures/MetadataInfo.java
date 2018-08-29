package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class MetadataInfo {
    private int id;
    public static int metadata_count;
    private CpoolInfo cpool;
    private int start = 0;
    private int end = 0;

    private int name;

    private int item_count;
    private ArrayList < ItemInfo > item_info = new ArrayList();

    private MetadataInfo(AbcFile abc) {
        this.cpool = abc.getConstant_pool();
        abc.ENmetadata_info.add(this);
        this.id = (abc.ENmetadata_info.size() - 1);
    }

    public static void metadata_info(ByteBufferFlash bbuf, int position, CpoolInfo cpool, AbcFile abc) {
        try {
            bbuf.position(position);

            metadata_count = bbuf.unsigned30int();

            if (metadata_count > 0) {
                for (int i2 = 0; i2 < metadata_count; i2++) {
                    MetadataInfo metadata_info = new MetadataInfo(abc);

                    metadata_info.start = bbuf.position();
                    metadata_info.name = bbuf.unsigned30int();
                    metadata_info.item_count = bbuf.unsigned30int();
                    for (int i = 0; i < metadata_info.item_count; i++) {
                        metadata_info.item_info.add(new ItemInfo(bbuf, cpool));
                    }
                    metadata_info.end = (bbuf.position() - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] toByteCode() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.name));

        byteDate.write(ByteBufferFlash.getUI32B(this.item_count));

        for (ItemInfo item: this.item_info) {
            byteDate.write(item.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public int getName() {
        return this.name;
    }

    public String getName_string() {
        return this.cpool.getString(this.name);
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public ArrayList < ItemInfo > getItem_info() {
        return this.item_info;
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();

        wynik.append("\n---#--- metadata_info " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n");

        wynik.append(">" + getStart() + "\n");
        wynik.append("name: " + getName_string() + "\n");

        wynik.append("<" + getEnd() + "\n");

        wynik.append("\n---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end);
        return wynik.toString();
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + this.start + " - " + this.end;
        return wynik;
    }

    public String toString2() {
        String wynik = "";
        wynik = wynik + "metadata_info " + this.start + " - " + this.end;
        return wynik;
    }

    public int getId() {
        return this.id;
    }
}