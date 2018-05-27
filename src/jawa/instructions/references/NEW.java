package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.heap.ConstantPool;
import jawa.rtda.heap.XClass;
import jawa.rtda.heap.XClassRef;
import jawa.rtda.heap.XObject;

import static jawa.instructions.base.InitLogic.initClass;

public class NEW extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getxClass().getConstantPool();
        XClassRef classRef = (XClassRef) cp.getConstant(index).get();
        XClass xClass = classRef.resolvedClass();
        if(!xClass.isInitStarted()){
            frame.revertNextPc();
            initClass(frame.getThread(),xClass);
            return;
        }
        if (xClass.isInterface() || xClass.isAbstract())
            throw new RuntimeException("java.lang.InstantiationError");
        XObject ref = xClass.newObject();
        frame.getOperandStack().pushRef(ref);
    }

    public String toString() {
        return "NEW{" +
                "index=" + index +
                '}';
    }
}
