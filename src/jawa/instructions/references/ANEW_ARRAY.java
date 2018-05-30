package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.heap.*;

public class ANEW_ARRAY extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getxClass().getConstantPool();
        XClassRef xClassRef = (XClassRef) cp.getConstant(index).get();
        XClass xClass = xClassRef.resolvedClass();
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if(count < 0)
            throw new RuntimeException("java.lang.NegativeArraySizeException");
        XClass arrClass = xClass.getArrayClass();
        XObject arr = ArrayClass.newArray(count,arrClass);
        stack.pushRef(arr);
    }
}
