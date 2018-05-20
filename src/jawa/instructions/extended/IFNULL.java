package jawa.instructions.extended;

import jawa.instructions.base.BranchInstruction;
import jawa.instructions.base.BranchLogic;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class IFNULL extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        if(frame.getOperandStack().popRef() == null)
            BranchLogic.branch(frame,offset);
    }
}
