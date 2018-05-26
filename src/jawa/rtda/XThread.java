package jawa.rtda;

import jawa.rtda.heap.XMethod;

import java.util.Stack;

/**
 * @author xck
 */
public class XThread {
    private int pc;
    private JvmStack stack;
    private static final int STACK_MAX_SIZE = 0124;

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public XThread() {
        stack = new JvmStack(1024);
    }

    public void pushFrame(Frame frame) {
        stack.push(frame);
    }

    public Frame popFrame() {
        return stack.pop();
    }

    public Frame currentFrame() {
        return stack.getTop();
    }

    public Frame topFrame() {
        return stack.getTop();
    }

    public Frame newFrame(int maxLocals, int maxStack) {
        return new Frame(this, maxLocals, maxStack);
    }

    public Frame newFrame(XMethod xMethod) {
        return new Frame(this,xMethod);
    }

    public boolean isStackEmpty(){
        return stack.isEmpty();
    }

}
