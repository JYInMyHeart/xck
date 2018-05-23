package jawa.instructions.base;

import jawa.rtda.Frame;

/**
 * @author xck
 */
public class Index8Instruction implements Instruction {
    protected int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readUInt8();
    }

    @Override
    public void execute(Frame frame) {

    }
}
