package jawa.classFile;

import java.io.UnsupportedEncodingException;

/**
 * @author xck
 */
public class CpUtf8Info {
    class ConstantUtf8Info{
        private String str;
        public void readInfo(ClassReader reader) throws UnsupportedEncodingException {
            byte[] length = reader.readUint32();
            byte[] bytes = reader.readBytes(Integer.parseInt(ClassFile.bytesToHexString(length)));
            str = new String(bytes,"UTF-8");
        }
    }
}
