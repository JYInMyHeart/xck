package jawa.instructions.base;

import jawa.rtda.Frame;
import jawa.rtda.XThread;
import jawa.rtda.heap.XClass;
import jawa.rtda.heap.XMethod;
import org.jetbrains.annotations.NotNull;

/**
 * @author xck
 */
public class InitLogic {
    public static void initClass(XThread xThread,@NotNull XClass xClass){
        xClass.startInit();;
        scheduleClinit(xThread,xClass);
        initSuperClass(xThread,xClass);
    }

    public static void scheduleClinit(XThread xThread,@NotNull XClass xClass){
        XMethod clinit = xClass.getClinitMethod();
        if(clinit != null){
            Frame newFrame = xThread.newFrame(clinit);
            xThread.pushFrame(newFrame);
        }
    }

    public static void initSuperClass(XThread xThread,@NotNull XClass xClass){
        if(!xClass.isInterface()){
            XClass superClass = xClass.getSuperClass();
            if(superClass != null && superClass.isInitStarted())
                initClass(xThread,superClass);
        }
    }
}
