package jawa.instructions.base;

import jawa.rtda.Frame;
import jawa.rtda.Slot;
import jawa.rtda.XThread;
import jawa.rtda.heap.XMethod;

/**
 * @author xck
 */
public class InvokeLogic {
    public static void invokeMethod(Frame invokerFrame, XMethod xMethod){
        XThread thread = invokerFrame.getThread();
        Frame newFrame = thread.newFrame(xMethod);
        thread.pushFrame(newFrame);
        int argSlotSlot = xMethod.getArgSlotCount();
        if(argSlotSlot > 0){
            for (int i = argSlotSlot - 1;i >= 0;i--){
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().getSlots()[i] = slot;
            }
        }
    }
}
