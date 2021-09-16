package myJava.proxy;

public class Hello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("hello Alex");
    }
}
