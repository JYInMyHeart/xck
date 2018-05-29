package jawa.rtda.heap;

import jawa.rtda.Slot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xck
 */
public class XObject<T> {
    private XClass xClass;
    private Slot[] data;
    private List<T> array;

    public XObject(XClass xClass) {
        this.xClass = xClass;
        data = new Slot[xClass.getInstanceSlotCount()];
    }


    public XObject() {
        this.array = new ArrayList<>();
    }

    public boolean isInstanceof(XClass xClass) {
        return xClass.isAssignableFrom(this.xClass);
    }

    public XClass getxClass() {
        return xClass;
    }

    public void setxClass(XClass xClass) {
        this.xClass = xClass;
    }

    public Slot[] getData() {
        return data;
    }

    public void setData(Slot[] data) {
        this.data = data;
    }

    public List<T> getArray() {
        return array;
    }

    public void setArray(List<T> array) {
        this.array = array;
    }

    //todo
}
