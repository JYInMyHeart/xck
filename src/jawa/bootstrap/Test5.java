package jawa.bootstrap;

public class Test5 {
    public static void main(String[] args) {
        Test5 t = new Test5();
        int a = 1000000;
        int b = fibonacci(30);
        System.out.println(t.f(a, b));


    }
    private static int fibonacci(int n) {
        if (n <= 1) { return n; }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    public  int f(int n,int z){
        return n - z;
    }

}
