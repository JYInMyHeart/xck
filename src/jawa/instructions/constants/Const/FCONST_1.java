package jawa.instructions.constants.Const;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class FCONST_1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(1.0f);
    }
}
