package jawa.rtda;

/**
 * @author xck
 */
public class Frame {
    private Frame lower;
    private LocalVars localvars;
    private OperandStack operandStack;
    private XThread thread;
    private int nextPc;

    public XThread getThread() {
        return thread;
    }

    public void setThread(XThread thread) {
        this.thread = thread;
    }

    public int getNextPc() {
        return nextPc;
    }

    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }

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
        localvars = new LocalVars(maxLocals + 1);
        operandStack = new OperandStack(maxStack + 1);
    }

    public Frame(XThread thread,int maxLocals, int maxStack) {
        this(maxLocals,maxStack);
        this.thread = thread;
    }


}
