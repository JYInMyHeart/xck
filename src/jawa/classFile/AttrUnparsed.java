package jawa.classFile;

public class AttrUnparsed implements AttributeInfo{
    private String name;
    private int length;
    byte[] info;

    @Override
    public void readInfo(ClassReader reader) {
        info = reader.readBytes(length);
    }
}
