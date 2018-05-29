package jawa.rtda.heap;

public class ArrayObject {
    private XObject xObject;

    public ArrayObject(XObject xObject) {
        this.xObject = xObject;
    }

    public  Byte[] getBytes(){
        return (Byte[]) xObject.getArray().toArray();
    }
    public  Short[] getShorts(){
        return (Short[]) xObject.getArray().toArray();
    }
    public  Integer[] getInts(){
        return (Integer[]) xObject.getArray().toArray();
    }
    public  Long[] getLongs(){
        return (Long[]) xObject.getArray().toArray();
    }
    public  Float[] getFloat(){
        return (Float[]) xObject.getArray().toArray();
    }
    public  Double[] getDouble(){
        return (Double[]) xObject.getArray().toArray();
    }
    public  XObject[] getRefs() {
        return (XObject[]) xObject.getArray().toArray();
    }

    public int arrayLength(){
        return xObject.getArray().size();
    }
}
