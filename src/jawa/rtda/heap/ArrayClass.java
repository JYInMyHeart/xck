package jawa.rtda.heap;


public class ArrayClass extends XClass{

    public ArrayClass() {

    }

    public static XObject newArray(int count,XClass xClass){
        assert isArray(xClass);
        switch(xClass.getName()){
            case "'[I'":{
                return new ArrayObject<Integer>();
            }
            case "'[Z'":{
                return new ArrayObject<Byte>();
            }
            case "'[B'":{
                return new ArrayObject<Byte>();
            }
            case "'[C'":{
                return new ArrayObject<Short>();
            }
            case "'[S'":{
                return new ArrayObject<Short>();
            }
            case "'[J'":{
                return new ArrayObject<Long>();
            }
            case "'[F'":{
                return new ArrayObject<Float>();
            }
            case "'[D'":{
                return new ArrayObject<Double>();
            }
            default:return new ArrayObject<XObject>();

        }

    }
    public static boolean isArray(XClass xClass){
        return xClass.getName().charAt(0) == '[';
    }
}
