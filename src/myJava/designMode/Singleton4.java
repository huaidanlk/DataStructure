package myJava.designMode;

//静态内部类单例模式
// 这利用了 JVM 类加载机制，实现单例的懒加载。JVM 在加载类的的时候，如果没有用到它里面的静态内部类，是不会加载里面的静态内部类的，自然就不会初始化静态内部类的静态对象
public class Singleton4 {
    private static Singleton4 singleton4 = new Singleton4();

    //禁止外部实例化
    private Singleton4() {

    }

    private static class InstanceHolder{
        private final static Singleton4 instance = new Singleton4();
    }

    public static Singleton4 getInstance() {
        return InstanceHolder.instance;
    }

}
