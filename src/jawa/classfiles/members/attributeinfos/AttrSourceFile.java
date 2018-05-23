package jawa.classfiles.members.attributeinfos;

import jawa.classfiles.ClassReader;
import jawa.classfiles.constant.ConstantPool;
import jawa.classfiles.members.AttributeInfo;

import static jawa.Utils.Sth.getIntIndex;

public class AttrSourceFile implements AttributeInfo {
    private ConstantPool cp;
    private int sourceFileIndex;

    @Override
    public void readInfo(ClassReader reader) {
        sourceFileIndex = getIntIndex(reader.readUint16());
    }

    public String getFileName() throws Exception {
        return cp.getUtf8(sourceFileIndex);
    }
}
