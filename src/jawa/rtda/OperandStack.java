package jawa.rtda;

/**
 * @author xck
 */
public class OperandStack {
    private int size;
    private Slot[] slots;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }


    public void pushInt(int value){
        slots[size++].setNum(value);
    }

    public int popInt(){
        return slots[size--].getNum();
    }

    public void pushRef(XObject ref){
        slots[size++].setRef(ref);
    }

    public XObject popRef(){
        Slot currentSlot = slots[size];
        slots[size--].setRef(null);
        return currentSlot.getRef();
    }

    public static OperandStack newOperandStack(int maxStack){
        assert maxStack > 0;
        OperandStack operandStack = new OperandStack();
        operandStack.slots = new Slot[maxStack];
        return operandStack;
    }
}
