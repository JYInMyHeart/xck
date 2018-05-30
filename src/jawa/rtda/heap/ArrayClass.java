package jawa.rtda.heap;


public class ArrayClass{
    private XClass xClass;

    public ArrayClass(XClass xClass) {
        this.xClass = xClass;
    }

    public static XObject newArray(int count,XClass xClass){
        assert isArray(xClass);
        switch(xClass.getName()){
            case "'[I'":{
                return new XObject<Integer>();
            }
            case "'[Z'":{
                return new XObject<Byte>();
            }
            default:return new XObject<XObject>();

        }

    }
    public static boolean isArray(XClass xClass){
        return xClass.getName().charAt(0) == '[';
    }
}
