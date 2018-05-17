package jawa.classFile;

import static jawa.Utils.Sth.getIntIndex;

public class AttrSourceFile implements AttributeInfo {
    private ConstantPool cp;
    private int sourceFileIndex;
    @Override
    public void readInfo(ClassReader reader) {
        sourceFileIndex = getIntIndex(reader.readUint16());
    }
    public String getFileName(){
        return cp.getUtf8(sourceFileIndex);
    }
}
