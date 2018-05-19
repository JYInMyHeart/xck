package jawa.instructions.base;

import jawa.rtda.Frame;

/**
 * @author xck
 */
public class Index16Instruction implements Instruction{
    private int index;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readInt16();
    }

    @Override
    public void execute(Frame frame) {

    }
}
