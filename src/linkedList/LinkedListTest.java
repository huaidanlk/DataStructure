package linkedList;

public class LinkedListTest {

    public static void main(String[] args) {
//        ListNode head = new ListNode(0);
//        ListNode temp = head;
//        for (int i = 1; i < 5; i++) {
//            temp.next = new ListNode(i);
//            temp = temp.next;
//        }
//        printListNode(head);
//        System.out.println();
//        printListNode(reversalList(head));
//        printListNode(reversalList1(head));

        ListNode testCycleHead = new ListNode(3);
        ListNode testCycleHead1 = new ListNode(2);
        ListNode testCycleHead2 = new ListNode(0);
        ListNode testCycleHead3 = new ListNode(-4);
        testCycleHead.next = testCycleHead1;
        testCycleHead1.next = testCycleHead2;
        testCycleHead2.next = testCycleHead3;
        testCycleHead3.next = testCycleHead1;
        System.out.println(detectCycle(testCycleHead).val);
    }


    //递归法 翻转链表
    public static ListNode reversalList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode newHead = reversalList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    //迭代法
    public static ListNode reversalList1(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    //是否有环
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;

    }



    //找到环形链表入环点
    public static ListNode detectCycle(ListNode head) {

//        if (head == null) {
//            return null;
//        }
//        ListNode slow = head, fast = head;
//        while (fast != null) {
//            slow = slow.next;
//            if (fast.next != null) {
//                fast = fast.next.next;
//            } else {
//                return null;
//            }
//            if (fast == slow) {
//                ListNode ptr = head;
//                while (ptr != slow) {
//                    ptr = ptr.next;
//                    slow = slow.next;
//                }
//                return ptr;
//            }
//        }
//        return null;

//            if(head == null || head.next == null)
//                return null;
//            ListNode slow = head;
//            ListNode fast = head.next;
//            while(slow != fast){
//                if(fast == null || fast.next == null)
//                    return null;
//                slow = slow.next;
//                fast = fast.next.next;
//            }
//            ListNode temp = head ;
//            while(slow != temp){
//                slow = slow.next;
//                temp = temp.next;
//            }
//            return slow;

         ListNode fast = head, slow = head;
         while (true) {
             if (fast == null || fast.next == null) return null;
             fast = fast.next.next;
             slow = slow.next;
             if (fast == slow) break;
         }
         fast = head;
         while (slow != fast) {
             slow = slow.next;
             fast = fast.next;
         }
         return fast;
    }

    public static void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.toString());
            head = head.next;
        }
    }

}
