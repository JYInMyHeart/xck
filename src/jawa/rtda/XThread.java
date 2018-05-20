package jawa.rtda;

import java.util.Stack;

/**
 * @author xck
 */
public class XThread {
    private int pc;
    private Stack<Frame> stack;
    private static final int STACK_MAX_SIZE = 0124;

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public XThread() {
        stack = new Stack();
    }

    public void pushFrame(Frame frame){
        stack.push(frame);
    }

    public Frame popFrame(){
        return stack.pop();
    }

    public Frame currentFrame(){
        return stack.peek();
    }

    public Frame newFrame(int maxLocals,int maxStack){
        return new Frame(this,maxLocals,maxStack);
    }
}
