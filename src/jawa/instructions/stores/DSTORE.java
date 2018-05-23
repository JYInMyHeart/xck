package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class DSTORE extends Index8Instruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setDouble(index, frame.getOperandStack().popDouble());
    }
}
