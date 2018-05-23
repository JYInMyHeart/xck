package jawa.classfiles.members.attributeinfos;

import jawa.classfiles.ClassReader;
import jawa.classfiles.members.AttributeInfo;

import java.util.ArrayList;
import java.util.List;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */
public class LineNumberTableAttribute implements AttributeInfo {
    private List<LineNumberTableEntry> lineNumberTable;

    @Override
    public void readInfo(ClassReader reader) {
        short lineNumberTableLength = getShortIndex(reader.readUint16());
        List<LineNumberTableEntry> lineNumberTable = new ArrayList<LineNumberTableEntry>();
        for (int i = 0; i < lineNumberTableLength; i++) {
            LineNumberTableEntry l = new LineNumberTableEntry();
            l.setStartPc(getShortIndex(reader.readUint16()));
            l.setLineNumber(getShortIndex(reader.readUint16()));
            lineNumberTable.add(l);
        }
        this.lineNumberTable = lineNumberTable;
    }

    public List<LineNumberTableEntry> getLineNumberTable() {
        return lineNumberTable;
    }

    public String toString() {
        return "lineNumberTable=" + lineNumberTable;
    }
}