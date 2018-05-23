package jawa.instructions.comparisions.ifcond;

import jawa.instructions.base.BranchInstruction;
import jawa.instructions.base.BranchLogic;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class IFGE extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        if (frame.getOperandStack().popInt() >= 0)
            BranchLogic.branch(frame, offset);
    }
}
