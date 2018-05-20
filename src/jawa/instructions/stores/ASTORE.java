package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class ASTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(index,frame.getOperandStack().popRef());
    }
}
