package com.flagstone.transform.as3.abcfile.structures;

import java.io.ByteArrayOutputStream;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.instruction.IOpCodes;
import com.flagstone.transform.as3.abcfile.instructions.container.ContainerInstructionV2;
import com.flagstone.transform.as3.abcfile.instructions.container.IContainerInstruction;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class MethodBodyInfo
implements IOpCodes {
    public static final ABCLogger LOGGER = new ABCLogger(MethodBodyInfo.class);

    private int id;

    public static int method_body_count;
    private int codeBegin = 0;
    private int codeEnd = 0;

    public AbcFile abc;

    private int start;

    private int end;
    private int method;
    private int max_stack;
    private int local_count;
    private int init_scope_depth;
    private int max_scope_depth;
    private int code_length;
    private byte[] code = null;
    public HashMap < Integer, Integer > EN_InstructionMapowanie = new HashMap();

    private int exception_count;

    private ArrayList < ExceptionInfo > exception_info = new ArrayList();
    private int trait_count;
    private ArrayList < TraitInfo > tinfo = new ArrayList();

    public MethodBodyInfo(AbcFile abc, int method, int max_stack, int local_count, int init_scope_depth, int max_scope_depth, int code_length, byte[] code, int exception_count, ArrayList < ExceptionInfo > exception_info, int trait_count, ArrayList < TraitInfo > tinfo) {
        this.abc = abc;
        abc.ENmethod_body_info.add(this);
        this.id = (abc.ENmethod_body_info.size() - 1);

        this.method = method;
        this.max_stack = max_stack;
        this.local_count = local_count;
        this.init_scope_depth = init_scope_depth;
        this.max_scope_depth = max_scope_depth;
        this.code_length = code_length;
        this.code = code;
        this.exception_count = exception_count;
        if (exception_info != null)
            this.exception_info = exception_info;
        this.trait_count = trait_count;
        if (tinfo != null)
            this.tinfo = tinfo;
        method_body_count += 1;
    }

    private MethodBodyInfo(AbcFile abc) {
        this.abc = abc;
        abc.ENmethod_body_info.add(this);
        this.id = (abc.ENmethod_body_info.size() - 1);
    }

    public static void method_body_info(ByteBufferFlash bbuf, int position, CpoolInfo cpool, AbcFile abc) {
        try {
            bbuf.position(position);

            method_body_count = bbuf.unsigned30int();
            if (method_body_count == 0) {
                LOGGER.info("Warning method body_count is set 0");
                method_body_count = MethodInfo.method_count;
            }

            for (int i = 0; i < method_body_count; i++) {
                MethodBodyInfo method_body_info = new MethodBodyInfo(abc);

                method_body_info.start = bbuf.position();

                method_body_info.method = bbuf.unsigned30int();

                method_body_info.max_stack = bbuf.unsigned30int();

                method_body_info.local_count = bbuf.unsigned30int();

                method_body_info.init_scope_depth = bbuf.unsigned30int();

                method_body_info.max_scope_depth = bbuf.unsigned30int();

                method_body_info.code_length = bbuf.unsigned30int();

                method_body_info.code = Arrays.copyOfRange(bbuf.array(), bbuf.position(), bbuf.position() + method_body_info.code_length);

                method_body_info.codeBegin = bbuf.position();

                method_body_info.codeEnd = (bbuf.position() + method_body_info.code_length);

                bbuf.position(method_body_info.codeEnd);

                method_body_info.exception_count = bbuf.unsigned30int();

                for (int i3 = 0; i3 < method_body_info.exception_count; i3++) {
                    method_body_info.exception_info.add(new ExceptionInfo(bbuf, abc));
                }
                method_body_info.trait_count = bbuf.unsigned30int();

                for (int i4 = 0; i4 < method_body_info.trait_count; i4++) {
                    method_body_info.tinfo.add(new TraitInfo(abc, bbuf, TraitInfo.Type.METHOD));
                }
                method_body_info.end = (bbuf.position() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean ifJump(int opcode) {
        if ((opcode == 12) ||
            (opcode == 13) ||
            (opcode == 14) ||
            (opcode == 15) ||
            (opcode == 16) ||
            (opcode == 17) ||
            (opcode == 18) ||
            (opcode == 19) ||
            (opcode == 20) ||
            (opcode == 21) ||
            (opcode == 22) ||
            (opcode == 23) ||
            (opcode == 24) ||
            (opcode == 25) ||
            (opcode == 26)) {
            return true;
        }
        return false;
    }

    public String czytajKod(int pocz, int kon, ByteBufferFlash swfBbuf) {
        swfBbuf.position(pocz);
        StringBuffer s = new StringBuffer();

        while (swfBbuf.position() < kon) {
            ByteBufferFlash bbuf = swfBbuf;

            s.append(bbuf.position() + "\t");
            int opcode = bbuf.unsignedByte();

            s.append(opNames[opcode]);
            s.append(opNames[opcode].length() < 8 ? "\t\t" : "\t");

            switch (opcode) {

                case 44:
                case 241:
                    s.append('"' + this.abc.getConstant_pool().getString(bbuf.unsigned32int()).replace("\n", "\\n").replace("\t", "\\t") + '"');
                    break;
                case 49:
                    s.append(" " + this.abc.getConstant_pool().getNamespace(bbuf.unsigned32int()));
                    break;
                case 45:
                    int i = this.abc.getConstant_pool().getInts(bbuf.unsigned32int());
                    s.append(i + "\t// 0x" + Integer.toHexString(i));
                    break;
                case 46:
                    int u = ((Integer) this.abc.getConstant_pool().uints.get(bbuf.unsigned32int())).intValue();
                    s.append(u + "\t// 0x" + Integer.toHexString(u));
                    break;
                case 47:
                    s.append(" " + this.abc.getConstant_pool().doubles.get(bbuf.unsigned32int()));
                    break;
                case 4:
                case 5:
                case 89:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 102:
                case 104:
                case 106:
                case 128:
                case 134:
                case 178:
                    s.append(" " + this.abc.getConstant_pool().getMultiname(bbuf.unsigned32int()));

                    break;
                case 69:
                case 70:
                case 74:
                case 76:
                case 78:
                case 79:
                    s.append(" " + this.abc.getConstant_pool().getMultiname(bbuf.unsigned32int()));

                    s.append(" (" + bbuf.unsigned32int() + ")");
                    break;
                case 64:
                    int method_id = bbuf.unsigned32int();
                    s.append((String) this.abc.methodsNames.get(Integer.valueOf(method_id)));
                    break;

                case 68:
                    s.append((String) this.abc.methodsNames.get(Integer.valueOf(bbuf.unsigned32int())));
                    s.append(" (" + bbuf.unsigned32int() + ")");
                    break;
                case 88:
                    s.append(" " + (String) this.abc.ClassNames.get(Integer.valueOf(bbuf.unsigned32int())));
                    break;
                case 27:
                    int pos = bbuf.position() - 1;
                    int target = pos + bbuf.readS24();
                    int maxindex = bbuf.unsigned32int();

                    s.append("default:" + target);
                    s.append(" maxcase:" + maxindex);
                    for (int i1 = 0; i1 <= maxindex; i1++) {
                        target = pos + bbuf.readS24();

                        s.append(" " + target);
                    }
                    break;
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    int offset = bbuf.readS24();
                    int target1 = bbuf.position() + offset;

                    s.append(target1);

                    break;
                case 8:
                case 37:
                case 90:
                case 98:
                case 99:
                case 108:
                case 109:
                case 110:
                case 111:
                case 146:
                case 148:
                case 194:
                case 195:
                case 240:
                    s.append(bbuf.unsigned32int());
                    break;
                case 239:
                    s.append(bbuf.unsignedByte());
                    s.append(" " + bbuf.unsigned32int());
                    s.append(" " + bbuf.unsignedByte());
                    s.append(" " + bbuf.unsigned32int());
                    break;
                case 85:
                    s.append("{" + bbuf.unsigned32int() + "}");
                    break;
                case 86:
                    s.append("[" + bbuf.unsigned32int() + "]");
                    break;
                case 65:
                case 66:
                case 73:
                    s.append("(" + bbuf.unsigned32int() + ")");
                    break;
                case 36:
                case 101:
                    s.append(bbuf.unsignedByte());
                    break;
                case 50:
                    s.append(bbuf.unsigned32int() + " " + bbuf.unsigned32int());
                    break;
                case 54:
                    s.append(bbuf.unsigned32int());
                    break;
            }

            s.append("\n");
        }

        return s.toString();
    }

    public ArrayList < String > czytajKodZmienneLokalne() {
        ByteBufferFlash bbuf = ByteBufferFlash.allocate(this.code.length);
        bbuf.put(this.code);
        bbuf.rewind();
        bbuf.order(ByteOrder.LITTLE_ENDIAN);

        ArrayList < String > asPush = new ArrayList();

        StringBuffer s = new StringBuffer();

        while (bbuf.position() < bbuf.capacity()) {

            s.append(bbuf.position() + "\t");
            int opcode = bbuf.unsignedByte();

            s.append(opNames[opcode]);
            s.append(opNames[opcode].length() < 8 ? "\t\t" : "\t");

            switch (opcode) {

                case 44:
                case 241:
                    asPush.add(this.abc.getConstant_pool().getString(bbuf.unsigned32int()).replace("\n", "\\n").replace("\t", "\\t"));
                    break;
                case 49:
                    s.append(" " + this.abc.getConstant_pool().getNamespace(bbuf.unsigned32int()));
                    break;
                case 45:
                    int i = this.abc.getConstant_pool().getInts(bbuf.unsigned32int());
                    s.append(i + "\t// 0x" + Integer.toHexString(i));
                    break;
                case 46:
                    int u = ((Integer) this.abc.getConstant_pool().uints.get(bbuf.unsigned32int())).intValue();
                    s.append(u + "\t// 0x" + Integer.toHexString(u));
                    break;
                case 47:
                    s.append(" " + this.abc.getConstant_pool().doubles.get(bbuf.unsigned32int()));
                    break;
                case 4:
                case 5:
                case 89:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 102:
                case 104:
                case 106:
                case 128:
                case 134:
                case 178:
                    s.append(" " + this.abc.getConstant_pool().getMultiname(bbuf.unsigned32int()));

                    break;
                case 69:
                case 70:
                case 74:
                case 76:
                case 78:
                case 79:
                    s.append(" " + this.abc.getConstant_pool().getMultiname(bbuf.unsigned32int()));

                    s.append(" (" + bbuf.unsigned32int() + ")");
                    break;
                case 64:
                    int method_id = bbuf.unsigned32int();
                    s.append((String) this.abc.methodsNames.get(Integer.valueOf(method_id)));
                    break;

                case 68:
                    s.append((String) this.abc.methodsNames.get(Integer.valueOf(bbuf.unsigned32int())));
                    s.append(" (" + bbuf.unsigned32int() + ")");
                    break;
                case 88:
                    s.append(" " + (String) this.abc.ClassNames.get(Integer.valueOf(bbuf.unsigned32int())));
                    break;
                case 27:
                    int pos = bbuf.position() - 1;
                    int target = pos + bbuf.readS24();
                    int maxindex = bbuf.unsigned32int();

                    s.append("default:" + target);
                    s.append(" maxcase:" + maxindex);
                    for (int i1 = 0; i1 <= maxindex; i1++) {
                        target = pos + bbuf.readS24();

                        s.append(" " + target);
                    }
                    break;
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    int offset = bbuf.readS24();
                    int target1 = bbuf.position() + offset;

                    s.append(target1);

                    break;
                case 8:
                case 37:
                case 90:
                case 98:
                case 99:
                case 108:
                case 109:
                case 110:
                case 111:
                case 146:
                case 148:
                case 194:
                case 195:
                case 240:
                    s.append(bbuf.unsigned32int());
                    break;
                case 239:
                    s.append(bbuf.unsignedByte());
                    s.append(" " + bbuf.unsigned32int());
                    s.append(" " + bbuf.unsignedByte());
                    s.append(" " + bbuf.unsigned32int());
                    break;
                case 85:
                    s.append("{" + bbuf.unsigned32int() + "}");
                    break;
                case 86:
                    s.append("[" + bbuf.unsigned32int() + "]");
                    break;
                case 65:
                case 66:
                case 73:
                    s.append("(" + bbuf.unsigned32int() + ")");
                    break;
                case 36:
                case 101:
                    s.append(bbuf.unsignedByte());
                    break;
                case 50:
                    s.append(bbuf.unsigned32int() + " " + bbuf.unsigned32int());
            }

            s.append("\n");
        }

        return asPush;
    }

    public byte[] toByteCode()
    throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        byteDate.write(ByteBufferFlash.getUI32B(this.method));

        byteDate.write(ByteBufferFlash.getUI32B(this.max_stack));

        byteDate.write(ByteBufferFlash.getUI32B(this.local_count));

        byteDate.write(ByteBufferFlash.getUI32B(this.init_scope_depth));

        byteDate.write(ByteBufferFlash.getUI32B(this.max_scope_depth));

        byteDate.write(ByteBufferFlash.getUI32B(this.code_length));

        ByteBufferFlash bbc = ByteBufferFlash.allocate(this.code.length);
        bbc.order(ByteOrder.LITTLE_ENDIAN);
        bbc.put(this.code);
        bbc.rewind();

        IContainerInstruction container = null;
        try {
            container = ContainerInstructionV2.readCode(bbc, this.exception_info, this, this.abc);
        } catch (Exception e) {
            LOGGER.error("Can't parse AS3 container in MethodBody", e);
        }
        if (container != null) {
            byteDate.write(container.getBytes());
        } else {
            byteDate.write(this.code);
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.exception_count));

        for (ExceptionInfo exc: this.exception_info) {
            byteDate.write(exc.toByteCode());
        }

        byteDate.write(ByteBufferFlash.getUI32B(this.trait_count));

        for (TraitInfo trait: this.tinfo) {
            byteDate.write(trait.toByteCode());
        }

        return byteDate.toByteArray();
    }

    public int getId() {
        return this.id;
    }

    public String getCode_string(ByteBufferFlash swfBbuf) {
        if ((this.codeBegin == 0) && (this.codeEnd == 0)) return "KOD NIE WKOMPILOWANY W SWF";
        return czytajKod(this.codeBegin, this.codeEnd, swfBbuf);
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getMethod() {
        return this.method;
    }

    public int getMax_stack() {
        return this.max_stack;
    }

    public int getLocal_count() {
        return this.local_count;
    }

    public int getInit_scope_depth() {
        return this.init_scope_depth;
    }

    public int getMax_scope_depth() {
        return this.max_scope_depth;
    }

    public int getCode_length() {
        return this.code_length;
    }

    public byte[] getCode() {
        return this.code;
    }

    public int getException_count() {
        return this.exception_count;
    }

    public ArrayList < ExceptionInfo > getException_info() {
        return this.exception_info;
    }

    public int getTrait_count() {
        return this.trait_count;
    }

    public ArrayList < TraitInfo > getTinfo() {
        return this.tinfo;
    }

    public void setEnd(int end) {
        this.end = end;
        ByteBufferFlash.out("ok");
    }

    public void setMethod(int method) {
        this.method = method;
        ByteBufferFlash.out("ok");
    }

    public void setMax_stack(int max_stack) {
        this.max_stack = max_stack;
        ByteBufferFlash.out("ok");
    }

    public void setLocal_count(int local_count) {
        this.local_count = local_count;
        ByteBufferFlash.out("ok");
    }

    public void setInit_scope_depth(int init_scope_depth) {
        this.init_scope_depth = init_scope_depth;
        ByteBufferFlash.out("ok");
    }

    public void setMax_scope_depth(int max_scope_depth) {
        this.max_scope_depth = max_scope_depth;
        ByteBufferFlash.out("ok");
    }

    public void setCode_length(int code_length) {
        this.code_length = code_length;
        ByteBufferFlash.out("ok");
    }

    public void setCode(byte[] code) {
        this.code = code;
        this.code_length = code.length;
    }

    public void setException_count(int exception_count) {
        this.exception_count = exception_count;
        ByteBufferFlash.out("ok");
    }

    public void setException_info(ArrayList < ExceptionInfo > exception_info) {
        this.exception_info = exception_info;
        ByteBufferFlash.out("ok");
    }

    public void setTrait_count(int trait_count) {
        this.trait_count = trait_count;
        ByteBufferFlash.out("ok");
    }

    public void addException(ExceptionInfo ex) {
        this.exception_info.add(ex);
        this.exception_count += 1;
    }

    public void addTrait(TraitInfo tr) {
        this.tinfo.add(tr);
        this.trait_count += 1;
    }

    public void removeException(int id) {
        this.exception_info.remove(id);
        this.exception_count -= 1;
    }

    public void removeTrait(int id) {
        this.tinfo.remove(id);
        this.trait_count -= 1;
    }

    public void changeException(int id, ExceptionInfo ex) {
        this.exception_info.set(id, ex);
    }

    public void changeTrait(int id, TraitInfo tr) {
        this.tinfo.set(id, tr);
    }

    public String String() {
        StringBuffer wynik = new StringBuffer();

        wynik.append(">" + getStart() + "\n");

        wynik.append("method: " + getMethod() + "\n");

        wynik.append("max_stack: " + getMax_stack() + "\n");

        wynik.append("local_count: " + getLocal_count() + "\n");

        wynik.append("init_scope_depth: " + getInit_scope_depth() + "\n");

        wynik.append("max_scope_depth: " + getMax_scope_depth() + "\n");

        wynik.append("code_length: " + getCode_length() + "\n");

        wynik.append("exception_count: " + getException_count() + "\n");

        wynik.append("trait_count: " + getTrait_count() + "\n");

        wynik.append("<" + getEnd() + "\n");

        return wynik.toString();
    }

    public String toString() {
        String wynik = "";
        wynik = wynik + "method_body_info id " + this.id + " " + this.start + " - " + this.end;
        return wynik;
    }

    public String toString2() {
        String wynik = "";
        wynik = wynik + "method_body_info";
        return wynik;
    }
}