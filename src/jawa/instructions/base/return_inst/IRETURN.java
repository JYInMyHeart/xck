package jawa.instructions.base.return_inst;

import jawa.instructions.base.NoOperandsInstruction;
import jawa.rtda.Frame;
import jawa.rtda.XThread;

/**
 * @author xck
 */
public class IRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        XThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        int retVal = currentFrame.getOperandStack().popInt();
        invokerFrame.getOperandStack().pushInt(retVal);
    }

    @Override
    public String toString() {
        return "IRETURN{}";
    }
}
