import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static  List<String> list =new ArrayList<>();

    public static void main(String[] args) {



        test1();
    }

    public  static void test1(){

        list.add("Alex");
        String a = new String(new StringBuffer("1234"));
        try {
            Field field = Test.class.getDeclaredField("list");
            field.setAccessible(true);
            List list1 = (List) field.get(Test.class);
            list1.add(Test.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Object s : list){
            System.out.println(s);
        }

    }
}
