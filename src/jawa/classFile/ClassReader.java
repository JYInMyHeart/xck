package jawa.classFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassReader {
    private byte[] data;
    private int offset = 0;

    public ClassReader(byte[] data) {
        this.data = data;
    }

    public byte[] readUint8(){
        byte[] data = new byte[1];
        System.arraycopy(this.data,offset,data,0,data.length);
        offset += data.length;
        return data;
    }
    public byte[] readUint16(){
        byte[] data = new byte[2];
        System.arraycopy(this.data,offset,data,0,data.length);
        offset += data.length;
        return data;
    }
    public byte[] readUint32(){
        byte[] data = new byte[4];
        System.arraycopy(this.data,offset,data,0,data.length);
        offset += data.length;
        return data;
    }
    public byte[] readUint64(){
        byte[] data = new byte[8];
        System.arraycopy(this.data,offset,data,0,data.length);
        offset += data.length;
        return data;
    }
    public List<String> readUint16s(){
        int n = Integer.valueOf(new String(readUint16()));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new String(readUint16()));
        }
        return list;
    }
    public byte[] readBytes(int n){
        byte[] data = new byte[n];
        System.arraycopy(this.data,offset,data,0,data.length);
        offset += n;
        return data;
    }

}
