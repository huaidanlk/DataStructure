package leetCode;

public class MergeSort {
    public static int[] arrays = new int[]{1, 5, 23, 3, 6, 2, 34, 63425, 24, 62, 568};
    public static int[] leftArray = new int[arrays.length >> 1];

    public static void main(String[] args) {


        sort(0, arrays.length);

        moveChar();
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + "_");
        }
    }

    public static void sort(int begin, int end) {
        if (end - begin < 2) return;
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    public static void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;

        int ri = mid, re = end;
        int ai = begin;

        //复制左边的数组
        for (int i = li; i < le; i++) {
            leftArray[i] = arrays[begin + i];
        }

        // 左边还未结束
        while (li < le) {
            // ri >=re  右边已经结束了， 把剩下的 leftArray 复制到 arrays
            if (ri >= re || leftArray[li] < arrays[ri]) { //
                arrays[ai] = leftArray[li];
                li++;
                ai++;
            } else {
                arrays[ai] = arrays[ri];
                ri++;
                ai++;
            }
        }

    }

    public static void moveChar() {
        String sa = "*asd***a**d*s";
        char s[] = sa.toCharArray();
        int index = sa.lastIndexOf('*');
        for (int i = index - 1; i >= 0; i--) {
            if (s[i] != '*') {
                s[index--] = s[i];
                s[i] = '*';
            }
        }
        System.out.println(s);
    }
}
