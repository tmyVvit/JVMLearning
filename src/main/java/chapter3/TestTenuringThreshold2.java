package chapter3;

/**
 *
 *  VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *  -XX:MaxTenuringThreshold=15
 *  -XX:+PrintTenuringDistribution
 */

public class TestTenuringThreshold2 {
    private static final int _1MB = 1024 * 1024;

    // 如果在Survivor空间中相同年龄所有对象大小的总和大于Survivor空间的一半，年龄 大于等于 该年龄的对象就会直接进入老年代
    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        byte[] alloc1, alloc2, alloc3, alloc4;
        alloc1 = new byte[_1MB/4];
        alloc4 = new byte[_1MB/4];
        // alloc1 + alloc4 大于 survivor 空间一半
        alloc2 = new byte[4 * _1MB];
        alloc3 = new byte[4 * _1MB];
        alloc3 = null;
        alloc3 = new byte[4 * _1MB];
    }

    private static void test2() {
        byte[] alloc1, alloc2, alloc3, alloc4, alloc5;
        alloc1 = new byte[_1MB/8];
        alloc2 = new byte[4 * _1MB];
        alloc3 = new byte[4 * _1MB];
        alloc3 = null;
        alloc3 = new byte[4 * _1MB];

        alloc4 = new byte[_1MB/4];
        alloc5 = new byte[_1MB/4];

        alloc3 = null;
        alloc3 = new byte[4 * _1MB];

        alloc3 = null;
        alloc3 = new byte[4 * _1MB];

    }
}
