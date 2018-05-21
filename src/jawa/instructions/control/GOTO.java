package jawa.instructions.control;

import jawa.instructions.base.BranchInstruction;
import jawa.instructions.base.BranchLogic;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class GOTO extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        BranchLogic.branch(frame,offset);
    }

    public String toString() {
        return "GOTO{" +
                "offset=" + offset +
                '}';
    }
}
