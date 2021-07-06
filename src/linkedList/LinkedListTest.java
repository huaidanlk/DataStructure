package linkedList;

public class LinkedListTest {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for (int i = 1; i < 5; i++) {
            ListNode node = new ListNode(i);
            temp.next = node;
            temp = temp.next;
        }
        printListNode(head);
        System.out.println();
        printListNode(reversalList(head));
        printListNode(reversalList1(head));
    }


    //递归法
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

    public static void printListNode(ListNode head) {
        while (head != null) {
            System.out.print(head.toString());
            head = head.next;
        }
    }
}
