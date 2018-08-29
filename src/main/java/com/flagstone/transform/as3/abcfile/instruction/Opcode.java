package com.flagstone.transform.as3.abcfile.instruction;

import java.util.HashMap;
import java.util.Map;

import com.flagstone.transform.as3.abcfile.utils.ABCLogger;

public enum Opcode {
    OP_bkpt(1),
        OP_nop(2),
        OP_throw(3),
        OP_getsuper(4),
        OP_setsuper(5),
        OP_dxns(6),
        OP_dxnslate(7),
        OP_kill(8),
        OP_label(9),
        OP_ifnlt(12),
        OP_ifnle(13),
        OP_ifngt(14),
        OP_ifnge(15),
        OP_jump(16),
        OP_iftrue(17),
        OP_iffalse(18),
        OP_ifeq(19),
        OP_ifne(20),
        OP_iflt(21),
        OP_ifle(22),
        OP_ifgt(23),
        OP_ifge(24),
        OP_ifstricteq(25),
        OP_ifstrictne(26),
        OP_lookupswitch(27),
        OP_pushwith(28),
        OP_popscope(29),
        OP_nextname(30),
        OP_hasnext(31),
        OP_pushnull(32),
        OP_pushundefined(33),
        OP_pushconstant(34),
        OP_nextvalue(35),
        OP_pushbyte(36),
        OP_pushshort(37),
        OP_pushtrue(38),
        OP_pushfalse(39),
        OP_pushnan(40),
        OP_pop(41),
        OP_dup(42),
        OP_swap(43),
        OP_pushstring(44),
        OP_pushint(45),
        OP_pushuint(46),
        OP_pushdouble(47),
        OP_pushscope(48),
        OP_pushnamespace(49),
        OP_hasnext2(50),
        OP_lf32(56),
        OP_lf64(57),
        OP_li16(54),
        OP_li32(55),
        OP_li8(53),
        OP_newfunction(64),
        OP_call(65),
        OP_construct(66),
        OP_callmethod(67),
        OP_callstatic(68),
        OP_callsuper(69),
        OP_callproperty(70),
        OP_returnvoid(71),
        OP_returnvalue(72),
        OP_constructsuper(73),
        OP_constructprop(74),
        OP_callsuperid(75),
        OP_callproplex(76),
        OP_callinterface(77),
        OP_callsupervoid(78),
        OP_callpropvoid(79),
        OP_applytype(83),
        OP_newobject(85),
        OP_newarray(86),
        OP_newactivation(87),
        OP_newclass(88),
        OP_getdescendants(89),
        OP_newcatch(90),
        OP_findpropstrict(93),
        OP_findproperty(94),
        OP_finddef(95),
        OP_getlex(96),
        OP_setproperty(97),
        OP_getlocal(98),
        OP_setlocal(99),
        OP_getglobalscope(100),
        OP_getscopeobject(101),
        OP_getproperty(102),
        OP_getpropertylate(103),
        OP_initproperty(104),
        OP_setpropertylate(105),
        OP_deleteproperty(106),
        OP_deletepropertylate(107),
        OP_getslot(108),
        OP_setslot(109),
        OP_getglobalslot(110),
        OP_setglobalslot(111),
        OP_convert_s(112),
        OP_esc_xelem(113),
        OP_esc_xattr(114),
        OP_convert_i(115),
        OP_convert_u(116),
        OP_convert_d(117),
        OP_convert_b(118),
        OP_convert_o(119),
        OP_checkfilter(120),
        OP_coerce(128),
        OP_coerce_b(129),
        OP_coerce_a(130),
        OP_coerce_i(131),
        OP_coerce_d(132),
        OP_coerce_s(133),
        OP_astype(134),
        OP_astypelate(135),
        OP_coerce_u(136),
        OP_coerce_o(137),
        OP_negate(144),
        OP_increment(145),
        OP_inclocal(146),
        OP_decrement(147),
        OP_declocal(148),
        OP_typeof(149),
        OP_not(150),
        OP_bitnot(151),
        OP_concat(154),
        OP_add_d(155),
        OP_add(160),
        OP_subtract(161),
        OP_multiply(162),
        OP_divide(163),
        OP_modulo(164),
        OP_lshift(165),
        OP_rshift(166),
        OP_urshift(167),
        OP_bitand(168),
        OP_bitor(169),
        OP_bitxor(170),
        OP_equals(171),
        OP_strictequals(172),
        OP_lessthan(173),
        OP_lessequals(174),
        OP_greaterthan(175),
        OP_greaterequals(176),
        OP_instanceof(177),
        OP_istype(178),
        OP_istypelate(179),
        OP_in(180),
        OP_increment_i(192),
        OP_decrement_i(193),
        OP_inclocal_i(194),
        OP_declocal_i(195),
        OP_negate_i(196),
        OP_add_i(197),
        OP_subtract_i(198),
        OP_multiply_i(199),
        OP_getlocal0(208),
        OP_getlocal1(209),
        OP_getlocal2(210),
        OP_getlocal3(211),
        OP_setlocal0(212),
        OP_setlocal1(213),
        OP_setlocal2(214),
        OP_setlocal3(215),
        OP_debug(239),
        OP_debugline(240),
        OP_debugfile(241),
        OP_bkptline(242),
        OP_undefined(0);
    public static final ABCLogger LOGGER = new ABCLogger(Opcode.class);;
    private static Map < Integer, Opcode > opcodeMap;
    private final int opcode;

    static {

        opcodeMap = new HashMap();

        Opcode[] arrayOfOpcode;
        int j = (arrayOfOpcode = values()).length;
        for (int i = 0; i < j; i++) {
            Opcode opcode = arrayOfOpcode[i];
            opcodeMap.put(Integer.valueOf(opcode.opcode), opcode);
        }
    }

    private Opcode(int opcode) {
        this.opcode = opcode;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public static Opcode getOpcode(int opcode) {
        Opcode result = (Opcode) opcodeMap.get(Integer.valueOf(opcode));
        if (result == null) {
            LOGGER.error(String.format("Ca't find such opcode %d", new Object[] {
                Integer.valueOf(opcode)
            }));
            return OP_undefined;
        }
        return result;
    }
}