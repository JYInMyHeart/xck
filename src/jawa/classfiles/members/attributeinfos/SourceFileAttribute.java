package jawa.classfiles.members.attributeinfos;

import jawa.classfiles.ClassReader;
import jawa.classfiles.members.AttributeInfo;

import static jawa.Utils.Sth.getShortIndex;

/**
 * @author xck
 */
public class SourceFileAttribute implements AttributeInfo {
    private short sourceFileIndex;

    @Override
    public void readInfo(ClassReader reader) {
        sourceFileIndex = getShortIndex(reader.readUint16());
    }


    public String toString() {
        return "sourceFileIndex=" + sourceFileIndex;
    }
}
