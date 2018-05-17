package jawa.classFile;

import java.util.Map;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */
public class CpMemberRef{
    static abstract class ConstantMemberrefInfo implements ConstantInfo{
        private ConstantPool cp;
        private short classIndex;
        private short nameAndTypeIndex;
        @Override
        public void readInfo(ClassReader reader){
            classIndex = getShortIndex(reader.readUint16());
            nameAndTypeIndex = getShortIndex(reader.readUint16());
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
