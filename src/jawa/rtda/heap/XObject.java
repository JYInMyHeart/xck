package jawa.rtda.heap;

import jawa.rtda.Slot;

/**
 * @author xck
 */
public class XObject {
    private XClass xClass;
    private Slot[] fields;

    public XObject(XClass xClass) {
        this.xClass = xClass;
        fields = new Slot[xClass.getInstanceSlotCount()];
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

    public Slot[] getFields() {
        return fields;
    }

    public void setFields(Slot[] fields) {
        this.fields = fields;
    }

    //todo
}
