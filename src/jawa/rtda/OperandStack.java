package jawa.rtda;

import jawa.rtda.heap.XObject;

/**
 * @author xck
 */
public class
OperandStack {
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


    public void pushInt(int value) {
        slots[size] = new Slot(value, null);
        size++;
    }

    public int popInt() {
        if (size > 0) {
            size--;
            return slots[size].getNum();
        } else
            return 0;
    }

    public void pushFloat(float value) {
        slots[size++] = new Slot((int) value, null);
    }

    public float popFloat() {
        return (float) slots[--size].getNum();
    }

    public void pushLong(long value) {
        slots[size++] = new Slot((int) value, null);
    }

    public long popLong() {
        return slots[--size].getNum();
    }

    public void pushDouble(double value) {
        slots[size++] = new Slot((int) value, null);
    }

    public double popDouble() {
        return slots[--size].getNum();
    }

    public void pushRef(XObject ref) {
        slots[size++] = new Slot(0, ref);
    }

    public XObject popRef() {
        Slot currentSlot = slots[--size];
        return currentSlot.getRef();
    }

    public OperandStack(int maxStack) {
        assert maxStack > 0;
        size = 0;
        slots = new Slot[maxStack];
    }

    public void pushSlot(Slot slot) {
        slots[size++] = slot;
    }

    public Slot popSlot() {
        return slots[--size];
    }
}
