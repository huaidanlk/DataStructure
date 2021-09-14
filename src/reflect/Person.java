package reflect;

public class Person {
    public int age;
    private String name;

    static { //静态代码块，只执行一次
        System.out.println("nihao_static");
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public Person() {
        System.out.println("nihao_constructor");
    }


    //公有 有参方法
    public void public_show(String str, int i) {
        System.out.println("public show " + str + "..." + i);
    }

    //公有 无参方法
    public void public_prin() {
        System.out.println("public prin");
    }


    //私有 有参方法
    private void private_show(String str, int i) {
        System.out.println("private show " + str + "..." + i);
    }

    //私有 无参方法
    private void private_prin() {
        System.out.println("private prin");
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
