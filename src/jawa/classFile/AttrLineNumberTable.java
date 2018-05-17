package jawa.classFile;


import java.util.ArrayList;
import java.util.List;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */
public class AttrLineNumberTable {
    static class LineNumberTableEntry implements AttributeInfo{
        private short startPc;
        private short lineNumber;

        @Override
        public void readInfo(ClassReader reader) {

        }
    }
    static class LineNumberTableAttribute implements AttributeInfo{
        private List<LineNumberTableEntry> lineNumberTable;

        @Override
        public void readInfo(ClassReader reader) {
            var lineNumberTableLength = getShortIndex(reader.readUint16());
            var lineNumberTable = new ArrayList<LineNumberTableEntry>();
            for (int i = 0; i < lineNumberTableLength; i++) {
                var l = new LineNumberTableEntry();
                l.startPc = getShortIndex(reader.readUint16());
                l.lineNumber = getShortIndex(reader.readUint16());
                lineNumberTable.add(l);
            }
            this.lineNumberTable = lineNumberTable;
        }
    }
    static class LocalVariableTableAttribute implements AttributeInfo{

        @Override
        public void readInfo(ClassReader reader) {

        }
    }
    static class SourceFileAttribute implements AttributeInfo{

        @Override
        public void readInfo(ClassReader reader) {

        }
    }

}
