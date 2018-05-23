package jawa.instructions.extended;

import jawa.instructions.base.BranchLogic;
import jawa.instructions.base.ByteCodeReader;
import jawa.instructions.base.Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class GOTO_W implements Instruction {
    private int offset;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public void fetchOperands(ByteCodeReader reader) {
        offset = reader.readInt32();
    }

    @Override
    public void execute(Frame frame) {
        BranchLogic.branch(frame, offset);
    }
}
