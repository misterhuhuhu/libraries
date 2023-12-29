package bytebuddy;

public class Foo {
    public Foo() {
        System.out.println("Foo 构造");
    }
    
    public String sayHelloFoo() {
        return "Hello in Foo!";
    }
    public static String sayFoo() {
        return "Foo in Foo!";
    }
    public String sayHelloFoo(int num) {
        return "Hello in Foo!  " + num;
    }
}
