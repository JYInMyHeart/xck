package jawa.instructions.stores;

import jawa.instructions.base.Index8Instruction;
import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class FSTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(2,frame.getOperandStack().popFloat());
    }
}
