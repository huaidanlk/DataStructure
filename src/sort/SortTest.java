package sort;

import java.util.LinkedList;
import java.util.Queue;

public class SortTest {
    public static void main(String[] args) {
        int[] array = new int[]{3, 1, 6, 3, 8, 6, 4, 89, 2, 78, 12, 356, 23, 45};
//        SelectionSort(array);
//        HeapSort(array);
//        insertionSort1(array);
//        mergeSort(array);
//        quickSort(array);
//        printArray(array);

        System.out.println();
//        System.out.println(indexOfBinarySearch(array, 356));
//        System.out.println(indexOfBinarySearch(array, 89));
//        System.out.println(indexOfBinarySearch(array, 1));
//        search(new int[]{4,5,6,7,0,1,2},0);
//        System.out.println(findMin(new int[]{3,4,5,1,2}));
        isPalindrome("A man, a plan, a canal: Panama");
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

    //归并排序
    public static void mergeSort(int[] array) {
        int[] leftArray = new int[array.length >> 1];
        sort(0, array.length, array, leftArray);
    }

    // [begin,end)
    public static void sort(int begin, int end, int[] array, int[] leftArray) {
        //至少两个元素
        if (end - begin < 2)
            return;
        int mid = (end + begin) >> 1;
        //对begin - end 范围的数据进行切割
        sort(begin, mid, array, leftArray);
        sort(mid, end, array, leftArray);
        //对begin - end 合并
        merge(begin, mid, end, array, leftArray);
    }

    public static void merge(int begin, int mid, int end, int[] array, int[] leftArray) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;

        //复制左边的数组到 leftArray
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }
        while (li < le) {   //li = le 代表左边数组已经挪完 此时已经有序
            if (ri < re && leftArray[li] > array[ri]) {    //ri = re 代表右边数组已经挪完 此时只需要挪动左边数组
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }

    }

    //快速排序
    public static void quickSort(int[] array) {
        sort(0, array.length, array);
    }

    // [begin,end)
    public static void sort(int begin, int end, int[] array) {
        if (end - begin < 2)
            return;
        int mid = pivotIndex(begin, end, array);
        sort(begin, mid, array);
        sort(mid + 1, end, array);
    }

    //构造 [begin,end) 范围的轴点元素
    public static int pivotIndex(int begin, int end, int[] array) {
        int pivot = array[begin];
        end--;
        while (begin < end) {
            while (begin < end) {
                if (pivot < array[end]) {  // 右边元素 > 轴点元素
                    end--;
                } else {  // 右边元素 <= 轴点元素
                    array[begin] = array[end];
                    begin++;
                    break;
                }
            }
            while (begin < end) {
                if (pivot > array[begin]) {
                    begin++;
                } else {
                    array[end] = array[begin];
                    end--;
                    break;
                }
            }
        }
        array[begin] = pivot;
        return begin;
    }

    // 旋转升序数组最小值
    public static int findMin(int[] nums) {
        // int min = nums[0];
        // for(int i = 1;i<nums.length;i++){
        //     if(nums[i]<min){
        //         min = nums[i];
        //         break;
        //     }
        // }
        // return min;

        int begin = 0;
        int end = nums.length-1;
        while(begin<end){
            int mid = begin + ((end - begin)>>1);
            if(nums[mid] > nums[end]){ //在右边找
                begin =mid +1;
            }else{
                end =mid;
            }
        }
        return nums[begin];

    }

    public static int  search(int[] nums, int target) {
        return  binarySearch(0,nums.length,nums,target);

    }
    public static int binarySearch(int begin ,int end ,int [] nums,int target){

        int mid1 = (begin+end)>>1;
        if(nums[mid1] == target)
            return mid1;
        //左边有序
        if(nums[begin] < nums[mid1]){
            int left = begin;
            int right = mid1;
            while(left<right){
                int mid = (left+right)>>1;
                if(nums[mid] == target)
                    return mid ;
                if(nums[mid] < target){
                    left = mid+1;
                }else if(target < nums[mid]  ) {
                    right = mid;
                }
            }
            return  binarySearch(mid1,end,nums,target);
        }else if(nums[mid1] < nums[begin]){ //右边有序
            int left = mid1;
            int right = end;
            while(left<right){
                int mid = (left+right)>>1;
                if(nums[mid] ==  target)
                    return mid ;
                if(nums[mid] < target){
                    left = mid+1;
                }else if(target < nums[mid]  ) {
                    right = mid;
                }
            }
            return binarySearch(begin,mid1,nums,target);
        }
        return -1;

    }

    //回文数
    public boolean isPalindrome(int x) {
        if (x < 0 || (x%10== 0 && x !=0))
            return false;
        int z =0;
        while (x>z){
            z = z*10 + x%10;
            x = x/10;
        }

        return  x == z || x == z/10;

    }

    //回文字符串  "A man, a plan, a canal: Panama"
    public static boolean isPalindrome(String s) {
        String newString = "";
        for(int i =0;i<s.length();i++){
            char temp = s.charAt(i);
            if((temp >= 'a' && temp<='z') ||(temp >= 'A' && temp<='Z') )
                newString +=temp;
        }
        newString = newString.toLowerCase();
        int n = newString.length();
        int left =0;
        int right = n-1;
        while(left<right){
            if(newString.charAt(left) != newString.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
}
