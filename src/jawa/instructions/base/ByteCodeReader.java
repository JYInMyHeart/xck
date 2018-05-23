package jawa.instructions.base;

/**
 * @author xck
 */
public class ByteCodeReader {
    private byte[] code;
    private int pc;


    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public int getPc() {
        return pc;
    }

    public int readUInt8() {
        return Byte.toUnsignedInt(code[pc++]);
    }

    public int readInt8() {
        return code[pc++];
    }

    public int readUInt16() {
        return (int) Integer.toUnsignedLong(((readInt8() << 8) | readInt8()));
    }

    public int readInt16() {
        return ((readInt8() << 8) | readInt8());
    }

    public long readUInt32() {
        return Integer.toUnsignedLong((readInt8() << 24)
                | (readInt8() << 16)
                | (readInt8() << 8)
                | readInt8());
    }

    public int readInt32() {
        return (readInt8() << 24)
                | (readInt8() << 16)
                | (readInt8() << 8)
                | readInt8();
    }

    public int[] readInt32s(int count) {
        int[] res = new int[count];
        for (int i = 0; i < count; i++) {
            res[i] = readInt32();
        }
        return res;
    }

    public void skipPadding() {
        while (pc % 4 != 0)
            readInt8();
    }
}
