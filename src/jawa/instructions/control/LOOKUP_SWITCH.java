package jawa.instructions.control;

import jawa.instructions.base.BranchLogic;
import jawa.instructions.base.ByteCodeReader;
import jawa.instructions.base.Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class LOOKUP_SWITCH implements Instruction {
    private int defaultOffset;
    private int npairs;
    private int[] matchOffsets;
    @Override
    public void fetchOperands(ByteCodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        npairs = reader.readInt32();
        matchOffsets = reader.readInt32s(npairs * 2);
    }

    @Override
    public void execute(Frame frame) {
        int key = frame.getOperandStack().popInt();
        for (int i = 0; i < npairs * 2; i += 2) {
            if(matchOffsets[i] == key){
                int offset = matchOffsets[i + 1];
                BranchLogic.branch(frame,offset);
                return;
            }
        }
        BranchLogic.branch(frame,defaultOffset);
    }
}
