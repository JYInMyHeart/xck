package jawa.instructions.comparisions.ifacmp;

import jawa.instructions.base.BranchInstruction;
import jawa.instructions.base.BranchLogic;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.XObject;

/**
 * @author xck
 */
public class IF_ACMPEQ extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        XObject ref2 = stack.popRef();
        XObject ref1 = stack.popRef();
        if(ref1 == ref2)
            BranchLogic.branch(frame,offset);
    }
}
