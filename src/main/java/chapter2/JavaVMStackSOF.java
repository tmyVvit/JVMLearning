package chapter2;

/**
 * -Xss 设置栈容量
 * VM Args: -Xss256k
 */
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable{
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable t) {
            System.out.println("Stack length: " + oom.stackLength);
            throw t;
        }
    }
}
