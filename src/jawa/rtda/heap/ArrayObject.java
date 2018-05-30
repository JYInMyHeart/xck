package jawa.rtda.heap;

public class ArrayObject<T> extends XObject<T>{

    public ArrayObject() {

    }

    public  Byte[] getBytes(){
        return (Byte[])getArray().toArray();
    }
    public  Short[] getShorts(){
        return (Short[])getArray().toArray();
    }
    public  Integer[] getInts(){
        return (Integer[])getArray().toArray();
    }
    public  Long[] getLongs(){
        return (Long[]) getArray().toArray();
    }
    public  Float[] getFloat(){
        return (Float[]) getArray().toArray();
    }
    public  Double[] getDouble(){
        return (Double[]) getArray().toArray();
    }
    public  XObject[] getRefs() {
        return (XObject[]) getArray().toArray();
    }

    public int arrayLength(){
        return getArray().size();
    }
}
