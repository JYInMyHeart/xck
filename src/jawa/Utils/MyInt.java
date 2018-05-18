package jawa.Utils;

public class MyInt {
    private int value;

    public int getValue() {
        return value;
    }

    public MyInt(int value) {
        this.value = value;
    }

    public MyInt() {
        this(0);
    }

    public void increase(int n){
        value += n;
    }
    public void increase(){
        increase(1);
    }
}
