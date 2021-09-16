package myJava.designMode;

//懒汉模式
public class Singleton2 {
    private static Singleton2 singleton2;

    //禁止外部实例化
    private Singleton2() {

    }

    //synchronized 锁 Singleton2.class 高并发时效率不高
    public static synchronized Singleton2 getInstance() {
        if (singleton2 == null)
            singleton2 = new Singleton2();
        return singleton2;
    }

}
