package jawa.rtda;

/**
 * @author xck
 */
public class Frame {
    private Frame lower;
    private LocalVars localvars;
    private OperandStack operandStack;

    public Frame getLower() {
        return lower;
    }

    public void setLower(Frame lower) {
        this.lower = lower;
    }

    public LocalVars getLocalVars() {
        return localvars;
    }

    public void setLocalVars(LocalVars localvars) {
        this.localvars = localvars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public void setOperandStack(OperandStack operandStack) {
        this.operandStack = operandStack;
    }

    public Frame newFrame(int maxLocals, int maxStack){
        Frame frame = new Frame();
        frame.setLocalVars(LocalVars.newLocalVars(maxLocals));
        frame.setOperandStack(OperandStack.newOperandStack(maxStack));
        return frame;
    }
}
