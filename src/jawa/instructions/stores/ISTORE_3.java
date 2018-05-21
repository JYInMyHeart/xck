package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ISTORE_3 extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setInt(3,frame.getOperandStack().popInt());
    }
}
