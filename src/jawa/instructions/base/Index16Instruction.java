package jawa.instructions.base;

import jawa.rtda.Frame;

/**
 * @author xck
 */
public class Index16Instruction implements Instruction {
    protected int index;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readUInt16();
    }

    @Override
    public void execute(Frame frame) {

    }
}
