package jawa.classfiles;

import java.util.ArrayList;
import java.util.List;

import static jawa.Utils.Sth.getShortIndex;

public class ClassReader {
    private byte[] data;
    private int offset = 0;

    public int getOffset() {
        return offset;
    }

    public byte[] getData() {
        return data;
    }

    public ClassReader(byte[] data) {
        this.data = data;
    }

    public byte[] readUint8() {
        byte[] data = new byte[1];
        System.arraycopy(this.data, offset, data, 0, data.length);
        offset += data.length;
        return data;
    }

    public byte[] readUint16() {
        byte[] data = new byte[2];
        System.arraycopy(this.data, offset, data, 0, data.length);
        offset += data.length;
        return data;
    }

    public byte[] readUint32() {
        byte[] data = new byte[4];
        System.arraycopy(this.data, offset, data, 0, data.length);
        offset += data.length;
        return data;
    }

    public byte[] readUint64() {
        byte[] data = new byte[8];
        System.arraycopy(this.data, offset, data, 0, data.length);
        offset += data.length;
        return data;
    }

    public List<byte[]> readUint16s() {
        int n = getShortIndex(readUint16());
        List<byte[]> list = new ArrayList<byte[]>();
        for (int i = 0; i < n; i++) {
            list.add(readUint16());
        }
        return list;
    }

    public byte[] readBytes(int n) {
        byte[] data = new byte[n];
        System.arraycopy(this.data, offset, data, 0, data.length);
        offset += data.length;
        return data;
    }

}
