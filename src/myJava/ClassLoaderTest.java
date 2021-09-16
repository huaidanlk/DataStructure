package myJava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {

    //https://www.cnblogs.com/renyuanwei/p/9394247.html private public 无参 有参 的调用

    public static void main(String[] args) {
//        DiskClassLoader diskClassLoader = new DiskClassLoader("/Users/lk/myJava");//1   见路径下的Jobs1 文件
        DiskClassLoader diskClassLoader = new DiskClassLoader("/Users/lk/GithubProjects/OkHttpTest/app/src/main/java");

        try {
//            Class c = diskClassLoader.loadClass("com.example.Jobs1");//2
            Class c = diskClassLoader.loadClass("com.example.okhttptest.myJava.Jobs1");//2

            if (c != null) {
                try {
                    Jobs1 obj = (Jobs1)c.newInstance();
                    System.out.println(c.getClassLoader());
                    Method method = c.getMethod("say", null);
                    method.invoke(obj, null);//3
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException
                        | SecurityException |
                        IllegalArgumentException |
                        InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}