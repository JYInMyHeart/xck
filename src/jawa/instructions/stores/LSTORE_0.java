package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class LSTORE_0 extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setLong(0,frame.getOperandStack().popLong());
    }
}
