package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ASTORE_1 extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(1,frame.getOperandStack().popRef());
    }
}
