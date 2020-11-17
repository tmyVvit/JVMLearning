package chapter2;

import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;

/**
 *  在JDK 1.6及以前的版本中，常量池分配在永久代中
 *  -XX:PerSize 设置永久代大小
 * VM Args: -XX:PerSize=10m -XX:MaxPermSize=10m
 *  jdk1.8: -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {

    }
}
