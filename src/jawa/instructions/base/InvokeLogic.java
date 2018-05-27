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
        int argSlotSlot = xMethod.getArgSlotCount();
        if(argSlotSlot > 0){
            for (int i = argSlotSlot - 1;i >= 0;i--){
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i,slot);
            }
        }
        thread.pushFrame(newFrame);
        invokerFrame.setThread(thread);

        if(xMethod.isNative()){
            if(xMethod.getName().equals("'registerNatives'")){
                thread.popFrame();
            }else{
                System.out.println(String.format("native method;%s.%s%s\n",
                        xMethod.getxClass().getName(),xMethod.getName(),xMethod.getDescroptor()));
            }
        }
    }
}
