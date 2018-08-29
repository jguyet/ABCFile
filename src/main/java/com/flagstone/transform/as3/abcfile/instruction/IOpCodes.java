package com.flagstone.transform.as3.abcfile.instruction;

public abstract interface IOpCodes {
    public static final String[] opNames = {
        "OP_0x00       ",
        "bkpt          ",
        "nop           ",
        "throw         ",
        "getsuper      ",
        "setsuper      ",
        "dxns          ",
        "dxnslate      ",
        "kill          ",
        "label         ",
        "OP_0x0A       ",
        "OP_0x0B       ",
        "ifnlt         ",
        "ifnle         ",
        "ifngt         ",
        "ifnge         ",
        "jump          ",
        "iftrue        ",
        "iffalse       ",
        "ifeq          ",
        "ifne          ",
        "iflt          ",
        "ifle          ",
        "ifgt          ",
        "ifge          ",
        "ifstricteq    ",
        "ifstrictne    ",
        "lookupswitch  ",
        "pushwith      ",
        "popscope      ",
        "nextname      ",
        "hasnext       ",
        "pushnull      ",
        "pushundefined ",
        "pushconstant  ",
        "nextvalue     ",
        "pushbyte      ",
        "pushshort     ",
        "pushtrue      ",
        "pushfalse     ",
        "pushnan       ",
        "pop           ",
        "dup           ",
        "swap          ",
        "pushstring    ",
        "pushint       ",
        "pushuint      ",
        "pushdouble    ",
        "pushscope     ",
        "pushnamespace ",
        "hasnext2      ",
        "OP_0x33       ",
        "OP_0x34       ",
        "OP_0x35       ",
        "OP_0x36       ",
        "OP_0x37       ",
        "OP_0x38       ",
        "OP_0x39       ",
        "OP_0x3A       ",
        "OP_0x3B       ",
        "OP_0x3C       ",
        "OP_0x3D       ",
        "OP_0x3E       ",
        "OP_0x3F       ",
        "newfunction   ",
        "call          ",
        "construct     ",
        "callmethod    ",
        "callstatic    ",
        "callsuper     ",
        "callproperty  ",
        "returnvoid    ",
        "returnvalue   ",
        "constructsuper",
        "constructprop ",
        "callsuperid   ",
        "callproplex   ",
        "callinterface ",
        "callsupervoid ",
        "callpropvoid  ",
        "OP_0x50       ",
        "OP_0x51       ",
        "OP_0x52       ",
        "OP_0x53       ",
        "OP_0x54       ",
        "newobject     ",
        "newarray      ",
        "newactivation ",
        "newclass      ",
        "getdescendants",
        "newcatch      ",
        "OP_0x5B       ",
        "OP_0x5C       ",
        "findpropstrict",
        "findproperty  ",
        "finddef       ",
        "getlex        ",
        "setproperty   ",
        "getlocal      ",
        "setlocal      ",
        "getglobalscope",
        "getscopeobject",
        "getproperty   ",
        "OP_0x67       ",
        "initproperty  ",
        "OP_0x69       ",
        "deleteproperty",
        "OP_0x6A       ",
        "getslot       ",
        "setslot       ",
        "getglobalslot ",
        "setglobalslot ",
        "convert_s     ",
        "esc_xelem     ",
        "esc_xattr     ",
        "convert_i     ",
        "convert_u     ",
        "convert_d     ",
        "convert_b     ",
        "convert_o     ",
        "checkfilter   ",
        "OP_0x79       ",
        "OP_0x7A       ",
        "OP_0x7B       ",
        "OP_0x7C       ",
        "OP_0x7D       ",
        "OP_0x7E       ",
        "OP_0x7F       ",
        "coerce        ",
        "coerce_b      ",
        "coerce_a      ",
        "coerce_i      ",
        "coerce_d      ",
        "coerce_s      ",
        "astype        ",
        "astypelate    ",
        "coerce_u      ",
        "coerce_o      ",
        "OP_0x8A       ",
        "OP_0x8B       ",
        "OP_0x8C       ",
        "OP_0x8D       ",
        "OP_0x8E       ",
        "OP_0x8F       ",
        "negate        ",
        "increment     ",
        "inclocal      ",
        "decrement     ",
        "declocal      ",
        "typeof        ",
        "not           ",
        "bitnot        ",
        "OP_0x98       ",
        "OP_0x99       ",
        "concat        ",
        "add_d         ",
        "OP_0x9C       ",
        "OP_0x9D       ",
        "OP_0x9E       ",
        "OP_0x9F       ",
        "add           ",
        "subtract      ",
        "multiply      ",
        "divide        ",
        "modulo        ",
        "lshift        ",
        "rshift        ",
        "urshift       ",
        "bitand        ",
        "bitor         ",
        "bitxor        ",
        "equals        ",
        "strictequals  ",
        "lessthan      ",
        "lessequals    ",
        "greaterthan   ",
        "greaterequals ",
        "instanceof    ",
        "istype        ",
        "istypelate    ",
        "in            ",
        "OP_0xB5       ",
        "OP_0xB6       ",
        "OP_0xB7       ",
        "OP_0xB8       ",
        "OP_0xB9       ",
        "OP_0xBA       ",
        "OP_0xBB       ",
        "OP_0xBC       ",
        "OP_0xBD       ",
        "OP_0xBE       ",
        "OP_0xBF       ",
        "increment_i   ",
        "decrement_i   ",
        "inclocal_i    ",
        "declocal_i    ",
        "negate_i      ",
        "add_i         ",
        "subtract_i    ",
        "multiply_i    ",
        "OP_0xC8       ",
        "OP_0xC9       ",
        "OP_0xCA       ",
        "OP_0xCB       ",
        "OP_0xCC       ",
        "OP_0xCD       ",
        "OP_0xCE       ",
        "OP_0xCF       ",
        "getlocal0     ",
        "getlocal1     ",
        "getlocal2     ",
        "getlocal3     ",
        "setlocal0     ",
        "setlocal1     ",
        "setlocal2     ",
        "setlocal3     ",
        "OP_0xD8       ",
        "OP_0xD9       ",
        "OP_0xDA       ",
        "OP_0xDB       ",
        "OP_0xDC       ",
        "OP_0xDD       ",
        "OP_0xDE       ",
        "OP_0xDF       ",
        "OP_0xE0       ",
        "OP_0xE1       ",
        "OP_0xE2       ",
        "OP_0xE3       ",
        "OP_0xE4       ",
        "OP_0xE5       ",
        "OP_0xE6       ",
        "OP_0xE7       ",
        "OP_0xE8       ",
        "OP_0xE9       ",
        "OP_0xEA       ",
        "OP_0xEB       ",
        "OP_0xEC       ",
        "OP_0xED       ",
        "OP_0xEE       ",
        "debug         ",
        "debugline     ",
        "debugfile     ",
        "bkptline      ",
        "timestamp     ",
        "OP_0xF4       ",
        "verifypass    ",
        "alloc         ",
        "mark          ",
        "wb            ",
        "prologue      ",
        "sendenter     ",
        "doubletoatom  ",
        "sweep         ",
        "codegenop     ",
        "verifyop      ",
        "decode        ",
        "li16          "
    };
    public static final int OP_bkpt = 1;
    public static final int OP_nop = 2;
    public static final int OP_throw = 3;
    public static final int OP_getsuper = 4;
    public static final int OP_setsuper = 5;
    public static final int OP_dxns = 6;
    public static final int OP_dxnslate = 7;
    public static final int OP_kill = 8;
    public static final int OP_label = 9;
    public static final int OP_ifnlt = 12;
    public static final int OP_ifnle = 13;
    public static final int OP_ifngt = 14;
    public static final int OP_ifnge = 15;
    public static final int OP_jump = 16;
    public static final int OP_iftrue = 17;
    public static final int OP_iffalse = 18;
    public static final int OP_ifeq = 19;
    public static final int OP_ifne = 20;
    public static final int OP_iflt = 21;
    public static final int OP_ifle = 22;
    public static final int OP_ifgt = 23;
    public static final int OP_ifge = 24;
    public static final int OP_ifstricteq = 25;
    public static final int OP_ifstrictne = 26;
    public static final int OP_lookupswitch = 27;
    public static final int OP_pushwith = 28;
    public static final int OP_popscope = 29;
    public static final int OP_nextname = 30;
    public static final int OP_hasnext = 31;
    public static final int OP_pushnull = 32;
    public static final int OP_pushundefined = 33;
    public static final int OP_pushconstant = 34;
    public static final int OP_nextvalue = 35;
    public static final int OP_pushbyte = 36;
    public static final int OP_pushshort = 37;
    public static final int OP_pushtrue = 38;
    public static final int OP_pushfalse = 39;
    public static final int OP_pushnan = 40;
    public static final int OP_pop = 41;
    public static final int OP_dup = 42;
    public static final int OP_swap = 43;
    public static final int OP_pushstring = 44;
    public static final int OP_pushint = 45;
    public static final int OP_pushuint = 46;
    public static final int OP_pushdouble = 47;
    public static final int OP_pushscope = 48;
    public static final int OP_pushnamespace = 49;
    public static final int OP_hasnext2 = 50;
    public static final int OP_li16 = 54;
    public static final int OP_newfunction = 64;
    public static final int OP_call = 65;
    public static final int OP_construct = 66;
    public static final int OP_callmethod = 67;
    public static final int OP_callstatic = 68;
    public static final int OP_callsuper = 69;
    public static final int OP_callproperty = 70;
    public static final int OP_returnvoid = 71;
    public static final int OP_returnvalue = 72;
    public static final int OP_constructsuper = 73;
    public static final int OP_constructprop = 74;
    public static final int OP_callsuperid = 75;
    public static final int OP_callproplex = 76;
    public static final int OP_callinterface = 77;
    public static final int OP_callsupervoid = 78;
    public static final int OP_callpropvoid = 79;
    public static final int OP_newobject = 85;
    public static final int OP_newarray = 86;
    public static final int OP_newactivation = 87;
    public static final int OP_newclass = 88;
    public static final int OP_getdescendants = 89;
    public static final int OP_newcatch = 90;
    public static final int OP_findpropstrict = 93;
    public static final int OP_findproperty = 94;
    public static final int OP_finddef = 95;
    public static final int OP_getlex = 96;
    public static final int OP_setproperty = 97;
    public static final int OP_getlocal = 98;
    public static final int OP_setlocal = 99;
    public static final int OP_getglobalscope = 100;
    public static final int OP_getscopeobject = 101;
    public static final int OP_getproperty = 102;
    public static final int OP_getpropertylate = 103;
    public static final int OP_initproperty = 104;
    public static final int OP_setpropertylate = 105;
    public static final int OP_deleteproperty = 106;
    public static final int OP_deletepropertylate = 107;
    public static final int OP_getslot = 108;
    public static final int OP_setslot = 109;
    public static final int OP_getglobalslot = 110;
    public static final int OP_setglobalslot = 111;
    public static final int OP_convert_s = 112;
    public static final int OP_esc_xelem = 113;
    public static final int OP_esc_xattr = 114;
    public static final int OP_convert_i = 115;
    public static final int OP_convert_u = 116;
    public static final int OP_convert_d = 117;
    public static final int OP_convert_b = 118;
    public static final int OP_convert_o = 119;
    public static final int OP_checkfilter = 120;
    public static final int OP_coerce = 128;
    public static final int OP_coerce_b = 129;
    public static final int OP_coerce_a = 130;
    public static final int OP_coerce_i = 131;
    public static final int OP_coerce_d = 132;
    public static final int OP_coerce_s = 133;
    public static final int OP_astype = 134;
    public static final int OP_astypelate = 135;
    public static final int OP_coerce_u = 136;
    public static final int OP_coerce_o = 137;
    public static final int OP_negate = 144;
    public static final int OP_increment = 145;
    public static final int OP_inclocal = 146;
    public static final int OP_decrement = 147;
    public static final int OP_declocal = 148;
    public static final int OP_typeof = 149;
    public static final int OP_not = 150;
    public static final int OP_bitnot = 151;
    public static final int OP_concat = 154;
    public static final int OP_add_d = 155;
    public static final int OP_add = 160;
    public static final int OP_subtract = 161;
    public static final int OP_multiply = 162;
    public static final int OP_divide = 163;
    public static final int OP_modulo = 164;
    public static final int OP_lshift = 165;
    public static final int OP_rshift = 166;
    public static final int OP_urshift = 167;
    public static final int OP_bitand = 168;
    public static final int OP_bitor = 169;
    public static final int OP_bitxor = 170;
    public static final int OP_equals = 171;
    public static final int OP_strictequals = 172;
    public static final int OP_lessthan = 173;
    public static final int OP_lessequals = 174;
    public static final int OP_greaterthan = 175;
    public static final int OP_greaterequals = 176;
    public static final int OP_instanceof = 177;
    public static final int OP_istype = 178;
    public static final int OP_istypelate = 179;
    public static final int OP_in = 180;
    public static final int OP_increment_i = 192;
    public static final int OP_decrement_i = 193;
    public static final int OP_inclocal_i = 194;
    public static final int OP_declocal_i = 195;
    public static final int OP_negate_i = 196;
    public static final int OP_add_i = 197;
    public static final int OP_subtract_i = 198;
    public static final int OP_multiply_i = 199;
    public static final int OP_getlocal0 = 208;
    public static final int OP_getlocal1 = 209;
    public static final int OP_getlocal2 = 210;
    public static final int OP_getlocal3 = 211;
    public static final int OP_setlocal0 = 212;
    public static final int OP_setlocal1 = 213;
    public static final int OP_setlocal2 = 214;
    public static final int OP_setlocal3 = 215;
    public static final int OP_debug = 239;
    public static final int OP_debugline = 240;
    public static final int OP_debugfile = 241;
    public static final int OP_bkptline = 242;
}