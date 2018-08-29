package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.cpool.MultinameInfo;
import com.flagstone.transform.as3.abcfile.cpool.StringInfo;
import com.flagstone.transform.as3.abcfile.trait.ConstantKind;
import com.flagstone.transform.as3.abcfile.trait.ITraitKindData;
import com.flagstone.transform.as3.abcfile.trait.TraitKind;
import com.flagstone.transform.as3.abcfile.trait.Trait_Slot_Const;
import com.flagstone.transform.as3.abcfile.trait.Trait_class;
import com.flagstone.transform.as3.abcfile.trait.Trait_function;
import com.flagstone.transform.as3.abcfile.trait.Trait_method_getter_setter;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class TraitInfo {
    public static enum Type {
        CLASS,
        INSTANCE,
        METHOD,
        SCRIPT;
    }

    private int k1 = 0;
    private int k2 = 0;

    private Type type;

    private int id;

    private CpoolInfo cpool;
    private AbcFile abc;
    private String attribute;
    private String dataDesription = "";

    private int start = 0;
    private int end = 0;

    private int name;
    private int kind;
    private ITraitKindData data = null;
    private int metadata_count = -1;
    private List < Integer > metadata = new ArrayList();

    public TraitInfo(AbcFile abc, int name, int kind, ITraitKindData data, int metadata_count, List < Integer > metadata) throws Exception {
        this.abc = abc;
        this.cpool = abc.getConstant_pool();
        this.name = name;
        this.kind = kind;
        this.data = data;
        this.metadata_count = metadata_count;

        if (metadata_count > 0) {
            this.metadata = metadata;
        }

        abc.ENtrait_info.add(this);
        this.id = (abc.ENtrait_info.size() - 1);
    }

    public TraitInfo(AbcFile abc, MultinameInfo name, TraitKind kind, List < Integer > metadata, MultinameInfo type_name, int vindex, ConstantKind constantKind)
    throws Exception {
        this.abc = abc;
        this.cpool = abc.getConstant_pool();
        this.name = name.getId();
        this.kind = kind.getKind();
        this.data = new Trait_Slot_Const(this, type_name, vindex, constantKind);

        if ((metadata != null) && (metadata.size() > 0)) {
            this.metadata_count = metadata.size();
            this.metadata = metadata;
        }
        abc.ENtrait_info.add(this);
        this.id = (abc.ENtrait_info.size() - 1);
    }

    public TraitInfo(AbcFile abc, ByteBufferFlash bbuf, Type type)
    throws Exception {
        this.type = type;
        this.abc = abc;
        this.cpool = abc.getConstant_pool();
        traits_info(bbuf);

        abc.ENtrait_info.add(this);
        this.id = (abc.ENtrait_info.size() - 1);
    }

    public void traits_info(ByteBufferFlash bbuf)
    throws Exception {
        this.start = bbuf.position();
        this.name = bbuf.unsigned30int();
        this.kind = bbuf.unsignedByte();
        int tag = this.kind & 0xF;
        int tag2 = this.kind & 0xF0;

        this.k1 = tag;
        this.k2 = tag2;
        switch (tag) {
            case 0:
            case 6:
                this.data = new Trait_Slot_Const(bbuf, this.cpool, this);
                String name;
                try {
                    name = getName_string();
                } catch (Exception e) {
                    e.printStackTrace();
                    name = "";
                }
                this.abc.constNames.add(name);
                this.dataDesription = Trait_Slot_Const.class.getName();
                break;
            case 1:
            case 2:
            case 3:
                Trait_method_getter_setter tr = new Trait_method_getter_setter(bbuf);
                this.data = tr;

                int id = tr.getMethod();
                String s;
                try {
                    s = getName_string();
                } catch (Exception e) {
                    e.printStackTrace();
                    s = "";
                }

                if (this.type == Type.INSTANCE) {
                    this.abc.methodsNames.put(Integer.valueOf(id), s);
                    this.abc.namesMethods.put(s, Integer.valueOf(id));
                }

                this.dataDesription = Trait_method_getter_setter.class.getName();
                if (tag2 == 16) this.attribute = "ATTR_Final";
                if (tag2 == 32) {
                    this.attribute = "ATTR_Override";
                }
                break;
            case 4:
                Trait_class tc = new Trait_class(bbuf);
                this.data = tc;
                String s1;
                try {
                    s1 = getName_string();
                } catch (Exception e) {
                    e.printStackTrace();
                    s1 = "";
                }
                int idClass = tc.getClassi();
                if (idClass >= 0) {

                    this.abc.ClassNames.put(Integer.valueOf(idClass), s1);
                }

                this.dataDesription = Trait_class.class.getName();
                break;
            case 5:
                this.data = new Trait_function(bbuf);
                this.dataDesription = Trait_function.class.getName();
        }

        if ((tag2 == 64) || (tag2 == 96) || (tag2 == 80)) {
            this.attribute = "ATTR_Metadata";

            this.metadata_count = bbuf.unsigned30int();

            for (int i = 0; i < this.metadata_count; i++)
                this.metadata.add(Integer.valueOf(bbuf.unsigned30int()));
        }
        this.end = (bbuf.position() - 1);
    }

    public byte[] toByteCode()
    throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.name));

        byteDate.write(this.kind);

        if (this.data != null) {

            if ((this.data instanceof Trait_Slot_Const)) {
                byteDate.write(((Trait_Slot_Const) this.data).toByteCode());
            } else if ((this.data instanceof Trait_method_getter_setter)) {
                byteDate.write(((Trait_method_getter_setter) this.data).toByteCode());
            } else if ((this.data instanceof Trait_class)) {
                byteDate.write(((Trait_class) this.data).toByteCode());
            } else if ((this.data instanceof Trait_function)) {
                byteDate.write(((Trait_function) this.data).toByteCode());
            }
        }

        if (this.metadata_count != -1) {
            byteDate.write(ByteBufferFlash.getUI32B(this.metadata_count));
        }

        if (this.metadata_count != -1) {
            for (int i = 0; i < this.metadata.size(); i++) {
                byteDate.write(ByteBufferFlash.getUI32B(((Integer) this.metadata.get(i)).intValue()));
            }
        }

        return byteDate.toByteArray();
    }

    public String getAttribute() {
        return this.attribute;
    }

    public int getId() {
        return this.id;
    }

    public ITraitKindData getData() {
        return this.data;
    }

    public int getMetadata_count() {
        return this.metadata_count;
    }

    public List < Integer > getMetadata() {
        return this.metadata;
    }

    public int getKind() {
        return this.kind;
    }

    public TraitKind getTraitKind() {
        return TraitKind.getTrait(this.kind & 0xF);
    }

    public int getName() {
        return this.name;
    }

    public MultinameInfo getNameMultinameInfo() {
        return (MultinameInfo) this.cpool.getMultinames().get(this.name);
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getName_string() {
        return this.cpool.getMultiname(this.name);
    }

    public String getNameWithoutPackage_string() {
        return ((MultinameInfo) this.cpool.getMultinames().get(this.name)).getNameString(false);
    }

    public ArrayList < StringInfo > getNameWithoutPackage_stringInfo() {
        return ((MultinameInfo) this.cpool.getMultinames().get(this.name)).getNameStringInfo(false);
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();

        wynik.append(">" + this.start + "\n");
        wynik.append("name: " + getName_string() + "\n");
        wynik.append("multiname: " + ((MultinameInfo) this.cpool.getMultinames().get(this.name)).String() + "\n");
        wynik.append("kind: " + this.kind + "\n");
        wynik.append("kind1: " + this.k1 + "\n");
        wynik.append("kind1: " + this.k2 + "\n");

        wynik.append("data: " + this.data + "\n");

        wynik.append("metadata_count: " + this.metadata_count + "\n");

        wynik.append("metadata: " + this.metadata + "\n");
        wynik.append("attribute: " + this.attribute + "\n");

        wynik.append("<" + this.end + "\n");

        return wynik.toString();
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "trait_info id " + this.id + " " + this.start + " - " + this.end;
        return wynik;
    }

    public CpoolInfo getCpool() {
        return this.cpool;
    }

    public void setData(ITraitKindData data) {
        this.data = data;
    }
}