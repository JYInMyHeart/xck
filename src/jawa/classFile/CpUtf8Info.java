package jawa.classFile;

import lombok.Getter;

import java.io.UnsupportedEncodingException;

import static jawa.Utils.Sth.getByteIndex;

/**
 * @author xck
 */

public class CpUtf8Info {
    @Getter
    static class ConstantUtf8Info implements ConstantInfo{
        private String str;
        public void readInfo(ClassReader reader) {
            var bytes = reader.readBytes(getByteIndex(reader.readUint32()));
            try {
                str = new String(bytes,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
