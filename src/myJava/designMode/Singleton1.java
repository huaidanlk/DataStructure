package myJava.designMode;

//饿汉模式
public class Singleton1 {
    //jvm加载该类时就初始化了，造成空间浪费
    private static Singleton1 singleton1 = new Singleton1();

    //禁止外部实例化
    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return singleton1;
    }

}
