package heap;

public class HeapTest {
    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap();
//        heap.add(3);
//        heap.add(72);
//        heap.add(40);
//        heap.add(14);
//        heap.add(43);
//        heap.add(50);
//        heap.add(47);
//        heap.add(68);
//        heap.add(38);
//        heap.add(21);

//        heap.add(68);
//        heap.add(72);
//        heap.add(43);
//        heap.add(50);
//        heap.add(38);
//        System.out.println(heap.remove());



        int[] data = new int[]{3,72,40,14,43,50,47,68,38,21};
        int k = 5;
        //top k 问题   先建立 size=k 的小顶堆
        for (int i = 0; i < data.length; i++) {
            if (heap.size<k){
                heap.add(data[i]);
            }else {
                if(data[i]> heap.get())
                     heap.replace(data[i]);
            }
        }

        heap.printHeap();

    }


}
