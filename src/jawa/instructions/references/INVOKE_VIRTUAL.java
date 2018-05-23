package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.heap.ConstantPool;
import jawa.rtda.heap.MethodRef;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        ConstantPool cp = frame.getMethod().getxClass().getConstantPool();
        MethodRef ref = (MethodRef) cp.getConstant(index).get();
        if (ref.getMethod().getName().equals("'println'")) {
            OperandStack stack = frame.getOperandStack();
            switch (ref.getMethod().getDescroptor()) {
                case "(Z)V":
                case "(C)V":
                case "(B)V":
                case "(S)V":
                case "(I)V": {
                    System.out.println(String.format("%d", stack.popInt()));
                }
                break;
                case "(J)V": {
                    System.out.println(String.format("%d", stack.popLong()));
                    break;
                }
                case "(F)V": {
                    System.out.println(String.format("%f", stack.popFloat()));
                    break;
                }
                case "(D)V": {
                    System.out.println(String.format("%f", stack.popDouble()));
                    break;
                }
                default:
                    throw new RuntimeException("println" + ref.getMethod().getDescroptor());
            }


            stack.popRef();
        }


    }
}
