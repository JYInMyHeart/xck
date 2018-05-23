package jawa.instructions.comparisions.ificmp;

import jawa.instructions.base.BranchInstruction;
import jawa.instructions.base.BranchLogic;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class IF_ICMPLT extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int v2 = stack.popInt();
        int v1 = stack.popInt();
        if (v1 < v2)
            BranchLogic.branch(frame, offset);
    }
}
