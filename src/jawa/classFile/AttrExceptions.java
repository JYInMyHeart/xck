package jawa.classFile;

import jawa.Utils.Sth;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xck
 */
public class AttrExceptions {
    static class ExceptionsAttribute implements AttributeInfo{

        private List<Short> exceptionIndexTable;

        @Override
        public void readInfo(ClassReader reader) {
            exceptionIndexTable = reader.readUint16s().stream().map(Sth::getShortIndex).collect(Collectors.toList());
        }

        public List<Short> getExceptionIndexTable() {
            return exceptionIndexTable;
        }
    }
}
