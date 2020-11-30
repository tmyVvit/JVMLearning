package chapter8;

public class StaticResolution {
    public static void sayHello() {
        System.out.println("hello world");
    }

    public final void sayBye() {
        System.out.println("bye bye");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
        new StaticResolution().sayBye();
    }
}
