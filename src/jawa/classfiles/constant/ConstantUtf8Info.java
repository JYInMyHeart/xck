package jawa.classfiles.constant;

import jawa.classfiles.ClassReader;
import lombok.Getter;

import java.io.UnsupportedEncodingException;

import static jawa.Utils.Sth.getByteIndex;

/**
 * @author xck
 */


@Getter
public class ConstantUtf8Info implements ConstantInfo {
    private String str;

    public void readInfo(ClassReader reader) {
        byte[] bytes = reader.readBytes(getByteIndex(reader.readUint32()));
        try {
            str = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

