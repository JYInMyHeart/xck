package jawa.rtda;

public class JvmStack {
    int maxSize;
    int size;
    Frame top;

    public JvmStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(Frame frame){
        if(size >= maxSize)
            throw new RuntimeException("java.lang.StackOverFlowError");
        if(top != null)
            frame.setLower(top);
        top = frame;
        size++;
    }
    public Frame pop(){
        if(top == null)
            throw new RuntimeException("jvm stack is empty");
        Frame tempTop = top;
        top = tempTop.getLower();
        tempTop.setLower(null);
        size--;
        return tempTop;
    }
    public Frame getTop(){
        if(top == null)
            throw new RuntimeException("jvm stack is empty");
        return top;
    }
    public boolean isEmpty(){
        return top == null;
    }
}
