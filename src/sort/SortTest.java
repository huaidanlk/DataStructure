package sort;

public class SortTest {
    public static void main(String[] args) {
        int[] array = new int[]{3, 1, 6, 3, 8, 6, 4, 89, 2, 78, 12, 356, 23, 45};
        SelectionSort(array);
        printArray(array);
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
}
