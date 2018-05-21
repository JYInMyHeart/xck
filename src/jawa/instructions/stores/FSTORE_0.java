package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class FSTORE_0 extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(0,frame.getOperandStack().popFloat());
    }
}
