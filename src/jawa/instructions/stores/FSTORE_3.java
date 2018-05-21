package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class FSTORE_3 extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(3,frame.getOperandStack().popFloat());
    }
}
