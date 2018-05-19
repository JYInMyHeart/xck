package jawa.rtda;

/**
 * @author xck
 */
public class LocalVars {
    private Slot[] slots;

    public static LocalVars newLocalVars(int maxLocals){
        assert maxLocals > 0;
        Slot[] slots = new Slot[maxLocals];
        LocalVars localVars = new LocalVars();
        localVars.slots = slots;
        return localVars;
    }

    public void setInt(int index,int value){
        slots[index].setNum(value);
    }

    public int getInt(int index){
        return slots[index].getNum();
    }


    public void setRef(int index,XObject ref){
        slots[index].setRef(ref);
    }

    public XObject getRef(int index){
        return slots[index].getRef();
    }
}
