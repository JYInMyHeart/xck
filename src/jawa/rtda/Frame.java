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

    public Frame(int maxLocals, int maxStack){
        localvars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }
}
