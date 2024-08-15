//  12. Write a linear time iterative algorithm to reverse a linked list.

public ListNode reverseList(ListNode head) {
        ListNode prev = head;
        ListNode curr = head;
        if(curr == null || curr.next == null) return head;
        curr = curr.next;

        while(curr != null){
            ListNode nxt = curr.next;
            curr.next = prev;
            if(head == prev) prev.next = null;
            prev = curr;
            curr = nxt;
        }
        return prev;

}
