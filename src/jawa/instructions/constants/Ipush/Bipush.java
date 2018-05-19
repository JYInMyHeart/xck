package jawa.instructions.constants.Ipush;

import jawa.instructions.base.Index8Instruction;
import jawa.rtda.Frame;

/**
 * @author xck
 */
public class Bipush extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(index);
    }
}
