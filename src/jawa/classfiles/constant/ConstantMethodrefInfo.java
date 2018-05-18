package jawa.classfiles.constant;

/**
 * @author xck
 */
public class ConstantMethodrefInfo extends ConstantMemberrefInfo{
    public ConstantMethodrefInfo(ConstantPool cp) {
        super(cp);
    }

    public String toString() {
        return "ConstantMethodrefInfo{" +
                "classIndex=" + classIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                '}';
    }
}
