package jawa.instructions.base;

import jawa.rtda.Frame;

/**
 * @author xck
 */
public class Index8Instruction implements Instruction{
    protected int index;

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        index = reader.readInt8();
    }

    @Override
    public void execute(Frame frame) {

    }
}
