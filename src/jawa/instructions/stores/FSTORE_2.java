package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class FSTORE_2 extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(2,frame.getOperandStack().popFloat());
    }
}
