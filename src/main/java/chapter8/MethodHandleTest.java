package chapter8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {
    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static MethodHandle getPrintlnMH(Object receiver) throws Throwable {
        // MethodType 代表方法类型，第一个参数是方法返回值，第二个及之后的参数是具体参数类型
        MethodType methodType = MethodType.methodType(void.class, String.class);
        // 在指定的类中查找符合给定的方法名称、方法类型，并符合调用权限的方法句柄
        return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.nanoTime() % 2 == 0 ? System.out : new ClassA();
        getPrintlnMH(obj).invokeExact("invoke");
    }
}
