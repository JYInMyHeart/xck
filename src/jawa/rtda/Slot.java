package jawa.rtda;

/**
 * @author xck
 */
public class Slot {
    private int num;
    private XObject ref;

    public Slot(int num, XObject ref) {
        this.num = num;
        this.ref = ref;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public XObject getRef() {
        return ref;
    }

    public void setRef(XObject ref) {
        this.ref = ref;
    }
}
