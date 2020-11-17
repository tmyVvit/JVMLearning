package chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 *   -Xms 设置堆最小值 -Xmx 设置堆最大值
 * VM Args: -Xms20m -Xmx20m -XX:-HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject {}
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while(true){
            list.add(new OOMObject());
        }
    }
}
