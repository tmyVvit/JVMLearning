package chapter3;

/**
 *
 *  VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *  -XX:MaxTenuringThreshold=1
 *  -XX:+PrintTenuringDistribution
 */

public class TestTenuringThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] alloc1, alloc2, alloc3;
        alloc1 = new byte[_1MB/4];
        // alloc1 什么时候进入老年代取决于 -XX:MaxTenuringThreshold 设置
        alloc2 = new byte[4 * _1MB];
        alloc3 = new byte[4 * _1MB];
        alloc3 = null;
        alloc3 = new byte[4 * _1MB];
    }
}
