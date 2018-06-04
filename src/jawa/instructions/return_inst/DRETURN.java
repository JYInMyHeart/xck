package jawa.instructions.return_inst;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.XThread;

/**
 * @author xck
 */
public class DRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        XThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        double retVal = currentFrame.getOperandStack().popDouble();
        invokerFrame.getOperandStack().pushDouble(retVal);
    }
}
