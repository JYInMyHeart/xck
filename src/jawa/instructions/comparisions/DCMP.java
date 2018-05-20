package jawa.instructions.comparisions;

import jawa.rtda.Frame;
import jawa.rtda.OperandStack;

/**
 * @author xck
 */
public class DCMP extends CMP {
    protected void fcmp(Frame frame,boolean flag){
        OperandStack stack = frame.getOperandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();
        cmp(stack,v1,v2,flag);
    }
}
