package jawa.instructions.constants.Const;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class DCONST_0 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(0.0);
    }
}
