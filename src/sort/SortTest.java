package sort;

public class SortTest {
    public static void main(String[] args) {
        int[] array = new int[]{3, 1, 6, 3, 8, 6, 4, 89, 2, 78, 12, 356, 23, 45};
//        SelectionSort(array);
//        HeapSort(array);
        insertionSort1(array);
        printArray(array);

        System.out.println();
        System.out.println(indexOfBinarySearch(array, 356));
        System.out.println(indexOfBinarySearch(array, 89));
        System.out.println(indexOfBinarySearch(array, 1));

    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + "_");
    }

    public static int cmp(int a, int b) {
        return a - b;
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public static int[] SelectionSort(int[] array) {
        for (int end = array.length - 1; end > 0; end--) {
            int max = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(array[max], array[begin]) < 0) {
                    max = begin;
                }
            }
            int temp = array[max];
            array[max] = array[end];
            array[end] = temp;

        }

        return array;
    }

    public static int[] BubbleSort(int[] array) {
        for (int end = array.length; end >= 1; end--) {
            for (int begin = 1; begin < end; begin++) {
                if (array[begin - 1] > array[begin]) {
                    int temp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = temp;
                }
            }
        }

        return array;

    }


    //优化方案 index 记录最后交换的位置，此时，index 后面的数据已经排好序了
    public static int[] BubbleSort1(int[] array) {
        for (int end = array.length; end >= 1; end--) {
            //index 初始值在完全升序的时候有用
            int index = 0;
            for (int begin = 1; begin < end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int temp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = temp;
                    index = begin;
                }
            }
            end = index;
        }
        return array;
    }

    public static int heapSize;

    //堆排序
    public static int[] HeapSort(int[] array) {
        heapSize = array.length;

        //原地建堆 heapify
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i, array);
        }

        while (heapSize > 1) {
            //交换对顶元素与尾部元素
            heapSize--;
            int temp = array[heapSize];
            array[heapSize] = array[0];
            array[0] = temp;

            //对0位置siftDown
            siftDown(0, array);
        }


        return array;
    }

    public static void siftDown(int index, int[] array) {

        int e = array[index];

        while (index < heapSize >> 1) {
            //默认用左子节点
            int childIndex = (index << 1) + 1;

            int rightIndex = childIndex + 1;
            if (rightIndex < heapSize) {
                childIndex = array[childIndex] > array[rightIndex] ? childIndex : rightIndex;
            }

            if (array[childIndex] > e) {
                array[index] = array[childIndex];
                index = childIndex;
            } else
                break;
        }
        array[index] = e;

    }

    //插入排序 n^2
    public static void insertionSort(int[] array) {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            int e = array[begin];
            while (cur > 0 && cmp(array[cur - 1], e) > 0) {
                // swap 替换为挪动减少交换次数
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = e;
        }
    }

    //插入排序  （二分搜索来优化，减少比较次数 logn） 由于挪动还是 n 最终 n^2
    public static void insertionSort1(int[] array) {
        for (int begin = 1; begin < array.length; begin++) {
            int insertIndex = indexOf(array, begin);

            int cur = begin;
            int e = array[begin];
            for (int tail = begin; tail > insertIndex; tail--)
                array[tail] = array[tail - 1];
            array[insertIndex] = e;
        }
    }

    // 利用二分查找 找到index位置元素待插入的位置
    // 已经排好序的数组区间是 [0，index)
    public static int indexOf(int[] array, int index) {
        int begin = 0;

        //通过 index 确定 end
        int end = index;

        int e = array[index];
        // begin == end 即是最终的插入位置
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (array[mid] < e) {
                begin = mid + 1;
            } else { // array[mid] >= e
                end = mid;
            }
        }
        return begin;
    }


    //二分搜索  前提有序 logn  [begin,end)
    public static int indexOfBinarySearch(int[] array, int e) {
        if (array == null || array.length == 0)
            return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (array[mid] > e) {
                end = mid;
            } else if (array[mid] < e) {
                begin = mid + 1;
            } else
                return mid;
        }
        return -1;
    }


    public static void mergeSort(){

    }
}
