package heap;

//二叉堆逻辑结构是完全二叉树

/**
 * 完全二叉树用数组实现，索引有特殊关系
 * 0
 * 1      2
 * 3  4
 * <p>
 * 索引 i 的左子节点  left = 2i+1; 右节点 right = 2i+2  父节点floor((i-1)/2)
 * 第一个叶子节点  floor (n/2)
 * <p>
 * 如果2i+1<= n-1
 */

public class BinaryHeap {
    private int[] elements = new int[10];
    public int size = 0;

    public BinaryHeap() {
    }

    public void add(int element) {
        elements[size++] = element;
        siftUp(size - 1);
    }

    // 删除堆顶元素
    public int remove() {
        if (size == 0)
            return 0;
        int root = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = 0;
        siftDown(0);
        return root;
    }

    //删除堆顶元素的同时添加  返回被删除的元素
    public int replace(int element){
        if (size == 0){
            elements[0] =element;
            size =1;
        }else {
            elements[0] = element;
            siftDown(0);
        }
        return elements[0];
    }

    public int get(){
        if(size == 0)
            return 0;
        return  elements[0];
    }
    /**
     * 上滤
     *
     * @param index index 要有父节点 ，如果有，和父节点比较  大于父节点就交换
     *              O(logn)
     */
    private void siftUp(int index) {
        int e = elements[index];
//        while (index > 0) {
//            int pIndex = (index - 1) >> 1;
//            int p = elements[pIndex];
//            if (cmp(e, p) > 0) {
//                int temp = elements[index];
//                elements[index] = elements[pIndex];
//                elements[pIndex] = temp;
//                index = pIndex;
//            } else
//                return;
//        }

        //优化   记下index 最后覆盖
        while (index > 0) {
            int pIndex = (index - 1) >> 1;
            int p = elements[pIndex];
            if (cmp(e, p) > 0) {
                elements[index] = p;
                index = pIndex;
            } else
                break;
        }
        elements[index] = e;

    }

    /**
     * 下滤
     *
     * @param index 选出 index 最大的子节点交换
     *              O(logn)
     */
    private void siftDown(int index) {
        //要下滤的元素
        int e = elements[index];

        //index 必须要有子节点   index < 第一个叶子节点的索引 ==  非叶子节点的数量 == (n/2)
        while (index < size >> 1) {
            //默认为左子节点的索引
            int childIndex = (index << 1) + 1;
            int child = elements[childIndex];

            //右子节点
            int rightIndex = childIndex + 1;

            if (rightIndex < size && cmp(elements[rightIndex], child) > 0) {
                childIndex = rightIndex;
                child = elements[childIndex];
            }

            if (cmp(e, child) >= 0)
                break;
            elements[index] = child;
            index = childIndex;

        }

        elements[index] = e;
    }

    //最大堆  自上而下的上滤
    private void heapify0() {
        for (int i = 1; i < size; i++) {
            siftUp(i);
        }
    }

    //最大堆  自下而上的下滤
    private void heapify1() {
        for (int i = (size >> 1)-1; i >=0; i--) {
            siftDown(i);
        }
    }

    private int cmp(int a, int b) {
//        return a - b;  //大顶堆
        return b-a;   //小顶堆
    }


    public void printHeap() {
        for (int i = 0; i < elements.length; i++) {
            System.out.print(elements[i] + "_");
        }
    }

}
