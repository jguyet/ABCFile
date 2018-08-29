package com.flagstone.transform.as3.abcfile.cpool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.flagstone.transform.as3.abcfile.multiname.MultinameKind;
import com.flagstone.transform.as3.abcfile.multiname.MultinameKindMultiname;
import com.flagstone.transform.as3.abcfile.multiname.MultinameKindMultinameL;
import com.flagstone.transform.as3.abcfile.multiname.MultinameKindQName;
import com.flagstone.transform.as3.abcfile.multiname.MultinameKindRTQName;
import com.flagstone.transform.as3.abcfile.multiname.MultinameKindRTQNameL;
import com.flagstone.transform.as3.abcfile.multiname.MultinameKindTypeName;
import com.flagstone.transform.as3.abcfile.structures.CpoolInfo;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;
import com.flagstone.transform.as3.abcfile.utils.Internatiolizer;

public class MultinameInfo {
    public static final ABCLogger LOGGER = new ABCLogger(MultinameInfo.class);

    public int kindTmp = 0;

    private CpoolInfo cpool;

    private int start = -1;
    private int end = -1;

    private int kind = -1;
    private MultinameKind data = null;

    public static final String UNDEFINED_MULTINAME = "undefined";
    public static final String NULL_MULTINAME = "null";
    public static final String BOOLEAN_MULTINAME = "Boolean";
    public static final String NUMBER_MULTINAME = "Number";
    public static final String INT_MULTINAME = "int";
    public static final String UINT_MULTINAME = "uint";
    public static final String STRING_MULTINAME = "String";
    public static final String FUNCTION_MULTINAME = "Function";
    public static final String XML_MULTINAME = "XML";
    public static final String XMLLIST_MULTINAME = "XMLList";
    public static final String OBJECT_MULTINAME = "Object";

    public MultinameInfo(CpoolInfo cpool, MultinameKindType multinameKind, int name) {
        this.cpool = cpool;
        this.kind = multinameKind.value;
        this.data = new MultinameKindRTQName(cpool, name);
    }

    public MultinameInfo(CpoolInfo cpool, MultinameKindQName data, MultinameKindType multinameKind) {
        this.cpool = cpool;
        this.data = data;
        this.kind = multinameKind.value;
    }

    public MultinameInfo(CpoolInfo cpool, MultinameKindRTQName data, MultinameKindType multinameKind) {
        this.cpool = cpool;
        this.data = data;
        this.kind = multinameKind.value;
    }

    public MultinameInfo(CpoolInfo cpool, MultinameKindRTQNameL data, MultinameKindType multinameKind) {
        this.cpool = cpool;
        this.data = data;
        this.kind = 17;
    }

    public MultinameInfo(CpoolInfo cpool, MultinameKindMultiname data, MultinameKindType multinameKind) {
        this.cpool = cpool;
        this.data = data;
        this.kind = multinameKind.value;
    }

    public MultinameInfo(CpoolInfo cpool, MultinameKindMultinameL data, MultinameKindType multinameKind) {
        this.cpool = cpool;
        this.data = data;
        this.kind = multinameKind.value;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public void setData(MultinameKind data) {
        this.data = data;
    }

    public int getKind() {
        return this.kind;
    }

    public MultinameInfo(ByteBufferFlash bbuf, CpoolInfo cpool) {
        this.cpool = cpool;
        multiname_info(bbuf);
    }

    private void multiname_info(ByteBufferFlash bbuf) {
        this.start = bbuf.position();

        this.kind = bbuf.unsignedByte();

        if ((this.kind == 7) || (this.kind == 13)) {
            this.data = new MultinameKindQName(bbuf, this.cpool);
        } else if ((this.kind == 15) || (this.kind == 16)) {
            this.data = new MultinameKindRTQName(bbuf, this.cpool);
        } else if ((this.kind == 17) || (this.kind == 18)) {
            this.data = new MultinameKindRTQNameL(bbuf);
        } else if ((this.kind == 9) || (this.kind == 14)) {
            this.data = new MultinameKindMultiname(bbuf, this.cpool);
        } else if ((this.kind == 27) || (this.kind == 28)) {
            this.data = new MultinameKindMultinameL(bbuf, this.cpool);
        } else if (this.kind == 29) {
            this.data = new MultinameKindTypeName(bbuf, this.cpool);
        } else {
            LOGGER.error("multiname_info bad kind: " + this.kind);
        }

        this.end = (bbuf.position() - 1);
    }

    public int getStringId() {
        if ((this.data instanceof MultinameKindQName)) {
            return ((MultinameKindQName) this.data).getName();
        }
        if ((this.data instanceof MultinameKindRTQName)) {
            return ((MultinameKindRTQName) this.data).getName();
        }
        if ((this.kind == 17) || (this.kind == 18)) {
            return -1;
        }
        if ((this.data instanceof MultinameKindMultiname)) {
            return ((MultinameKindMultiname) this.data).getName();
        }
        if ((this.kind == 27) || (this.kind == 28)) {

            return -1;
        }

        return -1;
    }

    public void setStringInfoId(int id) {
        if ((this.data instanceof MultinameKindQName)) {
            ((MultinameKindQName) this.data).setName(id);
        } else if ((this.data instanceof MultinameKindRTQName)) {
            ((MultinameKindRTQName) this.data).setName(id);
        } else if ((this.kind != 17) && (this.kind != 18)) {

            if ((this.data instanceof MultinameKindMultiname)) {
                ((MultinameKindMultiname) this.data).setName(id);
            } else if ((this.kind == 27) || (this.kind == 28)) {}
        }
    }

    public String getNameString(boolean isPackage) {
        if ((this.data instanceof MultinameKindQName)) {
            if (isPackage)
                return ((MultinameKindQName) this.data).getNs_string();
            return ((MultinameKindQName) this.data).getName_string();
        }
        if ((this.data instanceof MultinameKindRTQName)) {
            if (isPackage)
                return "";
            return ((MultinameKindRTQName) this.data).getName_string();
        }
        if ((this.kind == 17) || (this.kind == 18)) {
            return "";
        }
        if ((this.data instanceof MultinameKindMultiname)) {
            if (isPackage)
                return ((MultinameKindMultiname) this.data).getNs_set_string().toString();
            return ((MultinameKindMultiname) this.data).getName_string();
        }
        if ((this.kind == 27) || (this.kind == 28)) {
            LOGGER.info("Sprawdzic MultinameInfo");

            return "";
        }
        LOGGER.info("Sprawdzic MultinameInfo");
        return "";
    }

    public String getPackageNameString() {
        if ((this.data instanceof MultinameKindQName))
            return ((MultinameKindQName) this.data).getNs_string();
        if ((this.data instanceof MultinameKindRTQName))
            return "";
        if ((this.kind == 17) || (this.kind == 18)) {
            return "";
        }
        if ((this.data instanceof MultinameKindMultiname))
            return ((MultinameKindMultiname) this.data).getNs_set_string().toString();
        if ((this.kind == 27) || (this.kind == 28)) {
            LOGGER.info("Sprawdzic MultinameInfo");

            return "";
        }
        LOGGER.info("Sprawdzic MultinameInfo");
        return "";
    }

    public ArrayList < StringInfo > getNameStringInfo(boolean isPackage) {
        ArrayList < StringInfo > stringsInfo = new ArrayList();
        if ((this.data instanceof MultinameKindQName)) {
            if (isPackage) {
                stringsInfo.add(((MultinameKindQName) this.data).getNs_stringInfo());
            } else {
                stringsInfo.add(((MultinameKindQName) this.data).getName_stringInfo());
            }
        } else if ((this.data instanceof MultinameKindRTQName)) {
            if (!isPackage) {
                stringsInfo.add(((MultinameKindRTQName) this.data).getName_stringInfo());
            }
        } else if ((this.kind != 17) && (this.kind != 18)) {
            if ((this.data instanceof MultinameKindMultiname)) {
                if (isPackage) {
                    stringsInfo.addAll(((MultinameKindMultiname) this.data).getNs_set_stringInfo());
                } else {
                    stringsInfo.add(((MultinameKindMultiname) this.data).getName_stringInfo());
                }
            } else if ((this.kind == 27) || (this.kind == 28)) {
                LOGGER.info("Sprawdzic MultinameInfo");
            } else
                LOGGER.info("Sprawdzic MultinameInfo");
        }
        return stringsInfo;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getId() {
        return this.cpool.getMultinames().indexOf(this);
    }

    public String String() {
        String wynik = "";
        wynik = wynik + "\n\t---#--- Multiname_info " + Internatiolizer.msgs.getString("Position") + ": " + this.start + "\n\n";

        wynik = wynik + "\tkind: " + MultinameKindType.getMultinameKind(this.kind) + "\n";

        wynik = wynik + "\tdata: " + getData().String() + "\n";

        wynik = wynik + "\n\t---!!--- " + Internatiolizer.msgs.getString("Position") + ":  " + this.end;

        return wynik;
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + getData();
        return wynik;
    }

    public MultinameKind getData() {
        return this.data;
    }

    public byte[] toByteCode() throws IOException {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        if (this.kind != -1) {
            byteDate.write((byte) this.kind);
        }

        if (this.data != null) {
            if ((this.data instanceof MultinameKindQName)) {
                byteDate.write(((MultinameKindQName) this.data).toByteCode());

            } else if ((this.data instanceof MultinameKindRTQName)) {
                byteDate.write(((MultinameKindRTQName) this.data).toByteCode());

            } else if ((this.kind != 17) && (this.kind != 18)) {

                if ((this.data instanceof MultinameKindMultiname)) {
                    byteDate.write(((MultinameKindMultiname) this.data).toByteCode());

                } else if ((this.data instanceof MultinameKindMultinameL)) {
                    byteDate.write(((MultinameKindMultinameL) this.data).toByteCode());
                } else if ((this.data instanceof MultinameKindTypeName)) {
                    try {
                        byteDate.write(((MultinameKindTypeName) this.data).toByteCode());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return byteDate.toByteArray();
    }

    public CpoolInfo getCpool() {
        return this.cpool;
    }
}