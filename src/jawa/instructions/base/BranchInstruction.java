package jawa.instructions.base;

import jawa.rtda.Frame;

/**
 * @author xck
 */
public class BranchInstruction implements Instruction {
    protected int offset;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        offset = reader.readInt16();
    }

    @Override
    public void execute(Frame frame) {

    }
}
