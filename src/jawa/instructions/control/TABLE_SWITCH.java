package jawa.instructions.control;

import jawa.instructions.base.BranchLogic;
import jawa.instructions.base.ByteCodeReader;
import jawa.instructions.base.Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class TABLE_SWITCH implements Instruction {
    private int defaultOffset;
    private int low;
    private int high;
    private int[] jumpOffsets;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        low = reader.readInt32();
        high = reader.readInt32();
        int jumpOffsetsCount = high - low + 1;
        jumpOffsets = reader.readInt32s(jumpOffsetsCount);
    }

    @Override
    public void execute(Frame frame) {
        int index = frame.getOperandStack().popInt();
        int offset;
        if(index >= low && index <= high)
            offset = jumpOffsets[index - low];
        else
            offset = defaultOffset;
        BranchLogic.branch(frame,offset);
    }
}
