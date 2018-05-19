package jawa.classfiles.members.attributeinfos;

import jawa.classfiles.ClassReader;
import jawa.classfiles.members.AttributeInfo;


public class UnparsedAttribute implements AttributeInfo {
    private String name;
    private int length;
    byte[] info;

    public UnparsedAttribute(String name, int length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public void readInfo(ClassReader reader) {
        info = reader.readBytes(length);
    }

    public String toString() {
        return "UnparsedAttribute{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }

    public byte[] getInfo() {
        return null;
    }
}


