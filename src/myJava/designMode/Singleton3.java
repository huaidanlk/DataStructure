package myJava.designMode;

//双重检查的锁的模式(Double-checked locking, 简称 DCL)是在懒汉模式的基础上改造而来，
// 将同步方法改成同步代码块，因为大部分的情况下 instance 对象都不是为空的，所以如果不为空，直接返回即可，在高并发的情况下提高系统性能
public class Singleton3 {
    //使用volatile 避免指令重排 指令重排后，在单线程的情况下不会有问题，因为访问对象的成员会在初始化对象后；
    // 但是在多线程的情况下可能会出现对象不为空，但是对象还没有初始化。所以变量加上 volatile 关键字能够禁止指令重排
    private static volatile Singleton3 singleton3;

    //禁止外部实例化
    private Singleton3() {

    }

    //synchronized 锁 Singleton2.class 高并发时效率不高
    public static Singleton3 getInstance() {
        if (singleton3 == null){
            synchronized (Singleton3.class){
                if(singleton3 == null){
                    singleton3 = new Singleton3();
                }
            }
        }
        return singleton3;
    }

}
