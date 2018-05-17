package jawa.classFile;

public class AttrUnparsed {
    static class UnparsedAttribute implements AttributeInfo{
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
    }

}
