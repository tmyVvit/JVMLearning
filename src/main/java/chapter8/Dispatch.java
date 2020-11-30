package chapter8;

public class Dispatch {
    static class QQ{}
    static class _360 {}

    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("Father choose qq");
        }
        public void hardChoice(_360 arg) {
            System.out.println("Father choose 360");
        }
    }

    public static class Son extends Father {
        public void hardChoice(QQ arg) {
            System.out.println("Son choose qq");
        }
        public void hardChoice(_360 arg) {
            System.out.println("Son choose 360");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }
}
