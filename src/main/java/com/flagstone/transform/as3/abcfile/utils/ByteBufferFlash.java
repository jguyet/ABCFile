package com.flagstone.transform.as3.abcfile.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ByteBufferFlash {
    public static final ABCLogger LOGGER = new ABCLogger(ByteBufferFlash.class);

    public static int bitPos;
    public static int bitBuf;
    private ByteBuffer bbuf;
    private static final String HEXES = "0123456789ABCDEF";

    private ByteBufferFlash(int capacity) {
        this.bbuf = ByteBuffer.allocate(capacity);
        order(ByteOrder.LITTLE_ENDIAN);
    }

    private ByteBufferFlash(ByteBuffer bbuf) {
        this.bbuf = bbuf;
        order(ByteOrder.LITTLE_ENDIAN);
    }

    public static ByteBufferFlash wrap(byte[] array) {
        return new ByteBufferFlash(ByteBuffer.wrap(array));
    }

    public static ByteBufferFlash allocate(int capacity) {
        return new ByteBufferFlash(capacity);
    }

    public byte[] array() {
        return this.bbuf.array();
    }

    public void remove(int pos, int size) {
        ByteBuffer bbufTmp = ByteBuffer.allocate(this.bbuf.capacity() - size);
        bbufTmp.order(ByteOrder.LITTLE_ENDIAN);

        this.bbuf.position(0);
        for (int i = 0; i < this.bbuf.capacity(); i++) {
            if ((i < pos) || (i >= pos + size)) {
                byte b = this.bbuf.get(i);
                bbufTmp.put(b);
            } else {
                LOGGER.info("byte remove  " + this.bbuf.get(i));
            }
        }

        this.bbuf = bbufTmp;
    }

    public void position(int newPosition) {
        this.bbuf.position(newPosition);
    }

    public int position() {
        return this.bbuf.position();
    }

    public int capacity() {
        return this.bbuf.capacity();
    }

    public void rewind() {
        this.bbuf.rewind();
    }

    public void order(ByteOrder bo) {
        this.bbuf.order(bo);
    }

    public ByteOrder order() {
        return this.bbuf.order();
    }

    public CharBuffer asCharBuffer() {
        return this.bbuf.asCharBuffer();
    }

    public DoubleBuffer asDoubleBuffer() {
        return this.bbuf.asDoubleBuffer();
    }

    public FloatBuffer asFloatBuffer() {
        return this.bbuf.asFloatBuffer();
    }

    public IntBuffer asIntBuffer() {
        return this.bbuf.asIntBuffer();
    }

    public LongBuffer asLongBuffer() {
        return this.bbuf.asLongBuffer();
    }

    public ByteBuffer asReadOnlyBuffer() {
        return this.bbuf.asReadOnlyBuffer();
    }

    public ShortBuffer asShortBuffer() {
        return this.bbuf.asShortBuffer();
    }

    public ByteBuffer compact() {
        return this.bbuf.compact();
    }

    public ByteBuffer duplicate() {
        return this.bbuf.duplicate();
    }

    public ByteBuffer get(byte[] dst) {
        return this.bbuf.get(dst);
    }

    public byte get() {
        return this.bbuf.get();
    }

    public byte get(int index) {
        return this.bbuf.get(index);
    }

    public ByteBuffer get(byte[] dst, int offset, int length) {
        return this.bbuf.get(dst, offset, length);
    }

    public char getChar() {
        return this.bbuf.getChar();
    }

    public char getChar(int index) {
        return this.bbuf.getChar(index);
    }

    public double getDouble() {
        return this.bbuf.getDouble();
    }

    public double getDouble(int index) {
        return this.bbuf.getDouble(index);
    }

    public float getFloat() {
        return this.bbuf.getFloat();
    }

    public float getFloat(int index) {
        return this.bbuf.getFloat(index);
    }

    public int getInt() {
        return this.bbuf.getInt();
    }

    public int getInt(int index) {
        return this.bbuf.getInt(index);
    }

    public long getLong() {
        return this.bbuf.getLong();
    }

    public long getLong(int index) {
        return this.bbuf.getLong(index);
    }

    public short getShort() {
        return this.bbuf.getShort();
    }

    public short getShort(int index) {
        return this.bbuf.getShort(index);
    }

    public boolean isDirect() {
        return this.bbuf.isDirect();
    }

    public ByteBuffer put(ByteBufferFlash src) {
        return this.bbuf.put(src.bbuf);
    }

    public ByteBuffer put(ByteBuffer src) {
        return this.bbuf.put(src);
    }

    public ByteBuffer put(byte[] src) {
        return this.bbuf.put(src);
    }

    public ByteBuffer put(byte b) {
        return this.bbuf.put(b);
    }

    public ByteBuffer put(int index, byte b) {
        return this.bbuf.put(index, b);
    }

    public ByteBuffer put(byte[] src, int offset, int length) {
        return this.bbuf.put(src, offset, length);
    }

    public ByteBuffer putChar(char value) {
        return this.bbuf.putChar(value);
    }

    public ByteBuffer putChar(int index, char value) {
        return this.bbuf.putChar(index, value);
    }

    public ByteBuffer putDouble(double value) {
        return this.bbuf.putDouble(value);
    }

    public ByteBuffer putDouble(int index, double value) {
        return this.bbuf.putDouble(index, value);
    }

    public ByteBuffer putFloat(float value) {
        return this.bbuf.putFloat(value);
    }

    public ByteBuffer putFloat(int index, float value) {
        return this.bbuf.putFloat(index, value);
    }

    public ByteBuffer putInt(int value) {
        return this.bbuf.putInt(value);
    }

    public ByteBuffer putInt(int index, int value) {
        return this.bbuf.putInt(index, value);
    }

    public ByteBuffer putLong(long value) {
        return this.bbuf.putLong(value);
    }

    public ByteBuffer putLong(int index, long value) {
        return this.bbuf.putLong(index, value);
    }

    public ByteBuffer putShort(short value) {
        return this.bbuf.putShort(value);
    }

    public ByteBuffer putShort(int index, short value) {
        return this.bbuf.putShort(index, value);
    }

    public ByteBuffer slice() {
        return this.bbuf.slice();
    }

    public boolean isReadOnly() {
        return this.bbuf.isReadOnly();
    }

    public static byte[] intToShortBytes(int arg) {
        ByteBuffer bbuf = ByteBuffer.allocate(3);
        bbuf.order(ByteOrder.LITTLE_ENDIAN);
        bbuf.position(0);
        bbuf.putShort((short) arg);
        bbuf.position(0);
        return bbuf.array();
    }

    public static int unsignedInt(int b) {
        return b & 0xFFFFFFFF;
    }

    public int unsignedInt() {
        return this.bbuf.getInt() & 0xFFFFFFFF;
    }

    public static long unsignedInt2(int b) {
        return b & 0xFFFFFFFF;
    }

    public long unsignedInt2() {
        return this.bbuf.getInt() & 0xFFFFFFFF;
    }

    public static int unsignedByte(byte b) {
        return b & 0xFF;
    }

    public int unsignedByte() {
        return this.bbuf.get() & 0xFF;
    }

    public static short unsignedShort(short b) {
        return (short)(b & 0xFFFF);
    }

    public short unsignedShort() {
        return (short)(this.bbuf.getShort() & 0xFFFF);
    }

    public static int unsignedShort2(short b) {
        return b & 0xFFFF;
    }

    public int unsignedShort2() {
        return this.bbuf.getShort() & 0xFFFF;
    }

    public int unsigned30int() {
        return unsigned32int();
    }

    public int bbuf() {
        int result = unsignedByte(this.bbuf.get());

        if ((result & 0x80) <= 0)
            return result;
        result = result & 0x7F | unsignedByte(this.bbuf.get()) << 7;
        if ((result & 0x4000) <= 0)
            return result;
        result = result & 0x3FFF | unsignedByte(this.bbuf.get()) << 14;
        if ((result & 0x200000) <= 0)
            return result;
        result = result & 0x1FFFFF | unsignedByte(this.bbuf.get()) << 21;
        if ((result & 0x10000000) <= 0)
            return result;
        return result & 0xFFFFFFF | unsignedByte(this.bbuf.get()) << 28;
    }

    public int readS24() {
        int b = unsignedByte(this.bbuf.get());
        b |= unsignedByte(this.bbuf.get()) << 8;
        b |= this.bbuf.get() << 16;
        return b;
    }

    public static byte[] getS24(int value) {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        ByteBuffer bbuf = ByteBuffer.allocate(4);
        bbuf.order(ByteOrder.LITTLE_ENDIAN);
        bbuf.putInt(value);
        bbuf.position(0);
        byte[] res = new byte[3];
        bbuf.get(res);

        byteDate.write(res[0]);
        byteDate.write(res[1]);
        byteDate.write(res[2]);

        return byteDate.toByteArray();
    }

    public static void syncBits() {
        bitPos = 0;
    }

    public int readSBits(int numBits) {
        if (numBits > 32) {
            throw new Error("Number of bits > 32");
        }
        int num = readUBits(numBits);
        int shift = 32 - numBits;

        num = num << shift >> shift;
        return num;
    }

    public int readUBits(int numBits) {
        if (numBits == 0) {
            return 0;
        }
        int bitsLeft = numBits;
        int result = 0;

        if (bitPos == 0) {
            bitBuf = unsignedByte(this.bbuf.get());
            bitPos = 8;
        }
        int shift;
        for (;;) {
            shift = bitsLeft - bitPos;

            if (shift <= 0)
                break;
            result |= bitBuf << shift;
            bitsLeft -= bitPos;

            bitBuf = unsignedByte(this.bbuf.get());
            bitPos = 8;
        }

        result |= bitBuf >> -shift;
        bitPos -= bitsLeft;
        bitBuf &= 255 >> 8 - bitPos;

        return result;
    }

    public int signed32int() {
        int result = this.bbuf.get();

        if ((result & 0x80) <= 0) {
            return result;
        }
        result = result & 0x7F | this.bbuf.get() << 7;

        if ((result & 0x4000) <= 0) {
            return result;
        }
        result = result & 0x3FFF | this.bbuf.get() << 14;

        if ((result & 0x200000) <= 0) {
            return result;
        }
        result = result & 0x1FFFFF | this.bbuf.get() << 21;

        if ((result & 0x10000000) <= 0) {
            return result;
        }

        return result & 0xFFFFFFF | this.bbuf.get() << 28;
    }

    public int unsigned32int() {
        int result = unsignedByte(this.bbuf.get());
        if ((result & 0x80) == 0)
            return result;
        result = result & 0x7F | unsignedByte(this.bbuf.get()) << 7;
        if ((result & 0x4000) == 0)
            return result;
        result = result & 0x3FFF | unsignedByte(this.bbuf.get()) << 14;
        if ((result & 0x200000) == 0)
            return result;
        result = result & 0x1FFFFF | unsignedByte(this.bbuf.get()) << 21;
        if ((result & 0x10000000) == 0)
            return result;
        return result & 0xFFFFFFF | unsignedByte(this.bbuf.get()) << 28;
    }

    public int readSI32()
    throws IOException {

        int i;

        if (this.bbuf.capacity() - this.bbuf.position() >= 4) {
            i = this.bbuf.get() & 0xFF | (this.bbuf.get() & 0xFF) << 8 | (this.bbuf.get() & 0xFF) << 16 | this.bbuf.get() << 24;
        } else {
            if (this.bbuf != null) {
                i = this.bbuf.get() | this.bbuf.get() << 8 | this.bbuf.get() << 16 | this.bbuf.get() << 24;
            } else
                i = -1;
        }
        return i;
    }

    public long readUI32() throws IOException {
        long i = readSI32() & 0xFFFFFFFF;
        return i;
    }

    public static byte[] getSI32B2(int i) {
        int tmp = i;
        if (i < 0) {
            i *= -1;
        }
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        if (i < 128) {
            if (tmp < 0) {
                int i1 = (i ^ 0xFFFFFFFF) + 1 | 0x80;
                byteDate.write(i1);
                byteDate.write(255);
                byteDate.write(255);
                byteDate.write(255);
                byteDate.write(15);
                return byteDate.toByteArray();
            }
            byteDate.write(i);
            return byteDate.toByteArray();
        }
        if ((i < 16384) && (i >= 128)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 ^= 0xFFFFFFFF;
                byteDate.write((byte) i1);
                byteDate.write((byte) i2);
                byteDate.write(-1);
                byteDate.write(-1);
                byteDate.write(15);

                return byteDate.toByteArray();
            }

            byteDate.write((byte) i1);
            byteDate.write((byte) i2);
            return byteDate.toByteArray();
        }
        if ((i < 2097152) && (i >= 16384)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 = i2 ^ 0xFFFFFFFF | 0x80;
                i3 ^= 0xFFFFFFFF;
                byteDate.write((byte) i1);
                byteDate.write((byte) i2);
                byteDate.write((byte) i3);
                byteDate.write(-1);
                byteDate.write(15);
                return byteDate.toByteArray();
            }

            byteDate.write((byte) i1);
            byteDate.write((byte) i2);
            byteDate.write((byte) i3);
            return byteDate.toByteArray();
        }
        if ((i < 268435456) && (i >= 2097152)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14 | 0x80;
            int i4 = i >> 21;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 = i2 ^ 0xFFFFFFFF | 0x80;
                i3 = i3 ^ 0xFFFFFFFF | 0x80;
                i4 ^= 0xFFFFFFFF;
                byteDate.write((byte) i1);
                byteDate.write((byte) i2);
                byteDate.write((byte) i3);
                byteDate.write((byte) i4);
                byteDate.write(15);

                return byteDate.toByteArray();
            }
            byteDate.write((byte) i1);
            byteDate.write((byte) i2);
            byteDate.write((byte) i3);
            byteDate.write((byte) i4);

            return byteDate.toByteArray();
        }

        int i1 = i & 0x7F | 0x80;
        int i2 = i >> 7 | 0x80;
        int i3 = i >> 14 | 0x80;
        int i4 = i >> 21 | 0x80;
        int i5 = i >> 28;
        if (tmp < 0) {
            i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
            i2 = i2 ^ 0xFFFFFFFF | 0x80;
            i3 = i3 ^ 0xFFFFFFFF | 0x80;
            i4 = i4 ^ 0xFFFFFFFF | 0x80;
            i5 ^= 0xFFFFFFFF;
            byteDate.write((byte) i1);
            byteDate.write((byte) i2);
            byteDate.write((byte) i3);
            byteDate.write((byte) i4);
            byteDate.write((byte) i5 & 0xF);
            return byteDate.toByteArray();
        }
        byteDate.write((byte) i1);
        byteDate.write((byte) i2);
        byteDate.write((byte) i3);
        byteDate.write((byte) i4);
        byteDate.write((byte) i5);
        return byteDate.toByteArray();
    }

    public static byte[] getUI32B(long v) {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        if ((v < 128L) && (v > -1L)) {
            byteDate.write((byte)(int) v);
        } else if ((v < 16384L) && (v > -1L)) {
            byteDate.write((byte)(int)(v & 0x7F | 0x80));
            byteDate.write((byte)(int)(v >> 7 & 0x7F));
        } else if ((v < 2097152L) && (v > -1L)) {
            byteDate.write((byte)(int)(v & 0x7F | 0x80));
            byteDate.write((byte)(int)(v >> 7 | 0x80));
            byteDate.write((byte)(int)(v >> 14 & 0x7F));
        } else if ((v < 268435456L) && (v > -1L)) {
            byteDate.write((byte)(int)(v & 0x7F | 0x80));
            byteDate.write((byte)(int)(v >> 7 | 0x80));
            byteDate.write((byte)(int)(v >> 14 | 0x80));
            byteDate.write((byte)(int)(v >> 21 & 0x7F));
        } else {
            byteDate.write((byte)(int)(v & 0x7F | 0x80));
            byteDate.write((byte)(int)(v >> 7 | 0x80));
            byteDate.write((byte)(int)(v >> 14 | 0x80));
            byteDate.write((byte)(int)(v >> 21 | 0x80));
            byteDate.write((byte)(int)(v >> 28 & 0xF));
        }

        return byteDate.toByteArray();
    }

    public static byte[] getSI32B(int i) {
        int tmp = i;
        if (i < 0) {
            i *= -1;
        }
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        if (i <= 127) {
            if (tmp < 0) {
                int i1 = (i ^ 0xFFFFFFFF) + 1 | 0x80;
                byteDate.write(i1);
                byteDate.write(255);
                byteDate.write(255);
                byteDate.write(255);
                byteDate.write(15);
                return byteDate.toByteArray();
            }
            byteDate.write(i);
            return byteDate.toByteArray();
        }
        if ((i <= 16383) && (i >= 128)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 ^= 0xFFFFFFFF;
                byteDate.write((byte) i1);
                byteDate.write((byte) i2);
                byteDate.write(-1);
                byteDate.write(-1);
                byteDate.write(15);

                return byteDate.toByteArray();
            }

            byteDate.write((byte) i1);
            byteDate.write((byte) i2);
            return byteDate.toByteArray();
        }
        if ((i <= 2097151) && (i >= 16384)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 = i2 ^ 0xFFFFFFFF | 0x80;
                i3 ^= 0xFFFFFFFF;
                byteDate.write((byte) i1);
                byteDate.write((byte) i2);
                byteDate.write((byte) i3);
                byteDate.write(-1);
                byteDate.write(15);
                return byteDate.toByteArray();
            }

            byteDate.write((byte) i1);
            byteDate.write((byte) i2);
            byteDate.write((byte) i3);
            return byteDate.toByteArray();
        }
        if ((i <= 268435455) && (i >= 2097152)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14 | 0x80;
            int i4 = i >> 21;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 = i2 ^ 0xFFFFFFFF | 0x80;
                i3 = i3 ^ 0xFFFFFFFF | 0x80;
                i4 ^= 0xFFFFFFFF;
                byteDate.write((byte) i1);
                byteDate.write((byte) i2);
                byteDate.write((byte) i3);
                byteDate.write((byte) i4);
                byteDate.write(15);

                return byteDate.toByteArray();
            }
            byteDate.write((byte) i1);
            byteDate.write((byte) i2);
            byteDate.write((byte) i3);
            byteDate.write((byte) i4);

            return byteDate.toByteArray();
        }
        if (i >= 268435456) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14 | 0x80;
            int i4 = i >> 21 | 0x80;
            int i5 = i >> 28;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 = i2 ^ 0xFFFFFFFF | 0x80;
                i3 = i3 ^ 0xFFFFFFFF | 0x80;
                i4 = i4 ^ 0xFFFFFFFF | 0x80;
                i5 ^= 0xFFFFFFFF;
                byteDate.write((byte) i1);
                byteDate.write((byte) i2);
                byteDate.write((byte) i3);
                byteDate.write((byte) i4);
                byteDate.write((byte) i5);
                return byteDate.toByteArray();
            }
            byteDate.write((byte) i1);
            byteDate.write((byte) i2);
            byteDate.write((byte) i3);
            byteDate.write((byte) i4);
            byteDate.write((byte) i5);
            return byteDate.toByteArray();
        }
        return byteDate.toByteArray();
    }

    public static ArrayList < Byte > getUI32(int i) {
        ArrayList < Byte > al = new ArrayList();

        if (i <= 127) {
            al.add(Byte.valueOf((byte) i));
            return al;
        }
        if ((i <= 16383) && (i >= 128)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7;
            al.add(Byte.valueOf((byte) i1));
            al.add(Byte.valueOf((byte) i2));

            return al;
        }
        if ((i <= 2097151) && (i >= 16384)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14;
            al.add(Byte.valueOf((byte) i1));
            al.add(Byte.valueOf((byte) i2));
            al.add(Byte.valueOf((byte) i3));
            return al;
        }
        if ((i <= 268435455) && (i >= 2097152)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14 | 0x80;
            int i4 = i >> 21;
            al.add(Byte.valueOf((byte) i1));
            al.add(Byte.valueOf((byte) i2));
            al.add(Byte.valueOf((byte) i3));
            al.add(Byte.valueOf((byte) i4));
            return al;
        }
        if (i >= 268435456) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14 | 0x80;
            int i4 = i >> 21 | 0x80;
            int i5 = i >> 28;
            al.add(Byte.valueOf((byte) i1));
            al.add(Byte.valueOf((byte) i2));
            al.add(Byte.valueOf((byte) i3));
            al.add(Byte.valueOf((byte) i4));
            al.add(Byte.valueOf((byte) i5));
            return al;
        }
        return al;
    }

    public static ArrayList < Byte > getSI32(int i) {
        int tmp = i;
        if (i < 0) {
            i *= -1;
        }
        ArrayList < Byte > al = new ArrayList();

        if (i <= 127) {
            if (tmp < 0) {
                int i1 = (i ^ 0xFFFFFFFF) + 1 | 0x80;
                al.add(Byte.valueOf((byte) i1));
                al.add(Byte.valueOf((byte) - 1));
                al.add(Byte.valueOf((byte) - 1));
                al.add(Byte.valueOf((byte) - 1));
                al.add(Byte.valueOf((byte) 15));
                return al;
            }
            al.add(Byte.valueOf((byte) i));
            return al;
        }
        if ((i <= 16383) && (i >= 128)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 ^= 0xFFFFFFFF;
                al.add(Byte.valueOf((byte) i1));
                al.add(Byte.valueOf((byte) i2));
                al.add(Byte.valueOf((byte) - 1));
                al.add(Byte.valueOf((byte) - 1));
                al.add(Byte.valueOf((byte) 15));

                return al;
            }

            al.add(Byte.valueOf((byte) i1));
            al.add(Byte.valueOf((byte) i2));
            return al;
        }
        if ((i <= 2097151) && (i >= 16384)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 = i2 ^ 0xFFFFFFFF | 0x80;
                i3 ^= 0xFFFFFFFF;
                al.add(Byte.valueOf((byte) i1));
                al.add(Byte.valueOf((byte) i2));
                al.add(Byte.valueOf((byte) i3));
                al.add(Byte.valueOf((byte) - 1));
                al.add(Byte.valueOf((byte) 15));
                return al;
            }

            al.add(Byte.valueOf((byte) i1));
            al.add(Byte.valueOf((byte) i2));
            al.add(Byte.valueOf((byte) i3));
            return al;
        }
        if ((i <= 268435455) && (i >= 2097152)) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14 | 0x80;
            int i4 = i >> 21;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 = i2 ^ 0xFFFFFFFF | 0x80;
                i3 = i3 ^ 0xFFFFFFFF | 0x80;
                i4 ^= 0xFFFFFFFF;
                al.add(Byte.valueOf((byte) i1));
                al.add(Byte.valueOf((byte) i2));
                al.add(Byte.valueOf((byte) i3));
                al.add(Byte.valueOf((byte) i4));
                al.add(Byte.valueOf((byte) 15));

                return al;
            }
            al.add(Byte.valueOf((byte) i1));
            al.add(Byte.valueOf((byte) i2));
            al.add(Byte.valueOf((byte) i3));
            al.add(Byte.valueOf((byte) i4));

            return al;
        }
        if (i >= 268435456) {
            int i1 = i & 0x7F | 0x80;
            int i2 = i >> 7 | 0x80;
            int i3 = i >> 14 | 0x80;
            int i4 = i >> 21 | 0x80;
            int i5 = i >> 28;
            if (tmp < 0) {
                i1 = (i1 ^ 0xFFFFFFFF) + 1 | 0x80;
                i2 = i2 ^ 0xFFFFFFFF | 0x80;
                i3 = i3 ^ 0xFFFFFFFF | 0x80;
                i4 = i4 ^ 0xFFFFFFFF | 0x80;
                i5 ^= 0xFFFFFFFF;
                al.add(Byte.valueOf((byte) i1));
                al.add(Byte.valueOf((byte) i2));
                al.add(Byte.valueOf((byte) i3));
                al.add(Byte.valueOf((byte) i4));
                al.add(Byte.valueOf((byte) i5));
                return al;
            }
            al.add(Byte.valueOf((byte) i1));
            al.add(Byte.valueOf((byte) i2));
            al.add(Byte.valueOf((byte) i3));
            al.add(Byte.valueOf((byte) i4));
            al.add(Byte.valueOf((byte) i5));
            return al;
        }
        return al;
    }

    public static String formatInt(int k) {
        String s = Integer.toBinaryString(k);
        String wynik = "";
        if (s.length() < 8) {
            int i = 8 - s.length();

            for (int n = 0; n < i; n++) {
                wynik = wynik + "0";
            }
            wynik = wynik + s;
        } else {
            wynik = s.substring(0, 8);
        }
        return wynik;
    }

    public static ArrayList < Integer > array(int a, int b, int c) {
        ArrayList < Integer > al = new ArrayList();
        al.add(Integer.valueOf(a));
        al.add(Integer.valueOf(b));
        al.add(Integer.valueOf(c));
        return al;
    }

    public static ArrayList < Integer > array(int a, int b) {
        ArrayList < Integer > al = new ArrayList();
        al.add(Integer.valueOf(a));
        al.add(Integer.valueOf(b));
        return al;
    }

    public static String print(String s, HashMap < String, Object > hm) {
        Object o = hm.get(s);
        if (o != null) {
            return s + ":  " + o + "\n";
        }
        return "";
    }

    public static void konsola(String s) {
        LOGGER.info(s);
    }

    public static byte[] toBytaJava(byte data) {
        return new byte[] {
            data
        };
    }

    public static byte[] toBytaJava(double data) {
        ByteArrayOutputStream byte_out = new ByteArrayOutputStream();
        DataOutputStream data_out = new DataOutputStream(byte_out);
        try {
            data_out.writeDouble(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] tmp = byte_out.toByteArray();
        byte[] result = new byte[tmp.length];

        int ii = tmp.length;
        for (int i = 0; i < tmp.length; i++) {
            ii--;
            result[ii] = tmp[i];
        }

        return result;
    }

    public static void out(String s) {
        LOGGER.info(s);
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o: hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return Integer.valueOf(0);
    }

    public String readString(boolean isUtf8) {
        if (isUtf8) {
            return readUTF().intern();
        }
        return readASCII();
    }

    public String readASCII() {
        ByteArrayOutputStream out = new ByteArrayOutputStream(256) {
            public byte[] toByteArray() {
                return this.buf;
            }
        };

        int ch;
        while ((ch = unsignedByte(this.bbuf.get())) > 0) {
            out.write(ch);
        }

        String s = new String(out.toByteArray(), 0, out.size());
        out.reset();
        return s.intern();
    }

    public String readUTF() {
        int bpos = this.bbuf.position();

        StringBuilder b = new StringBuilder();
        try {
            int c;
            while ((c = unsignedByte(this.bbuf.get())) > 0) {
                switch (c >> 4) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        b.append((char) c);
                        break;

                    case 12:
                    case 13:
                        int c2 = unsignedByte(this.bbuf.get());
                        if ((c2 > 0) && ((c2 & 0xC0) != 128)) {}

                        b.append((char)((c & 0x1F) << 6 | c2 & 0x3F));
                        break;

                    case 14:
                        c2 = unsignedByte(this.bbuf.get());
                        int c3 = unsignedByte(this.bbuf.get());
                        if ((c2 > 0) && (c3 > 0) && ((c2 & 0xC0) == 128) && ((c3 & 0xC0) != 128)) {}

                        b.append((char)((c & 0xF) << 12 | (c2 & 0x3F) << 6 | c3 & 0x3F));
                        break;
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    default:
                        b.append((char) c);
                }

            }
        } catch (Exception e) {
            LOGGER.error("utf read warning " + b.toString().intern(), e);
        }

        return b.toString().intern();
    }

    public boolean readBit() {
        return readUBits(1) != 0;
    }

    public int readUI16() {
        syncBits();
        int count = this.bbuf.capacity();
        int i;
        if (count - this.bbuf.position() >= 2) {
            i = this.bbuf.get() & 0xFF | (this.bbuf.get() & 0xFF) << 8;
        } else {
            if (this.bbuf != null) {
                i = this.bbuf.get() | this.bbuf.get() << 8;
            } else
                return -1;
        }
        return i;
    }

    public static byte[] intToByteArray(int value) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            int offset = (b.length - 1 - i) * 8;
            b[i] = ((byte)(value >>> offset & 0xFF));
        }
        byte[] res = new byte[3];
        for (int i = 0; i < 3; i++) {
            res[i] = b[(3 - i)];
        }

        return res;
    }

    public ByteBuffer getBbuf() {
        return this.bbuf;
    }

    public void setBbuf(ByteBuffer bbuf) {
        this.bbuf = bbuf;
    }

    public void printBuff() {
        int pos = position();
        position(0);
        LOGGER.info(getHex(array()));
        position(pos);
    }

    private String getHex(byte[] raw) {
        if (raw == null) {
            return null;
        }
        StringBuilder hex = new StringBuilder(2 * raw.length);
        byte[] arrayOfByte;
        int j = (arrayOfByte = raw).length;
        for (int i = 0; i < j; i++) {
            byte b = arrayOfByte[i];
            hex.append("0123456789ABCDEF".charAt((b & 0xF0) >> 4)).append("0123456789ABCDEF".charAt(b & 0xF));
        }
        return hex.toString();
    }

    public static String getHex(byte b) {
        StringBuilder hex = new StringBuilder();

        hex.append("0123456789ABCDEF".charAt((b & 0xF0) >> 4)).append("0123456789ABCDEF".charAt(b & 0xF));

        return hex.toString();
    }
}