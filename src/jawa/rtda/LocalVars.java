package jawa.rtda;

import jawa.rtda.heap.XObject;

import java.util.Arrays;

/**
 * @author xck
 */
public class LocalVars {
    private Slot[] slots;

    public LocalVars(int maxLocals){
        assert maxLocals > 0;
        this.slots = new Slot[maxLocals];
    }

    public void setInt(int index,int value){
        Slot slot = new Slot(value,null);
        slots[index] = slot;
    }

    public int getInt(int index){
        return slots[index].getNum();
    }

    public void setLong(int index,long value){
        Slot slot = new Slot((int)value,null);
        slots[index] = slot;
    }

    public long getLong(int index){
        return slots[index].getNum();
    }

    public double getDouble(int index){
        return slots[index].getNum();
    }

    public void setDouble(int index,double value){
        Slot slot = new Slot((int)value,null);
        slots[index] = slot;
    }

    public float getFloat(int index){
        return slots[index].getNum();
    }

    public void setFloat(int index,float value){
        Slot slot = new Slot((int)value,null);
        slots[index] = slot;
    }


    public void setRef(int index, XObject ref){
        Slot slot = new Slot(0,ref);
        slots[index] = slot;
    }

    public XObject getRef(int index){
        return slots[index].getRef();
    }

    public String toString() {
        return "LocalVars{" +
                "slots=" + Arrays.toString(slots) +
                '}';
    }
}
