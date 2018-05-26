package jawa.instructions.references;

import jawa.instructions.base.Index16Instruction;
import jawa.rtda.Frame;
import jawa.rtda.OperandStack;
import jawa.rtda.heap.*;

import static jawa.instructions.base.InvokeLogic.invokeMethod;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(Frame frame) {
        XClass currentClass = frame.getMethod().getxClass();
        ConstantPool cp = currentClass.getConstantPool();
        MethodRef methodRef = (MethodRef) cp.getConstant(index).get();
        XMethod resolvedMethod = methodRef.resolvedMethod();
        if(resolvedMethod.isStatic())
            throw new RuntimeException("java.lang.IncompatibleClassChangeError");
        XObject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if(ref == null){
            if(methodRef.getName().equals("'println'")){
                printlnMsg(frame, methodRef);
                return;
            }
            throw new RuntimeException("java.lang.NullPointerException");
        }
        if(resolvedMethod.isProtected()
                && resolvedMethod.getxClass().isSuperClassOf(currentClass)
                && resolvedMethod.getxClass().getPackageName().equals(currentClass.getPackageName())
                && !ref.getxClass().getName().equals(currentClass.getName())
                && ref.getxClass().isSubClassOf(currentClass))
            throw new RuntimeException("java.lang.IllegalAccessError");
        XMethod methodToBeInvoked = methodRef.lookupMethodInClass(
                methodRef.getxClass(),methodRef.getName(),methodRef.getDescriptor()).orElse(null);
        if(methodToBeInvoked == null || methodToBeInvoked.isAbstract())
            throw new RuntimeException("java.lang.AbstractMethodError");
        invokeMethod(frame,methodToBeInvoked);
    }

    public void printlnMsg(Frame frame, MethodRef ref) {
        if ("'println'".equals(ref.getName())) {
            OperandStack stack = frame.getOperandStack();
            switch (ref.getDescriptor()) {
                case "'(Z)V'":
                case "'(C)V'":
                case "'(B)V'":
                case "'(S)V'":
                case "'(I)V'": {
                    System.out.println(String.format("%d", stack.popInt()));
                }
                break;
                case "'(J)V'": {
                    System.out.println(String.format("%d", stack.popLong()));
                    break;
                }
                case "'(F)V'": {
                    System.out.println(String.format("%f", stack.popFloat()));
                    break;
                }
                case "'(D)V'": {
                    System.out.println(String.format("%f", stack.popDouble()));
                    break;
                }
                default:
                    throw new RuntimeException("println" + ref.getMethod().getDescroptor());
            }


            stack.popRef();
        }
    }

    public String toString() {
        return "INVOKE_VIRTUAL{" +
                "index=" + index +
                '}';
    }
}
