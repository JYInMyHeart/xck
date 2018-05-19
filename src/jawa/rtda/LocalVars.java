package jawa.rtda;

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

    public long getlong(int index){
        return slots[index].getNum();
    }


    public void setRef(int index,XObject ref){
        Slot slot = new Slot(0,ref);
        slots[index] = slot;
    }

    public XObject getRef(int index){
        return slots[index].getRef();
    }
}
