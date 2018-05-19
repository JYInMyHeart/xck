package jawa.instructions.constants.Const;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ICONST_4 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(4);
    }
}
