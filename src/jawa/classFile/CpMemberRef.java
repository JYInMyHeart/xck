package jawa.classFile;

import java.util.Map;

/**
 * @author xck
 */
public class CpMemberRef {
    static abstract class ConstantMemberrefInfo{
        private ConstantPool cp;
        private short classIndex;
        private short nameAndTypeIndex;
        public void readInfo(ClassReader reader){
            classIndex = Short.parseShort(ClassFile.bytesToHexString(reader.readUint16()));
            nameAndTypeIndex = Short.parseShort(ClassFile.bytesToHexString(reader.readUint16()));
        }
        public String getClassName(){
            return cp.getClassName(classIndex);
        }
        public Map<String,String> getNameAndType(){
            return cp.getNameAndType(nameAndTypeIndex);
        }
    }
    static class ConstantFieldrefInfo extends ConstantMemberrefInfo{}
    static class ConstantMethodrefInfo extends ConstantMemberrefInfo{}
    static class ConstantInterfaceMethodrefInfo extends ConstantMemberrefInfo{}
}
