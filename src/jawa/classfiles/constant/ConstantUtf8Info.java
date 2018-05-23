package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;

import java.io.UnsupportedEncodingException;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */


public class ConstantUtf8Info implements ConstantInfo {
    private String str;

    public String getStr() {
        return str;
    }

    public void readInfo(ClassReader reader) {
        byte[] bytes = reader.readBytes(getShortIndex(reader.readUint16()));
        try {
            str = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "'" + str + '\'';
    }
}

