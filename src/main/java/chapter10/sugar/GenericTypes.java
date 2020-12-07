package chapter10.sugar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericTypes {
    public static void genericType() {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "world");
        map.put("world", "hello");
        System.out.println(map.get("hello"));
        System.out.println(map.get("world"));
    }

    public static int boxingAndFor() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
        return sum;
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);         // true  自动装箱 Integer.valueOf() 里面是有一个cache的，-128～127
        System.out.println(e == f);         // false
        System.out.println(c == (a+b));     // true
        System.out.println(c.equals(a+b));  // true   equals 首先会判断类型是否相等，然后判断大小
        System.out.println(g == (a+b));     // true
        System.out.println(g.equals(a+b));  // false  equals 首先会判断类型是否相等，然后判断大小
    }

}
