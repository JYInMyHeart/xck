package jawa.instructions.constants;

import jawa.classfiles.constant.ConstantDoubleInfo;
import jawa.classfiles.constant.ConstantFloatInfo;
import jawa.classfiles.constant.ConstantIntegerInfo;
import jawa.classfiles.constant.ConstantStringInfo;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.heap.ConstantPool;

public interface  _LDC {
    default void _ldc(Frame frame, int index) {
        OperandStack stack = frame.getOperandStack();
        ConstantPool cp = frame.getMethod().getxClass().getConstantPool();
        Object c = cp.getConstant(index).get();
        if (c instanceof ConstantIntegerInfo)
            stack.pushInt(((ConstantIntegerInfo) c).getValue());
        else if (c instanceof ConstantFloatInfo)
            stack.pushFloat(((ConstantFloatInfo) c).getValue());
        else if (c instanceof ConstantStringInfo) {

        } else if (c instanceof ConstantDoubleInfo) {

        } else throw new RuntimeException("todo ldc");
    }
}
