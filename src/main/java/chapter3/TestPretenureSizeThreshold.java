package chapter3;

/**
 *
 *  VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *
 *  -XX:PretenureSizeThreshold=3145728
 *  这个参数不能想-Xms之类的直接写3MB
 */
public class TestPretenureSizeThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4 * _1MB]; // 将直接分配在老年代中
    }
}
