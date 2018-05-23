package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class FSTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(index, frame.getOperandStack().popFloat());
    }
}
