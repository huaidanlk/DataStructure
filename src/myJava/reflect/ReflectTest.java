package myJava.reflect;

import java.lang.reflect.*;

public class ReflectTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//        fun();
//        funMethod();

//        getClassField();
//        getClassMethod();
//        getClassConstructor();
//        invokeMethod();
        invokeField();
    }

    public static void getClassField() {
        try {
            Class<?> clazz = Class.forName("myJava.reflect.Student");

            System.out.println("------------自身/父类 public 属性-----------------");

            Field[] fields = clazz.getFields();
            for (int i = 0; i < fields.length; i++) {
                //权限修饰符
                int mo = fields[i].getModifiers();
                String priv = Modifier.toString(mo);
                //属性类型
                Class<?> type = fields[i].getType();
                System.out.println(priv + " " + type.getName() + " " + fields[i].getName() + ";");

            }

            System.out.println("-------------自身 所有属性----------------");

            Field[] fields1 = clazz.getDeclaredFields();
            for (int i = 0; i < fields1.length; i++) {
                //权限修饰符
                int mo = fields1[i].getModifiers();
                String priv = Modifier.toString(mo);
                //属性类型
                Class<?> type = fields1[i].getType();
                System.out.println(priv + " " + type.getName() + " " + fields1[i].getName() + ";");

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getClassMethod() {
        try {
            Class<?> clazz = Class.forName("myJava.reflect.Person");
            System.out.println("------------自身/父类 public 方法-----------------");
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                Class<?> returnType = methods[i].getReturnType();
                Class<?>[] paras = methods[i].getParameterTypes();
                int temp = methods[i].getModifiers();
                System.out.print(Modifier.toString(temp) + " ");
                System.out.print(returnType.getName() + " ");
                System.out.println(methods[i].getName() + " ");

                for (int j = 0; j < paras.length; j++) {
                    System.out.print(paras[j].getName() + " " + "arg" + j);
                    if (j < paras.length - 1) {
                        System.out.print(" , ");
                    }
                }
                System.out.println("");

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void getClassConstructor() {
        try {
            Class<?> clazz = Class.forName("myJava.reflect.Person");
            Person person = (Person) clazz.newInstance();
            person.setAge(11);
            person.setName("Alex");
            System.out.println(person);

            Constructor<?>[] cons = clazz.getConstructors();
            for (int i = 0; i < cons.length; i++) {
                Class<?>[] paramsType = cons[i].getParameterTypes();

                //调用两个参数的构造函数
                if (paramsType.length == 2) {
                    person = (Person) cons[i].newInstance("kkk", 1);
                    System.out.println(person);
                }

                System.out.print("cons[" + i + "] (");
                for (int j = 0; j < paramsType.length; j++) {
                    if (j == paramsType.length - 1)
                        System.out.print(paramsType[j].getName());
                    else
                        System.out.print(paramsType[j].getName() + ",");
                }
                System.out.println(")");
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void invokeMethod() {
        try {
            Class<?> clazz = Class.forName("myJava.reflect.Person");
            Method method = clazz.getMethod("public_prin");
            method.invoke(clazz.newInstance());

            Method method1 = clazz.getMethod("public_show", String.class, int.class);
            method1.invoke(clazz.newInstance(), "Alex", 16);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public static void invokeField() {
        try {
            Class<?> clazz = Class.forName("myJava.reflect.Person");
            Class<?> clazz1 = Person.class;
            Person person = new Person();
            Class<?> clazz2 = person.getClass();

            Object obj = clazz.newInstance();
            Field field = clazz.getDeclaredField("name");
            field.setAccessible(true);
            field.set(obj, "反射调用");
            System.out.println(field.get(obj));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void fun() throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class clazz = InnerContainer.class;
        InnerContainer container = (InnerContainer) clazz.newInstance();
        Class innerClazz[] = clazz.getDeclaredClasses();
        for (Class cls : innerClazz) {
            int mod = cls.getModifiers();
            String modifier = Modifier.toString(mod);
            if (modifier.contains("static")) {
                //构造静态内部类实例
//              Constructor con1 = cls.getDeclaredConstructor();
                Object obj1 = cls.newInstance();
                Field field1 = cls.getDeclaredField("f");
                field1.setAccessible(true);
                System.out.println(field1.get(obj1));
            } else {
                // 构造成员内部类实例
                Constructor con2 = cls.getDeclaredConstructor(clazz);
                con2.setAccessible(true);
                Object obj2 = con2.newInstance(container);
                Field field2 = cls.getDeclaredField("f");
                field2.setAccessible(true);
                System.out.println(field2.get(obj2));
            }
        }
        // 获取匿名内部类实例
        Field field = clazz.getDeclaredField("r");
        field.setAccessible(true);
        Runnable r = (Runnable) field.get(container);
        r.run();
    }

    public static void funMethod() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
//        Class<Person> clazz = Person.class;
        Class<?> clazz = Class.forName("com.example.okhttptest.myJava.java.reflect.Person");
        Object obj = clazz.newInstance();

        //1.共有 无参方法
//        Method  personPublicPrin = clazz.getMethod("public_prin",null);
        Method personPublicPrin = clazz.getMethod("public_prin");
//        personPublicPrin.invoke(obj,null);
        personPublicPrin.invoke(obj);

        //2.共有 有参方法
        Method personPublicShow = clazz.getMethod("public_show", String.class, int.class);
        personPublicShow.invoke(obj, "Alex", 28);

        //3.私有 无参方法
//        Method personPrivatePrin = clazz.getDeclaredMethod("private_prin",null);
        Method personPrivatePrin = clazz.getDeclaredMethod("private_prin");
        personPrivatePrin.setAccessible(true);
//        personPrivatePrin.invoke(obj,null);
        personPrivatePrin.invoke(obj);

        //4.私有 有参方法
        Method personPrivateShow = clazz.getDeclaredMethod("private_show", String.class, int.class);
        personPrivateShow.setAccessible(true);
        personPrivateShow.invoke(obj, "Alex11", 88);


    }


}
